package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.assets.ADominio;

public interface IViewHelper {
	public ADominio getEntidade(HttpServletRequest request);
	public void setEntidade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
