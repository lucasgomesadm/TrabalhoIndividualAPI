package com.serratec.music.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Musica {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @Min(0)
    private Integer minutos;

    @Enumerated(EnumType.STRING)
    private GeneroMusical genero;

    @ManyToMany
    @JoinTable(name = "musica_artista",
        joinColumns = @JoinColumn(name = "musica_id"),
        inverseJoinColumns = @JoinColumn(name = "artista_id"))
    private Set<Artista> artistas = new HashSet<>();

    @ManyToMany(mappedBy = "musicas")
    private Set<Playlist> playlists = new HashSet<>();

    // getters e setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getTitulo(){return titulo;}
    public void setTitulo(String titulo){this.titulo = titulo;}
    public Integer getMinutos(){return minutos;}
    public void setMinutos(Integer minutos){this.minutos = minutos;}
    public GeneroMusical getGenero(){return genero;}
    public void setGenero(GeneroMusical genero){this.genero = genero;}
    public Set<Artista> getArtistas(){return artistas;}
    public void setArtistas(Set<Artista> artistas){this.artistas = artistas;}
    public Set<Playlist> getPlaylists(){return playlists;}
    public void setPlaylists(Set<Playlist> playlists){this.playlists = playlists;}
}
