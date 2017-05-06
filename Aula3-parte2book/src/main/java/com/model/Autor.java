package com.model;

import org.springframework.hateoas.ResourceSupport;

public class Autor extends ResourceSupport {
	private int idAutor;
	private String nome, sobrenome, nacionalidade, dtNascimento;
	
	
	public Autor(int idAutor, String nome, String sobrenome, String nacionalidade, String dtNascimento) {
		
		this.idAutor = idAutor;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.nacionalidade = nacionalidade;
		this.dtNascimento = dtNascimento;
	}
	
	
	public int getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	} 

}
