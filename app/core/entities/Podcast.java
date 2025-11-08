package app.core.entities;

import app.core.enums.Genero;

public class Podcast extends Midia {
    public Podcast(String titulo, String artista, int duracaoEmSegundos, Genero genero) {
        super(titulo, artista, duracaoEmSegundos, genero);
    }

    @Override
    public String getTipo() { return "Podcast"; }
}