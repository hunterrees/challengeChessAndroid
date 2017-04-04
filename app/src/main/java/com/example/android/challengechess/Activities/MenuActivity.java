package com.example.android.challengechess.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.challengechess.Model.UserDataHolder;
import com.example.android.challengechess.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button versusButton = (Button) findViewById(R.id.versusButton);
        versusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                versus();
            }
        });
        Button optionButton = (Button) findViewById(R.id.optionsButton);
        Button computerButton = (Button) findViewById(R.id.computerButton);
        computerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startComputerActivity();
            }
        });
        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOptionActivity();
            }
        });
    }
    private void versus()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Versus");
        alert.setMessage("Enter Player ID:");

// Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                startGameActivity();
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
    private void startGameActivity()
    {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
    private void startOptionActivity()
    {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }
    private void startComputerActivity()
    {
        Intent intent = new Intent(this, GameActivity.class);
        UserDataHolder holder = UserDataHolder.getInstance();
        holder.isPlayingAgainstAI = true;
        startActivity(intent);
    }
    private void startLearningActivity()
    {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

}
