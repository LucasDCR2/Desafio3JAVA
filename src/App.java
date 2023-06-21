import java.util.List;
import java.util.Scanner;

public class App {
    private static GerenciadorMusicPlaylist gerenciador;
    private static Scanner scanner;

    public static void main(String[] args) {
        gerenciador = new GerenciadorMusicPlaylist();
        scanner = new Scanner(System.in);

        executarPrograma();
    }

    //===========================================================<Main>=============================================================//

    public static void executarPrograma() {
        while (true) {
            exibirMenu();

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de entrada

            switch (opcao) {
                case 1:
                    cadastrarMusica();
                    break;

                case 2:
                    criarPlaylist();
                    break;

                case 3:
                    adicionarMusicaPlaylist();
                    break;

                case 4:
                    juntarPlaylists();
                    break;

                case 5:
                    reproduzirPlaylist();
                    break;

                case 6:
                    exibirMusicas();
                    break;

                case 7: 
                    exibirPlaylists();
                    break;
                

                default:
                    System.out.println("Opcao invalida! Por favor, digite novamente.");
                    break;
            }

            System.out.println();
        }
    }

    //=============================================================<Exibir Menu>=================================================================//

    public static void exibirMenu() {
        System.out.println();
        System.out.println("       O que voce deseja fazer?         ");
        System.out.println("----------------------------------------");
        System.out.println("|  1. Cadastrar musica                 |");
        System.out.println("|  2. Criar playlist                   |");
        System.out.println("|  3. Adicionar musica a playlist      |");
        System.out.println("|  4. Juntar playlists                 |");
        System.out.println("|  5. Reproduzir playlist              |");
        System.out.println("|  6. Exibir Musicas na Biblioteca     |");
        System.out.println("|  7. Exibir Playlists na Biblioteca   |");
        System.out.println("----------------------------------------");
        System.out.println();
    }

    //===========================================================<Cadastrar Músicas>=============================================================//

    public static void cadastrarMusica() {
        System.out.println("Digite o titulo da musica:");
        String titulo = scanner.nextLine();

        System.out.println("Digite o nome do artista:");
        String artista = scanner.nextLine();

        System.out.println("Digite a duracao da musica em segundos:");
        int duracao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        gerenciador.cadastrarMusica(titulo, artista, duracao);
        System.out.println("Musica cadastrada com sucesso!");
        System.out.println();
    }

    //============================================================<Criar Playlist>==============================================================//

    public static void criarPlaylist() {
        System.out.println("Digite a descricao da playlist:");
        String descricao = scanner.nextLine();

        gerenciador.criarPlaylist(descricao);
        System.out.println("Playlist criada com sucesso!");
    }

    //=========================================================<Add Música Playlist>=============================================================//

    public static void adicionarMusicaPlaylist() {
        System.out.println("Digite o numero da playlist:");
        int playlistIndex = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        System.out.println("Digite o numero da musica:");
        int musicaIndex = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        Playlist playlist = gerenciador.getPlaylists().get(playlistIndex);
        Music musica = gerenciador.getBiblioteca().get(musicaIndex);

        gerenciador.adicionarMusicaPlaylist(playlist, musica);
    }

//==============================================================<Juntar Playlist>===============================================================//

    public static void juntarPlaylists() {
        System.out.println("Digite o numero da primeira playlist:");
        int playlist1Index = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        System.out.println("Digite o numero da segunda playlist:");
        int playlist2Index = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        System.out.println("Digite a descricao da nova playlist:");
        String novaDescricao = scanner.nextLine();

        Playlist playlist1 = gerenciador.getPlaylists().get(playlist1Index);
        Playlist playlist2 = gerenciador.getPlaylists().get(playlist2Index);

        gerenciador.juntarPlaylists(playlist1, playlist2, novaDescricao);
        System.out.println("Playlists juntadas com sucesso!");
    }

//===========================================================<Reproduzir Playlist>==============================================================//

    public static void reproduzirPlaylist() {
        System.out.println("Digite o numero da playlist:");
        int playlistIndex = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        System.out.println("Deseja reproduzir na ordem original (1) ou aleatoriamente (2)?");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        Playlist playlist = gerenciador.getPlaylists().get(playlistIndex);

        boolean reproducaoAleatoria = (opcao == 2);
        gerenciador.reproduzirPlaylist(playlist, reproducaoAleatoria);
    }

    //===========================================================<Exibir Musicas>===============================================================//

    public static void exibirMusicas() {
        System.out.println();
        System.out.println("----- Musicas Disponiveis -----");
        System.out.println();
        List<Music> musicas = gerenciador.getBiblioteca();
        if (musicas.isEmpty()) {
            System.out.println("   Nenhuma musica cadastrada");
        } else { 
            for (int i = 0; i < musicas.size(); i++) {
                Music musica = musicas.get(i);
                System.out.println(i + ". " + musica.getNome() + " - " + musica.getArtista() + " - Duracao: " + musica.getDuracao() + " seg " );
            }
        }
        System.out.println();
    }

    //===========================================================<Exibir Playlists>=============================================================//

    public static void exibirPlaylists() {
        System.out.println();
        System.out.println("----- Playlists Disponiveis -----");
        System.out.println();
        List<Playlist> playlists = gerenciador.getPlaylists();
        if (playlists.isEmpty()) {
            System.out.println("     Nenhuma playlist criada");
        } else { 
            for (int i = 0; i < playlists.size(); i++) {
                Playlist playlist = playlists.get(i);
                int quantidadeMusicas = playlist.getMusicas().size();
                int duracaoTotal = playlist.duracaoTotal();
                int duracaoMinutos = duracaoTotal / 60;
                int duracaoSegundos = duracaoTotal % 60;
                System.out.println(i + ". " + "Playlist - " +playlist.getDescricao() + " - " + quantidadeMusicas + " musicas, Duracao: " + duracaoMinutos + " min " + duracaoSegundos + " seg");
            }
        }
        System.out.println();
    }
}

    //====================================================================<Fim>==================================================================//
