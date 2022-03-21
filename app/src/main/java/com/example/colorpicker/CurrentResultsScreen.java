package com.example.colorpicker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import static com.example.colorpicker.MenuScreen.allScores;
import static com.example.colorpicker.MenuScreen.avgPts;

public class CurrentResultsScreen extends AppCompatActivity
{
    //Every block of text has a score and a color in the background of the actual color it was
    private int gamesPlayed;
    private TextView average;
    private Intent goBack;
    private LinearLayout ll;

    private final DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_results_screen);

        initialize();
    }

    public void initialize()
    {
        //ScrollBar and AverageScore TextView
        ll = findViewById(R.id.ll);
        average = findViewById(R.id.average);

        goBack = new Intent( getApplication(), MenuScreen.class );

        addToList();
    }

    public void addToList()
    {
        //this loop adds every score block from the ArrayList to the scrollable list as a text view
        ll.removeAllViews();
        if ( allScores != null )
        {
            for (int i = 0; i < allScores.size(); i++)
            {
                TextView current = new TextView( getApplicationContext() ); //initialized the text view
                current.setGravity( Gravity.CENTER );
                current.setHeight( 200 );
                current.setTextColor( Color.WHITE ); //sets the correct text color
                current.setTextSize( 18 ); //sets the correct text size
                current.setText( String.valueOf( allScores.get(i).getPoints() ) ); //sets text to the current score block pts
                current.setBackgroundColor( allScores.get(i).getColor() ); //sets

                ll.addView( current ); //adds the the text view
            }
        }

        gamesPlayed = getIntent().getIntExtra( "GAMES_PLAYED", 0 );
        if ( gamesPlayed == 0 )
        {
            avgPts = 0;
        }
        String s = "Average: " + df.format( avgPts );
        average.setText( s );
    }

    public void onBackPressed()
    {
        goBack.putExtra( "GAMES_PLAYED", gamesPlayed );
        startActivityForResult( goBack , 0 );
    }
}
