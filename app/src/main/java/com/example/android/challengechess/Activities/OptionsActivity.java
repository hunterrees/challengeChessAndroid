package com.example.android.challengechess.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.android.challengechess.Model.UserDataHolder;
import com.example.android.challengechess.R;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ImageButton chooseClassic = (ImageButton) findViewById(R.id.chooseClassic);
        chooseClassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDataHolder holder = UserDataHolder.getInstance();
                holder.setChosenType(1);
            }
        });
        ImageButton chooseMaya = (ImageButton) findViewById(R.id.chooseMaya);
        chooseMaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDataHolder holder = UserDataHolder.getInstance();
                holder.setChosenType(2);
            }
        });
        ImageButton chooseNormal = (ImageButton) findViewById(R.id.chooseNormal);
        chooseNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDataHolder holder = UserDataHolder.getInstance();
                holder.setChosenType(3);
            }
        });
    }
}
