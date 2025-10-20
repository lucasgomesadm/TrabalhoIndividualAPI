package com.serratec.music.repository;
import com.serratec.music.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
