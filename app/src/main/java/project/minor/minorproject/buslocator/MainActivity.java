package project.minor.minorproject.buslocator;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button rgpv;
    private ListView buses;

    double Lat,Long;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppTheme);

        buses = (ListView) findViewById(R.id.bus_list);
        String[] routes = new String[] {
                "Prabhat Petrol Pump", "Pal Dhaba", "Govindpura", "Chetak Bridge", "Sevay Complex", "Gehu Kheda", "Bitthan Market", "Rachna Nagar", "MANIT Square", "Nandi Foundation", "Kailash Nagar", "Katara Hills", "Jubli Gate", "Shakti Nagar Market", "Patel Nagar", "Coach Factory", "12 No. Stop", "Berkhari", "Polytechnic Square"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, routes);

        buses.setAdapter(adapter);

        buses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(getApplicationContext(),InBus.class);
//                in.putExtra()
                startActivity(in);
            }
        });


    }
}
