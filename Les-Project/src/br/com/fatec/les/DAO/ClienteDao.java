package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
		
		String sql = "INSERT INTO clientes "
				+ "("
				+ "cli_nome, "
				+ "cli_numeroTelefone, "
				+ "cli_numeroDocumento, "
				+ "cli_usu_id, "
				+ "cli_end_id, "
				+ "cli_ativo, "
				+ "cli_dataHoraCriacao "
				+ ")"
				+ " VALUES ( ?, ?, ?, ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			String idEndereco = enderecoDao.salvar(cliente.getEndereco());
			String idUsuario = usuarioDao.salvar(cliente.getUsuario());
			
			if(idEndereco == null || idUsuario == null) {
				return "Erro ao tentar cadastrar cliente";
			}
			
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getNumeroTelefone());
			pstm.setString(3, cliente.getNumeroDocumento());
			pstm.setInt(4, Integer.parseInt(idUsuario));
			pstm.setInt(5, Integer.parseInt(idEndereco));
			pstm.executeUpdate();
			mensagem = "Usuário cadastrado com sucesso!";
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
		
		String sql = "UPDATE clientes SET "
				+ "cli_ativo = false"
				+ " WHERE cli_id = " + cliente.getId() + "";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			if(enderecoDao.deletar(cliente.getEndereco()) == null || usuarioDao.deletar(cliente.getUsuario()) == null) {
				return null;
			}
			pstm.executeUpdate();
			mensagem = "Usuário deletado com sucesso";
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
		
		String sql = "UPDATE clientes SET "
				
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
			
			if(enderecoDao.atualizar(cliente.getEndereco()) == null || usuarioDao.atualizar(cliente.getUsuario()) == null) {
				return null;
			}
			
			pstm.executeUpdate();
			mensagem = "Usuário atualizado com sucesso";
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
		
		Usuario u = new Usuario();
		Cliente c = new Cliente();
		Endereco e = new Endereco();
		
		List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
		List<EntidadeDominio> enderecos = new ArrayList<EntidadeDominio>();
		List<EntidadeDominio> usuarios = new ArrayList<EntidadeDominio>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "cli_id, "
				+ "cli_nome, "
				+ "cli_numeroTelefone, "
				+ "cli_numeroDocumento, "
				+ "cli_usu_id, "
				+ "cli_end_id "
				+ " FROM clientes WHERE cli_ativo = 1 ";
		if(cliente.getId() != null) {
			sql += "AND cli_id = " + cliente.getId();
		}
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			

			while(rs.next()) {
				c = new Cliente();
				u = new Usuario();
				e = new Endereco();
				
				usuarios = new ArrayList<EntidadeDominio>();
				enderecos = new ArrayList<EntidadeDominio>();
				
				c.setId(Long.parseLong(rs.getString("cli_id")));
				c.setNome(rs.getString("cli_nome"));
				c.setNumeroTelefone(rs.getString("cli_numeroTelefone"));
				c.setNumeroDocumento(rs.getString("cli_numeroDocumento"));
				
				u.setId(Long.parseLong(rs.getString("cli_usu_id")));
				e.setId(Long.parseLong(rs.getString("cli_end_id")));
				
				usuarios.addAll(usuarioDao.consultar(u));
				enderecos.addAll(enderecoDao.consultar(e));
				
				c.setEndereco((Endereco)enderecos.get(0));
				c.setUsuario((Usuario)usuarios.get(0));
//				c.setDataHoraCriacao(LocalDateTime.parse(rs.getString("cli_dataHoraCriacao")));

				clientes.add(c);
			}
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm, rs);
//		}
		
		return clientes;
	}

}
