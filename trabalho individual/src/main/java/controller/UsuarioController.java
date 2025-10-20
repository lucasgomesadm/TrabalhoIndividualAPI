package com.serratec.music.controller;

import com.serratec.music.domain.Usuario;
import com.serratec.music.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository repo;
    public UsuarioController(UsuarioRepository repo){this.repo = repo;}

    @GetMapping
    public List<Usuario> all(){return repo.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> get(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

   
    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario usuario){
        Usuario saved = repo.save(usuario);
        return ResponseEntity.created(URI.create("/usuarios/"+saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @Valid @RequestBody Usuario usuario){
        return repo.findById(id).map(existing -> {
            existing.setNome(usuario.getNome());
            existing.setEmail(usuario.getEmail());
            existing.setPerfil(usuario.getPerfil());
            Usuario s = repo.save(existing);
            return ResponseEntity.ok(s);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return repo.findById(id).map(u -> {repo.delete(u); return ResponseEntity.noContent().<Void>build();})
                .orElse(ResponseEntity.notFound().build());
    }
}
