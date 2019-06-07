package br.com.univercode.pdv.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ITarefa {

	public String executar(HttpServletRequest req, HttpServletResponse
			resp);
}
