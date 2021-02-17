package zombie.deliziusz.audiolibros;

import zombie.deliziusz.audiolibros.R;
import java.util.Vector;

public class Libro {
    public String titulo;
    public String autor;
    public int recursoImagen;
    public String urlAudio;
    public String genero;
    public Boolean novedad;
    public Boolean leido;
    public static final String G_TODOS = "Todos los generos";
    public static final String G_EPICO = "Poema epico";
    public static final String G_S_XIX = "Literatura del siglo XIX";
    public static final String G_SUSPENSE = "Suspense";
    public static final String[] G_ARRAY = new String[] {
        G_TODOS, G_EPICO, G_S_XIX, G_SUSPENSE
    };

    public Libro(String titulo, String autor, int recursoImagen,
        String urlAudio, String genero, Boolean novedad, Boolean leido) {
        this.titulo = titulo;
        this.autor = autor;
        this.recursoImagen = recursoImagen;
        this.urlAudio = urlAudio;
        this.genero = genero;
        this.novedad = novedad;
        this.leido = leido;
    }

    public static Vector<Libro> ejemploLibros() {
        final String SERVIDOR = "http://www.dcomg.upv.es/~jtomas/android/audiolibros/";
        Vector<Libro> libros = new Vector<Libro>();
        libros.add(new Libro("Kappa", "Akutagawa",
                R.drawable.kappa, SERVIDOR+"kappa.mp3",
                Libro.G_S_XIX, false, false));
        libros.add(new Libro("Avecilla", "Alas Clarin, Leopoldo",
                R.drawable.avecilla, SERVIDOR+"avecilla.mp3",
                Libro.G_S_XIX, true, false));
        libros.add(new Libro("Divina Comedia", "Dante",
                R.drawable.divina_comedia, SERVIDOR+"divina_comedia.mp3",
                Libro.G_EPICO, true, false));
        libros.add(new Libro("Viejo Pancho", "Alonso y Trelles, Jose",
                R.drawable.viejo_pancho, SERVIDOR+"viejo_pancho.mp3",
                Libro.G_S_XIX, true, true));
        libros.add(new Libro("Cancion de Rolando", "Anonimo",
                R.drawable.cancion_rolando, SERVIDOR+"cancion_rolando.mp3",
                Libro.G_EPICO, false, true));
        libros.add(new Libro("Matrimonio de sabuesos", "Agata Christie",
                R.drawable.matrim_sabuesos, SERVIDOR+"matrim_sabuesos.mp3",
                Libro.G_SUSPENSE, false, true));
        libros.add(new Libro("La iliada", "Homero",
                R.drawable.la_iliada, SERVIDOR+"la_iliada.mp3",
                Libro.G_EPICO, false, true));
        return libros;
    }

}
