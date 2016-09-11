package cs329e.coinflip;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by cjs2599 on 4/18/16.
 */

public class YelpRequest extends AsyncTask<HashMap<String, String>, Void, Response<SearchResponse>> {

    private Activity activity;

    // YelpAPI Manager
    private YelpAPIManager yelpAPI = new YelpAPIManager();

    // params
    HashMap<String, String> filters;

    public YelpRequest(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Response<SearchResponse> doInBackground(HashMap<String, String>... params) {
        Call<SearchResponse> call;
        try {
            params[0].put("term", "food");
            params[0].put("limit", "10");
            params[0].put("open", "false");
            params[0].put("rating", "0");
            params[0].put("deals", "false");
            call = yelpAPI.search("Austin", params[0]);
            filters = params[0];
        } catch (Exception e) {
            HashMap<String, String> makeParams = new HashMap<>();
            makeParams.put("term", "food");
            makeParams.put("limit", "10");
            makeParams.put("open", "false");
            call = yelpAPI.search("Austin", makeParams);
            filters = null;
        }

//        Log.v("params", params.toString());
        try {
            return call.execute();
        } catch (IOException ie) {
            Log.v("call", "failed");
            return null;
        }

    }

    @Override
    protected void onPostExecute(Response<SearchResponse> resp) {
        if (resp != null) {
            SearchResponse searchResp = resp.body();
            ArrayList<Business> businesses = searchResp.businesses();
//                for (Business business : businesses) {
//                    Log.v("bs", business.toString());
//                }
            Random rand = new Random();
            Boolean pass = true;
            Business business = null;
//            Log.v("test", searchResp.total().toString()); // show number of results grabbed; grabs all available responses, but not the amount that is actually returned
            for (int i = 0; i < businesses.size(); i++) {
                pass = true;
                int n = rand.nextInt(businesses.size());

                // grab chosen business
                business = businesses.get(n);
                // see if business meets the criteria
                Log.v("filters", filters.toString());
                if (filters != null) {
                    if (filters.get("open").equals("true") && business.isClosed() == true) {
                        pass = false;
                    }
                    if (!(filters.get("rating").equals("0")) && (business.rating() < Double.parseDouble(filters.get("rating")))) {
                        pass = false;
                    }
                    if ((filters.get("deals").equals("true")) && (business.deals() == null)) {
                        pass = false;
                    }
                }
                if (pass) {break;}
            }

            if (pass && business != null) {
                // get info from business
                String name = business.name();
                String open = business.isClosed() ? "Now Closed" : "Open Now";
                //            String food = business.categories().get(1).name();
                String food = business.categories().get(0).name();
                String extrafact;
                try {
                    extrafact = business.categories().get(1).name();
                } catch (Exception e) {
                    extrafact = null;
                }
                //            Log.v("cate", business.categories().toString());
                Intent i = new Intent(activity, FlippedActivity.class);
                i.putExtra("name", name);
                i.putExtra("open", open);
                i.putExtra("food", food);
                i.putExtra("extrafact", (extrafact != null) ? extrafact : "-");
                Log.v("rating", business.rating().toString());
                activity.startActivity(i);
            } else {
                filters.put("offset", "10");
                new YelpRequest(activity).execute(filters);
            }

        }
    }
}