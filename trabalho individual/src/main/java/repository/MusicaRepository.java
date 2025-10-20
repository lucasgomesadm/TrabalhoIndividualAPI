package com.serratec.music.repository;
import com.serratec.music.domain.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MusicaRepository extends JpaRepository<Musica, Long> {
}
