package cs329e.coinflip;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONStringer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class FlippedActivity extends AppCompatActivity {

    public String restaurant_name;
    public String location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipped);

        // execute asynchronous request through nested inner class
        new YelpRequest().execute();
    }


    protected void updateDisplays() {
        // set textview text to new variable values here
    }



    private class YelpRequest extends AsyncTask<String, Void, String> {

        private static final String CONSUMER_KEY = "ngvysMUnZ3h3aBNU4MBdjA";
        private static final String CONSUMER_SECRET = "A1g4VBz3GAUszZ7utSQinWCsg1I";
        private static final String TOKEN = "8DRYE5aifjtJWMbC2LbeXojy2-0TYZsV";
        private static final String TOKEN_SECRET = "6qUlZk4pTtxyHyZuub3YSEKVrRc";

        private HashMap<String, String> RESULT;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            YelpAPI api = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);

            return api.searchForBusinessesByLocation("tacos", "Austin");
        }


        @Override
        protected void onPostExecute(String resp) {
            RESULT = new HashMap<String, String>();
            Object obj = JSONValue.parse(resp); // parse resp object
            JSONObject jobj = (JSONObject) obj; // convert JSON to a MAP
            JSONArray businesses = (JSONArray) jobj.get("businesses"); // grab the array portion of the resp object and convert to a list/array
            for (Object business : businesses) {  // loop through each business and log their business information
                Log.v("business", business.toString());
                Map mbus = (Map) business; // turn into map
                Log.v("business keys", mbus.keySet().toString()); // see the keys
                for (Object entry : mbus.keySet()) {
                    Log.v("entry", "key: " + entry.toString() + "; value: " + mbus.get(entry).toString());
                    RESULT.put(entry.toString(), mbus.get(entry).toString());

                }
            }
            Log.v("RESULT", RESULT.toString());

            Log.v("test", jobj.get("total").toString());
            Log.v("test", jobj.get("region").toString());
            Log.v("res", resp);

            // Assign new values to parent class' variables
            restaurant_name = RESULT.get("name");
            location = RESULT.get("location");

            // Call parent class method to update UI
            updateDisplays();

        }

    }
}
