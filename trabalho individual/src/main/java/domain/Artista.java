package com.serratec.music.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Artista {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String nacionalidade;

    @ManyToMany(mappedBy = "artistas")
    private Set<Musica> musicas = new HashSet<>();

    // getters e setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}
    public String getNacionalidade(){return nacionalidade;}
    public void setNacionalidade(String nacionalidade){this.nacionalidade = nacionalidade;}
    public Set<Musica> getMusicas(){return musicas;}
    public void setMusicas(Set<Musica> musicas){this.musicas = musicas;}
}
