package org.androidtown.actionbar;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActionBar aBar = getSupportActionBar();
//        aBar.hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.menu_refresh:
                Toast.makeText(getApplicationContext(), "Refresh", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_search:
                Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_setting:
                Toast.makeText(getApplicationContext(), "Setting", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
