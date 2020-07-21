package com.merge.user.aninterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.merge.user.aninterface.mask.MainMask;
import com.merge.user.aninterface.mask2D.MainMuka;

public class ActivityAR extends AppCompatActivity {

    Button takeMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ar);

        takeMe = (Button)findViewById(R.id.takeMe);
        takeMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeAR();
            }
        });
    }

    public void takeAR() {
        Intent intent=new Intent(this, MainMask.class);
        startActivity(intent);
    }
}
