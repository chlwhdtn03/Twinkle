package com.example.twinkle;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomInfoWindow extends AppCompatActivity {

    TextView title, date, locate, subtitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        title = findViewById(R.id.info_title);
        date = findViewById(R.id.info_date);
        locate = findViewById(R.id.info_locate);
        subtitle = findViewById(R.id.info_subtitle);

        String[] info = getIntent().getStringArrayExtra("infos");

        Date time = new Date(info[2]);

        title.setText(info[0]);
        subtitle.setText(info[1]);
        date.setText(new SimpleDateFormat("YYYY년 MM월 dd일 HH:mm").format(time));
        locate.setText(MainActivity.getAddress(getBaseContext(), Double.parseDouble(info[3]), Double.parseDouble(info[4])));

    }

}
