package br.com.univercode.pdv.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.univercode.pdv.dao.FuncionarioDAO;
import br.com.univercode.pdv.dao.PagamentoDAO;
import br.com.univercode.pdv.model.Caixa;
import br.com.univercode.pdv.model.Funcionario;
import br.com.univercode.pdv.model.ITarefa;
import br.com.univercode.pdv.model.Pagamento;

public class IndexHome implements ITarefa{

	@Override
	public String executar(HttpServletRequest req, HttpServletResponse resp) {
		FuncionarioDAO fdao = new FuncionarioDAO();
		PagamentoDAO pdao = new PagamentoDAO();
		req.setAttribute("dataatual", Caixa.getInstance().getDataAtual());
		req.setAttribute("horaatual", Caixa.getInstance().getHoraAtual());
		
		HttpSession sessao = req.getSession();
		
		Caixa caixa = Caixa.getInstance();
		
		String valorinicial = req.getParameter("valorinicial");
		if(valorinicial != "" && valorinicial!=null) {
			Caixa.getInstance().abrirCaixa(Double.parseDouble(valorinicial.replace("," ,".")),(Funcionario) sessao.getAttribute("funcionario"));
		}
		
		String fechar = req.getParameter("fechar");
		if(fechar != "" && fechar!=null) {
			double dinheiro = Caixa.getInstance().getSaldoinicial(), cartoes = 0;
			ArrayList<Pagamento> pagamentos = pdao.listarTudo();
			for (Pagamento p : pagamentos) {
				if(p.getNome().equalsIgnoreCase("dinheiro")) {
					dinheiro += p.getValor();
				}else {
					cartoes += p.getValor();
				}
			}
			req.setAttribute("dinheiro", dinheiro);
			req.setAttribute("cartoes", cartoes);
			Caixa.getInstance().fecharCaixa();
		}
		
		req.setAttribute("horaabertura", Caixa.getInstance().getHoraabertura());
		req.setAttribute("dataabertura", Caixa.getInstance().getDataabertura());
		req.setAttribute("saldoabertura", Caixa.getInstance().getSaldoinicial());
		req.setAttribute("fabriu", Caixa.getInstance().getAbriu());
		req.setAttribute("aberto", Caixa.getInstance().isAberto());
		return "/WEB-INF/home.jsp";
	}

}
