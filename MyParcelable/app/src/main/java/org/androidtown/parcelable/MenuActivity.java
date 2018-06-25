package org.androidtown.parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    private void processIntent(Intent passedIntent) {

        if(passedIntent != null) {
            ArrayList<String> names = (ArrayList<String>) passedIntent.getSerializableExtra("names");
            if(names != null) {
                Toast.makeText(getApplicationContext(), "number of names:"+names.size(), Toast.LENGTH_LONG).show();
            }

            SimpleData data = passedIntent.getParcelableExtra("data");
            if(data != null) {
                Toast.makeText(getApplicationContext(), "simple data: "+data.message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
