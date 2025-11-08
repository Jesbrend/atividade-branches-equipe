package app.core.entities;

import java.util.ArrayList;
import java.util.List;

import app.core.exceptions.MidiaJaNaPlaylistException;
import app.core.exceptions.MidiaNaoEncontradaException;

public class Playlist {
    private String nome;
    private Usuario proprietario;
    // Lista de Mídias
    private List<Midia> midias;

    public Playlist(String nome, Usuario proprietario) {
        this.nome = nome;
        this.proprietario = proprietario;
        this.midias = new ArrayList<>();
    }

    public void adicionarMidia(Midia midia) throws MidiaJaNaPlaylistException {
        // evitar duplicatas
        if (midias.contains(midia)) {
            throw new MidiaJaNaPlaylistException("A mídia '" + midia.getTitulo() + "' já está na playlist '" + nome + "'.");
        }
        midias.add(midia);
    }

    public void removerMidia(Midia midia) throws MidiaNaoEncontradaException {
        if (!midias.remove(midia)) {
            throw new MidiaNaoEncontradaException("A mídia '" + midia.getTitulo() + "' não foi encontrada na playlist '" + nome + "'.");
        }
    }

    public int calcularDuracaoTotal() {
        return midias.stream()
                     .mapToInt(Midia::getDuracaoEmSegundos)
                     .sum();
    }

    public String getNome() { return nome; }
    public List<Midia> getMidias() { return midias; }

    @Override
    public String toString() {
        return "\n--- Playlist: " + nome + " (Proprietário: " + proprietario.getNome() + ") ---\n" +
               "Duração Total: " + calcularDuracaoTotal() + " segundos\n" +
               "Mídias: " + midias.size() + "\n" +
               midias;
    }
}
