package br.com.univercode.pdv.model;

public class Entrada {
	private double entrada;
	private String tipoentrada, dataentrada, horaentrada, formapagamento;
	private int idatendente, id;
	
	public Entrada(double entrada, String dataentrada, String horaentrada, String tipoentrada, String formapagamento, int idatendente) {
		this.entrada = entrada;
		this.tipoentrada = tipoentrada;
		this.formapagamento = formapagamento;
		this.idatendente = idatendente;
		this.dataentrada = horaentrada;
		this.horaentrada = dataentrada;
	}
	public void Entrada(double entrada, String tipoentrada, String formapagamento, int idatendente) {
		this.entrada = entrada;
		this.tipoentrada = tipoentrada;
		this.formapagamento = formapagamento;
		this.idatendente = idatendente;
		this.dataentrada = Caixa.getInstance().getDataAtual();
		this.horaentrada = Caixa.getInstance().getHoraAtual();
	}
	public double getEntrada() {
		return entrada;
	}
	public void setEntrada(double entrada) {
		this.entrada = entrada;
	}
	public String getTipoentrada() {
		return tipoentrada;
	}
	public void setTipoentrada(String tipoentrada) {
		this.tipoentrada = tipoentrada;
	}
	public String getDataentrada() {
		return dataentrada;
	}
	public void setDataentrada(String dataentrada) {
		this.dataentrada = dataentrada;
	}
	public String getHoraentrada() {
		return horaentrada;
	}
	public void setHoraentrada(String horaentrada) {
		this.horaentrada = horaentrada;
	}
	public String getFormapagamento() {
		return formapagamento;
	}
	public void setFormapagamento(String formapagamento) {
		this.formapagamento = formapagamento;
	}
	public int getIdatendente() {
		return idatendente;
	}
	public void setIdatendente(int idatendente) {
		this.idatendente = idatendente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
