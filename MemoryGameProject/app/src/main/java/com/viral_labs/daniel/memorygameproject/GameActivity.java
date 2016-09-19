package com.viral_labs.daniel.memorygameproject;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatDrawableManager;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private int numberOfElements;

    private int[] buttonImage;
    private int[] buttonImageLocation;

    private ToggleButton selectedButton1;
    private ToggleButton selectedButton2;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameSetup();
    }


    protected void gameSetup(){
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        int numCol = gridLayout.getColumnCount();
        int numRow = gridLayout.getRowCount();

        numberOfElements = numCol * numRow;

        buttonImage = new int[numberOfElements / 2];

        buttonImage[0] = R.drawable.toggle_bunny;
        buttonImage[1] = R.drawable.toggle_elephant;
        buttonImage[2] = R.drawable.toggle_fox;
        buttonImage[3] = R.drawable.toggle_gorilla;
        buttonImage[4] = R.drawable.toggle_maple;
        buttonImage[5] = R.drawable.toggle_penguin;
        buttonImage[6] = R.drawable.toggle_robo;
        buttonImage[7] = R.drawable.toggle_sword;
        buttonImage[8] = R.drawable.toggle_tiger;
        buttonImage[9] = R.drawable.toggle_witch;

        buttonImageLocation = new int[numberOfElements];

        for(int i = 0; i < numberOfElements; i++){
            buttonImageLocation[i] = i % (numberOfElements / 2);
        }

        //Shuffle the second set of drawables
        //Collections.shuffle(Arrays.asList(buttonImageLocation));
        shuffle();

        for(int i=0; i < numRow; i++){
            for(int j=0; j < numCol; j++){
                ToggleButton but = new ToggleButton (this);
                but.setMaxWidth(95);
                but.setMaxHeight(95);
                but.setChecked(false);
                but.setButtonDrawable(buttonImage[buttonImageLocation[i * numCol + j]]);
                but.setTextOff("");
                but.setTextOn("");
                but.setGravity(0);
                but.setTag(buttonImage[buttonImageLocation[i * numCol + j]]);
                but.setId(View.generateViewId());
                but.setOnClickListener(this);



                gridLayout.addView(but);
            }
        }
    }

    protected void shuffle(){
        Random random = new Random();

        for (int i = 0; i < numberOfElements; i++){
            int temp = buttonImageLocation[i];
            int swap = random.nextInt(20);

            buttonImageLocation[i] = buttonImageLocation[swap];
            buttonImageLocation[swap] = temp;
        }
    }

    @Override
    public void onClick(View v) {

        ToggleButton button = (ToggleButton) v;
        TextView theScore = (TextView)findViewById(R.id.playerScore);

        if(((ToggleButton) v).isChecked()){
            System.out.println("This Card Is Flipped");
            System.out.println(button.getId());
        }

        if(selectedButton1 == null){
            selectedButton1 = button;
            //selectedButton1.toggle();
            return;
        }

        if(selectedButton1.getId() == button.getId())
            return;

        if(button.getTag().equals(selectedButton1.getTag())){
            //System.out.println("THESE ARE THE SAME");
            score++;
            theScore.setText(""+score);

            selectedButton1.setEnabled(false);
            button.setEnabled(false);

            selectedButton1.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);

            selectedButton1 = null;

            return;

        } else {
            selectedButton2 = button;
            //selectedButton2.toggle();

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable(){
                @Override
                public void run() {
                    selectedButton1.toggle();
                    selectedButton2.toggle();
                    selectedButton1 = null;
                    selectedButton2 = null;
                    //System.out.println("THESE ARE DIFFERENT");
                }
            }, 500);
        }
    }



}
