package br.com.fatec.les.DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.produto.Produto;
import br.com.fatec.les.model.usuario.Carrinho;

public class ProdutoDao implements IDao{
	
    private Connection conexao = null;
    private Mensagem mensagem;

    public ProdutoDao() {
        conexao = ConexaoFactory.getConnection();
    }

	@Override
	public Mensagem salvar(EntidadeDominio entidadeDominio) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mensagem deletar(EntidadeDominio entidadeDominio) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mensagem atualizar(EntidadeDominio entidadeDominio) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException {
		Produto produto = (Produto) entidade;
		conexao = ConexaoFactory.getConnection();
		List<EntidadeDominio> produtos = new ArrayList<EntidadeDominio>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "pro_id, "
				+ "pro_nome, "
				+ "pro_preco, "
				+ "pro_descricao "
				+ " FROM tb_produto "
				+ " WHERE pro_ativo = 1 ";
		if(produto.getId() != null) {
			sql += " AND pro_id = " + produto.getId() + "";
		}
		
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Produto p = new Produto();

			while(rs.next()) {
				p = new Produto();
				p.setNome(rs.getString("pro_nome"));
				p.setDescricao(rs.getString("pro_descricao"));
				p.setPreco(rs.getFloat("pro_preco"));
				p.setId(rs.getLong("pro_id"));
				
				// TODO: Falta mais coisa

				produtos.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return produtos;
	}

}
