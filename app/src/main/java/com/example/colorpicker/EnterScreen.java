package com.example.colorpicker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Random;

import static com.example.colorpicker.MenuScreen.allScores;
import static com.example.colorpicker.MenuScreen.avgPts;
import static com.example.colorpicker.MenuScreen.sumPts;

public class EnterScreen extends AppCompatActivity
{
    private int r, g, b, c, gamesPlayed;
    private double pts;
    private EditText rText, gText, bText;
    private int[] rgbGuess;
    private Intent intentNext;
    private final DecimalFormat df = new DecimalFormat("#.00");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_screen);

        initialize();
        c = generateColor();
        setBackgroundColor( c );
    }

    public void initialize()
    {
        rText = findViewById(R.id.rText);
        gText = findViewById(R.id.gText);
        bText = findViewById(R.id.bText);
        intentNext = new Intent( getApplicationContext(), ResultScreen.class );
        rgbGuess = new int[3];

        gamesPlayed = getIntent().getIntExtra("GAMES_PLAYED", 0);
    }

    public int generateColor()
    {
        //generates 3 ints between 0-255
        r = new Random().nextInt( 256 );
        g = new Random().nextInt( 256 );
        b = new Random().nextInt( 256 );

        //returns the int value to be used on the screen
        return Color.rgb(r,g,b);
    }
    public void setBackgroundColor( int color )
    {
        getWindow().getDecorView().setBackgroundColor( color );
    }

    //These methods are used to calculate the score
    public double findDistance( int r1, int g1, int b1, int r2, int g2, int b2 )
    {
        return ( Math.abs(r2-r1) + Math.abs(g2-g1) + Math.abs(b2-b1) );
    }
    public int getRguess()
    {
        if ( ! rText.getText().toString().matches("") )
        {
            rgbGuess[0] = Integer.parseInt(rText.getText().toString());
            return rgbGuess[0];
        }
        return -1;
    }
    public int getGguess()
    {
        if ( ! gText.getText().toString().matches("") )
        {
            rgbGuess[1] = Integer.parseInt(gText.getText().toString());
            return rgbGuess[1];
        }
        return -1;
    }
    public int getBguess()
    {
        if ( ! bText.getText().toString().matches("") )
        {
            rgbGuess[2] = Integer.parseInt(bText.getText().toString());
            return  rgbGuess[2];
        }
        return -1;
    }
    public double getPoints()
    {
        pts = Double.parseDouble
                ( df.format( findDistance( r, g, b, getRguess(), getGguess(), getBguess() ) ) );
        return pts;
    }

    public void onSubmitClick( View v )
    {
        //Checks to see if the input is valid before continuing
        if ( !(getRguess()>255 || getGguess()>255 || getBguess()>255 ||
                  getRguess()<0 || getGguess()<0 || getBguess()<0) )
        {
            //Updates all game variables
            pts = getPoints();
            sumPts += pts;
            gamesPlayed++;
            avgPts = sumPts/(double)gamesPlayed;

            //adds a ScoreBlock to the score list
            ScoreBlock scoreBlock = new ScoreBlock( String.valueOf(pts), String.valueOf(c) );
            allScores.add( scoreBlock );

            intentNext.putExtra("GAMES_PLAYED", gamesPlayed);
            //Sends the new input info
            intentNext.putExtra("COLOR", c);
            intentNext.putExtra("POINTS", pts);
            intentNext.putExtra("RGB_GUESS", rgbGuess);
            
            startActivity(intentNext);
        }
        else
        {
            Toast.makeText( getApplicationContext(),
                    "Please enter valid values!\n(0-255)", Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public void onBackPressed()
    {

    }
}