package com.example.twinkle;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Write extends AppCompatActivity implements View.OnClickListener {

    Button submit;
    EditText title;
    EditText subtitle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        title = findViewById(R.id.title);
        subtitle = findViewById(R.id.subtitle);
        submit = findViewById(R.id.submitbtn);
        submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(title.getText().toString().isEmpty())
            return;
        if(subtitle.getText().toString().isEmpty())
            return;
        postFirebaseDatabase(true);
        finish();
    }

    public void postFirebaseDatabase(boolean add){
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if(add){
            Date date = new Date();
            date.setTime(Calendar.getInstance().getTimeInMillis());
            Post post = new Post(title.getText().toString(), subtitle.getText().toString(), date.toString(), MainActivity.x, MainActivity.y);
            postValues = post.toMap();
        }


        childUpdates.put("/"+hashCode()+"/"+MainActivity.idByTelephonyManager+"/" , postValues);
        MainActivity.databaseReference.updateChildren(childUpdates);
    }

}
