import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciadorMusicPlaylist {
    private List<Music> biblioteca;
    private List<Playlist> playlists;


    public GerenciadorMusicPlaylist() {
        this.biblioteca = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    public List<Music> getBiblioteca() {
        return biblioteca;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void cadastrarMusica(String nome, String artista, int duracao) {
        Music musica = new Music (nome, artista, duracao); 
        biblioteca.add(musica);
    }

    public void criarPlaylist(String descricao) {
        Playlist playlist = new Playlist(descricao);
        playlists.add(playlist);
    }

    public void adicionarMusicaPlaylist(Playlist playlist, Music musica) {
        if (biblioteca.contains(musica) && !playlist.getMusicas().contains(musica)) {
            playlist.adicionarMusica(musica);
            System.out.println("Musica adicionada a playlist com sucesso!");
        } else {
            System.out.println("A musica não está disponível na biblioteca ou já foi adicionada à playlist.");
        }
    }
    
    public void removerMusicaPlaylist(Playlist playlist, Music musica) {
        if (playlist.getMusicas().contains(musica)) {
            playlist.removerMusica(musica);
            System.out.println("Musica removida da playlist com sucesso!");
        } else {
            System.out.println("A musica não está presente na playlist.");
        }
    }

    public void juntarPlaylists(Playlist playlist1, Playlist playlist2, String novaDescricao) {
        if (!playlists.contains(playlist1) || !playlists.contains(playlist2)) {
            System.out.println("Uma ou ambas as playlists nao existem.");
            return;
        }

        Playlist novaPlaylist = new Playlist(novaDescricao);
        novaPlaylist.getMusicas().addAll(playlist1.getMusicas());
        novaPlaylist.getMusicas().addAll(playlist2.getMusicas());
        playlists.add(novaPlaylist);
        System.out.println("Playlists juntadas com sucesso!");
    }

    public void reproduzirPlaylist(Playlist playlist, boolean randomica) {
        if (playlist.getMusicas().isEmpty()) {
            System.out.println("A playlist esta vazia. Adicione musicas antes de reproduzi-la.");
            return;
        }

        List<Music> musicas = playlist.getMusicas();
        if (randomica) {
            List<Music> musicasRandom = new ArrayList<>(musicas);
            Random random = new Random();
            while (!musicasRandom.isEmpty()) {
                int index = random.nextInt(musicasRandom.size());
                Music musica = musicasRandom.remove(index);
                reproduzirMusica(musica);
            }
        } else {
            for (Music musica : musicas) {
                reproduzirMusica(musica);
            }
        }
    }

    public void reproduzirMusica(Music musica) {
        System.out.println("Reproduzindo: " + musica.getNome() + " - " + musica.getArtista());
    }
}
