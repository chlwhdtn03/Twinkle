package com.example.twinkle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomInfoWindow extends AppCompatActivity {

    TextView title, date, locate, subtitle;
    Button cancel, delete, findroutebtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        title = findViewById(R.id.info_title);
        date = findViewById(R.id.info_date);
        locate = findViewById(R.id.info_locate);
        subtitle = findViewById(R.id.info_subtitle);

        cancel = findViewById(R.id.info_cancel);
        delete = findViewById(R.id.info_delete);
        final String[] info = getIntent().getStringArrayExtra("infos");

        Date time = new Date(info[2]);

        title.setText(info[0]);
        subtitle.setText(info[1]);
        date.setText(new SimpleDateFormat("YYYY년 MM월 dd일 HH:mm").format(time));
        locate.setText(MainActivity.getAddress(getBaseContext(), Double.parseDouble(info[3]), Double.parseDouble(info[4])));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CustomInfoWindow.this)
                        .setTitle(info[0] + " 삭제 절차")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setIcon(android.R.drawable.ic_menu_save)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // 확인시 처리 로직
                                Map<String, Object> childUpdates = new HashMap<>();
                                Map<String, Object> postValues = null;
                                childUpdates.put("/" + info[5] + "/" + MainActivity.idByTelephonyManager + "/", postValues);
                                MainActivity.databaseReference.updateChildren(childUpdates);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // 취소시 처리 로직

                            }
                        })
                        .show();
            }
        });


    }

}
