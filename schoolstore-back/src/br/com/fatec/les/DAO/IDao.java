package br.com.fatec.les.DAO;

import java.sql.SQLException;
import java.util.List;

import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;

public interface IDao {
	public Mensagem salvar(EntidadeDominio entidadeDominio) throws SQLException;
	public Mensagem deletar(EntidadeDominio entidadeDominio) throws SQLException;
	public Mensagem atualizar(EntidadeDominio entidadeDominio) throws SQLException;
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException;
}
