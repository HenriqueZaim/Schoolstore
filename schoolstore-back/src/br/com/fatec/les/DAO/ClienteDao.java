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
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.endereco.Endereco;
import br.com.fatec.les.model.usuario.Cliente;
import br.com.fatec.les.model.usuario.Usuario;

public class ClienteDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	EnderecoDao enderecoDao = new EnderecoDao();
	UsuarioDao usuarioDao = new UsuarioDao();
	
	public ClienteDao() {
		conexao = ConexaoFactory.getConnection();
	}

	@Override
	public Mensagem salvar(EntidadeDominio entidadeDominio) throws SQLException {
		Cliente cliente = (Cliente) entidadeDominio;
		mensagem = new Mensagem();
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
			String idUsuario = usuarioDao.salvar(cliente.getUsuario()).getMensagem();
			
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
			
			mensagem.setMensagem("Cliente cadastrado com sucesso!");
			mensagem.setMensagemStatus(MensagemStatus.SUCESSO);
		}catch(SQLException e){
			mensagem.setMensagem("Ocorreu um erro durante a operação. Tente novamente ou consulte a equipe de desenvolvimento.");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm);
//		}
		return mensagem;
	}

	@Override
	public Mensagem deletar(EntidadeDominio entidadeDominio) throws SQLException {
		Cliente cliente  = (Cliente) entidadeDominio;
		mensagem = new Mensagem();
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
			mensagem.setMensagem("Cliente deletado com sucesso!");
			mensagem.setMensagemStatus(MensagemStatus.SUCESSO);
		}catch(SQLException e) {
			mensagem.setMensagem("Ocorreu um erro durante a operação. Tente novamente ou consulte a equipe de desenvolvimento.");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm);
//		}
		
		return mensagem;
	}

	@Override
	public Mensagem atualizar(EntidadeDominio entidadeDominio) throws SQLException {
		Cliente cliente  = (Cliente) entidadeDominio;
		mensagem = new Mensagem();
		
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
			
			mensagem = usuarioDao.atualizar(cliente.getUsuario());
			if(mensagem.getMensagemStatus() == MensagemStatus.ERRO)
				return mensagem;
			
			List<EntidadeDominio> enderecosBanco = new ArrayList<EntidadeDominio>();
			Endereco endereco = new Endereco();
			endereco.setCliente(cliente);
			
			enderecosBanco.addAll(enderecoDao.consultar(endereco));
			
			// Adiciona os novos endereços
			for(Endereco e : cliente.getEnderecos()) {
				if(e.getId() == null) {
					enderecoDao.salvar(e);
				}
			}
			
			// Remove do banco os que não existem mais					
			for(EntidadeDominio entidade : enderecosBanco) {
				entidade = (Endereco) entidade;
				boolean flag = false;
				for(Endereco e : cliente.getEnderecos()) {
					if(e.getId() != null) {
						if(e.getId() == entidade.getId()) {
							flag = true;
							break;
						}else {
							continue;
						}
					}
				}
				if(!flag) {
					enderecoDao.deletar(entidade);
				}
			}
			
			mensagem = new Mensagem();
			pstm.executeUpdate();
			mensagem.setMensagem("Cliente atualizado com sucesso!");
			mensagem.setMensagemStatus(MensagemStatus.SUCESSO);
		}catch(SQLException e) {
			mensagem.setMensagem("Ocorreu um erro durante a operação. Tente novamente ou consulte a equipe de desenvolvimento.");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
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
		}catch(SQLException e) {
			e.printStackTrace();
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm, rs);
//		}
		
		return clientesEntidade;
	}

}
