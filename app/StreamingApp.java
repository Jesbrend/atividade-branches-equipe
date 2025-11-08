package app;

import app.core.entities.*;
import app.core.*;
import app.core.exceptions.*;

public class StreamingApp {

    public static void main(String[] args) {
        Catalogo catalogo = Catalogo.getInstancia();

        // 1. Criação e Adição de Mídias no Catálogo
        System.out.println("--- 1. Cadastro de Mídias ---");
        Midia m1 = new Musica("Bohemian Rhapsody", "Queen", 354, Genero.ROCK);
        Midia m2 = new Musica("Garota de Ipanema", "Tom Jobim", 172, Genero.MPB);
        Midia m3 = new Musica("Thriller", "Michael Jackson", 357, Genero.POP);
        Midia p1 = new Podcast("Xadrez Verbal Diário", "Filipe Figueiredo", 900, Genero.OUTRO);
        Midia a1 = new Audiobook("A Arte da Guerra", "Sun Tzu", 3600, Genero.OUTRO);

        catalogo.adicionarMidia(m1);
        catalogo.adicionarMidia(m2);
        catalogo.adicionarMidia(m3);
        catalogo.adicionarMidia(p1);
        catalogo.adicionarMidia(a1);
        System.out.println("Catálogo inicializado com 5 mídias.");
        
        System.out.println("\n" + m1);
        System.out.println(p1);
        
        // 2. Busca no Catálogo
        System.out.println("\n--- 2. Buscas no Catálogo ---");
        System.out.println("Busca por Artista 'Queen': " + catalogo.buscarPorArtista("Queen"));
        System.out.println("Busca por Gênero 'MPB': " + catalogo.buscarPorGenero(Genero.MPB));
        
        // 3. Cadastro de Usuário
        Usuario user1 = new Usuario("Alice", "alice@email.com");
        System.out.println("\n--- 3. Cadastro de Usuário ---");
        System.out.println("Usuário cadastrado: " + user1);

        // 4. Criação e Gerenciamento de Playlists
        System.out.println("\n--- 4. Criação de Playlists ---");
        Playlist rockPop = user1.criarPlaylist("Rock e Pop");
        Playlist estudo = user1.criarPlaylist("Estudo");
        System.out.println("Playlists criadas: " + user1.getPlaylists().keySet());

        // 5. Adicionar Mídias e Exceções
        System.out.println("\n--- 5. Adicionar Mídias e Tratamento de Exceções ---");
        try {
            user1.adicionarMidiaNaPlaylist("Rock e Pop", m1); // Queen
            user1.adicionarMidiaNaPlaylist("Rock e Pop", m3); // MJ
            user1.adicionarMidiaNaPlaylist("Estudo", a1); // Audiobook
            user1.adicionarMidiaNaPlaylist("Estudo", p1); // Podcast

            // tenta adicionar duplicata
            user1.adicionarMidiaNaPlaylist("Estudo", p1); 
        } catch (MidiaNaoEncontradaException | MidiaJaNaPlaylistException e) {
            System.err.println("Erro Capturado: " + e.getMessage());
        }

        // 6. Visualização e Duração Total
        System.out.println("\n--- 6. Visualização de Playlists e Duração Total ---");
        try {
            Playlist p = user1.getPlaylist("Estudo");
            System.out.println(p);
            
            // 7. Remoção de Mídia
            System.out.println("\n--- 7. Remoção de Mídia ---");
            user1.removerMidiaDaPlaylist("Estudo", a1);
            System.out.println("Mídia removida: " + a1.getTitulo());
            System.out.println("Nova Duração Total de Estudo: " + p.calcularDuracaoTotal() + "s");

            // tenta remover mídia inexistente
            user1.removerMidiaDaPlaylist("Estudo", m2);
        } catch (MidiaNaoEncontradaException e) {
            System.err.println("Erro Capturado: " + e.getMessage());
        }
    }
}