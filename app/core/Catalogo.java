package app.core;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import app.core.entities.Midia;

public class Catalogo {
    private static Catalogo instancia;
    private Set<Midia> midiasDisponiveis;

    private Catalogo() {
        this.midiasDisponiveis = new HashSet<>();
    }

    public static Catalogo getInstancia() {
        if (instancia == null) {
            instancia = new Catalogo();
        }
        return instancia;
    }

    public void adicionarMidia(Midia midia) {
        midiasDisponiveis.add(midia);
    }

    public Set<Midia> buscarPorTitulo(String titulo) {
        return midiasDisponiveis.stream()
                                .filter(m -> m.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                                .collect(Collectors.toSet());
    }

    public Set<Midia> buscarPorArtista(String artista) {
        return midiasDisponiveis.stream()
                                .filter(m -> m.getArtista().toLowerCase().contains(artista.toLowerCase()))
                                .collect(Collectors.toSet());
    }

    public Set<Midia> buscarPorGenero(Genero genero) {
        return midiasDisponiveis.stream()
                                .filter(m -> m.getGenero().equals(genero))
                                .collect(Collectors.toSet());
    }

    public Set<Midia> getTodasMidias() {
        return midiasDisponiveis;
    }
}
