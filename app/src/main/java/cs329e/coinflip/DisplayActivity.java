package cs329e.coinflip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by cjs2599 on 3/25/16.
 */

public class DisplayActivity extends AppCompatActivity {

    private static TextView price;
    private static TextView open;
    private static TextView deals;
    private static TextView distance;

    private String price_val;
    private String open_val;
    private String deals_val;
    private String distance_val;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_filter_display);

        price = (TextView) findViewById(R.id.price_display);
        open = (TextView) findViewById(R.id.open_display);
        deals = (TextView) findViewById(R.id.deals_display);
        distance = (TextView) findViewById(R.id.distance_display);

        readAndDisplay();
    }

    public void readAndDisplay() {

        price_val = getIntent().getExtras().getString("price");
        open_val = (getIntent().getExtras().getBoolean("open") ? "Yes" : "No Preference");
        deals_val = (getIntent().getExtras().getBoolean("deals") ? "Yes" : "No Preference");
        distance_val = getIntent().getExtras().getString("distance");

        price.setText(price_val);
        open.setText(open_val);
        deals.setText(deals_val);
        distance.setText(distance_val);

    }

}
