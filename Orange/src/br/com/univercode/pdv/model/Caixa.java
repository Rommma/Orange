package br.com.univercode.pdv.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import br.com.univercode.pdv.dao.EntradaDAO;

public class Caixa {
	static Caixa caixa;
	public static Caixa getInstance() {
		if(caixa == null) {
			caixa = new Caixa();
		}
		return caixa;
	}
	EntradaDAO edao = new EntradaDAO();
	ArrayList<Entrada> entradas;
	String horaabertura, dataabertura;
	double saldoinicial;
	Funcionario abriu;
	String aberto = "false";
	
	public String getDataAtual() {
		LocalDateTime agora = LocalDateTime.now();

		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = formatterData.format(agora);
		return dataFormatada;
	}
	public String getHoraAtual() {
		LocalDateTime agora = LocalDateTime.now();
		
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
		String horaFormatada = formatterHora.format(agora);
		return horaFormatada;
	}
	public void abrirCaixa(double saldoinicial, Funcionario f) {
		this.saldoinicial = saldoinicial;
		this.horaabertura = this.getHoraAtual();
		this.dataabertura = this.getDataAtual();
		this.abriu = f;
		aberto = "true";
	}
	public void fecharCaixa() {
		aberto = "false";
	}
	public void venda(Entrada e) {
		
	}
	public EntradaDAO getEdao() {
		return edao;
	}
	public void setEdao(EntradaDAO edao) {
		this.edao = edao;
	}
	public ArrayList<Entrada> getEntradas() {
		return entradas;
	}
	public void setEntradas(ArrayList<Entrada> entradas) {
		this.entradas = entradas;
	}
	public String getHoraabertura() {
		return horaabertura;
	}
	public void setHoraabertura(String horaabertura) {
		this.horaabertura = horaabertura;
	}
	public String getDataabertura() {
		return dataabertura;
	}
	public void setDataabertura(String dataabertura) {
		this.dataabertura = dataabertura;
	}
	public double getSaldoinicial() {
		return saldoinicial;
	}
	public void setSaldoinicial(double saldoinicial) {
		this.saldoinicial = saldoinicial;
	}
	public String isAberto() {
		return aberto;
	}
	public void setAberto(String aberto) {
		this.aberto = aberto;
	}

	public Funcionario getAbriu() {
		return abriu;
	}

	public void setAbriu(Funcionario abriu) {
		this.abriu = abriu;
	}
	
}
