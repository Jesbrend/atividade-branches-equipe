package app.core.services;

import app.core.entities.Midia;
import app.core.entities.Playlist;
import app.core.entities.Usuario;
import app.core.exceptions.MidiaJaNaPlaylistException;
import app.core.exceptions.MidiaNaoEncontradaException;
import app.core.exceptions.UsuarioJaCadastradoException;
import app.core.repositories.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository usuarioRepositorio;

    public UsuarioService() {
        this.usuarioRepositorio = UsuarioRepository.getInstancia();
    }

    public Usuario cadastrarUsuario(String nome, String email) throws UsuarioJaCadastradoException {
        if (this.usuarioRepositorio.contains(email)) {
            throw new UsuarioJaCadastradoException("E-mail já cadastrado!");
        }
        Usuario novoUsuario = new Usuario(nome, email);
        return this.usuarioRepositorio.adicionarUsuario(novoUsuario);
    }

    public Playlist criarPlaylist(String email, String nome) {
        Usuario usuario = this.usuarioRepositorio.getUsuario(email);
        return usuario.criarPlaylist(nome);
    }

    public void adicionarMidiaNaPlaylist(String email, String playlist, Midia midia) throws MidiaNaoEncontradaException, MidiaJaNaPlaylistException {
        Usuario usuario = this.usuarioRepositorio.getUsuario(email);
        usuario.adicionarMidiaNaPlaylist(playlist, midia);
    }

    public Playlist getPlaylist(String email, String playlist) throws MidiaNaoEncontradaException {
        return this.usuarioRepositorio.getUsuario(email).getPlaylist(playlist);
    }

    public void removerMidiaDaPlaylist(String email, String playlist, Midia midia) throws MidiaNaoEncontradaException {
        Usuario usuario = this.usuarioRepositorio.getUsuario(email);
        usuario.removerMidiaDaPlaylist(playlist, midia);
    }
}