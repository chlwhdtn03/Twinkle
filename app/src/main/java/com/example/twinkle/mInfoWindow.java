package com.example.twinkle;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class mInfoWindow implements MapboxMap.InfoWindowAdapter {

    private LayoutInflater layoutInflater;
    public  mInfoWindow(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }


    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        Date date = new Date();
        for(Post post : MainActivity.postlist) {
            if (post.getTitle().equals(marker.getTitle()) && post.getSubtitle().equals(marker.getSnippet())
                    && marker.getPosition().equals(new LatLng(post.getX(), post.getY()))) {
                date = new Date(post.getDate());

                break;
            }
        }

        View view = layoutInflater.inflate(R.layout.custominfo, null);
        TextView titleview = view.findViewById(R.id.minfo_title);
        TextView subtitleview = view.findViewById(R.id.minfo_subtitle);
        TextView dateview = view.findViewById(R.id.minfo_date);

        titleview.setText(marker.getTitle());
        subtitleview.setText(marker.getSnippet());
        dateview.setText(new SimpleDateFormat("YYYY-MM.dd").format(date));

        subtitleview.setMaxWidth(800);
        subtitleview.setMinWidth(350);

        return view;
    }
}
