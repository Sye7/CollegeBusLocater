package project.minor.minorproject.buslocator;

import android.content.Context;
import android.util.Log;

import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;



public class FirebaseJobSchedular {

    /*public static int HOURS = 1;
    public static int SECONDS = (int) TimeUnit.HOURS.toSeconds(HOURS);*/
    public static String TAG = "JobTag";
    public static FirebaseJobDispatcher dispatcher;

    private static boolean sInitialized;

    synchronized public static void scheduleFetching(Context con){
        Log.v("Running---","SCHEDULAR");

        if(sInitialized) {
            Log.v("Schedular---"," Already Init");
            return;
        }
        Driver driver = new GooglePlayDriver(con);
        dispatcher = new FirebaseJobDispatcher(driver);
        Job fetchingJob = dispatcher.newJobBuilder()
                .setService(FetchLocationService.class)
                .setTag(TAG)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setReplaceCurrent(true)
                .setTrigger(Trigger.executionWindow(5,10))
                .build();

        dispatcher.schedule(fetchingJob);
        sInitialized = true;
        Log.v("Schedular-------","Dispatched");
    }
}
