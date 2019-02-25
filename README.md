# Assignment 2
## Roshambo 
**ro-sham-bo** AKA rock, paper, siccors is a popular game. You will write an Android implementation of the game. The user picks their move by clicking on an ImageView representing each possible move. (if you implement rock, paper, scissors, lizard, Spock, you will have to modify the Model). You can use the provided images, or your own. 

![](./images/Rock-paper-scissors.png) 

* create your android studio project in this repository and push it on the master branch to submit. 
* use the MVC pattern 
* use the given java class Roshambo.java as your model. 
* use clickable ImageViews for the player to select their next move. 
* Display the game result, both what the game chose as it's move and the game result win, lose, or draw. 
* you can use [property animations](https://developer.android.com/training/animation/overview) to make the game more interactive. 
* COMMENTS
	* You must have a header comment with your name and the date in every file that you modify
	* Any methods that are not obvious must have comments
	* identifier names, variables, classes, functions, must be descriptive and meaningful
	* Any method that is more than 15 lines long must have comments (hint, if you make your methods shorter you don't need comments)


You can use ObjectAnimator to animate any View property that there is a setter for. In this example ```player``` and ```game``` are instances of ImageView and their rotation is being animated. An animation set is being used so that the two animations can be run concurently. An interpolator is being used to give add a windup and overshoot effect to the animation. 

```
        // you can animate any prop that there is a setter for
        // player.setRotationX(0f);

        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(player,
                "rotationX", 0f, 360f)
                .setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(game,
                "rotationY", 0f, 360f)
                .setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();
```