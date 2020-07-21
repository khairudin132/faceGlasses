package com.merge.user.aninterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class ActivityFaceShape extends AppCompatActivity {

    private CardView roundCard, squareCard, heartCard, ovalCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_card_view);

        //reference
        roundCard = (CardView)findViewById(R.id.roundFaceCard);
        squareCard = (CardView)findViewById(R.id.squareFaceCard);
        heartCard = (CardView)findViewById(R.id.heartFaceCard);
        ovalCard = (CardView)findViewById(R.id.ovalFaceCard);

        roundCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundDes();
            }
        });

        squareCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                squareDes();
            }
        });

        heartCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartDes();
            }
        });

        ovalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ovalDes();
            }
        });
    }

    public void roundDes(){
        Intent intent=new Intent(this, roundClass.class);
        startActivity(intent);
    }

    public void squareDes(){
        Intent intent=new Intent(this, squareClass.class);
        startActivity(intent);
    }

    public void heartDes(){
        Intent intent=new Intent(this, heartClass.class);
        startActivity(intent);
    }

    public void ovalDes(){
        Intent intent=new Intent(this, ovalClass.class);
        startActivity(intent);
    }
}
