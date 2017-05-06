package com.model;

import org.springframework.hateoas.ResourceSupport;

public class LivroRelacionado extends ResourceSupport {
	
	private String ISBN, Titulo;
	
	
	
	public LivroRelacionado(String iSBN, String titulo) {
		ISBN = iSBN;
		Titulo = titulo;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setName(String titulo) {
		Titulo = titulo;
	}

}
