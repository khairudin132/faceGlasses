package com.merge.user.aninterface.mask2D;

import android.content.Intent;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.merge.user.aninterface.ActivityAR;
import com.merge.user.aninterface.R;
import com.merge.user.aninterface.heart.MainHeart;
import com.merge.user.aninterface.oval.MainOval;

public class HatiMuka extends AppCompatActivity {

    private static final String TAG = BujurMuka.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;

    //declare button
    private Button roundMask;
    private Button heartMask;
    private Button squareMask;
    private Button ovalMask;
    private Button select;
    private Button back;
    private ImageView gmbrMuka;
    private Matrix matrix = new Matrix();
    private float scale = 1f;
    private ScaleGestureDetector sgd;
    //---------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hati_muka);
        roundMask = (Button)findViewById(R.id.roundMask);
        heartMask = (Button)findViewById(R.id.heartMask);
        squareMask = (Button)findViewById(R.id.squareMask);
        ovalMask = (Button)findViewById(R.id.ovalMask);
        select = (Button)findViewById(R.id.select);
        back = (Button)findViewById(R.id.back);
        gmbrMuka = (ImageView)findViewById(R.id.hati);
        sgd = new ScaleGestureDetector(this, new HatiMuka.ScaleListener());

        heartMask.setEnabled(false);

        roundMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRoundMask();
            }
        });

        squareMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSquareMask();
            }
        });

        ovalMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOvalMask();
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGlasses();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBack();
            }
        });
    }

    @Override
    public boolean onTouchEvent (MotionEvent ev) {
        sgd.onTouchEvent(ev);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale (ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5.0f));
            matrix.setScale(scale, scale);
            gmbrMuka.setImageMatrix(matrix);
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }



    public void goToRoundMask() {
        Intent intent = new Intent(this, BulatMuka.class);
        String message = "browline glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToSquareMask() {
        Intent intent = new Intent(this, PetakMuka.class);
        String message = "czteye glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToOvalMask() {
        Intent intent = new Intent(this, BujurMuka.class);
        String message = "roundframe glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToGlasses() {
        Intent intent = new Intent(this, MainHeart.class);
        String message = "roundframe glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToBack() {
        Intent intent = new Intent(this, ActivityAR.class);
        String message = "roundframe glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

}
