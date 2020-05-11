package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.estoque.Estoque;
import br.com.fatec.les.model.estoque.Fornecedor;
import br.com.fatec.les.model.estoque.ItemEstoque;
import br.com.fatec.les.model.produto.Produto;

public class ItemEstoqueDao implements IDao{
	 
	private Connection conexao = null;
	FornecedorDao fornecedorDao = new FornecedorDao();

	@Override
	public Mensagem salvar(EntidadeDominio entidadeDominio) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public Mensagem deletar(EntidadeDominio entidadeDominio) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public Mensagem atualizar(EntidadeDominio entidadeDominio) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}
	
	public List<EntidadeDominio> consultarItem(Long idSto) throws SQLException {
		conexao = ConexaoFactory.getConnection();
		List<EntidadeDominio> itens = new ArrayList<EntidadeDominio>();
		List<ItemEstoque> itensEstoque = new ArrayList<ItemEstoque>();


		ItemEstoque itemEstoque = new ItemEstoque();
		Fornecedor fornecedor = new Fornecedor();
		Produto produto = new Produto();
		Estoque estoque = new Estoque();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "isto_quantidade, "
				+ "isto_dataEntrada,"
				+ "isto_valor,"
				+ "isto_for_id "
				+ " FROM tb_itemEstoque "
				+ " WHERE isto_sto_id = " + idSto + "";

		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			while(rs.next()) {
				itemEstoque = new ItemEstoque();
				fornecedor = new Fornecedor();
				produto = new Produto();
				estoque = new Estoque();
				
				itemEstoque.setQuantidade(rs.getInt("isto_quantidade"));
				itemEstoque.setValor(rs.getFloat("isto_valor"));
				
				fornecedor.setId(rs.getLong("isto_for_id"));
				itemEstoque.setFornecedor((Fornecedor)fornecedorDao.consultar(fornecedor).get(0));
				
				itensEstoque.add(itemEstoque);
			}
			
			estoque.setItensEstoque(itensEstoque);
			produto.setEstoque(estoque);
			
			itens.add(produto);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return itens;
	}

}
