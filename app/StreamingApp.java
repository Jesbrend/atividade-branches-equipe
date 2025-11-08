package app;

import app.core.entities.*;
import app.core.enums.Genero;
import app.core.exceptions.*;
import app.core.repositories.*;
import app.core.services.UsuarioService;

public class StreamingApp {

    public static void main(String[] args) {
        Catalogo catalogo = Catalogo.getInstancia();
        UsuarioService usuarioService = new UsuarioService();

        // 1. Criação e Adição de Mídias no Catálogo
        System.out.println("--- 1. Cadastro de Mídias ---");
        Midia m1 = new Musica("Bohemian Rhapsody", "Queen", 354, Genero.ROCK);
        Midia m2 = new Musica("Garota de Ipanema", "Tom Jobim", 172, Genero.MPB);
        Midia m3 = new Musica("Thriller", "Michael Jackson", 357, Genero.POP);
        Midia m4 = new Musica("Fantasy", "JADE", 216, Genero.POP);
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
        System.out.println("Busca por Gênero 'MPB': " + catalogo.buscarPorGenero(Genero.POP));
        
        // 3. Cadastro de Usuários
        System.out.println("\n--- 3. Cadastro de Usuário e Tratamento de Exceções---");
        try{
            Usuario user1 = usuarioService.cadastrarUsuario("Alice", "alice@email.com");
            System.out.println("Usuário cadastrado: " + user1);
            
            Usuario user2 = usuarioService.cadastrarUsuario("Lucas", "lucas@email.com");
            System.out.println("Usuário cadastrado: " + user2);
                
            try{
                // tenta adicionar usuário já existente
                Usuario user3 = usuarioService.cadastrarUsuario("Alice", "alice@email.com");
                System.out.println("Usuário cadastrado: " + user3);
            } catch (UsuarioJaCadastradoException e){
                System.out.println("Erro Capturado:" + e.getMessage());
            }

            // 4. Criação e Gerenciamento de Playlists
            System.out.println("\n--- 4. Criação de Playlists ---");
            Playlist rockPop = usuarioService.criarPlaylist(user1.getEmail(), "Rock e Pop");
            Playlist estudo = usuarioService.criarPlaylist(user1.getEmail(), "Estudo");
            System.out.println("Playlists criadas: " + rockPop + "\n" + estudo);

            // 5. Adicionar Mídias e Exceções
            System.out.println("\n--- 5. Adicionar Mídias e Tratamento de Exceções ---");
            usuarioService.adicionarMidiaNaPlaylist(user1.getEmail(), "Rock e Pop", m1); // Queen
            usuarioService.adicionarMidiaNaPlaylist(user1.getEmail(), "Rock e Pop", m3); // MJ
            usuarioService.adicionarMidiaNaPlaylist(user1.getEmail(), "Estudo", a1); // Audiobook
            usuarioService.adicionarMidiaNaPlaylist(user1.getEmail(), "Estudo", p1); // Podcast

            // tenta adicionar em playlist não existente
            try{
                usuarioService.adicionarMidiaNaPlaylist(user2.getEmail(), "POP", m4); // JADE
            } catch(MidiaNaoEncontradaException e){
                System.out.println("Erro Capturado:" + e.getMessage());
            }

            // tenta adicionar duplicata
            try{
            usuarioService.adicionarMidiaNaPlaylist(user1.getEmail(), "Estudo", p1);
            } catch(MidiaJaNaPlaylistException e){
                System.out.println("Erro Capturado:" + e.getMessage());
            }

            // 6. Visualização e Duração Total
            System.out.println("\n--- 6. Visualização de Playlists e Duração Total ---");
            Playlist p = usuarioService.getPlaylist(user1.getEmail(), "Estudo");
            System.out.println(p);
                
            // 7. Remoção de Mídia
            System.out.println("\n--- 7. Remoção de Mídia ---");
            usuarioService.removerMidiaDaPlaylist(user1.getEmail(), "Estudo", a1);
            System.out.println("Mídia removida: " + a1.getTitulo());
            System.out.println("Nova Duração Total de Estudo: " + p.calcularDuracaoTotal() + "s");

            // tenta remover mídia inexistente
            try{
            usuarioService.removerMidiaDaPlaylist(user1.getEmail(), "Estudo", m2);
            } catch(MidiaNaoEncontradaException e){
                System.out.println("Erro Capturado:" + e.getMessage());
            }

        } catch (UsuarioJaCadastradoException | MidiaNaoEncontradaException | MidiaJaNaPlaylistException e) {
            System.err.println("Erro Capturado: " + e.getMessage());
        }
    }
}