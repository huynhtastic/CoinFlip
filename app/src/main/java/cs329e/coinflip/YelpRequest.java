package cs329e.coinflip;

import android.os.AsyncTask;
import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.Map;
import java.util.Set;

/**
 * Created by cjs2599 on 4/18/16.
 */

// AsyncTask takes <Params, Progress, Result> as arguments
public class YelpRequest extends AsyncTask<String, Void, String> {

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

    @Override protected void onPostExecute(String resp) {
        Object obj = JSONValue.parse(resp); // parse resp object
        JSONObject jobj = (JSONObject) obj; // convert JSON to a MAP
        JSONArray businesses = (JSONArray) jobj.get("businesses"); // grab the array portion of the resp object and convert to a list/array
        for (Object business : businesses) {  // loop through each business and log their business information
            Log.v("business", business.toString());
        }
        Log.v("test", jobj.get("total").toString());
        Log.v("test", jobj.get("region").toString());
        Log.v("res", resp);
    }




}
