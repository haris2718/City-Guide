package com.example.kastoria_guide;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kastoria_guide.Model.Place;
import com.example.kastoria_guide.Model.Vote;
import com.example.kastoria_guide.Retrofit.RemoteUpdateDB;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.regex.Pattern;

public class RateFragment extends Fragment {
View view;
Place p;
Vote vote;
RatingBar ratingbar2;
TextView emailtext;
Button Submit;
Intent intent;
private RemoteUpdateDB updateDB;
private ProgressBar loadingPB2;
ShapeableImageView image;
private final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_rate, container, false);
        image= (ShapeableImageView) view.findViewById(R.id.imageScreenTwoActivity);
        emailtext=(TextView)view.findViewById(R.id.editTextTextEmailAddress);
        Submit = (Button)view.findViewById(R.id.submitButton);
        ratingbar2=(RatingBar)view.findViewById(R.id.ratingBar2);
        loadingPB2 =(ProgressBar) view.findViewById(R.id.progressBar);
        intent=getActivity().getIntent();
        p =(Place)intent.getParcelableExtra("place");
        System.out.println("this is the pos  "+ p.getId());


        System.out.println(p.toString());

        Glide.with(this)
                .asBitmap()
                .load(p.getImageUrl())
                .into(image);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (patternMatches(String.valueOf(emailtext.getText()),regexPattern)){
                    String rating=String.valueOf(ratingbar2.getRating());
                    Toast.makeText(getActivity(),"hjfsdkfkhhksffsfdskj"+ rating, Toast.LENGTH_LONG).show();
                    rateThis(ratingbar2.getRating());
                }else{
                    Toast.makeText(getActivity(),"Please enter a proper email", Toast.LENGTH_LONG).show();
                }

            }
        });


        return view;
    }
    private void rateThis(Float rate){
        if(loadingPB2.getVisibility() != View.VISIBLE) {
            loadingPB2.setVisibility(View.VISIBLE);
            Submit.setClickable(false);
        }
        updateDB=new RemoteUpdateDB(getActivity().getApplication());
        Submit.setBackgroundColor(Color.RED);
        vote=new Vote(String.valueOf(emailtext.getText()),rate,p.getId());
        updateDB.postVote(loadingPB2,Submit,vote);

    }
    //Για να δείς εάν είναι email αυτό που εισάγει ο χρήστης
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}