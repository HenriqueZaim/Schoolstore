package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.Endereco;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Usuario;

public class ClienteDao implements IDao{
	
	private Connection conexao = null;
	private String mensagem = null;
	EnderecoDao enderecoDao = new EnderecoDao();
	UsuarioDao usuarioDao = new UsuarioDao();
	
	public ClienteDao() {
		conexao = ConexaoFactory.getConnection();
	}

	@Override
	public String salvar(EntidadeDominio entidadeDominio) throws SQLException {
		Cliente cliente = (Cliente) entidadeDominio;
		ResultSet rs;
		
		String sql = "INSERT INTO tb_cliente "
				+ "("
				+ "cli_nome, "
				+ "cli_numeroTelefone, "
				+ "cli_numeroDocumento, "
				+ "cli_usu_id, "
				+ "cli_ativo, "
				+ "cli_dataHoraCriacao "
				+ ")"
				+ " VALUES ( ?, ?, ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			String idUsuario = usuarioDao.salvar(cliente.getUsuario());
			
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getNumeroTelefone());
			pstm.setString(3, cliente.getNumeroDocumento());
			pstm.setInt(4, Integer.parseInt(idUsuario));
			pstm.executeUpdate();
			
			rs = pstm.getGeneratedKeys();
			if (rs.next()){
				String idCliente = Integer.toString(rs.getInt(1));
				for(Endereco e : cliente.getEnderecos()) {
					cliente = new Cliente();
					cliente.setId(Long.parseLong(idCliente));
					e.setCliente(cliente);
					enderecoDao.salvar(e);
				}
			}
			
			mensagem = "Cliente cadastrado com sucesso!";
		}catch(SQLException e){
			mensagem = e.toString();
			mensagem = "Erro: " + mensagem;
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm);
//		}
		return mensagem;
	}

	@Override
	public String deletar(EntidadeDominio entidadeDominio) throws SQLException {
		Cliente cliente  = (Cliente) entidadeDominio;
		Endereco endereco = new Endereco();
		endereco.setCliente(cliente);
		
		String sql = "UPDATE tb_cliente SET "
				+ "cli_ativo = false"
				+ " WHERE cli_id = " + cliente.getId() + "";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);

			if(enderecoDao.deletar(endereco) == null)
				return null;

			if(usuarioDao.deletar(cliente.getUsuario()) == null)
				return null;
			
			pstm.executeUpdate();
			mensagem = "Cliente deletado com sucesso!";
		}catch(SQLException e) {
			mensagem = e.getMessage();
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm);
//		}
		
		return mensagem;
	}

	@Override
	public String atualizar(EntidadeDominio entidadeDominio) throws SQLException {
		Cliente cliente  = (Cliente) entidadeDominio;
//		List<Endereco> enderecos = new ArrayList<Endereco>();
//		enderecos.addAll(cliente.getEnderecos());
		
		String sql = "UPDATE tb_cliente SET "
				
				+ "cli_nome = ?, "
				+ "cli_numeroTelefone = ?, "
				+ "cli_numeroDocumento = ? "				
				+ " WHERE cli_id = ?";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getNumeroTelefone());
			pstm.setString(3, cliente.getNumeroDocumento());
			pstm.setLong(4, cliente.getId());
			
//			for(Endereco endereco : enderecos) {
//				if(enderecoDao.atualizar(endereco) == null) 
//					return null;
//				else
//					continue;
//			}
			if(usuarioDao.atualizar(cliente.getUsuario()) == null) {
				return null;
			}
			
			pstm.executeUpdate();
			mensagem = "Cliente atualizado com sucesso!";
		}catch(SQLException e) {
			mensagem = "Erro ao tentar atualizar usuário. Contacte a equipe de desenvolvimento";
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm);
//		}
		
		return mensagem;
	}

	@Override
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException {
		Cliente cliente = (Cliente) entidade;

		List<EntidadeDominio> clientesEntidade = new ArrayList<EntidadeDominio>();
		List<EntidadeDominio> enderecosEntidade = new ArrayList<EntidadeDominio>();
		List<Endereco> enderecos = new ArrayList<Endereco>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "cli_id, "
				+ "cli_nome, "
				+ "cli_numeroTelefone, "
				+ "cli_numeroDocumento, "
				+ "cli_usu_id "
				+ " FROM tb_cliente WHERE cli_ativo = 1 ";
		if(cliente.getId() != null) {
			sql += "AND cli_id = " + cliente.getId();
		}
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			Usuario u = new Usuario();
			Cliente c = new Cliente();
			Endereco e = new Endereco();

			while(rs.next()) {
				c = new Cliente();
				u = new Usuario();
				e = new Endereco();
				
				enderecosEntidade = new ArrayList<EntidadeDominio>();
				enderecos = new ArrayList<Endereco>();
				
				c.setId(Long.parseLong(rs.getString("cli_id")));
				c.setNome(rs.getString("cli_nome"));
				c.setNumeroTelefone(rs.getString("cli_numeroTelefone"));
				c.setNumeroDocumento(rs.getString("cli_numeroDocumento"));
				
				u.setId(Long.parseLong(rs.getString("cli_usu_id")));
				
				e.setCliente(c);
				enderecosEntidade.addAll(enderecoDao.consultar(e));
				for(EntidadeDominio endereco : enderecosEntidade) {
					enderecos.add((Endereco)endereco);
				}

				c.setUsuario((Usuario)usuarioDao.consultar(u).get(0));
				c.setEnderecos(enderecos);

				clientesEntidade.add(c);
			}
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm, rs);
//		}
		
		return clientesEntidade;
	}

}
