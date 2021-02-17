package zombie.deliziusz.audiolibros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

public class MainServicio extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        startService(new Intent(MainServicio.this, ServicioReproducir.class));
        super.onResume();
    }
    @Override
    protected void onStop() {
        startService(new Intent(MainServicio.this, ServicioReproducir.class));
        super.onStop();
    }
}
