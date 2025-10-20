package com.serratec.music.controller;
import com.serratec.music.domain.Artista;
import com.serratec.music.repository.ArtistaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {
    private final ArtistaRepository repo;
    public ArtistaController(ArtistaRepository repo){this.repo = repo;}
    @GetMapping public List<Artista> all(){return repo.findAll();}
    @GetMapping("/{id}") public ResponseEntity<Artista> get(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public ResponseEntity<Artista> create(@Valid @RequestBody Artista artista){
        Artista s = repo.save(artista);
        return ResponseEntity.created(URI.create("/artistas/"+s.getId())).body(s);
    }
    @PutMapping("/{id}") public ResponseEntity<Artista> update(@PathVariable Long id, @Valid @RequestBody Artista artista){
        return repo.findById(id).map(existing->{existing.setNome(artista.getNome()); existing.setNacionalidade(artista.getNacionalidade()); return ResponseEntity.ok(repo.save(existing));}).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ return repo.findById(id).map(a->{repo.delete(a); return ResponseEntity.noContent().<Void>build();}).orElse(ResponseEntity.notFound().build()); }
}
