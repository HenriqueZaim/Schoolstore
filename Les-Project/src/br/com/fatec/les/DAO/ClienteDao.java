package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Usuario;

public class ClienteDao implements IDao{
	
	private Connection conexao = null;
	private String mensagem = null;
	
	public ClienteDao() {
		conexao = ConexaoFactory.getConnection();
	}

	@Override
	public String salvar(EntidadeDominio entidadeDominio) throws SQLException {
		Cliente cliente = (Cliente) entidadeDominio;
		EnderecoDao enderecoDao = new EnderecoDao();
		UsuarioDao usuarioDao = new UsuarioDao();
		
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
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getNumeroTelefone());
			pstm.setString(3, cliente.getNumeroDocumento());
			pstm.setInt(4, Integer.parseInt(usuarioDao.salvar(cliente.getUsuario())));
			pstm.setInt(5, Integer.parseInt(enderecoDao.salvar(cliente.getEndereco())));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String atualizar(EntidadeDominio entidadeDominio) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
