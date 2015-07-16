package com.itescia.rkouraichi_ckula.allergenalert.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.itescia.rkouraichi_ckula.allergenalert.R;
import com.itescia.rkouraichi_ckula.allergenalert.pojos.Article;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static ArrayList<Article> ARTICLES;

    public final static String ALLERGY_ARACHID = "ARACHID";
    public final static String ALLERGY_LACTOSE = "LACTOSE";
    public final static String ALLERGY_GLUTEN = "GLUTEN";
    private final String APP_FIRST_LAUNCH = "APP_FIRST_LAUNCH";

    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences(SettingsActivity.SETTINGS_FILE, 0);

        if (sp.getBoolean(APP_FIRST_LAUNCH, true)) {
            sp.edit().putBoolean(APP_FIRST_LAUNCH, false).apply();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.dialog_message_first_launch));
            builder.setTitle(getResources().getString(R.string.dialog_title_first_launch));
            builder.setPositiveButton(getResources().getString(R.string.dialog_positive_button_first_launch),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                            startActivity(intent);
                        }
                    });
            builder.setCancelable(false);

            AlertDialog dialog = builder.create();
            dialog.show();
        }

        setContentView(R.layout.activity_main);

        fillArticleArray();

    }

    private void fillArticleArray() {
        ARTICLES = new ArrayList<>();
        ARTICLES.clear();

        String[] articlesDescription = getResources().getStringArray(R.array.list_articles_description);
        String[] articlesName = getResources().getStringArray(R.array.list_articles_name);
        String[] articlesEanCode = getResources().getStringArray(R.array.list_articles_ean_code);

        ArrayList<String> allergy_nothing = new ArrayList<>();
        ArrayList<String> allergy_all = new ArrayList<>();
        ArrayList<String> allergy_arachid = new ArrayList<>();
        ArrayList<String> allergy_gluten = new ArrayList<>();
        ArrayList<String> allergy_lactose = new ArrayList<>();
        ArrayList<String> allergy_arachid_gluten = new ArrayList<>();
        ArrayList<String> allergy_arachid_lactose = new ArrayList<>();
        ArrayList<String> allergy_gluten_lactose = new ArrayList<>();

        allergy_nothing.clear();

        allergy_all.add(MainActivity.ALLERGY_ARACHID);
        allergy_all.add(MainActivity.ALLERGY_GLUTEN);
        allergy_all.add(MainActivity.ALLERGY_LACTOSE);

        allergy_arachid.add(MainActivity.ALLERGY_ARACHID);

        allergy_gluten.add(MainActivity.ALLERGY_GLUTEN);

        allergy_lactose.add(MainActivity.ALLERGY_LACTOSE);

        allergy_arachid_gluten.add(MainActivity.ALLERGY_ARACHID);
        allergy_arachid_gluten.add(MainActivity.ALLERGY_GLUTEN);

        allergy_arachid_lactose.add(MainActivity.ALLERGY_ARACHID);
        allergy_arachid_lactose.add(MainActivity.ALLERGY_LACTOSE);

        allergy_gluten_lactose.add(MainActivity.ALLERGY_GLUTEN);
        allergy_gluten_lactose.add(MainActivity.ALLERGY_LACTOSE);

        Article laitCandia = new Article(articlesName[0], articlesDescription[0], articlesEanCode[0], 1.25);
        Article laitMatinLeger = new Article(articlesName[1], articlesDescription[1], articlesEanCode[1], 1.50);
        Article mielPops = new Article(articlesName[2], articlesDescription[2], articlesEanCode[2], 2.30);
        Article cocoPops = new Article(articlesName[3], articlesDescription[3], articlesEanCode[3], 2.55);
        Article biscuitLu = new Article(articlesName[4], articlesDescription[4], articlesEanCode[4], 1.23);
        Article nutella = new Article(articlesName[5], articlesDescription[5], articlesEanCode[5], 4.30);


        laitMatinLeger.setAllergies(allergy_nothing);
        laitCandia.setAllergies(allergy_lactose);
        mielPops.setAllergies(allergy_gluten);
        cocoPops.setAllergies(allergy_gluten);
        nutella.setAllergies(allergy_arachid);
        biscuitLu.setAllergies(allergy_all);


        laitMatinLeger.setMoreInfoLink("http://fr.openfoodfacts.org/produit/3428273090011/matin-leger-lactel");
        laitCandia.setMoreInfoLink("http://fr.openfoodfacts.org/produit/3176571983008/viva-candia");
        mielPops.setMoreInfoLink("http://fr.openfoodfacts.org/produit/3159470001011/miel-pops-kellogg-s");
        cocoPops.setMoreInfoLink("http://fr.openfoodfacts.org/produit/5053827101493/coco-pops-kellogg-s");
        nutella.setMoreInfoLink("http://fr.openfoodfacts.org/produit/3017624044003/nutella-400g-ferrero");
        biscuitLu.setMoreInfoLink("http://fr.openfoodfacts.org/produit/3017760000109/le-veritable-petit-beurre-lu");

        ARTICLES.add(laitCandia);
        ARTICLES.add(laitMatinLeger);

        ARTICLES.add(mielPops);
        ARTICLES.add(cocoPops);

        ARTICLES.add(biscuitLu);
        ARTICLES.add(nutella);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;

            case R.id.action_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult.getContents() != null) {
            String scanContent = scanningResult.getContents();

            Article article = null;
            boolean articleFound = false;
            for (Article articleToFind : ARTICLES) {
                if (articleToFind.getEanCode().equals(scanContent)) {
                    article = articleToFind;
                    articleFound = true;
                    break;
                }
            }

            if (articleFound) {
                final Article art = article;
                findViewById(R.id.article_info).setVisibility(View.VISIBLE);
                findViewById(R.id.ll_first_scan_product).setVisibility(View.GONE);

                TextView tv = (TextView) findViewById(R.id.article_name);
                tv.setText(article.getName());

                tv = (TextView) findViewById(R.id.article_description);
                tv.setText(article.getDescription());

                tv = (TextView) findViewById(R.id.article_more_info_link);
                tv.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TextView textV = (TextView) v;
                                textV.setTextColor(Color.parseColor("#0000B2"));
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(art.getMoreInfoLink()));

                                startActivity(browserIntent);
                            }
                        }
                );

                tv = (TextView) findViewById(R.id.article_price);
                tv.setText(Double.toString(article.getPrice()) + "€");

                boolean allergyInArticleDetected = false;
                if (article.getAllergies().size() > 0) {
                    boolean userAllergyArachid = sp.getBoolean(MainActivity.ALLERGY_ARACHID, false);
                    boolean userAllergyGluten = sp.getBoolean(MainActivity.ALLERGY_GLUTEN, false);
                    boolean userAllergyLactose = sp.getBoolean(MainActivity.ALLERGY_LACTOSE, false);

                    for (String allergy : article.getAllergies()) {
                        if (allergy.equals(MainActivity.ALLERGY_ARACHID) && userAllergyArachid) {
                            allergyInArticleDetected = true;
                            break;
                        }

                        if (allergy.equals(MainActivity.ALLERGY_GLUTEN) && userAllergyGluten) {
                            allergyInArticleDetected = true;
                            break;
                        }

                        if (allergy.equals(MainActivity.ALLERGY_LACTOSE) && userAllergyLactose) {
                            allergyInArticleDetected = true;
                            break;
                        }
                    }
                }

                if (allergyInArticleDetected) {
                    tv = ((TextView) findViewById(R.id.article_allergies_info));
                    tv.setText(getResources().getString(R.string.caution_product_can_cause_allergies));
                    tv.setTextColor(Color.RED);
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500);

                } else {
                    tv = ((TextView) findViewById(R.id.article_allergies_info));
                    tv.setText(getResources().getString(R.string.product_allergies_free));
                    tv.setTextColor(Color.BLACK);
                }
            } else {
                Toast.makeText(this, getResources().getString(R.string.article_unknown), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void takePicture(View v) {
        ArrayList<String> desiredBarcodeFormats = new ArrayList<>();
        desiredBarcodeFormats.add("EAN-13");

        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan(desiredBarcodeFormats);
    }
}