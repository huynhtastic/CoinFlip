package cs329e.coinflip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // create references to widgets
    private static Button btnFlip;
    private static Button btnFilter;
    private static Button btnConnectGplay;

    HashMap<String, String> params = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect widgets to references
        btnFlip = (Button) findViewById(R.id.buttonFlip);
        btnFilter = (Button) findViewById(R.id.buttonFilter);
        btnConnectGplay = (Button) findViewById(R.id.buttonConnectGplay);


        params.put("term", "food");
        params.put("limit", "10");

        onClickButtonListeners();
    }

    public void onClickButtonListeners() {
        btnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == btnFlip.getId()) {
                    new YelpRequest(MainActivity.this).execute(params);
//                    Intent i = new Intent(MainActivity.this, LoadingActivity.class);
//                    startActivity(i);
                }
            }
        });
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == btnFilter.getId()) {
                    Intent i = new Intent(MainActivity.this, FilterActivity.class);
                    startActivity(i);
                }
            }
        });
    }

}
