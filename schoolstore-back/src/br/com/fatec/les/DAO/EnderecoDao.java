package br.com.fatec.les.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.endereco.Cidade;
import br.com.fatec.les.model.endereco.Endereco;
import br.com.fatec.les.model.usuario.Cliente;

public class EnderecoDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	CidadeDao cidadeDao = new CidadeDao();
	
	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		Endereco endereco = (Endereco) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		String sql = "INSERT INTO tb_endereco "
				+ "("
				+ "end_logradouro, "
				+ "end_bairro, "
				+ "end_cep, "
				+ "end_numero, "
				+ "end_complemento, "
				+ "end_referencia, "
				+ "end_favorito, "
				+ "end_cid_id, "
				+ "end_cli_id,"
				+ "end_nome, "
				+ "end_ativo, "
				+ "end_dataHoraCriacao"
				+ ") "
				+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, endereco.getLogradouro());
			pstm.setString(2, endereco.getBairro());
			pstm.setString(3, endereco.getCep());
			pstm.setInt(4, endereco.getNumero());
			pstm.setString(5, endereco.getComplemento());
			pstm.setString(6, endereco.getReferencia());
			pstm.setBoolean(7, endereco.isFavorito());
			pstm.setLong(8, endereco.getCidade().getId());
			pstm.setLong(9, endereco.getCliente().getId());
			pstm.setString(10, endereco.getNome());
			pstm.executeUpdate();

			mensagem.setMensagem("Operação realizada com sucesso!");
			mensagem.setMensagemStatus(MensagemStatus.OPERACAO);
			return mensagem;

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
		Endereco endereco = (Endereco) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		String sql = "UPDATE tb_endereco SET "
				+ "end_ativo = false"
				+ " WHERE ";
		if(endereco.getId() != null ) {
			sql += "end_id = " + endereco.getId() + "";
		}else {
			sql += "end_cli_id = " + endereco.getCliente().getId() + "";
		}
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.executeUpdate();
			mensagem.setMensagem("Endereço deletado com sucesso!");
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
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public List<ADominio> consultar(ADominio entidade) throws SQLException {
		Endereco enderecoEntidade = (Endereco) entidade;
		conexao = ConexaoFactory.getConnection();
		Endereco endereco = new Endereco();
		Cidade cidade = new Cidade();
		Cliente cliente = new Cliente();
		
		List<ADominio> enderecos = new ArrayList<ADominio>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT "
				+ "end_id, "
				+ "end_logradouro, "
				+ "end_bairro, "
				+ "end_cep, "
				+ "end_numero, "
				+ "end_complemento, "
				+ "end_referencia, "
				+ "end_favorito,"
				+ "end_nome, "
				+ "end_cid_id,"
				+ "end_cli_id "
				+ " FROM tb_endereco WHERE end_ativo = 1 ";
		if(enderecoEntidade.getId() != null) {
			sql += "AND end_id = " + enderecoEntidade.getId();
		}
		if(enderecoEntidade.getCliente().getId() != null) {
			sql += "AND end_cli_id = " + enderecoEntidade.getCliente().getId() ;
		}
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				endereco = new Endereco();
				cidade = new Cidade();
				cliente = new Cliente();
				
				cidade.setId(Long.parseLong(rs.getString("end_cid_id")));
				cliente.setId(Long.parseLong(rs.getString("end_cli_id")));

				endereco.setCidade((Cidade)cidadeDao.consultar(cidade).get(0));
				endereco.setCliente(cliente);
				
				endereco.setId(Long.parseLong(rs.getString("end_id")));
				endereco.setLogradouro(rs.getString("end_logradouro"));
				endereco.setBairro(rs.getString("end_bairro"));
				endereco.setCep(rs.getString("end_cep"));
				endereco.setNumero(Integer.parseInt(rs.getString("end_numero")));
				endereco.setComplemento(rs.getString("end_complemento"));
				endereco.setReferencia(rs.getString("end_referencia"));
				endereco.setFavorito(rs.getString("end_favorito").equals("1") ? true : false);
				endereco.setNome(rs.getString("end_nome"));
				
				enderecos.add(endereco);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return enderecos;
	}

}
