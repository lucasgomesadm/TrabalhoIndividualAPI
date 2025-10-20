package com.serratec.music.controller;
import com.serratec.music.domain.Musica;
import com.serratec.music.domain.Artista;
import com.serratec.music.repository.MusicaRepository;
import com.serratec.music.repository.ArtistaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/musicas")
public class MusicaController {
    private final MusicaRepository repo;
    private final ArtistaRepository artistaRepo;
    public MusicaController(MusicaRepository repo, ArtistaRepository artistaRepo){this.repo = repo; this.artistaRepo = artistaRepo;}

    @GetMapping public List<Musica> all(){return repo.findAll();}
    @GetMapping("/{id}") public ResponseEntity<Musica> get(@PathVariable Long id){ return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    public static record ArtistaIdsWrapper(Set<Long> artistaIds) {}

    @PostMapping
    public ResponseEntity<Musica> create(@Valid @RequestBody Musica musica){
        Musica saved = repo.save(musica);
        return ResponseEntity.created(URI.create("/musicas/"+saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musica> update(@PathVariable Long id, @Valid @RequestBody Musica musica){
        return repo.findById(id).map(existing -> {
            existing.setTitulo(musica.getTitulo());
            existing.setMinutos(musica.getMinutos());
            existing.setGenero(musica.getGenero());
           
            if (musica.getArtistas() != null && !musica.getArtistas().isEmpty()) {
                Set<Artista> artistas = musica.getArtistas().stream()
                        .map(a -> artistaRepo.findById(a.getId()).orElse(null))
                        .filter(java.util.Objects::nonNull)
                        .collect(Collectors.toSet());
                existing.setArtistas(artistas);
            }
            return ResponseEntity.ok(repo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return repo.findById(id).map(m->{repo.delete(m); return ResponseEntity.noContent().<Void>build();}).orElse(ResponseEntity.notFound().build());
    }
}
