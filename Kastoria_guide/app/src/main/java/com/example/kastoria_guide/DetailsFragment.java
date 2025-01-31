package com.example.kastoria_guide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kastoria_guide.Model.Place;
import com.google.android.material.imageview.ShapeableImageView;


public class DetailsFragment extends Fragment {

View view;
Place p;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_details, container, false);
        Intent intent=getActivity().getIntent();
        p =(Place)intent.getParcelableExtra("place");
        System.out.println("this is the pos  "+ p.getId());


        System.out.println(p.toString());


        ShapeableImageView image= (ShapeableImageView) view.findViewById(R.id.imageScreenTwoActivity);
        //Toast.makeText(this, p.getName()+" selected, id "+ p.getId()+p.getLat() + " "+ p.getLon(), Toast.LENGTH_LONG).show();
        TextView textViewName = (TextView)view.findViewById(R.id.ScreenTwoNameTextView);
        TextView textViewdescription = (TextView)view.findViewById(R.id.ScreenTwoDescriptionTextView);
        Glide.with(this)
                .asBitmap()
                .load(p.getImageUrl())
                .into(image);
        textViewName.setText(p.getName());
        textViewdescription.setText(p.getDescription());
        Button btnFindInMap = (Button)view.findViewById(R.id.ScreenTwoFindInMapButton);
        btnFindInMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("http://maps.google.com/maps?q="+String.valueOf(p.getName())+"@"+String.valueOf(p.getLat())+","+String.valueOf(p.getLon())+"&z=16&output=embed\" height=\"450\" width=\"600\"");
                //goToUrl("http://maps.google.com/maps?q="+String.valueOf(p.getLat())+","+String.valueOf(p.getLon())+"&z=16&output=embed\" height=\"450\" width=\"600\"");

            }
        });

        Button btnGoToSite = (Button)view.findViewById(R.id.ScreenTwoGoToSiteButton);
        btnGoToSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl(p.getPlaceUrl());

            }
        });


        return view;
    }
    private void goToUrl (String url) {
        Uri myUrl = Uri.parse(url);
        Intent openBrowser = new Intent(Intent.ACTION_VIEW, myUrl);
        startActivity(openBrowser);

    }

}