package com.example.fyp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.fyp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;

public class fragControlChild extends Fragment {

    Integer crollerProgress;
    Croller mCroller;
    FloatingActionButton plusButton;
    FloatingActionButton minusButton;

    public fragControlChild() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_control_child, container, false);

        mCroller = root.findViewById(R.id.croller);
        plusButton = root.findViewById(R.id.plusButton);
        minusButton = root.findViewById(R.id.minusButton);

        mCroller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                crollerProgress = mCroller.getProgress() + 15;
                mCroller.setLabel(crollerProgress.toString());
            }

            @Override
            public void onStartTrackingTouch(Croller croller) {

            }

            @Override
            public void onStopTrackingTouch(Croller croller) {

            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(crollerProgress>=30 || mCroller.getProgress() >= mCroller.getMax()) {
                    crollerProgress = 30;
                    mCroller.setProgress(mCroller.getMax());
                }else{
                    crollerProgress ++;
                    mCroller.setProgress(mCroller.getProgress()+ 1);
                }
                mCroller.setLabel(crollerProgress.toString());
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(crollerProgress<=16 || mCroller.getProgress() <= mCroller.getMin()) {
                    crollerProgress = 16;
                    mCroller.setProgress(mCroller.getMin());
                }else{
                    crollerProgress --;
                    mCroller.setProgress(mCroller.getProgress()- 1);
                }
                mCroller.setLabel(crollerProgress.toString());
            }
        });

        return root;
    }

}
