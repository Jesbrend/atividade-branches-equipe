package app.core.entities;

import app.core.enums.Genero;

public class Musica extends Midia {
    public Musica(String titulo, String artista, int duracaoEmSegundos, Genero genero) {
        super(titulo, artista, duracaoEmSegundos, genero);
    }

    @Override
    public String getTipo() { return "Música"; }
}