package app.core.entities;

import java.util.HashMap;
import java.util.Map;

import app.core.exceptions.MidiaJaNaPlaylistException;
import app.core.exceptions.MidiaNaoEncontradaException;

public class Usuario {
    private String nome;
    private String email;

    //Map para Playlists (chave: nome da playlist)
    private Map<String, Playlist> playlists;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.playlists = new HashMap<>();
    }

    public Playlist criarPlaylist(String nomePlaylist) {
        Playlist novaPlaylist = new Playlist(nomePlaylist, this);
        playlists.put(nomePlaylist.toLowerCase(), novaPlaylist);
        return novaPlaylist;
    }

    public Playlist getPlaylist(String nomePlaylist) throws MidiaNaoEncontradaException {
        Playlist p = playlists.get(nomePlaylist.toLowerCase());
        if (p == null) {
            throw new MidiaNaoEncontradaException("Playlist '" + nomePlaylist + "' não encontrada para o usuário " + nome + ".");
        }
        return p;
    }
    
    public void adicionarMidiaNaPlaylist(String nomePlaylist, Midia midia) throws MidiaNaoEncontradaException, MidiaJaNaPlaylistException {
        Playlist p = getPlaylist(nomePlaylist);
        p.adicionarMidia(midia);
    }

    public void removerMidiaDaPlaylist(String nomePlaylist, Midia midia) throws MidiaNaoEncontradaException {
        Playlist p = getPlaylist(nomePlaylist);
        p.removerMidia(midia);
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public Map<String, Playlist> getPlaylists() { return playlists; }

    @Override
    public String toString() {
        return "Usuário [Nome: " + nome + ", E-mail: " + email + ", Playlists: " + playlists.keySet() + "]";
    }
}