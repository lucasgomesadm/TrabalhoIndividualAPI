package com.serratec.music.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

@Entity
public class Perfil {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String telefone;

    private LocalDate dataNascimento;

    @OneToOne(mappedBy = "perfil")
    private Usuario usuario;

    // getters e setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getTelefone(){return telefone;}
    public void setTelefone(String telefone){this.telefone = telefone;}
    public LocalDate getDataNascimento(){return dataNascimento;}
    public void setDataNascimento(LocalDate dataNascimento){this.dataNascimento = dataNascimento;}
    public Usuario getUsuario(){return usuario;}
    public void setUsuario(Usuario usuario){this.usuario = usuario;}
}
