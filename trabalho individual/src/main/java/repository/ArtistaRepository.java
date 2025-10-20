package com.serratec.music.repository;
import com.serratec.music.domain.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
