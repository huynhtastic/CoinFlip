package cs329e.coinflip;

/**
 * Created by cjs2599 on 4/15/16.
 */

import android.util.Log;


public class YelpIncoming {

    public String result;
    public YelpAPI api;

    public void getResults(String sTerm, String loc) {

        result = api.searchForBusinessesByLocation(sTerm, loc);
        Log.d("res", "RESULTS!");

    }

    public void main() {
        getResults("tacos", "Austin");
    }
}
