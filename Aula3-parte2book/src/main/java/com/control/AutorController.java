package com.control;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Autor;

@RestController
@RequestMapping("/autor")
public class AutorController {
	private static List<Autor> autores = CriaAutores();
	
	
	public static List<Autor> CriaAutores(){
		List<Autor> autores = new ArrayList<Autor>();
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Autor a1 = new Autor(1, "Autor", "1", "Basileiro", "27/07/1945");
		Autor a2 = new Autor(2, "Autor", "2", "Basileiro", "27/07/1975");
		Autor a3 = new Autor(3, "Autor", "3", "Basileiro", "27/07/1980");
		Autor a4 = new Autor(4, "Autor", "4", "Basileiro", "27/07/1950");
		
		autores.add(a1);
		autores.add(a2);
		autores.add(a3);
		autores.add(a4);
		return autores;
	}
	@RequestMapping("/byid/")
    public ResponseEntity<Autor> getAutorById(@RequestParam(value="id") int idAutor) {
		Autor ar = null;
		for(Autor a : autores){
			if (a.getIdAutor() == idAutor){
				if (!a.hasLinks()){
					a.add(linkTo(methodOn(AutorController.class).getAutorById(a.getIdAutor())).withSelfRel());
				}
				ar = a;
			}
			
			
		}
        return new ResponseEntity<Autor>(ar, HttpStatus.OK);
    }

}
