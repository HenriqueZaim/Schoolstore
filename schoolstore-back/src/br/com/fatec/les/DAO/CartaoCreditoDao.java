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
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.pagamento.cartao.CartaoCredito;
import br.com.fatec.les.model.usuario.Cliente;

public class CartaoCreditoDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	
	public CartaoCreditoDao() {
		conexao = ConexaoFactory.getConnection();
	}

	@Override
	public Mensagem salvar(EntidadeDominio entidadeDominio) throws SQLException {
		CartaoCredito cartaoCredito = (CartaoCredito) entidadeDominio;
		mensagem = new Mensagem();
		String sql = "INSERT INTO tb_cartaoCredito "
				+ "("
				+ "ccr_numero, "
				+ "ccr_nomeImpresso, "
				+ "ccr_favorito,"
				+ "ccr_cli_id,"
				+ "ccr_ativo,"
				+ "ccr_dataHoraCriacao "
				+ ") "
				+ " VALUES ( ?, ?, ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, cartaoCredito.getNumero());
			pstm.setString(2, cartaoCredito.getNomeImpresso());
			pstm.setBoolean(3, cartaoCredito.isFavorito());
			pstm.setLong(4, cartaoCredito.getCliente().getId());
			pstm.executeUpdate();

			mensagem.setMensagem("Operação realizada com sucesso!");
			mensagem.setMensagemStatus(MensagemStatus.OPERACAO);
			return mensagem;
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
		CartaoCredito cartaoCredito = (CartaoCredito) entidadeDominio;
		mensagem = new Mensagem();
		String sql = "UPDATE tb_cartaoCredito SET "
				+ "ccr_ativo = false"
				+ " WHERE ";
		if(cartaoCredito.getId() != null ) {
			sql += "end_id = " + cartaoCredito.getId() + "";
		}else {
			sql += "end_cli_id = " + cartaoCredito.getCliente().getId() + "";
		}
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.executeUpdate();
			mensagem.setMensagem("Cartão de crédito deletado com sucesso!");
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
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException {
		CartaoCredito cartaoEntidade = (CartaoCredito) entidade;
		CartaoCredito cartaoCredito = new CartaoCredito();
		Cliente cliente = new Cliente();
		
		List<EntidadeDominio> cartoes = new ArrayList<EntidadeDominio>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT "
				+ "ccr_id, "
				+ "ccr_numero, "
				+ "ccr_nomeImpresso, "
				+ "ccr_favorito, "
				+ "ccr_codigo, "
				+ "ccr_cli_id "
				+ " FROM tb_cartaoCredito WHERE ccr_ativo = 1 ";
		if(cartaoEntidade.getId() != null) {
			sql += "AND ccr_id = " + cartaoEntidade.getId();
		}
		if(cartaoEntidade.getCliente().getId() != null) {
			sql += "AND ccr_cli_id = " + cartaoEntidade.getCliente().getId() ;
		}
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				cartaoCredito = new CartaoCredito();
				cliente = new Cliente();

				
				
				cartaoCredito.setId(Long.parseLong(rs.getString("ccr_id")));
				cartaoCredito.setCodigo(rs.getString("ccr_codigo"));
				cartaoCredito.setNomeImpresso(rs.getString("ccr_nomeImpresso"));
				cartaoCredito.setNumero(rs.getString("ccr_numero"));
				cartaoCredito.setFavorito(rs.getString("end_favorito").equals("1") ? true : false);
				cliente.setId(Long.parseLong(rs.getString("ccr_cli_id")));
				cartaoCredito.setCliente(cliente);
				
				cartoes.add(cartaoCredito);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm, rs);
//		}
		
		return cartoes;
	}

}
