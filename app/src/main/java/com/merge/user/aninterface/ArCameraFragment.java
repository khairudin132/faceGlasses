package com.merge.user.aninterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ArCameraFragment extends Fragment {

    private Button takeMeThere;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ar, container, false);

        takeMeThere = (Button)view.findViewById(R.id.takeMe);
        takeMeThere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.takeMe:
                        Intent myIntent = new Intent(getActivity(), SelectFaceShape.class);
                        startActivity(myIntent);
                        break;
                }

            }
        });
        return view;
    }


}
