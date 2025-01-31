package com.example.kastoria_guide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.kastoria_guide.Model.Place;

public class ScreenTwoActivity_Fragm extends AppCompatActivity {
    private Place p;
    private Button detailsButton;
    private Button rateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_two_fragm);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //για να επιλέγεις ποιό fragment θα εμφανίζεται κάθε φορά
        FragmentManager fragmentManager = getSupportFragmentManager();

        detailsButton=findViewById(R.id.DetailsButton);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //πάνε στο detailsfragment
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, DetailsFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // Name can be null
                        .commit();

            }
        });

        rateButton=findViewById(R.id.RateButton);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //πάνε στο ratefragment
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, RateFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // Name can be null
                        .commit();

            }
        });

    }
    //Για το βελάκι επιστροφής
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}