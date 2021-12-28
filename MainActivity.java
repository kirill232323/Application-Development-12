package com.example.myfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Place place = new Place("Beijing", 80, 70);
    Button send;
    EditText name, lon, lat;
    TextView result;
    double lonD, latD;
    String lonS, latS;
    private RelativeLayout activity_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        lon = findViewById(R.id.longitude);
        lat = findViewById(R.id.latitude);
        send= findViewById(R.id.send);
        result = findViewById(R.id.result);

        View.OnClickListener sendBtn= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place.name = name.getText().toString();
                lonS = lon.getText().toString();
                lonD= Double.parseDouble(lonS);
                place.lon = lonD;
                latS = lat.getText().toString();
                latD= Double.parseDouble(latS);
                place.lat = latD;

                Log.d("mytag", "name: " + place.name);
                Log.d("mytag", "lat: " + place.lat);
                Log.d("mytag", "lon: " + place.lon);

                result.setText("Your data was successfully sent!");

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference ref_name = db.getReference("name");
                ref_name.setValue(place.name);
                DatabaseReference ref_lat = db.getReference("coordinates").child("latitude");
                ref_lat.setValue(place.lat);
                DatabaseReference ref_lon = db.getReference("coordinates").child("longitude");
                ref_lon.setValue(place.lon);
            }
        };
        send.setOnClickListener(sendBtn);
    }
}
