package com.example.kastoria_guide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.kastoria_guide.R;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

import com.example.kastoria_guide.Model.Place;
import com.example.kastoria_guide.Sorting.Sorting;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Place> placeList =new ArrayList<>();
    Sorting sorting;
    private float lat,lon;
    //Η μεταβλητη που θα κρατήσει το αντικέιμενο context
    // (Στην συγκεκριμένη περίπτωση το πλαισιο της com.example.kastoria_guide.ScreenOneActivity.java)
    private Context context;
/*
    private SharedPreferences sharePrefPlaceActivity;
    private SharedPreferences.Editor myEditorPlaceActivity;
*/

    //ο constructor της recyclerViewAdapter
    public RecyclerViewAdapter(Context context) {
        this.context=context;
    }


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //"ενωνεται το αντικείμενο  view με το hotel_list_item.xml αρχειο
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item,parent,false);
        //ενώνεται το holder που είναι αντικέιμενο της κλάσης ViewHolder με το αντικείμενο view που δημιουργήσαμε στην προηγούμενη γραμμή
        ViewHolder holder= new ViewHolder(view);
        return holder;

    }







    @Override
    //Η πιο σημαντική μέθοδο της RecyclerView εδώ συνδέονται  τα δεδομένα με
    //της όψης ViewHolder που εμφανίζονται στην λίστα
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        //εμφάνισε το όνομα
        //holder.getAdapterPosition() αντικατέστησα το position για δοκιμή έτσι θα γίνει και στα
        //άλλα, το hotelList.get(position) είναι deprecated
        holder.txtname.setText(placeList.get(holder.getAdapterPosition()).getName());
        holder.txDinstance.setText(String.format("%.2f",placeList.get(holder.getAdapterPosition()).getDistance())+" Km");
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, placeList.get(holder.getAdapterPosition()).getName()+" selected",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, ScreenTwoActivity_Fragm.class);
                i.putExtra("place",placeList.get(holder.getAdapterPosition()));
                context.startActivity(i);
            }
        });
        holder.ratingBar.setRating(placeList.get(holder.getAdapterPosition()).getRating());
        //Η βιβλιοθήκη Glide φορτώνει την εικόνα στο holderView
        Glide.with(context)
                .asBitmap()
                .load(placeList.get(holder.getAdapterPosition()).getImageUrl())
                .into(holder.image);
    }
    @Override
    //πόσα αντικέιμενα έχει η λίστα
    public int getItemCount() {
        return placeList.size();
    }
    //εδώ γινεται η αποθήκευση της λίστας με τα ξενοδοχεία
    //εδώ είχα arraylist αλλά το
    public void setPlaceList(List<Place> places) {
       /* sharePrefPlaceActivity =context.getSharedPreferences("com.example.kastoria_guide.data_prefs_file",0);
        myEditorPlaceActivity = sharePrefPlaceActivity.edit();
        if (sharePrefPlaceActivity.getInt("sorting_variable", 0) == 1){

        }*/
        //sorting==new Sorting(context,)
        this.placeList = places;
        //εάν αλλάξει η  λίστα ειδοποίησε
        notifyDataSetChanged();
    }


    private float get_distance(float wayLatitude, float wayLongitude, float lat2, float lon2){
        Location curentLocation=new Location("");
        curentLocation.setLatitude(wayLatitude);
        curentLocation.setLongitude(wayLongitude);
        Location hotelLocation = new Location("");
        hotelLocation.setLatitude(lat2);
        hotelLocation.setLongitude(lon2);
        return curentLocation.distanceTo(hotelLocation)/1000 ;
    }

    public void setDistance(List<Place> placeList,float lat, float lon) {
        for (Place p : placeList) {
            p.setDistance((float) get_distance(lat,lon,p.getLat(), p.getLon()));
        }

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        // private final TextView wordItemView;
        private final TextView txtname, txDinstance;
        private final CardView parent;
        private final ImageView image;
        private final RatingBar ratingBar;

        private ViewHolder(View itemView) {
            super(itemView);
            //wordItemView = itemView.findViewById(R.id.textView);
            txtname = itemView.findViewById(R.id.txtname);
            txDinstance = itemView.findViewById(R.id.distance);
            image = itemView.findViewById(R.id.image);
            parent = itemView.findViewById(R.id.parent);
            ratingBar = itemView.findViewById(R.id.ratingbar);
        }

    }//end of ViewHolder

}

