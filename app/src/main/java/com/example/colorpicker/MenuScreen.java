package com.example.colorpicker;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//https://stackoverflow.com/questions/6533942/adding-gif-image-in-an-imageview-in-android

/**
 * @author Kayla Tucker
 * @version 1/23/20
 * App is fully functional! hooray!
 */
public class MenuScreen extends AppCompatActivity
{
    public static ArrayList<ScoreBlock> allScores;
    public static double avgPts, sumPts;
    private int gamesPlayed;
    Button resultsButton, beginButton;
    Intent resultsIntent, beginIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finds and sets all necessary variables
        initialize();
    }

    public void initialize()
    {
        //Buttons
        resultsButton = findViewById(R.id.currentResultsButton);
        beginButton = findViewById(R.id.beginButton);

        //Intents
        resultsIntent = new Intent( getApplication(), CurrentResultsScreen.class );
        beginIntent = new Intent( getApplication(), EnterScreen.class  );

        //Sets variables based on if this is the first activity started or not
        ComponentName previousActivity  = getCallingActivity();
        if ( previousActivity == null )
        {
            allScores = new ArrayList<>();
            gamesPlayed = 0;
        }
        else
        {
            //If the intent came from the CurrentResultsScreen class, initialize these too
            gamesPlayed = getIntent().getIntExtra("GAMES_PLAYED", 0);
        }
    }

    public void showResults( View v )
    {
        resultsIntent.putExtra("GAMES_PLAYED", gamesPlayed);
        startActivity( resultsIntent );
    }
    public void beginGame( View v )
    {
        beginIntent.putExtra("GAMES_PLAYED", gamesPlayed);
        startActivity( beginIntent );
    }

    @Override
    public void onBackPressed()
    {

    }
}