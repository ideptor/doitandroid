package org.androidtown.smsreceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {

    EditText sender;
    EditText contents;
    EditText receivedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        sender = findViewById(R.id.sender);
        contents = findViewById(R.id.contents);
        receivedTime = findViewById(R.id.receivedTime);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent passedIntent = getIntent();
        processCommand(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processCommand(intent);
    }

    private void processCommand(Intent intent) {
        if(intent != null) {
            String strSender = intent.getStringExtra("sender");
            String strContents = intent.getStringExtra("contents");
            String strReceivedTime = intent.getStringExtra("receivedDate");

            this.sender.setText(strSender);
            this.contents.setText(strContents);
            this.receivedTime.setText(strReceivedTime);
        }
    }


}
