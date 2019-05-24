package com.imie.a2dev.teamculte.quizzmaster.views;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Switch;
import com.imie.a2dev.teamculte.quizzmaster.R;

/**
 * Activity managing the settings of the app.
 */
public class SettingsActivity extends AppCompatActivity {
    /**
     * Stores the preferences tag.
     */
    public static final String PREFERENCES_TAG = "QuizzMasterPrefs";
    
    /**
     * Stores the preferences tag.
     */
    public static final String LANGUAGE_TAG = "Language";
    
    /**
     * Stores the preferences tag.
     */
    public static final String SOUND_TAG = "Sounds";
    
    /**
     * Stores the language spinner.
     */
    private Spinner spinnerLanguage;

    /**
     * Stores the switch to enable/disable sounds. 
     */
    private Switch switchSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
        this.init();
    }

    /**
     * Initializes the view components.
     */
    private void init() {
        this.spinnerLanguage = this.findViewById(R.id.spinner_language);
        this.switchSound = this.findViewById(R.id.switch_sound);

        
        this.initValues();
        this.findViewById(R.id.btn_validate).setOnClickListener(view -> {
            SettingsActivity.this.saveSetting();
            
            SettingsActivity.this.finish();
        });
    }
    
    private void initValues() {
        SharedPreferences preferences = this.getSharedPreferences(PREFERENCES_TAG, MODE_PRIVATE);

        this.switchSound.setChecked(preferences.getBoolean(SOUND_TAG, true));
        
        String[] languages = this.getResources().getStringArray(R.array.languages);
        String selectedLanguage = preferences.getString(LANGUAGE_TAG, languages[0]);

        for (int i = 0; i < languages.length; i++) {
            if (selectedLanguage.equals(languages[i])) {
                this.spinnerLanguage.setSelection(i);
            }
        }
    }

    /**
     * Save the settings.
     */
    private void saveSetting() {
        SharedPreferences preferences = this.getSharedPreferences(PREFERENCES_TAG, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        
        editor.putString(LANGUAGE_TAG, this.spinnerLanguage.getSelectedItem().toString());
        editor.putBoolean(SOUND_TAG, this.switchSound.isChecked());
        
        editor.apply();
    }
}
