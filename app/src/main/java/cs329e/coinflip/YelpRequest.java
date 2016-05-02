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

public class YelpRequest extends AsyncTask<Map<String, String>, Void, Response<SearchResponse>> {

    private Activity activity;

    // YelpAPI Manager
    private YelpAPIManager yelpAPI = new YelpAPIManager();

    public YelpRequest(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Response<SearchResponse> doInBackground(Map<String, String>... params) {

        Call<SearchResponse> call = yelpAPI.search("San Francisco", params[0]);
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
//                Log.v("call", resp.body().toString());
            SearchResponse searchResp = resp.body();
            ArrayList<Business> businesses = searchResp.businesses();
                for (Business business : businesses) {
                    Log.v("bs", business.toString());
                }
            Random rand = new Random();
            Log.v("test", searchResp.total().toString()); // show number of results grabbed; grabs all available responses, but not the amount that is actually returned
            int n = rand.nextInt(businesses.size());
            Log.v("rand", String.valueOf(n - 1)); // show which result was randomly chosen in the array
//                Log.v("randbs", businesses.get(n).toString());

            // grab chosen business
            Business business = businesses.get(n);

            // get info from business
            String name = business.name();
            String open = business.isClosed() ? "Now Closed" : "Open Now";
//            String food = business.categories().get(1).name();
            String cate = business.categories().get(0).name();
            Log.v("cate", business.categories().toString());
            Intent i = new Intent(activity, FlippedActivity.class);
            i.putExtra("name", name);
            i.putExtra("open", open);
            i.putExtra("cate", cate);
            activity.startActivity(i);

        }
    }
}