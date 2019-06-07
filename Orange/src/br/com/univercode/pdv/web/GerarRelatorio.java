package br.com.univercode.pdv.web;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.univercode.pdv.dao.Conexao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@WebServlet(urlPatterns = {"/relatorio"})
public class GerarRelatorio extends HttpServlet{
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String relatorio = req.getParameter("relatorio");
		
		
		if(relatorio.equals("produto")) {
			
			String id = req.getParameter("relatorioid");
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("id", Integer.parseInt(id));
			
			try {
				
				String jrx = getServletContext().getRealPath("relatorio_produto_individual.jrxml");
			
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
		}else if(relatorio.equals("produtos")) {
			try {
				
				String jrx = getServletContext().getRealPath("relatorio_produtos.jrxml");
			
				String jasper = JasperCompileManager.compileReportToFile(jrx);
			
				Connection conexao = Conexao.getConexao();
				JasperPrint print = JasperFillManager.fillReport(jasper, null, conexao);
				
				//FileOutputStream saida = new FileOutputStream("produtos.pdf");
				JRExporter exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, resp.getOutputStream());
				exporter.exportReport();
			
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(relatorio.equals("funcionarios")) {
			
			try {
				
				String jrx = getServletContext().getRealPath("relatorio_funcionarios.jrxml");
			
				String jasper = JasperCompileManager.compileReportToFile(jrx);
			
				Connection conexao = Conexao.getConexao();
				JasperPrint print = JasperFillManager.fillReport(jasper, null, conexao);
				
				//FileOutputStream saida = new FileOutputStream("produtos.pdf");
				JRExporter exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, resp.getOutputStream());
				exporter.exportReport();
			
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(relatorio.equals("funcionario")) {
			String id = req.getParameter("relatorioid");
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("id", Integer.parseInt(id));
			
			try {
				
				String jrx = getServletContext().getRealPath("relatorio_funcionario_individual.jrxml");
			
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
		}else if(relatorio.equals("venda")) {
			String id = req.getParameter("relatorioid");
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("id", id);
			
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
}
