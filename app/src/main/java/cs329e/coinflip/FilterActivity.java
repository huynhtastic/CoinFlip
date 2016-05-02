/*
    This activity is used to allow the user to input filters that will be applied to search for
    restaurants.
 */

package cs329e.coinflip;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.lang.reflect.Array;
import java.util.HashMap;

public class FilterActivity extends AppCompatActivity {

    // create widget reference variables
    private static Switch schPrice;
    private static Switch schOpen;
    private static Switch schDeals;
    private static Switch schDistance;
    private static Switch schRating;
    private static Spinner spnPrice;
    private static Spinner spnRating;
    private static EditText edtDistance;
    private static Button btnFlip;

    // sharedPrefs Object
    // ...

    // Params Hashmap
    HashMap<String, String> params = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // link references to widgets
        schPrice = (Switch) findViewById(R.id.switchPrice);
        schOpen = (Switch) findViewById(R.id.switchOpen);
        schDeals = (Switch) findViewById(R.id.switchDeals);
        schDistance = (Switch) findViewById(R.id.switchDistance);
        schRating = (Switch) findViewById(R.id.switchRating);
        edtDistance = (EditText) findViewById(R.id.editDistance);
        btnFlip = (Button) findViewById(R.id.buttonFlip);

        // create and set price spinner
        // this spinner uses labels and gets the position of the label to call the corresponding value from the price_values array
        spnPrice = (Spinner) findViewById(R.id.spinnerPrice);
        ArrayAdapter<CharSequence> priceAdapter = ArrayAdapter.createFromResource(this, R.array.price_labels, android.R.layout.simple_spinner_item);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPrice.setAdapter(priceAdapter);

        // create and set rating spinner
        // spinner uses the same concept and methodology as the price spinner
        spnRating = (Spinner) findViewById(R.id.spinnerRating);
        ArrayAdapter<CharSequence> ratingAdapter = ArrayAdapter.createFromResource(this, R.array.rating_labels, android.R.layout.simple_spinner_item);
        ratingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRating.setAdapter(ratingAdapter);

        setOnButtonClickListener();
    }

    public void setOnButtonClickListener() {
        btnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == btnFlip.getId()) {
                    // check what switches are flipped and read values when necessary
                    params.put("open", schOpen.isChecked() ? "true" : "false");
                    params.put("rating", schRating.isChecked() ? getResources().getStringArray(R.array.ratings_values)[spnRating.getSelectedItemPosition()] : "0");
                    params.put("deals", schDeals.isChecked() ? "true" : "false");

                    new YelpRequest(FilterActivity.this).execute(params);
//                    Intent i = new Intent(FilterActivity.this, FlippedActivity.class);
//                    Intent i = new Intent(FilterActivity.this, DisplayActivity.class); // FOR PROTOTYPE ONLY
                    //i.putExtra("price", (sch  Price.isChecked()) ? spnPrice.getSelectedItem().toString() : false); // if it's switched on, take the value; false if it's off
//                    i.putExtra("price", (schPrice.isChecked()) ? getResources().getIntArray(R.array.price_values)[spnPrice.getSelectedItemPosition()] : "No Preference"); // FOR PROTOTYPE ONLY
//                    i.putExtra("open", schOpen.isChecked());
//                    i.putExtra("deals", schDeals.isChecked());
                    //i.putExtra("distance", (schDistance.isChecked()) ? edtDistance.getText().toString() : false); // if it's switched on, take the value; false if it's off
//                    i.putExtra("distance", (schDistance.isChecked()) ? edtDistance.getText().toString() : "No Preference"); // FOR PROTOTYPE ONLY
//                    i.putExtra("rating", (schRating.isChecked()) ? getResources().getIntArray(R.array.ratings_values)[spnRating.getSelectedItemPosition()] : "No Preference");
//                    startActivity(i);
            }

            }
        });
    }


}
