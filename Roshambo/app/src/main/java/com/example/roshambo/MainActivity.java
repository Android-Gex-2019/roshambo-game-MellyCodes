/**
 * @file MainActivity.java
 * @project Roshambo
 * @author Melanie Roy-Plommer
 * @version 1.0
 *
 * @section DESCRIPTION
 * <  Roshambo game (Rock, Paper, Scissor) >
 *
 * @section LICENSE
 * Copyright 2018 - 2019
 * Permission to use, copy, modify, and/or distribute this software for
 * any purpose with or without fee is hereby granted, provided that the
 * above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * @section Academic Integrity
 * I certify that this work is solely my own and complies with
 * NBCC Academic Integrity Policy (policy 1111)
 */

package com.example.roshambo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // new game
    private Rochambo rochambo = new Rochambo();

    // Create ImageView and TextView variables
    private ImageView player;
    private ImageView computer;

    private TextView player_text;
    private TextView computer_text;
    private TextView result;

    /**
     * Initialize variables upon creation of activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = findViewById(R.id.playerResult_image);
        computer = findViewById(R.id.computerResult_Image);
        player_text = findViewById(R.id.playerResult_text);
        computer_text = findViewById(R.id.computerResult_text);
        result = findViewById(R.id.win_lose_draw_text);
    }


    /**
     * Playing the game. Gets appropriate tag from player's button press
     * Sets the choice, and then gets the computer's random choice
     * Sets the text property of the result TextView
     * animates the images
     * @param view
     */
    public void Play(View view){
        String choice = view.getTag().toString();

        PlayersChoice(choice);
        GamesChoice();

        // set result text
        result.setText(rochambo.winLoseOrDraw());
        // anminate images
        animateImages(player, computer);

    }


    /**
     * Sets the player's choice according to the tag in the button pressed
     * @param choice
     */
    public void PlayersChoice(String choice){

        switch (choice){
            case "rock":
                rochambo.playerMakesMove(Rochambo.ROCK);
                player.setImageResource(R.drawable.rock);
                break;
            case "paper":
                rochambo.playerMakesMove(Rochambo.PAPER);
                player.setImageResource(R.drawable.paper);
                break;
            case "scissors":
                rochambo.playerMakesMove(Rochambo.SCISSOR);
                player.setImageResource(R.drawable.scissors);
                break;
            default:
                break;
        }
        player_text.setText(choice);
    }

    /**
     * Gets random choice from the model and sets image and text
     * according to choice
     */
    public void GamesChoice(){
        int gameMove = rochambo.getGameMove();

        switch(gameMove){
            case 0:
                computer.setImageResource(R.drawable.rock);
                computer_text.setText(getString(R.string.computer_rock));
                break;
            case 1:
                computer.setImageResource(R.drawable.paper);
                computer_text.setText(getString(R.string.computer_paper));
                break;
            case 2:
                computer.setImageResource((R.drawable.scissors));
                computer_text.setText(getString(R.string.computer_scissors));
                break;
            default:
                break;
        }
    }


    /**
     * image animator function
     * @param player
     * @param computer
     */
    public void animateImages(ImageView player, ImageView computer){

        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(player,
                "rotationX", 0f, 360f)
                .setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(computer,
                "rotationY", 0f, 360f)
                .setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();
    }

}
