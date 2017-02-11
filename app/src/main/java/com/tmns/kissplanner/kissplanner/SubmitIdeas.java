package com.tmns.kissplanner.kissplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SubmitIdeas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
//                myIntent.putExtra("key", value); //Optional parameters

                TextView title = (TextView) findViewById(R.id.editKissIdea);
                System.out.println("title: " + title.getText());


                KissTable KissTable = new KissTable(SubmitIdeas.super.getApplicationContext());
                KissTable.open();
//                KissTable.createKiss(title.getText().toString(), "blah blah blah");
                KissTable.close();

//                retrieveValues();
                startActivity(myIntent);



            }
        });
    }



}
