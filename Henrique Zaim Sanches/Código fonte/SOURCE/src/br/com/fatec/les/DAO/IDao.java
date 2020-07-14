package br.com.fatec.les.DAO;

import java.sql.SQLException;
import java.util.List;

import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.config.EntidadeDominio;

public interface IDao {
	public Mensagem salvar(ADominio entidade) throws SQLException;
	public Mensagem deletar(ADominio entidade) throws SQLException;
	public Mensagem atualizar(ADominio entidade) throws SQLException;
	public List<ADominio> consultar(ADominio entidade) throws SQLException;
}
