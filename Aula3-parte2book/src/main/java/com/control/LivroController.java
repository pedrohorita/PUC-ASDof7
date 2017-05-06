package com.control;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.*;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/livro")
public class LivroController {
	private static List<Autor> autores = AutorController.CriaAutores();
	private static List<Livro> livros = CriaLivros();
	
	
	private static List<Livro> CriaLivros(){
		try {
			List<Livro> livros = new ArrayList<Livro>();
			
			
			Livro l1 = new Livro("0000000000001", "Livro 1", "Descrição Livro 1", 
					null, "PUC", "Descrição livre do Livro 1", Arrays.asList(autores.get(0)) ,
					Arrays.asList("Livro", "1"), 2010, 1, null);
			
			LivroRelacionado lr1 = new LivroRelacionado(l1.getISBN(), l1.getTitulo());
			
			Livro l2 = new Livro("0000000000002", "Livro 2", "Descrição Livro 2", 
					null, "PUC", "Descrição livre do Livro 2", Arrays.asList(autores.get(2),
							autores.get(3)) , Arrays.asList("Livro", "2"), 2015, 
					1, Arrays.asList(lr1));
			
			LivroRelacionado lr2 = new LivroRelacionado(l2.getISBN(), l2.getTitulo());
			
			Livro l3 = new Livro("0000000000003", "Livro 3", "Descrição Livro 3", 
					null, "PUC", "Descrição livre do Livro 3", Arrays.asList(autores.get(0),
							autores.get(1)) , Arrays.asList("Livro", "3"), 
					1999, 1, Arrays.asList(lr1));
			
			LivroRelacionado lr3 = new LivroRelacionado(l3.getISBN(), l3.getTitulo());
			
			l1.setLivrosrelacionados(Arrays.asList(lr2, lr3));
			
			Critica c1 = new Critica(1, "Pessoa 1", "Crítica 1 do livro 1", 7.2, "01/05/2016");
			Critica c2 = new Critica(2, "Pessoa 2", "Crítica 1 do livro 2", 5.7, "30/06/2016");
			Critica c3 = new Critica(3, "Pessoa 3", "Crítica 1 do livro 3", 6.0, "01/05/2016");
			Critica c4 = new Critica(4, "Pessoa 2", "Crítica 2 do livro 1", 8.5, "01/05/2016");
			Critica c5 = new Critica(5, "Pessoa 4", "Crítica 2 do livro 3", 9.0, "01/05/2016");
			Critica c6 = new Critica(6, "Pessoa 5", "Crítica 3 do livro 3", 8.8, "01/05/2016");
			l1.setCriticas(Arrays.asList(c1, c4));
			l2.setCriticas(Arrays.asList(c2));
			l3.setCriticas(Arrays.asList(c3, c5, c6));
			
			livros.add(l1);
			livros.add(l2);
			livros.add(l3);
			
			
			return livros;
		}
		catch (Exception e) {
			return null;
		}
		
		
	}
	
	@ApiOperation(value = "Consulta de livros por título")
	@RequestMapping(method = RequestMethod.GET, value ="/bytitulo")
    public ResponseEntity<List<Livro>> LivroByTitulo(@RequestParam(value="titulo") String titulo) {
		List<Livro> lr = new ArrayList<Livro>();
		for(Livro l : livros){
			if (l.getTitulo().equals(titulo)){
				if (!l.hasLinks()){
					l.add(linkTo(methodOn(LivroController.class).LivroByISBN(l.getISBN())).withSelfRel());
				}
				for(Autor a : l.getAutores()){
					if (!a.hasLinks()){
						a.add(linkTo(methodOn(AutorController.class).getAutorById(a.getIdAutor())).withSelfRel());
					}
				}
				for(LivroRelacionado ls : l.getLivrosrelacionados()){
					if (!ls.hasLinks()){
						ls.add(linkTo(methodOn(LivroController.class).LivroByISBN(ls.getISBN())).withSelfRel());
					}
				}
				for(Critica cl : l.getCriticas()){
					if (!cl.hasLinks()){
						cl.add(linkTo(methodOn(LivroController.class).CriticaLivro(l.getISBN(), 
								cl.getIdCritica())).withSelfRel());
					}
				}
				lr.add(l);
			}
			
			
		}
        return new ResponseEntity<List<Livro>>(lr, HttpStatus.OK);
    }
	
	@ApiOperation(value = "Consulta de livros por autor")
	@RequestMapping(method = RequestMethod.GET, value ="/byautor")
    public ResponseEntity<List<Livro>> LivroByAutor(@RequestParam(value="autorname") String autorName, 
    		@RequestParam(value="autorlastname") String autorlastname ) {
		List<Livro> lr = new ArrayList<Livro>();
		for(Livro l : livros){
			for(Autor a : l.getAutores()){
				if (a.getNome().equals(autorName) && a.getSobrenome().equals(autorlastname) ){
					if (!l.hasLinks()){
						l.add(linkTo(methodOn(LivroController.class).LivroByISBN(l.getISBN())).withSelfRel());
					}
					for(Autor as : l.getAutores()){
						if (!as.hasLinks()){
							as.add(linkTo(methodOn(AutorController.class).getAutorById(as.getIdAutor())).withSelfRel());
						}
					}
					for(Critica cl : l.getCriticas()){
						if (!cl.hasLinks()){
							cl.add(linkTo(methodOn(LivroController.class).CriticaLivro(l.getISBN(), 
									cl.getIdCritica())).withSelfRel());
						}
					}
					for(LivroRelacionado ls : l.getLivrosrelacionados()){
						if (!ls.hasLinks()){
							ls.add(linkTo(methodOn(LivroController.class).LivroByISBN(ls.getISBN())).withSelfRel());
						}
					}
					lr.add(l);
					}
			}
			
			
		}
        return new ResponseEntity<List<Livro>>(lr, HttpStatus.OK);
    }
	
