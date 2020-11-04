package edu.cs.birzeit.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText height;
    private EditText weight;
    private EditText name;
    private Spinner gender;
    public static final String NAME="NAME";
    public static final String HEIGHT="HEIGHT";
    public static final String WEIGHT="WEIGHT";
    public static final String GENDER="GENDER";
    public static final String FLAG="FLAG";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupSharedPrefs();
        checkPrefs();
        height=findViewById(R.id.editTextHeight);
        weight=findViewById(R.id.editTextWeight);
        name=findViewById(R.id.editTextName);
        gender=findViewById(R.id.spinnerGender);
    }

    public void btnSaveOnClick(View view){
        String name2=name.getText().toString();
        String height2=height.getText().toString();
        String weight2=weight.getText().toString();
        editor.putString(NAME,name2);
        editor.putString(HEIGHT,height2);
        editor.putString(WEIGHT,weight2);
        editor.putBoolean(FLAG,true);
        editor.commit();

         float h =0;
         float w=0;
         float bmi=0;
        try{
            h = Float.valueOf(height.getText().toString())/100;
            w = Float.valueOf(weight.getText().toString());
            bmi = (w/h*h);
            String result=Float.toString(bmi);

            Toast.makeText(this,result, Toast.LENGTH_SHORT).show();


        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
    private void setupSharedPrefs(){
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor=prefs.edit();
    }
    private void setupViews(){
        height=findViewById(R.id.editTextHeight);
        weight=findViewById(R.id.editTextWeight);
        name=findViewById(R.id.editTextName);
       // gender=findViewById(R.id.spinnerGender);

    }
    private void checkPrefs(){
        boolean flag =prefs.getBoolean(FLAG,false);

        if (flag){
            String name1=prefs.getString(NAME,"");
            String height1=prefs.getString(HEIGHT,"");
            String weight1=prefs.getString(WEIGHT,"");
           // String gender1=prefs.getString(GENDER,"");

            name.setText(name1);
            height.setText(height1);
            weight.setText(weight1);


        }
    }
}