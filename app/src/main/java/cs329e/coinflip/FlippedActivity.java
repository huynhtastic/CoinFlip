package cs329e.coinflip;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONStringer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Text;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class FlippedActivity extends AppCompatActivity {

    // Instantiate the widgets
    private static TextView txtRestaurantName;
    private static TextView txtFoodType;
    private static TextView txtFact1;
    private static TextView txtFact2;
    private static TextView txtPriceRange;
    private static TextView txtVisitCount;
    private static TextView txtLastVisit;
    private static TextView txtYourRating;
    private static TextView txtCoinRating;
    private static TextView txtFavorited;

    // Module level map for results
    private HashMap<String, String> RESULT;

    public String restaurant_name;
    public String location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipped);

        // connect widgets to variables
        txtRestaurantName = (TextView) findViewById(R.id.textRestaurantName);
        txtFoodType = (TextView) findViewById(R.id.textFoodType);
        txtFact1 = (TextView) findViewById(R.id.textFact1);
        txtFact2 = (TextView) findViewById(R.id.textFact2);
        txtPriceRange = (TextView) findViewById(R.id.textPriceRange);
        txtVisitCount = (TextView) findViewById(R.id.textVisitCount);
        txtLastVisit = (TextView) findViewById(R.id.textLastVisit);
        txtYourRating = (TextView) findViewById(R.id.textYourRating);
        txtCoinRating = (TextView) findViewById(R.id.textCoinRating);
        txtFavorited = (TextView) findViewById(R.id.textFavorited);

        // execute asynchronous request through nested inner class
        new YelpRequest().execute();
    }

    protected void updateDisplays() {
        // set textview text to new variable values here
        String name = RESULT.get("name");
        String closed = (Boolean.valueOf(RESULT.get("is_closed"))) ? "Now Closed" : "Open Now";
        Object parseCategories = JSONValue.parse(RESULT.get("categories"));
        JSONArray categories = (JSONArray) parseCategories;
        JSONArray foodType = (JSONArray) categories.get(1);
        JSONArray fact = (JSONArray) categories.get(0);

        txtRestaurantName.setText(name);
        txtFoodType.setText(foodType.get(0).toString() + " Food");
        txtFact1.setText(closed);
        txtFact2.setText(fact.get(0).toString());
    }



    private class YelpRequest extends AsyncTask<String, Void, String> {

        private static final String CONSUMER_KEY = "ngvysMUnZ3h3aBNU4MBdjA";
        private static final String CONSUMER_SECRET = "A1g4VBz3GAUszZ7utSQinWCsg1I";
        private static final String TOKEN = "8DRYE5aifjtJWMbC2LbeXojy2-0TYZsV";
        private static final String TOKEN_SECRET = "6qUlZk4pTtxyHyZuub3YSEKVrRc";

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
//            Log.v("test", resp.toString());
            JSONObject jobj = (JSONObject) obj; // convert JSON to a MAP
            JSONArray businesses = (JSONArray) jobj.get("businesses"); // grab the array portion of the resp object and convert to a list/array
            Random rand = new Random();
//            Log.v("test", String.valueOf(businesses.size())); // show number of results grabbed
            int n = rand.nextInt(businesses.size());
//            Log.v("rand", String.valueOf(n)); // show which result was randomly chosen in the array
            HashMap business = (HashMap) businesses.get(n);
            // get each key and value and print it
            for (Object entry : business.keySet()) {
                Log.v("entry", "key: " + entry.toString() + "; value: " + business.get(entry).toString());
                RESULT.put(entry.toString(), business.get(entry).toString());
            }
//            for (Object business : businesses) {  // loop through each business and log their business information
//                Log.v("business", business.toString());
//                Map mbus = (Map) business; // turn into map
//                Log.v("business keys", mbus.keySet().toString()); // see the keys
//                for (Object entry : mbus.keySet()) {
//                    Log.v("entry", "key: " + entry.toString() + "; value: " + mbus.get(entry).toString());
//                    RESULT.put(entry.toString(), mbus.get(entry).toString());
//
//                }
//            }
//            Log.v("RESULT", RESULT.toString());
//
//            Log.v("test", jobj.get("region").toString());
//            Log.v("res", resp);

            // Assign new values to parent class' variables
            restaurant_name = RESULT.get("name");
            location = RESULT.get("location");

            // Call parent class method to update UI
            updateDisplays();

        }

    }
}
