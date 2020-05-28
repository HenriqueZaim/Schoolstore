package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.estoque.Estoque;
import br.com.fatec.les.model.pedido.ItemPedido;
import br.com.fatec.les.model.pedido.Pedido;
import br.com.fatec.les.model.produto.Produto;

public class ItemPedidoDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	ProdutoDao produtoDao = new ProdutoDao();
	EstoqueDao estoqueDao = new EstoqueDao();

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		ItemPedido itemPedido = (ItemPedido) entidade;
		
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_itemPedido "
				+ "("
				+ "iped_quantidade, "
				+ "iped_pro_id, "
				+ "iped_ped_id "
				+ ") "
				+ " VALUES ( ?, ?, ? )";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setInt(1, itemPedido.getQuantidade());
			pstm.setLong(2, itemPedido.getProduto().getId());
			pstm.setLong(3, itemPedido.getPedido().getId());
			
			pstm.executeUpdate();

			mensagem.setMensagem("Operação bem sucedida!");
			mensagem.setMensagemStatus(MensagemStatus.OPERACAO);
		
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
		ItemPedido itemPedido = (ItemPedido) entidade;
		Estoque estoque = new Estoque();
		Produto produto = new Produto();
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "DELETE FROM tb_itemPedido "
					+ " WHERE iped_id = ?";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setLong(1, itemPedido.getId());
			
			produto.setId(itemPedido.getProduto().getId());
			estoque.setProduto(produto);
			estoque.setQuantidadeTotal(itemPedido.getQuantidade());
			estoqueDao.atualizar(estoque);
			
			pstm.executeUpdate();
			mensagem.setMensagem("Item do pedido excluído com sucesso!");
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
	public Mensagem atualizar(ADominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ADominio> consultar(ADominio entidade) throws SQLException {
		ItemPedido itemPedido = (ItemPedido) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		List<ADominio> itensPedido = new ArrayList<ADominio>();
		
		ResultSet rs = null;
		PreparedStatement pstm = null;

		String sql = "SELECT "
				+ "iped_id,"
				+ "iped_quantidade,"
				+ "iped_pro_id,"
				+ "iped_ped_id "
				+ " FROM tb_itemPedido "
				+ " WHERE iped_ped_id = " + itemPedido.getPedido().getId() + "";

		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			ItemPedido ip = new ItemPedido();
			Produto pr = new Produto();
			Pedido pe = new Pedido(); 

			while(rs.next()) {
				ip = new ItemPedido();
				pr = new Produto();
				pe = new Pedido(); 
				
				ip.setId(rs.getLong("iped_id"));
				ip.setQuantidade(rs.getInt("iped_quantidade"));
				
				pe.setId(rs.getLong("iped_ped_id"));
				ip.setPedido(pe);
				
				pr.setId(rs.getLong("iped_pro_id"));
				ip.setProduto((Produto)produtoDao.consultar(pr).get(0));
				
				itensPedido.add(ip);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		return itensPedido;
	}

}
