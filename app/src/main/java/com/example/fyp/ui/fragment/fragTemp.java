package com.example.fyp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fyp.R;
import com.example.fyp.helper.MyMarkerView;
import com.example.fyp.helper.generalData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class fragTemp extends Fragment {

    private LineChart chart;
    private DatabaseReference mDatabaseReference;
    private generalData data = new generalData();
    private ArrayList<generalData> mGeneralData =new ArrayList<>();
    private XAxis xAxis;
    private Long referenceTimestamp;

    public fragTemp() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_temp, container, false);
        TextView mText = root.findViewById(R.id.section_label);
        mText.setText("TEMP");

        chart = root.findViewById(R.id.chart1);

        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        chart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);
        chart.setHighlightPerDragEnabled(true);

        // set an alternative background color
        chart.setBackgroundColor(Color.WHITE);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();
        l.setEnabled(false);

        xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        xAxis.setTextSize(10f);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(true);
        xAxis.setCenterAxisLabels(true);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setDrawGridLines(true);
        leftAxis.setYOffset(-9f);
        leftAxis.setTextColor(Color.rgb(255, 192, 56));

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query mQuery = mDatabaseReference.child("room1").child("Temperature").orderByChild("timestamp").limitToLast(720);
        mQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mGeneralData.add(dataSnapshot.getValue(generalData.class));

                ArrayList<Entry> mEntries = new ArrayList<>();
                referenceTimestamp = mGeneralData.get(0).getTimestamp()/1000;

                xAxis.setValueFormatter(new ValueFormatter() {

                    private final SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

                    @Override
                    public String getFormattedValue(float value) {

                        // convertedTimestamp = originalTimestamp - referenceTimestamp
                        Long convertedTimestamp = (long) value;


                        // Retrieve original timestamp
                        Long originalTimestamp = referenceTimestamp + convertedTimestamp;
                        Log.d("time", "getFormattedValue: " + originalTimestamp);

                        String temp = mFormat.format(new Date(originalTimestamp*1000));
                        Log.d("time", "getFormattedValue: " + temp);

                        // Convert timestamp to hour:minute
                        return temp;
                    }
                });

                for(int x = 0; x<mGeneralData.size(); x++){
                    mEntries.add(new Entry(((mGeneralData.get(x).getTimestamp()/1000)-referenceTimestamp), mGeneralData.get(x).getData()));

                }

                LineDataSet dataSet = new LineDataSet(mEntries, "Temperature");
                LineData lineData = new LineData(dataSet);
                lineData.setDrawValues(false);
                MyMarkerView myMarkerView= new MyMarkerView(getActivity(), R.layout.custom_marker_view, referenceTimestamp, "Temperature");
                chart.setMarker(myMarkerView);
                chart.setData(lineData);
                chart.invalidate();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return root;
    }

}