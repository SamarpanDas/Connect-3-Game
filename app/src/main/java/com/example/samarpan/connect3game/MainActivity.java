package com.example.samarpan.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    // 0 : yellow
    // 1 : red
    // 2: empty

    int active = 0;
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int winningPositions[][] =  {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameActive = true;

    public void play(View view)
    {
        Log.i("Info: ", "Game Started");

        ImageView counter = (ImageView) view;
        Log.i("Tag", counter.getTag().toString());

        Integer tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive)
        {

            gameState[tappedCounter] = active;

            //counter.setY(-1500);   // setY() moves the image with reference to 0,0
            counter.setTranslationY(-1500); //setTranslationY() with reference to its default view.

            if (active == 0) {
                counter.setImageResource(R.drawable.yellow);  //this is the procedure of setting a certain image at a particular position
                active = 1;
            } else if (active == 1) {
                counter.setImageResource(R.drawable.red);
                active = 0;
            }


            counter.animate().translationYBy(1500).rotationBy(1080).setDuration(800);

            for (int winningPosition[] : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && (gameState[winningPosition[0]] != 2)) {
                    {
                        String winner = "";
                        gameActive = false;
                        if (active == 0)
                            //Toast.makeText(this, "Red has won", Toast.LENGTH_SHORT).show();
                            winner = "Red";

                        else if (active == 1)
                            //Toast.makeText(this, "Yellow has won", Toast.LENGTH_SHORT).show();
                            winner = "Yellow";


                        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                        winnerTextView.setText(winner + " has won");

                        playAgainButton.setVisibility(View.VISIBLE);
                        winnerTextView.setVisibility(View.VISIBLE);

                    }

                }


            }
        }


    }

    public  void playAgain(View view)
    {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        //winnerTextView.setText(winner + " has won");

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i<gridLayout.getChildCount();i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        active = 0;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i] = 2;
        }

         gameActive = true;



    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
