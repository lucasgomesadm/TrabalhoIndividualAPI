package com.serratec.music.controller;
import com.serratec.music.domain.Playlist;
import com.serratec.music.domain.Musica;
import com.serratec.music.domain.Usuario;
import com.serratec.music.repository.PlaylistRepository;
import com.serratec.music.repository.MusicaRepository;
import com.serratec.music.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    private final PlaylistRepository repo;
    private final UsuarioRepository usuarioRepo;
    private final MusicaRepository musicaRepo;
    public PlaylistController(PlaylistRepository repo, UsuarioRepository usuarioRepo, MusicaRepository musicaRepo){
        this.repo = repo; this.usuarioRepo = usuarioRepo; this.musicaRepo = musicaRepo;
    }

    @GetMapping public List<Playlist> all(){return repo.findAll();}
    @GetMapping("/{id}") public ResponseEntity<Playlist> get(@PathVariable Long id){ return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }


    public static record PlaylistCreate(String nome, String descricao, Long usuarioId, Set<Long> musicaIds) {}

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PlaylistCreate input){
        Usuario u = usuarioRepo.findById(input.usuarioId()).orElse(null);
        if (u == null) return ResponseEntity.badRequest().body("Usuário não encontrado com id: " + input.usuarioId());
        Playlist p = new Playlist();
        p.setNome(input.nome());
        p.setDescricao(input.descricao());
        p.setUsuario(u);
        if (input.musicaIds() != null && !input.musicaIds().isEmpty()){
            Set<Musica> musicas = input.musicaIds().stream().map(id -> musicaRepo.findById(id).orElse(null)).filter(java.util.Objects::nonNull).collect(Collectors.toSet());
            p.setMusicas(musicas);
        }
        Playlist saved = repo.save(p);
        return ResponseEntity.created(URI.create("/playlists/"+saved.getId())).body(saved);
    }

    
    public static record PlaylistUpdate(String nome, String descricao, Set<Long> musicaIds) {}

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PlaylistUpdate input){
        return repo.findById(id).map(existing -> {
            if (input.nome() != null) existing.setNome(input.nome());
            if (input.descricao() != null) existing.setDescricao(input.descricao());
            if (input.musicaIds() != null){
                Set<Musica> musicas = input.musicaIds().stream().map(mid -> musicaRepo.findById(mid).orElse(null)).filter(java.util.Objects::nonNull).collect(Collectors.toSet());
                existing.setMusicas(musicas);
            }
            return ResponseEntity.ok(repo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ return repo.findById(id).map(p->{repo.delete(p); return ResponseEntity.noContent().<Void>build();}).orElse(ResponseEntity.notFound().build()); }
}
