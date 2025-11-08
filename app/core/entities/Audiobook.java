package app.core.entities;

import app.core.Genero;

public class Audiobook extends Midia {
    public Audiobook(String titulo, String artista, int duracaoEmSegundos, Genero genero) {
        super(titulo, artista, duracaoEmSegundos, genero);
    }

    @Override
    public String getTipo() { return "Audiobook"; }
}