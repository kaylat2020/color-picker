package com.example.colorpicker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;

import static com.example.colorpicker.MenuScreen.avgPts;

public class ResultScreen extends AppCompatActivity
{
    private Intent intentNewColor, intentMainMenu;
    private TextView rText, gText, bText, rActual, gActual, bActual, dText;
    private GifImageView gif;
    private int c, gamesPlayed;
    private double pts;
    private int[] rgbGuess;


    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        initialize();
        setBackgroundColor(c);
        setTexts();
    }

    public void initialize()
    {
        rText = findViewById(R.id.rText2);
        gText = findViewById(R.id.gText2);
        bText = findViewById(R.id.bText2);
        rActual = findViewById(R.id.rActual);
        gActual = findViewById(R.id.gActual);
        bActual = findViewById(R.id.bActual);
        dText = findViewById(R.id.distanceText);
        intentMainMenu = new Intent( getApplication(), MenuScreen.class );
        intentNewColor = new Intent( getApplication(), EnterScreen.class );
        gif = findViewById( R.id.gif );

        gamesPlayed = getIntent().getIntExtra("GAMES_PLAYED", 0);
        pts = getIntent().getDoubleExtra("POINTS", 1);
        c = getIntent().getIntExtra("COLOR", 0);
        rgbGuess = getIntent().getIntArrayExtra("RGB_GUESS");
    }

    public void setTexts()
    {
        rText.setText( String.valueOf(rgbGuess[0]) );
        gText.setText( String.valueOf(rgbGuess[1]) );
        bText.setText( String.valueOf(rgbGuess[2]) );
        rActual.setText( String.valueOf(Color.red(c)) );
        gActual.setText( String.valueOf(Color.green(c)) );
        bActual.setText( String.valueOf(Color.blue(c)) );

        String s = "Your guess was a distance of "+ pts +" off!\n";
        if ( pts > avgPts )
        {
            String s2 = "Worse than your average";
            s += s2;

            dText.setText( s );
            gif.setImageResource( R.drawable.wasted_gif );
        }
        else
        {
            String s2 = "Better than your average";
            s += s2;

            dText.setText( s );
            gif.setImageResource( R.drawable.happy_shocked_meme );
        }
    }

    public void setBackgroundColor( int color )
    {
        getWindow().getDecorView().setBackgroundColor( color );
    }

    public void onMainMenuClick( View v )
    {
        intentMainMenu.putExtra("GAMES_PLAYED", gamesPlayed);
        //So that the MenuScreen knows a game has been played
        startActivityForResult( intentMainMenu, 0);
    }
    public void onNewColorClick( View v )
    {
        intentNewColor.putExtra("GAMES_PLAYED", gamesPlayed);
        startActivity( intentNewColor );
    }
    @Override
    public void onBackPressed()
    {

    }
}