package com.example.android.challengechess.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.challengechess.Model.GameModel;
import com.example.android.challengechess.R;
import com.example.android.challengechess.SquareAdapter;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boardview);

        GameModel gameModel = new GameModel(this, false, false);
        Button forfeit = (Button) findViewById(R.id.forfeit);
        forfeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDialog();
            }
        });

        GridView gridview = (GridView) findViewById(R.id.boardview);
        SquareAdapter squareAdapter = new SquareAdapter(this,gameModel,gridview,true);
        gridview.setAdapter(squareAdapter);
        gameModel.setSquareAdapter(squareAdapter);


    }

    private void makeDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(GameActivity.this);

        alert.setTitle("Forfeit?");
        alert.setMessage("Are you sure?");

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                startMenuActivity();
                // Do something with value!
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
    }

    private void startMenuActivity()
    {
        finish();
    }
}
