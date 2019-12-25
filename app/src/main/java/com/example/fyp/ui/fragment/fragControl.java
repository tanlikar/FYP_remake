package com.example.fyp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fyp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class fragControl extends Fragment {

    public fragControl() {
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
        View root = inflater.inflate(R.layout.fragment_control, container, false);

        ImageButton mTherButton = root.findViewById(R.id.thermoImgButton);
        ImageButton mFanButton = root.findViewById(R.id.fanImgButton);

        mTherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getActivity(), "ther", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        mFanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getActivity(), "fan", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment childFragment = new fragControlChild();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.childFragContainer, childFragment).commit();
    }

}