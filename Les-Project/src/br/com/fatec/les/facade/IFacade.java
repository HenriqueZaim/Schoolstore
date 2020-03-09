package br.com.fatec.les.facade;

import br.com.fatec.les.model.EntidadeDominio;

public interface IFacade {
	public Result salvar(EntidadeDominio entidadeDominio);
	public Result atualizar(EntidadeDominio entidadeDominio);
	public Result deletar(EntidadeDominio entidadeDominio);
	public Result consultar(EntidadeDominio entidadeDominio);
}
