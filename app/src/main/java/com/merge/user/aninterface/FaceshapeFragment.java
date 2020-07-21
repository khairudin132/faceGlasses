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

public class FaceshapeFragment extends Fragment implements View.OnClickListener {

    private CardView roundCard, squareCard, heartCard, ovalCard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_card_view, container, false);

        //reference
        roundCard = (CardView) view.findViewById(R.id.roundFaceCard);
        squareCard = (CardView) view.findViewById(R.id.squareFaceCard);
        heartCard = (CardView) view.findViewById(R.id.heartFaceCard);
        ovalCard = (CardView) view.findViewById(R.id.ovalFaceCard);

        //onclicklistener
        roundCard.setOnClickListener(this);
        squareCard.setOnClickListener(this);
        heartCard.setOnClickListener(this);
        ovalCard.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {
            case R.id.roundFaceCard : i = new Intent(getActivity(), roundClass.class); startActivity(i); break;
            case R.id.squareFaceCard : i = new Intent(getActivity(), squareClass.class); startActivity(i); break;
            case R.id.heartFaceCard : i = new Intent(getActivity(), heartClass.class); startActivity(i); break;
            case R.id.ovalFaceCard : i = new Intent(getActivity(), ovalClass.class); startActivity(i); break;
            default: break;
        }


    }
}
