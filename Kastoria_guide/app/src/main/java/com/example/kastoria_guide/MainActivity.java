package com.example.kastoria_guide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.kastoria_guide.R;

import com.example.kastoria_guide.Model.PlaceRepository;
import com.example.kastoria_guide.Retrofit.RemoteUpdateDB;
import com.example.kastoria_guide.ViewModel.ScreenOneViewModel;

public class MainActivity extends AppCompatActivity {
    //this is the previously created class
    private ScreenOneViewModel mScreenOneViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private RecyclerView RecyclerViewPlace;
    private RecyclerViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main3);
        //παραμπέμπει στο αρχείο activity_main.xml file στο φάκελο res/layout folder.
        //Η όψη-μορφή (View) που θα έχει η activity
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.main3);
        //Ο τίτλος που θα έχει το app
        setTitle(getString(R.string.app_name));

        CardView hotel=(CardView) findViewById(R.id.cv_hotel);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ScreenOneActivity.class);
                intent.putExtra("category", "Hotel");
                startActivity(intent);

            }
        });

        CardView sights=(CardView) findViewById(R.id.cv_sights);
        sights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ScreenOneActivity.class);
                intent.putExtra("category", "Sights");
                startActivity(intent);

            }
        });

        //CardView restaurant=(CardView) findViewById(R.id.cv_restaurant);
        CardView restaurant=(CardView) findViewById(R.id.cv_rest);
        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ScreenOneActivity.class);
                intent.putExtra("category", "Rest");
                startActivity(intent);

            }
        });

        CardView entertainment=(CardView) findViewById(R.id.cv_entertainment);
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ScreenOneActivity.class);
                intent.putExtra("category", "Entert");
                startActivity(intent);

            }
        });

        CardView setings=(CardView) findViewById(R.id.cv_setting);
        setings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Settings.class ));

            }
        });


    }

}