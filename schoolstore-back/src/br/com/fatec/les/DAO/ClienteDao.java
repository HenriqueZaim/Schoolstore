package br.com.fatec.les.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.endereco.Endereco;
import br.com.fatec.les.model.pagamento.cartao.CartaoCredito;
import br.com.fatec.les.model.usuario.Carrinho;
import br.com.fatec.les.model.usuario.Cliente;
import br.com.fatec.les.model.usuario.Usuario;

public class ClienteDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	EnderecoDao enderecoDao = new EnderecoDao();
	UsuarioDao usuarioDao = new UsuarioDao();
	CartaoCreditoDao cartaoCreditoDao = new CartaoCreditoDao();
	CarrinhoDao carrinhoDao = new CarrinhoDao();
	
	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		Cliente cliente = (Cliente) entidade;
		conexao = ConexaoFactory.getConnection();
		Cliente aux = new Cliente();
		mensagem = new Mensagem();
		ResultSet rs;
		
		String sql = "INSERT INTO tb_cliente "
				+ "("
				+ "cli_nome, "
				+ "cli_numeroTelefone, "
				+ "cli_numeroDocumento, "
				+ "cli_usu_id, "
				+ "cli_car_id, "
				+ "cli_ativo, "
				+ "cli_dataHoraCriacao "
				+ ")"
				+ " VALUES ( ?, ?, ?, ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			mensagem = usuarioDao.salvar(cliente.getUsuario());
			if(mensagem.getMensagemStatus() == MensagemStatus.ERRO) {
				throw new SQLException();
			}
			String idUsuario = mensagem.getMensagem();
			mensagem = carrinhoDao.salvar(cliente.getCarrinho());
			if(mensagem.getMensagemStatus() == MensagemStatus.ERRO) {
				throw new SQLException();
			}
			String idCarrinho = mensagem.getMensagem();
			
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getNumeroTelefone());
			pstm.setString(3, cliente.getNumeroDocumento());
			pstm.setInt(4, Integer.parseInt(idUsuario));
			pstm.setInt(5, Integer.parseInt(idCarrinho));
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
		finally {
			ConexaoFactory.closeConnection(conexao, pstm);
		}
		return mensagem;
	}

	@Override
	public Mensagem deletar(ADominio entidade) throws SQLException {
		Cliente cliente  = (Cliente) entidade;
		conexao = ConexaoFactory.getConnection();
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
					usuarioDao.deletar(cliente.getUsuario()).getMensagemStatus() == MensagemStatus.ERRO || 
					carrinhoDao.deletar(cliente.getCarrinho()).getMensagemStatus() == MensagemStatus.ERRO) {
				throw new SQLException();
			}

			pstm.executeUpdate();
			mensagem.setMensagem("Cliente deletado com sucesso!");
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
		Cliente cliente  = (Cliente) entidade;
		conexao = ConexaoFactory.getConnection();
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
			
			List<ADominio> cartoesBanco = new ArrayList<ADominio>();
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
				for(ADominio entidadeD : cartoesBanco) {
					entidadeD = (CartaoCredito) entidadeD;
					boolean flag = false;
					if(!cliente.getCartoesCredito().isEmpty()) {
						for(CartaoCredito c : cliente.getCartoesCredito()) {
							if(c.getId() != null) {
								if(c.getId() == entidadeD.getId()) {
									flag = true;
									break;
								}else {
									continue;
								}
							}
						}
					}
					if(!flag) {
						if(enderecoDao.deletar(entidadeD).getMensagemStatus() == MensagemStatus.ERRO)
							throw new SQLException();
					}
				}
			}
			
			List<ADominio> enderecosBanco = new ArrayList<ADominio>();
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
			for(ADominio entidadeE : enderecosBanco) {
				entidadeE = (Endereco) entidadeE;
				boolean flag = false;
				for(Endereco e : cliente.getEnderecos()) {
					if(e.getId() != null) {
						if(e.getId() == entidadeE.getId()) {
							flag = true;
							break;
						}else {
							continue;
						}
					}
				}
				if(!flag) {
					if(enderecoDao.deletar(entidadeE).getMensagemStatus() == MensagemStatus.ERRO)
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
		finally {
			ConexaoFactory.closeConnection(conexao, pstm);
		}
		
		return mensagem;
	}

	@Override
	public List<ADominio> consultar(ADominio entidade) throws SQLException {
		Cliente cliente = (Cliente) entidade;
		conexao = ConexaoFactory.getConnection();

		List<ADominio> clientesEntidade = new ArrayList<ADominio>();
		List<ADominio> enderecosEntidade = new ArrayList<ADominio>();
		List<ADominio> cartoesEntidade = new ArrayList<ADominio>();

		List<Endereco> enderecos = new ArrayList<Endereco>();
		List<CartaoCredito> cartoes = new ArrayList<CartaoCredito>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "cli_id, "
				+ "cli_nome, "
				+ "cli_numeroTelefone, "
				+ "cli_numeroDocumento, "
				+ "cli_usu_id,"
				+ "cli_car_id "
				+ " FROM tb_cliente WHERE cli_ativo = 1 ";
		if(cliente.getId() != null) {
			sql += " AND cli_id = " + cliente.getId() + "";
		}
		if(cliente.getUsuario() != null && cliente.getUsuario().getId() != null) {
			sql += " AND cli_usu_id = " + cliente.getUsuario().getId() + "";
		}
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			Usuario u = new Usuario();
			Cliente c = new Cliente();
			Endereco e = new Endereco();
			CartaoCredito ccr = new CartaoCredito();
			Carrinho car = new Carrinho();

			while(rs.next()) {
				c = new Cliente();
				u = new Usuario();
				e = new Endereco();
				ccr = new CartaoCredito();
				car = new Carrinho();
				
				enderecosEntidade = new ArrayList<ADominio>();
				cartoesEntidade = new ArrayList<ADominio>();
				enderecos = new ArrayList<Endereco>();
				cartoes = new ArrayList<CartaoCredito>();
				
				c.setId(Long.parseLong(rs.getString("cli_id")));
				c.setNome(rs.getString("cli_nome"));
				c.setNumeroTelefone(rs.getString("cli_numeroTelefone"));
				c.setNumeroDocumento(rs.getString("cli_numeroDocumento"));
				
				e.setCliente(c);
				enderecosEntidade.addAll(enderecoDao.consultar(e));
				for(ADominio endereco : enderecosEntidade) {
					enderecos.add((Endereco)endereco);
				}
				ccr.setCliente(c);
				cartoesEntidade.addAll(cartaoCreditoDao.consultar(ccr));
				if(!cartoesEntidade.isEmpty()) {
					for(ADominio cartao : cartoesEntidade) {
						cartoes.add((CartaoCredito)cartao);
					}
				}
				
				car.setId(rs.getLong("cli_car_id"));
				c.setCarrinho(car);
				
				u.setId(Long.parseLong(rs.getString("cli_usu_id")));
				c.setUsuario((Usuario)usuarioDao.consultar(u).get(0));
				c.setEnderecos(enderecos);
				c.setCartoesCredito(cartoes);

				clientesEntidade.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return clientesEntidade;
	}

}
