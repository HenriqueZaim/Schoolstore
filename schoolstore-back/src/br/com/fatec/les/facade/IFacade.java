package br.com.fatec.les.facade;

import br.com.fatec.les.model.assets.EntidadeDominio;

public interface IFacade {
	public Resultado salvar(EntidadeDominio entidadeDominio);
	public Resultado atualizar(EntidadeDominio entidadeDominio);
	public Resultado deletar(EntidadeDominio entidadeDominio);
	public Resultado consultar(EntidadeDominio entidadeDominio);
}
