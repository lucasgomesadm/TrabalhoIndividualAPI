
**Aluno:** Lucas Gomes dos Santos  
**Disciplina:** API  - Serratec Music (Trabalho Individual)

## Descrição
Projeto Spring Boot que implementa a API RESTful solicitada no trabalho prático. Implementa as entidades: Usuario, Perfil, Artista, Musica e Playlist com os relacionamentos:
- Usuario 1:1 Perfil
- Usuario 1:N Playlist
- Musica N:M Artista (tabela musica_artista)
- Playlist N:M Musica (tabela playlist_musica)

## Como executar
1. Java 17 e Maven 
2. Configurar banco PostgreSQL local com um banco `serratecdb` e atualizar `src/main/resources/application.properties` com usuário/senha.

## Endpoints principais
- /usuarios
- /artistas
- /musicas
- /playlists
