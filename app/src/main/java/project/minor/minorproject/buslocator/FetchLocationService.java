package project.minor.minorproject.buslocator;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FetchLocationService extends JobService{

    private FusedLocationProviderClient mFusedLocationClient;
    private Double Lat,Long;
    private AsyncTask fetchLocation;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        fetchLocation = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {

                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
                try {
                    mFusedLocationClient.getLastLocation()
                            .addOnSuccessListener( new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    // Got last known location. In some rare situations this can be null.
                                    if (location != null) {
                                        Long = location.getLongitude();
                                        Lat = location.getLatitude();
                                        Log.v("LatLong--",String.valueOf(Long)+"  "+String.valueOf(Lat));
                                    }
                                    else{
                                        Log.v("LatLong--","NULL");
                                    }
                                }
                            });
                    addOnDatabase();
                }
                catch (SecurityException e){
                    Log.v("Security Exception--",e.toString());
                }



                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Log.v("onPostExecute---", "Running");
                jobFinished(jobParameters, false);
            }
        };
        fetchLocation.execute();
        return false;
    }

    private void addOnDatabase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("location");
        databaseReference.push().setValue(new Locate(Lat,Long));
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
