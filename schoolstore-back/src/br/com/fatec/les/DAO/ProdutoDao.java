package br.com.fatec.les.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.assets.Imagem;
import br.com.fatec.les.model.produto.Categoria;
import br.com.fatec.les.model.produto.Precificacao;
import br.com.fatec.les.model.produto.Produto;

public class ProdutoDao implements IDao{
	
    private Connection conexao = null;
    ImagemDao imagemDao = new ImagemDao();
    PrecificacaoDao precificacaoDao = new PrecificacaoDao();
    CategoriaDao categoriaDao = new CategoriaDao();
    EstoqueDao estoqueDao = new EstoqueDao();

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public Mensagem deletar(ADominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public Mensagem atualizar(ADominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public List<ADominio> consultar(ADominio entidade) throws SQLException {
		Produto produto = (Produto) entidade;
		conexao = ConexaoFactory.getConnection();
		List<ADominio> produtos = new ArrayList<ADominio>();
		List<ADominio> categoriasEntidade = new ArrayList<ADominio>();
		List<Categoria> categorias = new ArrayList<Categoria>();

		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "pro_id, "
				+ "pro_nome, "
				+ "pro_preco, "
				+ "pro_descricao,"
				+ "pro_ima_id, "
				+ "pro_pre_id "
				+ " FROM tb_produto "
				+ " WHERE pro_ativo = 1 ";
		if(produto.getId() != null) {
			sql += " AND pro_id = " + produto.getId() + "";
		}
		
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Produto p = new Produto();
			Produto aux = new Produto();
			Imagem i = new Imagem();
			Precificacao pr = new Precificacao();

			while(rs.next()) {
				p = new Produto();
				aux = new Produto();
				i = new Imagem();
				pr = new Precificacao();
				categoriasEntidade = new ArrayList<ADominio>();
				categorias = new ArrayList<Categoria>();
				
				p.setNome(rs.getString("pro_nome"));
				p.setDescricao(rs.getString("pro_descricao"));
				p.setPreco(rs.getFloat("pro_preco"));
				p.setId(rs.getLong("pro_id"));
				
				i.setId(rs.getLong("pro_ima_id"));
				p.setImagem((Imagem)imagemDao.consultar(i).get(0));
				
				pr.setId(rs.getLong("pro_pre_id"));
				p.setPrecificacao((Precificacao)precificacaoDao.consultar(pr).get(0));
				
				categoriasEntidade.addAll(categoriaDao.consultar(p));
				for(ADominio cat : categoriasEntidade) {
					categorias.add((Categoria)cat);
				}
				p.setCategoria(categorias);
				
				aux = (Produto) estoqueDao.consultar(p).get(0);
				p.setEstoque(aux.getEstoque());

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
