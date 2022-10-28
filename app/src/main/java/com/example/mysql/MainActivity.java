package com.example.mysql;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.TextView;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    TextView text,errorText;

    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text = (TextView) findViewById(R.id.textView);

        errorText = (TextView) findViewById(R.id.textView2);

        show = (Button) findViewById(R.id.button);

        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new Async().execute();
            }
        });

    }

    class Async extends AsyncTask<Void, Void, Void> {

        String records = "",error="";

        @Override

        protected Void doInBackground(Void... voids) {

            Connection connection = null;

            try
            {
                String url1 = "jdbc:mysql://localhost:3306/sample";
                String user = "root";
                String password = "1234";
                connection = DriverManager.getConnection(url1,user,password);

                if(connection!=null) {

                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM paper");

                    while (resultSet.next()) {

                        //  text.setText("success");
                        records += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";
                    }
                }
                else{

                    text.setText("error");

                }
            }
            catch(Exception e)
            {
                error = e.toString();
            }

            return null;
        }

        @Override

        protected void onPostExecute(Void aVoid) {

            text.setText(records);

            if(error != "")

                errorText.setText(error);

            super.onPostExecute(aVoid);

        }





    }

}