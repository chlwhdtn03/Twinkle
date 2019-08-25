package com.example.twinkle;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return MainActivity.postlist.size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.postlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlist,null);
        TextView title = v.findViewById(R.id.list_title);
        TextView locate = v.findViewById(R.id.list_locate);
        TextView date = v.findViewById(R.id.list_date);
        title.setText(MainActivity.postlist.get(position).getTitle());
        locate.setText(getAddress(v.getContext(), MainActivity.postlist.get(position).getX(),MainActivity.postlist.get(position).getY()));
        date.setText(new SimpleDateFormat("YYYY-MM.dd").format(new Date(MainActivity.postlist.get(position).getDate())));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraPosition cam = new CameraPosition.Builder()
                        .target(new LatLng(MainActivity.postlist.get(position).getX(), MainActivity.postlist.get(position).getY()))
                        .zoom(16)
                        .build();
                MainActivity.mapboxMap.setCameraPosition(cam);
            }
        });
        return v;
    }

    public static String getAddress(Context mContext, double lat, double lng) {
        String nowAddress ="해당 위치를 확인 할 수 없습니다.";
        Geocoder geocoder = new Geocoder(mContext, Locale.KOREA);
        List<Address> address;
        try {
            if (geocoder != null) {
                //세번째 파라미터는 좌표에 대해 주소를 리턴 받는 갯수로
                //한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 최대갯수 설정
                address = geocoder.getFromLocation(lat, lng, 1);

                if (address != null && address.size() > 0) {
                    // 주소 받아오기
                    String currentLocationAddress = address.get(0).getAddressLine(0).toString();
                    nowAddress  = currentLocationAddress;

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return nowAddress;
    }
}
