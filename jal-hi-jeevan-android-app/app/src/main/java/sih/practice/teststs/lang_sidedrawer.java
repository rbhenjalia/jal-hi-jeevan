package sih.practice.teststs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Locale;

public class lang_sidedrawer extends AppCompatActivity {
    private Locale myLocale;
    public Button tvNEXT;
    //Listview
    ListView simpleList;
    String langList[] = {"English", "हिंदी", "മലയാളം", "தமிழ்", "ગુજરાતી", "ਪੰਜਾਬੀ","ಕನ್ನಡ","বাঙালি","मराठी","తెలుగు","ନୀୟ"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_sidedrawer);
        //LIST
        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.listview_lang, R.id.textView, langList);
        simpleList.setAdapter(arrayAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String no = String.valueOf(position);
                //String selectedItem = (String) parent.getSelectedItemPosition();
                //     Toast.makeText(getApplicationContext(),no,Toast.LENGTH_SHORT).show();
                String lang = "ml";
                switch (no)
                {
                    case "0":
                    lang = "en";
                    break;
                    case "1":
                        lang = "hi";
                        break;
                    case "2":
                        lang = "ml";
                        break;
                    case "3":
                        lang = "ta";
                        break;
                    case "4":
                        lang = "gu";
                        break;
                    case "5":
                        lang = "pa";
                        break;
                    case "6":
                        lang = "kn";
                        break;
                    case "7":
                        lang = "bn";
                        break;
                    case "8":
                        lang = "mr";
                        break;
                    case "9":
                        lang="te";
                        break;
                    case "10":
                        lang="or";
                        break;
                    default:
                        break;
                }
                changeLang(lang);
                //  Toast.makeText(getApplicationContext(),no+"---"+lang,Toast.LENGTH_SHORT).show();
            }
        });

    }
  /*  public void onClick(View v)
    {
        String lang = "ml";
        switch (v.getId())
        {
            case R.id.tvEnglish:
                lang = "en";
                break;
            case R.id.tvMalayalam:
                lang = "ml";
                break;
            case R.id.tvHindi:
                lang = "hi";
                break;
            case R.id.tvMarathi:
                lang = "mr";
                break;
            case R.id.tvGujarati:
                lang = "gu";
                break;
            case R.id.tvPunjabi:
                lang = "pa";
                break;
            case R.id.tvTamil:
                lang = "ta";
                break;
            case R.id.tvBengali:
                lang = "bn";
                break;
            case R.id.tvKannada:
                lang = "kn";
                break;
            case R.id.tvTelugu:
                lang="te";
                break;
            case R.id.tvOriya:
                lang="or";
                break;
            default:
                break;
        }
        changeLang(lang);

    }*/

    public void changeLang(String lang)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        updateTexts();
    }
    public void saveLocale(String lang)
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }
    private void updateTexts()
    {
        tvNEXT = (Button)findViewById(R.id.tvNEXT);
        tvNEXT.setText(R.string.Next);
    }
    public void ToWelcomeSlider(View v)
    {

        Intent i = new Intent(getApplicationContext(),Authentication.class);
        startActivity(i);
    }
}
