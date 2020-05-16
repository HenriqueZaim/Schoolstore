package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.pedido.ItemPedido;
import br.com.fatec.les.model.pedido.Pedido;
import br.com.fatec.les.model.produto.Produto;

public class ItemPedidoDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	ProdutoDao produtoDao = new ProdutoDao();

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mensagem deletar(ADominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
