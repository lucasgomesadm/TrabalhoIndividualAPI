package com.serratec.music.repository;
import com.serratec.music.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
