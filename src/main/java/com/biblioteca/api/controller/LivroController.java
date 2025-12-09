package com.biblioteca.api.controller;

import com.biblioteca.api.model.Livro;
import com.biblioteca.api.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    
    private final LivroRepository livroRepository;
    
    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
    
    @PostMapping
    public ResponseEntity<Livro> criarLivro(@Valid @RequestBody Livro livro) {
        Livro novoLivro = livroRepository.save(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
    }
    
    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> livros = livroRepository.findAll();
        return ResponseEntity.ok(livros);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        return livro.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, 
                                                  @Valid @RequestBody Livro livroAtualizado) {
        Optional<Livro> livroExistente = livroRepository.findById(id);
        
        if (livroExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Livro livro = livroExistente.get();
        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());
        livro.setIsbn(livroAtualizado.getIsbn());
        livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
        livro.setDisponivel(livroAtualizado.getDisponivel());
        
        Livro livroSalvo = livroRepository.save(livro);
        return ResponseEntity.ok(livroSalvo);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Livro> atualizarParcialLivro(@PathVariable Long id, 
                                                         @RequestBody Livro livroAtualizado) {
        Optional<Livro> livroExistente = livroRepository.findById(id);
        
        if (livroExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Livro livro = livroExistente.get();
        
        if (livroAtualizado.getTitulo() != null && !livroAtualizado.getTitulo().isBlank()) {
            livro.setTitulo(livroAtualizado.getTitulo());
        }
        if (livroAtualizado.getAutor() != null && !livroAtualizado.getAutor().isBlank()) {
            livro.setAutor(livroAtualizado.getAutor());
        }
        if (livroAtualizado.getIsbn() != null && !livroAtualizado.getIsbn().isBlank()) {
            livro.setIsbn(livroAtualizado.getIsbn());
        }
        if (livroAtualizado.getAnoPublicacao() != null) {
            livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
        }
        if (livroAtualizado.getDisponivel() != null) {
            livro.setDisponivel(livroAtualizado.getDisponivel());
        }
        
        Livro livroSalvo = livroRepository.save(livro);
        return ResponseEntity.ok(livroSalvo);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        
        if (livro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        livroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
