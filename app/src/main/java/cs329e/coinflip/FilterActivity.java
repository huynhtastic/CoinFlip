package cs329e.coinflip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class FilterActivity extends AppCompatActivity {

    // create widget reference variables
    private static Switch schPrice;
    private static Switch schOpen;
    private static Switch schDeals;
    private static Switch schDistance;
    private static Spinner spnPrice;
    private static EditText edtDistance;
    private static Button btnFlip;

    // sharedPrefs Object
    // ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // link references to widgets
        schPrice = (Switch) findViewById(R.id.switchPrice);
        schOpen = (Switch) findViewById(R.id.switchOpen);
        schDeals = (Switch) findViewById(R.id.switchDeals);
        schDistance = (Switch) findViewById(R.id.switchDistance);
        edtDistance = (EditText) findViewById(R.id.editDistance);
        btnFlip = (Button) findViewById(R.id.buttonFlip);

        // create and set price spinner
        spnPrice = (Spinner) findViewById(R.id.spinnerPrice);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.filter_array_price, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPrice.setAdapter(adapter);

        setOnButtonClickListener();
    }

    public void setOnButtonClickListener() {
        btnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == btnFlip.getId()) {
                    // see what switches are flipped to filter

                    //Intent i = new Intent(FilterActivity.this, FlippedActivity.class);
                    Intent i = new Intent(FilterActivity.this, DisplayActivity.class); // FOR PROTOTYPE ONLY
                    //i.putExtra("price", (schPrice.isChecked()) ? spnPrice.getSelectedItem().toString() : false); // if it's switched on, take the value; false if it's off
                    i.putExtra("price", (schPrice.isChecked()) ? spnPrice.getSelectedItem().toString() : "No Preference"); // FOR PROTOTYPE ONLY
                    i.putExtra("open", schOpen.isChecked());
                    i.putExtra("deals", schDeals.isChecked());
                    //i.putExtra("distance", (schDistance.isChecked()) ? edtDistance.getText().toString() : false); // if it's switched on, take the value; false if it's off
                    i.putExtra("distance", (schDistance.isChecked()) ? edtDistance.getText().toString() : "No Preference"); // FOR PROTOTYPE ONLY
                    startActivity(i);
                }
            }
        });
    }
}
