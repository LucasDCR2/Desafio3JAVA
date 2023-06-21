import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String descricao;
    private List<Music> musicas;


    public Playlist(String descricao) {
        this.descricao = descricao;
        this.musicas = new ArrayList<>();
    }


    public String getDescricao() {
        return descricao;
    }

    public List<Music> getMusicas() {
        return musicas;
    }

    public void adicionarMusica(Music musica) {
        musicas.add(musica);
    }

    public void removerMusica(Music musica) {
        musicas.remove(musica);
    }

    public int duracaoTotal() {
        int duracaoTotal = 0;
        for (Music musica : musicas) {
            duracaoTotal += musica.getDuracao();
        } 
        return duracaoTotal;
    }

    public List<Music> geMusics() {
        return musicas;
    }
}
