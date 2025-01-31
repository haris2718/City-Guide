package com.example.kastoria_guide;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

//import com.example.Kastoria_guide.R;
import com.example.kastoria_guide.R;
import com.example.kastoria_guide.Retrofit.RemoteUpdateDB;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEditor;
    Switch sw_alphabetical,sw_dinstance;
    TextView tv_alpabetical,tv_dinstance;
    Button updates;
    RemoteUpdateDB updateDB;
    private ProgressBar loadingPB;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        sw_alphabetical=findViewById(R.id.switch2_alphabetical);
        sw_dinstance=findViewById(R.id.switch1_distance);
        tv_alpabetical=findViewById(R.id.tv_alphabetical);
        tv_dinstance=findViewById(R.id.tv_distance);
        updates=findViewById(R.id.buttonupdates);
        sharedPreferences =getSharedPreferences("com.example.kastoria_guide.data_prefs_file",MODE_PRIVATE);
        myEditor= sharedPreferences.edit();
        loadingPB =(ProgressBar) findViewById(R.id.idLoadingPB);
        unit();

        sw_dinstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sw_alphabetical.isChecked()){
                    sw_alphabetical.toggle();
                    //set preference to dinstance
                    myEditor.putInt("sorting_variable",1);
                    //myEditor.commit();
                }else if(!sw_dinstance.isChecked()){
                    sw_alphabetical.toggle();
                    //set preference to dinstance
                    myEditor.putInt("sorting_variable",2);
                    //myEditor.commit();

                }

            }
        });

        sw_alphabetical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sw_dinstance.isChecked()){
                    sw_dinstance.toggle();
                    //set preference to alphabetical
                    myEditor.putInt("sorting_variable",2);
                    //myEditor.commit();

                }else if(!sw_alphabetical.isChecked()){
                    sw_dinstance.toggle();
                    //set preference to alphabetical
                    myEditor.putInt("sorting_variable",1);
                    //myEditor.commit();

                }
            }
        });

        updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loadingPB.getVisibility() != View.VISIBLE) {
                    loadingPB.setVisibility(View.VISIBLE);
                    updates.setClickable(false);
                }
                updateDB=new RemoteUpdateDB(getApplication());
                updates.setBackgroundColor(Color.RED);
                updateDB.loadPlacesFromSettings(loadingPB,updates);
                //loadingPB.setVisibility(View.GONE);
                //updates.setClickable(true);

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        myEditor.commit();

    }

    private void unit(){
        if( sharedPreferences != null){
            if(sharedPreferences.getInt("sorting_variable",1)==1){
                sw_dinstance.setChecked(true);
                sw_alphabetical.setChecked(false);
            }else{
                sw_dinstance.setChecked(false);
                sw_alphabetical.setChecked(true);
            }
        }else {
            sw_dinstance.setChecked(true);
            sw_alphabetical.setChecked(false);
            myEditor.putInt("sorting_variable",1);
            myEditor.commit();
        };
    }




}