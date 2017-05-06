package com.model;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;



public class Livro extends ResourceSupport {
	private String ISBN, Titulo, Descricao, Editora, Descricaolivro;
	private List<Autor> Autores;
	private List<String> Palavraschave;
	private List<Critica> Criticas;
	private int AnoPublicacao, Edicao;
	private List<LivroRelacionado> Livrosrelacionados;
	
	
	
	
	public Livro(String iSBN, String titulo, String descricao, List<Critica> criticas, String editora, String descricaolivro,
			List<Autor> autores, List<String> palavraschave, int anoPublicacao, int edicao,
			List<LivroRelacionado> livrosrelacionados) {
		
		if (iSBN.length() != 13 || autores.size() < 1 ) {
			
		}
		else { 
			ISBN = iSBN;
			Titulo = titulo;
			Descricao = descricao;
			Criticas = criticas;
			Editora = editora;
			Descricaolivro = descricaolivro;
			Autores = autores;
			Palavraschave = palavraschave;
			AnoPublicacao = anoPublicacao;
			Edicao = edicao;
			Livrosrelacionados = livrosrelacionados;
		}
	}
	public String getISBN() {
		return ISBN;
	}
	
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public List<Critica> getCriticas() {
		return Criticas;
	}
	public void setCriticas(List<Critica> criticas) {
		Criticas = criticas;
	}
	public String getEditoria() {
		return Editora;
	}
	public void setEditoria(String editoria) {
		Editora = editoria;
	}
	public String getDescricaolivro() {
		return Descricaolivro;
	}
	public void setDescricaolivro(String descricaolivro) {
		Descricaolivro = descricaolivro;
	}
	public List<Autor> getAutores() {
		return Autores;
	}
	public void setAutores(List<Autor> autores) {
		if (autores.size() < 1) {
		Autores = autores;
		}
		else {
			
		}
	}
	public List<String> getPalavraschave() {
		return Palavraschave;
	}
	public void setPalavraschave(List<String> palavraschave) {
		Palavraschave = palavraschave;
	}
	public int getAnoPublicacao() {
		return AnoPublicacao;
	}
	public void setAnoPublicacao(int anoPublicacao) {
		AnoPublicacao = anoPublicacao;
	}
	public int getEdicao() {
		return Edicao;
	}
	public void setEdicao(int edicao) {
		Edicao = edicao;
	}
	public List<LivroRelacionado> getLivrosrelacionados() {
		return Livrosrelacionados;
	}
	public void setLivrosrelacionados(List<LivroRelacionado> livrosrelacionados) {
		Livrosrelacionados = livrosrelacionados;
	}

}
