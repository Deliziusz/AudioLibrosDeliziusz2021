package zombie.deliziusz.audiolibros;

import android.app.Application;

import java.util.Vector;

public class Aplicacion extends Application {

    private Vector<Libro> vectoLibros;
    private AdaptadorLibrosFiltro adaptador;

    @Override
    public void onCreate() {
        super.onCreate();
        vectoLibros = Libro.ejemploLibros();
        adaptador = new AdaptadorLibrosFiltro(this, vectoLibros);
    }

    public AdaptadorLibrosFiltro getAdaptador() {
        return adaptador;
    }

    public Vector<Libro> getVectoLibros() {
        return vectoLibros;
    }

}
