package org.serratec.backend.controller;

import org.serratec.backend.dto.IsbnResponseDTO;
import org.serratec.backend.service.IsbnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/isbn")
public class IsbnController {

	@Autowired
    private IsbnService service;
	
    @GetMapping("/{isbn}")
	public ResponseEntity<IsbnResponseDTO> buscarLivro(@PathVariable String isbn){
		var livro = service.buscarLivroPorIsbn(isbn);
		
		if(livro != null) {
			return ResponseEntity.ok(livro);
		}
		return ResponseEntity.notFound().build();
	}
    
}
