package com.ideptor.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    TextView textView;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String databaseName = editText.getText().toString();
                createDatabase(databaseName);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String tableName = editText2.getText().toString();
                createTable(tableName);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                insertRecord();
            }
        });
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                executeQuery();
            }
        });
    }

    private void insertRecord() {
        println("Call insertRecord()");

        if(database == null) {
            println("Database should be open");
            return;
        }

        String tableName = editText2.getText().toString();
        if(tableName == null) {
            println("Table name should be specified");
            return;
        }
        try {

            String sql = "INSERT INTO " + tableName + "(name, age, mobile) VALUES ('john', 20, '010-0001-1111')";
            database.execSQL(sql);
            println("Record added");

        } catch(Exception e) {
            e.printStackTrace();
            println(e.toString());
        }
    }

    private void createTable(String tableName) {
        println("Call createTable()");

        if(database == null) {
            println("Database should be open");
            return;
        }

        try {

            String sql = "create table if not exists " + tableName + " ( _id integer PRIMARY KEY autoincrement, name text, age integer, mobile text);";
            database.execSQL(sql);
            println("Table created: " + tableName);

        } catch(Exception e) {
            e.printStackTrace();
            println(e.toString());
        }
    }

    private void createDatabase(String databaseName) {
        println("Call createDatabase()");

        try {
            database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
            println("Database created: " + databaseName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeQuery() {
        println("Call executeQuery()");

        if(database == null) {
            println("Database should be open");
            return;
        }

        String tableName = editText2.getText().toString();
        if(tableName == null) {
            println("Table name should be specified");
            return;
        }
        try {

            String sql = "SELECT _id, name, age, mobile FROM " + tableName;
            Cursor cursor = database.rawQuery(sql, null);
            int recordCnt = cursor.getCount();
            println("Num of record: "+recordCnt);
            for(int i=0; i<recordCnt; i++) {
                cursor.moveToNext();
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String mobile = cursor.getString(3);

                println("Record#"+i+" : "+id+", "+name+", "+age+", "+mobile);
            }
            cursor.close();


        } catch(Exception e) {
            e.printStackTrace();
            println(e.toString());
        }
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}
