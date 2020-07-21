package com.merge.user.aninterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.merge.user.aninterface.heart.MainHeart;
import com.merge.user.aninterface.oval.MainOval;
import com.merge.user.aninterface.round.MainRound;
import com.merge.user.aninterface.square.MainSquare;

public class SelectFaceShape extends AppCompatActivity {

    private Button roundFaceBtn;
    private Button squareFaceBtn;
    private Button heartFaceBtn;
    private Button ovalFaceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_face_shape);
        roundFaceBtn = (Button)findViewById(R.id.roundFaceBtn);
        squareFaceBtn = (Button)findViewById(R.id.squareFaceBtn);
        heartFaceBtn = (Button)findViewById(R.id.heartFaceBtn);
        ovalFaceBtn = (Button)findViewById(R.id.ovalFaceBtn);

        roundFaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRoundGlasses();
            }
        });

        squareFaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSquareGlasses();
            }
        });

        heartFaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHeartGlasses();
            }
        });

        ovalFaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOvalGlasses();
            }
        });

    }

    public void goToRoundGlasses() {
        Intent intent = new Intent(this, MainRound.class);
        String message = "round glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToSquareGlasses() {
        Intent intent = new Intent(this, MainSquare.class);
        String message = "square glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToHeartGlasses() {
        Intent intent = new Intent(this, MainHeart.class);
        String message = "heart glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToOvalGlasses() {
        Intent intent = new Intent(this, MainOval.class);
        String message = "oval glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }
}
