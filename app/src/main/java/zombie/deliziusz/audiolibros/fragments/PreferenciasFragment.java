package zombie.deliziusz.audiolibros.fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import zombie.deliziusz.audiolibros.R;


public class PreferenciasFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
