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

    private Rochambo rochambo = new Rochambo();
    private ImageView player;
    private ImageView computer;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = findViewById(R.id.playerResult_image);
        computer = findViewById(R.id.computerResult_Image);
        result = findViewById(R.id.win_lose_draw_text);
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

    }

    public void GamesChoice(){
        int gameMove = rochambo.getGameMove();

        switch(gameMove){
            case 0:
                computer.setImageResource(R.drawable.rock);
                break;
            case 1:
                computer.setImageResource(R.drawable.paper);
                break;
            case 2:
                computer.setImageResource((R.drawable.scissors));
                break;
            default:
                break;
        }
    }


    /**
     *
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
