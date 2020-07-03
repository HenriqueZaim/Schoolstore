package br.com.fatec.les.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.pagamento.cartao.CartaoCredito;
import br.com.fatec.les.model.usuario.Cliente;

public class CartaoCreditoDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	
	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_cartaoCredito "
				+ "("
				+ "ccr_numero, "
				+ "ccr_nomeImpresso, "
				+ "ccr_favorito, "
				+ "ccr_cli_id, "
				+ "ccr_codigo, "
				+ "ccr_ativo, "
				+ "ccr_dataHoraCriacao "
				+ ") "
				+ " VALUES ( ?, ?, ?, ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, cartaoCredito.getNumero());
			pstm.setString(2, cartaoCredito.getNomeImpresso());
			pstm.setBoolean(3, cartaoCredito.isFavorito());
			pstm.setLong(4, cartaoCredito.getCliente().getId());
			pstm.setString(5, cartaoCredito.getCodigo());
			pstm.executeUpdate();

			mensagem.setMensagem("Operação realizada com sucesso!");
			mensagem.setMensagemStatus(MensagemStatus.OPERACAO);
			return mensagem;
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
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "UPDATE tb_cartaoCredito SET "
				+ "ccr_ativo = false"
				+ " WHERE ";
		if(cartaoCredito.getId() != null ) {
			sql += "ccr_id = " + cartaoCredito.getId() + "";
		}else {
			sql += "ccr_cli_id = " + cartaoCredito.getCliente().getId() + "";
		}
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.executeUpdate();
			mensagem.setMensagem("Cartão de crédito deletado com sucesso!");
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
	public Mensagem atualizar(ADominio  entidade) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public List<ADominio> consultar(ADominio  entidade) throws SQLException {
		CartaoCredito cartaoEntidade = (CartaoCredito) entidade;
		CartaoCredito cartaoCredito = new CartaoCredito();
		Cliente cliente = new Cliente();
		conexao = ConexaoFactory.getConnection();
		
		List<ADominio> cartoes = new ArrayList<ADominio>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT "
				+ "ccr_id, "
				+ "ccr_numero, "
				+ "ccr_nomeImpresso, "
				+ "ccr_favorito, "
				+ "ccr_codigo, "
				+ "ccr_cli_id "
				+ " FROM tb_cartaoCredito WHERE ccr_ativo = 1 ";
		if(cartaoEntidade.getId() != null) {
			sql += "AND ccr_id = " + cartaoEntidade.getId();
		}
		if(cartaoEntidade.getCliente().getId() != null) {
			sql += "AND ccr_cli_id = " + cartaoEntidade.getCliente().getId() ;
		}
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				cartaoCredito = new CartaoCredito();
				cliente = new Cliente();

				cartaoCredito.setId(Long.parseLong(rs.getString("ccr_id")));
				cartaoCredito.setCodigo(rs.getString("ccr_codigo"));
				cartaoCredito.setNomeImpresso(rs.getString("ccr_nomeImpresso"));
				cartaoCredito.setNumero(rs.getString("ccr_numero"));
				cartaoCredito.setFavorito(rs.getString("ccr_favorito").equals("1") ? true : false);
				cliente.setId(Long.parseLong(rs.getString("ccr_cli_id")));
				cartaoCredito.setCliente(cliente);
				
				cartoes.add(cartaoCredito);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return cartoes;
	}

}
