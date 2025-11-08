package app.core.entities;

import app.core.enums.Genero;

public abstract class Midia {
    private String titulo;
    private String artista;
    private int duracaoEmSegundos;
    private Genero genero;

    public Midia(String titulo, String artista, int duracaoEmSegundos, Genero genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoEmSegundos = duracaoEmSegundos;
        this.genero = genero;
    }

    public abstract String getTipo();

    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public int getDuracaoEmSegundos() { return duracaoEmSegundos; }
    public Genero getGenero() { return genero; }

    @Override
    public String toString() {
        return getTipo() + " [Título: " + titulo + ", Artista: " + artista + ", Duração: " + duracaoEmSegundos + "s, Gênero: " + genero + "]";
    }
}