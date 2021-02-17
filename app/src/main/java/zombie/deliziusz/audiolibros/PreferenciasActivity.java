package zombie.deliziusz.audiolibros;

import android.os.Bundle;

import androidx.annotation.Nullable;


import androidx.appcompat.app.AppCompatActivity;

import zombie.deliziusz.audiolibros.fragments.PreferenciasFragment;

public class PreferenciasActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferenciasFragment()).commit();

    }
}
