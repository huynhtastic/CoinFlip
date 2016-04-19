package cs329e.coinflip;

import android.os.AsyncTask;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONStringer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

/**
 * Created by cjs2599 on 4/18/16.
 */

// AsyncTask takes <Params, Progress, Result> as arguments
public class YelpRequest extends AsyncTask<String, Void, String> {

    private static final String CONSUMER_KEY = "ngvysMUnZ3h3aBNU4MBdjA";
    private static final String CONSUMER_SECRET = "A1g4VBz3GAUszZ7utSQinWCsg1I";
    private static final String TOKEN = "8DRYE5aifjtJWMbC2LbeXojy2-0TYZsV";
    private static final String TOKEN_SECRET = "6qUlZk4pTtxyHyZuub3YSEKVrRc";

    private Map<String, String> RESULT;



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
        Log.v("res", resp);
        RESULT.put("key", "value");

    }

    private void randomSelector(String response) {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(response);

            JSONObject json = (JSONObject) obj;

            



        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
