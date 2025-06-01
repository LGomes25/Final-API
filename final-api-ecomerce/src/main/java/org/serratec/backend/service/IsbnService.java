package org.serratec.backend.service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.backend.dto.IsbnResponseDTO;
import org.serratec.backend.dto.OpenLibraryResponseDTO;
import org.serratec.backend.entity.Isbn;
import org.serratec.backend.exception.IsbnException;
import org.serratec.backend.repository.IsbnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IsbnService {
    
    @Autowired
    private IsbnRepository repository;
    
    public IsbnResponseDTO buscarLivroPorIsbn(String isbn) {
        
        var lv = Optional.ofNullable(repository.findByIsbn(isbn));
        
        if(lv.isPresent()) {
            return new IsbnResponseDTO(lv.get());
        } else {
            RestTemplate rs = new RestTemplate();
            String url = "https://openlibrary.org/isbn/" + isbn + ".json";

            Optional<OpenLibraryResponseDTO> livroOpenlibrary = Optional.ofNullable(
                    rs.getForObject(url, OpenLibraryResponseDTO.class)
            );
            
            if (livroOpenlibrary.isPresent()) {
                OpenLibraryResponseDTO livroAPI = livroOpenlibrary.get();
                
                Isbn livro = new Isbn();
                livro.setIsbn(isbn);
                livro.setTitulo(livroAPI.getTitle());
                livro.setDescricao(extrairDescricao(livroAPI));
                livro.setNumeroPaginas(livroAPI.getNumberOfPages() != null ? livroAPI.getNumberOfPages() : "Número de páginas não fornecido");
                livro.setDataPublicacao(livroAPI.getPublishDate() != null ? livroAPI.getPublishDate() : "Data da publicação não fornecida");
                livro.setEditoras(livroAPI.getPublishers() != null ? String.join(", ", livroAPI.getPublishers()) : "Editora(s) não fornecida");
                livro.setAutores(extrairAutores(livroAPI));
                livro.setUrlCapa("https://covers.openlibrary.org/b/isbn/" + isbn + "-L.jpg");

                return inserir(livro);
            }
        }
        throw new IsbnException("Livro não encontrado para ISBN: " + isbn);
    }
    
    private IsbnResponseDTO inserir(Isbn isbn) {
        return new IsbnResponseDTO(repository.save(isbn));
    }

    
    private String extrairAutores(OpenLibraryResponseDTO livroAPI) {
        if (livroAPI.getAuthors() == null) return "Autor(es) não fornecido";
        return livroAPI.getAuthors().stream()
                .map(a -> a.get("key").replace("/authors/", ""))
                .collect(Collectors.joining(", "));
    }

    private String extrairDescricao(OpenLibraryResponseDTO livroAPI) {
        if (livroAPI.getDescription() == null) return "Descrição não fornecida";
        //se for um string
        if (livroAPI.getDescription() instanceof String) {
            return (String) livroAPI.getDescription();
        }
        //se for um objeto
        if (livroAPI.getDescription() instanceof Map) {
            Map<?, ?> desc = (Map<?, ?>) livroAPI.getDescription();
            return (String) desc.get("value");
        }
        return null;
    }
}
