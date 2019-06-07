package br.com.univercode.pdv.web;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.univercode.pdv.dao.ProdutoDAO;
import br.com.univercode.pdv.model.Funcionario;
import br.com.univercode.pdv.model.ITarefa;
import br.com.univercode.pdv.model.Pagamento;
import br.com.univercode.pdv.model.Produto;
import br.com.univercode.pdv.repositorio.RepositorioP;
import br.com.univercode.pdv.repositorio.RepositorioV;

public class IndexVenda implements ITarefa{
	
	@Override
	public String executar(HttpServletRequest req, HttpServletResponse resp) {
		ProdutoDAO pdao = new ProdutoDAO(); 

		ArrayList<Produto> produtos = new ArrayList<Produto>();
		produtos = pdao.listarTudo();
		
		ArrayList<Produto> compras = RepositorioP.getInstance().getCompras();
		
		double total = 0;
		double troco = 0;
		
		
		String pagamento = req.getParameter("pagamento");
		if(pagamento != "" && pagamento!=null) {
			
			String trocotxt = req.getParameter("troco");
			if(trocotxt != "" && trocotxt!=null) {
				trocotxt = trocotxt.replaceAll(",", ".");
				troco = Double.parseDouble(trocotxt);
				System.out.println(troco);
			}
			
			ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
			
			String dinheiro = req.getParameter("dinheiro");
			if(dinheiro != "" && dinheiro!=null) {
				pagamentos.add(new Pagamento("dinheiro", Double.parseDouble(req.getParameter("dinheiro").replaceAll(",", "."))));
			}
			String credito = req.getParameter("credito");
			if(credito != "" && credito!=null) {
				pagamentos.add(new Pagamento("credito", Double.parseDouble(req.getParameter("credito").replaceAll(",", "."))));
			}
			String debito = req.getParameter("debito");
			if(debito != "" && debito!=null) {
				pagamentos.add(new Pagamento("debito", Double.parseDouble(req.getParameter("debito").replaceAll(",", "."))));
			}
			String vr = req.getParameter("vr");
			if(vr != "" && vr!=null) {
				pagamentos.add(new Pagamento("vr", Double.parseDouble(req.getParameter("vr").replaceAll(",", "."))));
			}
			String va = req.getParameter("va");
			if(va != "" && va!=null) {
				pagamentos.add(new Pagamento("va", Double.parseDouble(req.getParameter("va").replaceAll(",", "."))));
			}
			
			
			RepositorioV.getInstance().addVenda(troco, pagamentos);
	
			compras = null;
			RepositorioP.getInstance().deleteCompras();
			
		}
		
		String finalizar = req.getParameter("finalizar");
		if(finalizar != "" && finalizar!=null) {
			//double total = 0;
			for (int i = 0; i < compras.size(); i++) {
				if(req.getParameter("quant"+compras.get(i).getId()) != null && req.getParameter("quant"+compras.get(i).getId()) != "") {
					compras.get(i).setQuantidadecompra(Integer.parseInt(req.getParameter("quant"+compras.get(i).getId())));
				}
				//total+= compras.get(i).getQuantidadecompra() * compras.get(i).getValor();
			}
			
			double descontovalor = Double.parseDouble(req.getParameter("valordesconto").replaceAll(",", "."));
			total = Double.parseDouble(req.getParameter("total"));
			
			String cliente = req.getParameter("cliente");
			if(cliente == "" || cliente==null) {
				cliente = "SN";
			}
			HttpSession sessao = req.getSession();
			Funcionario vendedor = (Funcionario) sessao.getAttribute("funcionario");
			
			RepositorioV.getInstance().addInfo(cliente, vendedor.getNomeCompleto(), descontovalor, total, compras);
			
			req.setAttribute("totalpagamento", total);
			
			req.setAttribute("clientepagamento", cliente);
		}
		
		String desconto;
		
		String valordesconto = req.getParameter("valordesconto");
		if(valordesconto != "" && valordesconto!=null) {
			desconto = valordesconto.replaceAll(",", ".");
		}else {
			desconto = "0";
		}
		
		String qproduto = req.getParameter("qproduto");
		if(qproduto != "" && qproduto!=null) {
			
			int quant = Integer.parseInt(req.getParameter("quant"));
			
			/*Produto qp = pdao.listarPeloId(Integer.parseInt(req.getParameter("qproduto")));
			qp.setQuantidadecompra(quant);
			qproduto = null;*/
		}
		
		String add = req.getParameter("add");
		if(add != "" && add!=null) {
			boolean achou = false;
			for(int i=0;i< compras.size();i++) {
				if(compras.get(i).getId() == Integer.parseInt(add)) {
					achou = true;
				}
			}
			if(!achou) {
				RepositorioP.getInstance().addCompra(add);
			}
			add = null;
		}
					
		String excluir = req.getParameter("excluir");
		if(excluir != "" && excluir!=null) {
			RepositorioP.getInstance().deleteCompra(excluir);
			excluir = null;
		}
		
		compras = RepositorioP.getInstance().getCompras();
		
		if(compras != null && produtos!=null) {
			for (int i = 0; i < compras.size(); i++) {
				if(req.getParameter("quant"+compras.get(i).getId()) != null && req.getParameter("quant"+compras.get(i).getId()) != "") {
					compras.get(i).setQuantidadecompra(Integer.parseInt(req.getParameter("quant"+compras.get(i).getId())));
				}
				for (int j = 0; j < produtos.size(); j++) {
					if (compras.get(i).getId() == produtos.get(j).getId()) {
						produtos.remove(j);
					}
				}
			}
		}
		
		String ativo = RepositorioV.getInstance().getAtivo();
		
		req.setAttribute("ativo", ativo);
		
		req.setAttribute("desconto", desconto);
		
		req.setAttribute("compras", compras);
		
		req.setAttribute("produtos", produtos);
		
		return "WEB-INF/venda.jsp";
	}
}