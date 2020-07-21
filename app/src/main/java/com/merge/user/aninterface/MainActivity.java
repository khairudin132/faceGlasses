package com.merge.user.aninterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {


    ViewFlipper v_flipper;
    CardView selectFaceShape, selectAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int imageFaces[]={R.drawable.informative, R.drawable.vrtual, R.drawable.offline};

        v_flipper=findViewById(R.id.image_slider);
        for(int image:imageFaces) {
            flipperImage(image);
        }

        selectFaceShape = (CardView) findViewById(R.id.selectFaceShape);
        selectFaceShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faceShape();
            }
        });

        selectAR = (CardView) findViewById(R.id.selectAR);
        selectAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AR();
            }
        });
    }

    public void flipperImage(int image) {
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    public void faceShape() {
        Intent intent=new Intent(this, ActivityFaceShape.class);
        startActivity(intent);
    }

    public void AR() {
        Intent intent=new Intent(this, ActivityAR.class);
        startActivity(intent);
    }
}