	@ApiOperation(value = "Consulta de livros palavra chave")
	@RequestMapping(method = RequestMethod.GET, value ="/bypalavrachave")
    public ResponseEntity<List<Livro>> LivroByPalavraChave(@RequestParam(value="keyword") String keyword) {
		List<Livro> lr = new ArrayList<Livro>();
		for(Livro l : livros){
			for(String k : l.getPalavraschave()){
				if (k.equals(keyword) ){
					if (!l.hasLinks()){
						l.add(linkTo(methodOn(LivroController.class).LivroByISBN(l.getISBN())).withSelfRel());
					}
					for(Autor as : l.getAutores()){
						if (!as.hasLinks()){
							as.add(linkTo(methodOn(AutorController.class).getAutorById(as.getIdAutor())).withSelfRel());
						}
					}
					for(Critica cl : l.getCriticas()){
						if (!cl.hasLinks()){
							cl.add(linkTo(methodOn(LivroController.class).CriticaLivro(l.getISBN(), 
									cl.getIdCritica())).withSelfRel());
						}
					}
					for(LivroRelacionado ls : l.getLivrosrelacionados()){
						if (!ls.hasLinks()){
							ls.add(linkTo(methodOn(LivroController.class).LivroByISBN(ls.getISBN())).withSelfRel());
						}
					}
					lr.add(l);
					}
			}
			
			
		}
        return new ResponseEntity<List<Livro>>(lr, HttpStatus.OK);
    }
	
	@ApiOperation(value = "Consulta dos dados de livro por ISBN")
	@RequestMapping(method = RequestMethod.GET, value ="/byisbn/{id}")
    public ResponseEntity<Livro> LivroByISBN(@PathVariable("id") String isbn) {
		Livro lr = null;
		for(Livro l : livros){
			if (l.getISBN().equals(isbn)){
				if (!l.hasLinks()){
					l.add(linkTo(methodOn(LivroController.class).LivroByISBN(isbn)).withSelfRel());
				}
				for(Autor a : l.getAutores()){
					if (!a.hasLinks()){
						a.add(linkTo(methodOn(AutorController.class).getAutorById(a.getIdAutor())).withSelfRel());
					}									
				}
				for(Critica cl : l.getCriticas()){
					if (!cl.hasLinks()){
						cl.add(linkTo(methodOn(LivroController.class).CriticaLivro(l.getISBN(), 
								cl.getIdCritica())).withSelfRel());
					}
				}
				for(LivroRelacionado ls : l.getLivrosrelacionados()){
					if (!ls.hasLinks()){
						ls.add(linkTo(methodOn(LivroController.class).LivroByISBN(ls.getISBN())).withSelfRel());
					}
				}
				lr = l;
			}
			
			
		}
        return new ResponseEntity<Livro>(lr, HttpStatus.OK);
    }
	
	@ApiOperation(value = "Consulta de críticas de um livro por ISBN")
	@RequestMapping(method = RequestMethod.GET, value ="/criticasbyisbn")
    public ResponseEntity<List<Critica>> CriticasByISBN(@RequestParam(value="isbn") String isbn) {
		List<Critica> cr = new ArrayList<Critica>();
		for(Livro l : livros) {
			if (l.getISBN().equals(isbn)) {
				for (Critica c : l.getCriticas()){
					if (!c.hasLinks()){
						c.add(linkTo(methodOn(LivroController.class).CriticaLivro(l.getISBN(), 
								c.getIdCritica())).withSelfRel());
					}
					cr.add(c);
				}
			}
		}
				
        return new ResponseEntity<List<Critica>>(cr, HttpStatus.OK);
    }
	
	@ApiOperation(value = "Consulta de crítica de um livro")
	@RequestMapping(method = RequestMethod.GET, value ="/{id}/Critica/{idcritica}")
    public ResponseEntity<Critica> CriticaLivro(@PathVariable("id") String isbn, @PathVariable("idcritica") int idcritica) {
		Critica cr = null;
		for(Livro l : livros){
			if (l.getISBN().equals(isbn)){
				for (Critica c : l.getCriticas()){
					if (c.getIdCritica() == idcritica) {
						if (!c.hasLinks()){
							c.add(linkTo(methodOn(LivroController.class).CriticaLivro(l.getISBN(), 
										c.getIdCritica())).withSelfRel());
						}
						cr = c;
					}
				}
						
			}
		}
				
        return new ResponseEntity<Critica>(cr, HttpStatus.OK);
    }
	
	
	

}
