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
import br.com.fatec.les.model.pagamento.cartao.CartaoCredito;
import br.com.fatec.les.model.usuario.Cliente;
import br.com.fatec.les.model.usuario.Usuario;

public class ClienteDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	EnderecoDao enderecoDao = new EnderecoDao();
	UsuarioDao usuarioDao = new UsuarioDao();
	CartaoCreditoDao cartaoCreditoDao = new CartaoCreditoDao();
	
	public ClienteDao() {
		conexao = ConexaoFactory.getConnection();
	}

	@Override
	public Mensagem salvar(EntidadeDominio entidadeDominio) throws SQLException {
		Cliente cliente = (Cliente) entidadeDominio;
		Cliente aux = new Cliente();
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
			mensagem = usuarioDao.salvar(cliente.getUsuario());
			if(mensagem.getMensagemStatus() == MensagemStatus.ERRO) {
				throw new SQLException();
			}
			String idUsuario = mensagem.getMensagem();
			
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getNumeroTelefone());
			pstm.setString(3, cliente.getNumeroDocumento());
			pstm.setInt(4, Integer.parseInt(idUsuario));
			pstm.executeUpdate();
			
			rs = pstm.getGeneratedKeys();
			if (rs.next()){
				String idCliente = Integer.toString(rs.getInt(1));
				aux = new Cliente();
				aux.setId(Long.parseLong(idCliente));
				for(Endereco e : cliente.getEnderecos()) {
					e.setCliente(aux);
					if(enderecoDao.salvar(e).getMensagemStatus() == MensagemStatus.ERRO) {
						throw new SQLException();
					}
				}
				if(!cliente.getCartoesCredito().isEmpty()) {
					for(CartaoCredito c : cliente.getCartoesCredito()) {
						c.setCliente(aux);
						if(cartaoCreditoDao.salvar(c).getMensagemStatus() == MensagemStatus.ERRO) {
							throw new SQLException();
						}
					}
				}
			}
			
			mensagem = new Mensagem();
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
		CartaoCredito cartaoCredito = new CartaoCredito();
		endereco.setCliente(cliente);
		cartaoCredito.setCliente(cliente);
		
		String sql = "UPDATE tb_cliente SET "
				+ "cli_ativo = false"
				+ " WHERE cli_id = " + cliente.getId() + "";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);

			if(enderecoDao.deletar(endereco).getMensagemStatus() == MensagemStatus.ERRO || 
					cartaoCreditoDao.deletar(cartaoCredito).getMensagemStatus() == MensagemStatus.ERRO ||
					usuarioDao.deletar(cliente.getUsuario()).getMensagemStatus() == MensagemStatus.ERRO) {
				throw new SQLException();
			}

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
			
			if(usuarioDao.atualizar(cliente.getUsuario()).getMensagemStatus() == MensagemStatus.ERRO)
				throw new SQLException();
			
			List<EntidadeDominio> cartoesBanco = new ArrayList<EntidadeDominio>();
			CartaoCredito cartaoCredito = new CartaoCredito();
			cartaoCredito.setCliente(cliente);
			
			cartoesBanco.addAll(cartaoCreditoDao.consultar(cartaoCredito));
			
			// Adiciona os novos endereços
			if(!cliente.getCartoesCredito().isEmpty()) {
				for(CartaoCredito c : cliente.getCartoesCredito()) {
					if(c.getId() == null) {
						if(cartaoCreditoDao.salvar(c).getMensagemStatus() == MensagemStatus.ERRO)
							throw new SQLException();
					}
				}
			}

			if(!cartoesBanco.isEmpty()) {
				for(EntidadeDominio entidade : cartoesBanco) {
					entidade = (CartaoCredito) entidade;
					boolean flag = false;
					if(!cliente.getCartoesCredito().isEmpty()) {
						for(CartaoCredito c : cliente.getCartoesCredito()) {
							if(c.getId() != null) {
								if(c.getId() == entidade.getId()) {
									flag = true;
									break;
								}else {
									continue;
								}
							}
						}
					}
					if(!flag) {
						if(enderecoDao.deletar(entidade).getMensagemStatus() == MensagemStatus.ERRO)
							throw new SQLException();
					}
				}
			}
			
			List<EntidadeDominio> enderecosBanco = new ArrayList<EntidadeDominio>();
			Endereco endereco = new Endereco();
			endereco.setCliente(cliente);
			
			enderecosBanco.addAll(enderecoDao.consultar(endereco));
			
			// Adiciona os novos endereços
			for(Endereco e : cliente.getEnderecos()) {
				if(e.getId() == null) {
					if(enderecoDao.salvar(e).getMensagemStatus() == MensagemStatus.ERRO)
						throw new SQLException();
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
					if(enderecoDao.deletar(entidade).getMensagemStatus() == MensagemStatus.ERRO)
						throw new SQLException();
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
		List<EntidadeDominio> cartoesEntidade = new ArrayList<EntidadeDominio>();

		List<Endereco> enderecos = new ArrayList<Endereco>();
		List<CartaoCredito> cartoes = new ArrayList<CartaoCredito>();
		
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
			CartaoCredito ccr = new CartaoCredito();

			while(rs.next()) {
				c = new Cliente();
				u = new Usuario();
				e = new Endereco();
				ccr = new CartaoCredito();
				
				enderecosEntidade = new ArrayList<EntidadeDominio>();
				cartoesEntidade = new ArrayList<EntidadeDominio>();
				enderecos = new ArrayList<Endereco>();
				cartoes = new ArrayList<CartaoCredito>();
				
				c.setId(Long.parseLong(rs.getString("cli_id")));
				c.setNome(rs.getString("cli_nome"));
				c.setNumeroTelefone(rs.getString("cli_numeroTelefone"));
				c.setNumeroDocumento(rs.getString("cli_numeroDocumento"));
				
				e.setCliente(c);
				enderecosEntidade.addAll(enderecoDao.consultar(e));
				for(EntidadeDominio endereco : enderecosEntidade) {
					enderecos.add((Endereco)endereco);
				}
				ccr.setCliente(c);
				cartoesEntidade.addAll(cartaoCreditoDao.consultar(ccr));
				if(!cartoesEntidade.isEmpty()) {
					for(EntidadeDominio cartao : cartoesEntidade) {
						cartoes.add((CartaoCredito)cartao);
					}
				}
				
				u.setId(Long.parseLong(rs.getString("cli_usu_id")));
				c.setUsuario((Usuario)usuarioDao.consultar(u).get(0));
				c.setEnderecos(enderecos);
				c.setCartoesCredito(cartoes);

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
