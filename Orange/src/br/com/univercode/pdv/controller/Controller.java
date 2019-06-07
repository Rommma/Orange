package br.com.univercode.pdv.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.univercode.pdv.dao.Conexao;
import br.com.univercode.pdv.model.Funcionario;
import br.com.univercode.pdv.model.ITarefa;
import br.com.univercode.pdv.repositorio.RepositorioV;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@WebServlet(urlPatterns = {"/index","/controller"})
public class Controller extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			String tarefa = req.getParameter("tarefa");
			//System.out.println("tarefa="+tarefa);
			
			HttpSession sessao = req.getSession();
			
			if(tarefa==null || sessao.getAttribute("funcionario") == null && !tarefa.equals("Login")){ tarefa="Index";}
		
			tarefa = "br.com.univercode.pdv.web."+tarefa;
			try {
				
				Class<?> ClasseTarefa = Class.forName(tarefa);
				
				ITarefa  ObjTarefa = (ITarefa) ClasseTarefa.newInstance();
				String retorno = ObjTarefa.executar(req, resp);
				
				if(tarefa.equals("br.com.univercode.pdv.web.Login")) {
					tarefa = "IndexHome";
					tarefa = "br.com.univercode.pdv.web."+tarefa;
					ClasseTarefa = Class.forName(tarefa);
					
					ObjTarefa = (ITarefa) ClasseTarefa.newInstance();
					retorno = ObjTarefa.executar(req, resp);
				}else if(tarefa.equals("br.com.univercode.pdv.web.IndexVenda")) {
					if(RepositorioV.getInstance().getUltimavenda()!=null) {
					
						Map<String, Object> parametros = new HashMap<>();
						parametros.put("id",Integer.parseInt(RepositorioV.getInstance().getUltimavenda()));
						
						RepositorioV.getInstance().deleteultimavenda();
						
						try {
							
							String jrx = getServletContext().getRealPath("relatorio_venda_individual.jrxml");
						
							String jasper = JasperCompileManager.compileReportToFile(jrx);
						
							Connection conexao = Conexao.getConexao();
							JasperPrint print = JasperFillManager.fillReport(jasper, parametros, conexao);
							
							//FileOutputStream saida = new FileOutputStream("produtos.pdf");
							JRExporter exporter = new JRPdfExporter();
							exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
							exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, resp.getOutputStream());
							exporter.exportReport();
						
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				req.getRequestDispatcher(retorno).forward(req, resp);
				//resp.sendRedirect(retorno);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
	}
}
