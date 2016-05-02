package cs329e.coinflip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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


    // YelpAPI Manager
    YelpAPIManager yelpAPI = new YelpAPIManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipped);

        // connect widgets to variables
        txtRestaurantName = (TextView) findViewById(R.id.textRestaurantName);
        txtFoodType = (TextView) findViewById(R.id.textFoodType);
        txtFact1 = (TextView) findViewById(R.id.textFact1);
        txtFact2 = (TextView) findViewById(R.id.textFact2);
//        txtPriceRange = (TextView) findViewById(R.id.textPriceRange);
        txtVisitCount = (TextView) findViewById(R.id.textVisitCount);
        txtLastVisit = (TextView) findViewById(R.id.textLastVisit);
        txtYourRating = (TextView) findViewById(R.id.textYourRating);
        txtCoinRating = (TextView) findViewById(R.id.textCoinRating);
        txtFavorited = (TextView) findViewById(R.id.textFavorited);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            txtRestaurantName.setText(extras.getString("name"));
            txtFact1.setText(extras.getString("open"));
            txtFact2.setText(extras.getString("cate"));
        }
    }
}

