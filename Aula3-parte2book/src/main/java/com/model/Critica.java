package com.model;


import org.springframework.hateoas.ResourceSupport;

public class Critica extends ResourceSupport{
	private int idCritica;
	private String NomePessoa, texto, data;
	private double nota;

	
	
	public Critica(int idCritica, String nomePessoa, String texto, double nota, String data) {
		
		this.idCritica = idCritica;
		NomePessoa = nomePessoa;
		this.texto = texto;
		this.nota = nota;
		this.data = data;
	}
	

	public int getIdCritica() {
		return idCritica;
	}
	public String getNomePessoa() {
		return NomePessoa;
	}
	public void setNomePessoa(String nomePessoa) {
		NomePessoa = nomePessoa;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
