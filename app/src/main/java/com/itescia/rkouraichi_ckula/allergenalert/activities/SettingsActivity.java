package com.itescia.rkouraichi_ckula.allergenalert.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.itescia.rkouraichi_ckula.allergenalert.R;
import com.itescia.rkouraichi_ckula.allergenalert.adapters.AllergyAdapter;
import com.itescia.rkouraichi_ckula.allergenalert.pojos.Allergy;

import java.util.ArrayList;


public class SettingsActivity extends AppCompatActivity {
    public static final String SETTINGS_FILE = "SETTINGS FILE";
    private SharedPreferences sp;

    private static final String[] ALLERGIES_SETTINGS = {MainActivity.ALLERGY_ARACHID, MainActivity.ALLERGY_LACTOSE, MainActivity.ALLERGY_GLUTEN};

    private static ArrayList<Allergy> ALLERGIES;
    private AllergyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences(SETTINGS_FILE, 0);
        setContentView(R.layout.activity_settings);

        fillAllergyArray();

        ListView lv = (ListView) findViewById(R.id.list_allergies);
        adapter = new AllergyAdapter(this, ALLERGIES);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    protected void onPause() {
        super.onPause();
        ListView lv = (ListView) findViewById(R.id.list_allergies);
        SharedPreferences.Editor ed = sp.edit();

        for (int i = 0; i < adapter.getCount(); i++) {
            LinearLayout ll = (LinearLayout) lv.getChildAt(i);
            CheckBox cb = (CheckBox) ll.getChildAt(0);
            ed.putBoolean(ALLERGIES_SETTINGS[i], cb.isChecked());
        }
        ed.apply();
    }

    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void fillAllergyArray() {
        ALLERGIES = new ArrayList<>();

        String[] allergiesName = getResources().getStringArray(R.array.list_allergies_name);
        String[] allergiesDescription = getResources().getStringArray(R.array.list_allergies_description);

        for (int i = 0; i < allergiesName.length; i++) {
            ALLERGIES.add(new Allergy(allergiesName[i], allergiesDescription[i], sp.getBoolean(ALLERGIES_SETTINGS[i], false)));
        }
    }
}
