package com.example.fyp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.fyp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class fragPM25 extends Fragment {

    public fragPM25() {
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
        View root = inflater.inflate(R.layout.fragment_pm25, container, false);
        TextView mText = root.findViewById(R.id.section_label);
        mText.setText("25");

        return root;
    }

}