package project.minor.minorproject.buslocator;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InBus extends AppCompatActivity {

    Button locate, fetch;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Locate curr_location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_bus);
        setTheme(R.style.AppTheme);

        readLocation();

        locate = (Button) findViewById(R.id.locate);
        fetch = (Button) findViewById(R.id.fetch);

        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseJobSchedular.scheduleFetching(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Sending data", Toast.LENGTH_SHORT).show();
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Fetching Bus Location", Toast.LENGTH_SHORT).show();

                /*try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                Intent map_in = new Intent(getApplicationContext(),MapsActivity.class);
                map_in.putExtra("Lat",23.252318/*curr_location.getLati()*/);
                map_in.putExtra("Longi",77.490902/*curr_location.getLongi()*/);
                startActivity(map_in);
            }
        });
    }

    public void readLocation() {

        AsyncTask fetchLocation;
        fetchLocation = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference().child("location");
                Log.v("reading database---", "running");

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.v("Value---", String.valueOf(dataSnapshot.getChildrenCount()));

                        for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                            curr_location = userSnapshot.getValue(Locate.class);

                            Log.v("Curr---", String.valueOf(curr_location.getLati()));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Failed to read value
                        Log.v("Failed to read value---", "");
                    }
                });
                return null;
            }
        };
        fetchLocation.execute();


    }
}

