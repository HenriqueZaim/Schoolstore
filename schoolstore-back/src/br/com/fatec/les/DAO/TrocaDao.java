package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.pagamento.cupom.Cupom;
import br.com.fatec.les.model.pedido.Pedido;
import br.com.fatec.les.model.troca.ItemTroca;
import br.com.fatec.les.model.troca.StatusTroca;
import br.com.fatec.les.model.troca.Troca;
import br.com.fatec.les.model.usuario.Cliente;

public class TrocaDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	ItemTrocaDao itemTrocaDao = new ItemTrocaDao();
	ClienteDao clienteDao = new ClienteDao();
	PedidoDao pedidoDao = new PedidoDao();
	CupomDao cupomDao = new CupomDao();

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		Troca troca = (Troca) entidade;
		Cupom cupom = new Cupom();
		conexao = ConexaoFactory.getConnection();
		ResultSet rs;
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_troca "
				+ "("
				+ "tro_cli_id, "
				+ "tro_ped_id,"
				+ "tro_statusTroca, "
				+ "tro_ativo, "
				+ "tro_dataHoraCriacao "
				+ ") "
				+ " VALUES ( ?, ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			cupom.setValor(troca.getPedido().getValor());
			cupom.setUsuario(troca.getCliente().getUsuario());
			cupomDao.salvar(cupom);
			
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setLong(1, troca.getCliente().getId());
			pstm.setLong(2, troca.getPedido().getId());
			pstm.setString(3, troca.getStatusTroca().toString());
			
			pstm.executeUpdate();
						
			rs = pstm.getGeneratedKeys();
			if (rs.next()){
				String id = Integer.toString(rs.getInt(1));
				Troca trocaAux = new Troca();
				for(ItemTroca item : troca.getItensTroca()) {
					trocaAux.setId(Long.parseLong(id));
					item.setTroca(trocaAux);
					itemTrocaDao.salvar(item);
				}
				
				mensagem.setMensagem("Solicitação para troca realizada com sucesso! Acompanhe o status da troca na página 'Minhas Solicitações'");
				mensagem.setMensagemStatus(MensagemStatus.OPERACAO);
			}				
		}catch(SQLException e){
			mensagem.setMensagem("Ocorreu um erro durante a operação. Tente novamente ou consulte a equipe de desenvolvimento.");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm);
		}
		return mensagem;
	}

	@Override
	public Mensagem deletar(ADominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mensagem atualizar(ADominio entidade) throws SQLException {
		Troca troca  = (Troca) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "UPDATE tb_troca SET "
					+ "tro_statusTroca = ? "			
					+ " WHERE tro_id = ?";
		
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, troca.getStatusTroca().toString());
			pstm.setLong(2, troca.getId());

			pstm.executeUpdate();
			mensagem.setMensagem("Troca atualizada com sucesso!");
			mensagem.setMensagemStatus(MensagemStatus.SUCESSO);
		}catch(SQLException e) {
			mensagem.setMensagem("Ocorreu um erro durante a operação. Tente novamente ou consulte a equipe de desenvolvimento.");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm);
		}
		
		return mensagem;
	}

	@Override
	public List<ADominio> consultar(ADominio entidade) throws SQLException {
		Troca troca = (Troca) entidade;
		conexao = ConexaoFactory.getConnection();
		
		List<ADominio> trocas = new ArrayList<ADominio>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "tro_id, "
				+ "tro_statusTroca, "
				+ "tro_ped_id, "
				+ "tro_cli_id "
				+ " FROM tb_troca "
				+ " WHERE tro_ativo = 1 ";
		if(troca.getCliente() != null && troca.getCliente().getId() != null) {
			sql += " AND tro_cli_id = " + troca.getCliente().getId() + "";
		}
		
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Troca t = new Troca();
			Pedido p = new Pedido();
			Cliente c = new Cliente();
			ItemTroca itemTroca = new ItemTroca();
			
			List<ADominio> itensTrocaEntidade = new ArrayList<ADominio>();
			List<ItemTroca> itensTroca = new ArrayList<ItemTroca>();

			while(rs.next()) {
				t = new Troca();
				p = new Pedido();
				c = new Cliente();
				
				itemTroca = new ItemTroca();
				itensTrocaEntidade = new ArrayList<ADominio>();
				itensTroca = new ArrayList<ItemTroca>();
				
				p.setId(rs.getLong("tro_ped_id"));
				c.setId(rs.getLong("tro_cli_id"));
				
				t.setPedido((Pedido)pedidoDao.consultar(p).get(0));
				t.setCliente((Cliente)clienteDao.consultar(c).get(0));
				
				t.setId(rs.getLong("tro_id"));
				t.setStatusTroca(StatusTroca.valueOf(rs.getString("tro_statusTroca")));
				
				itemTroca.setTroca(t);
				itensTrocaEntidade.addAll(itemTrocaDao.consultar(itemTroca));
				if(!itensTrocaEntidade.isEmpty()) {
					for(ADominio item : itensTrocaEntidade) {
						itensTroca.add((ItemTroca)item);
					}
				}
				t.setItensTroca(itensTroca);

				trocas.add(t);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return trocas;
	}

}
