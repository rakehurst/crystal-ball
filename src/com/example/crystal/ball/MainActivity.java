package com.example.crystal.ball;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private CrystalBall mCrystalBall = new CrystalBall();
	private TextView mAnswerLabel;
	private Button mGetAnswerButton;
	private ImageView mCrystalBallImage;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Assign the Views from the layout file
        mAnswerLabel = (TextView) findViewById(R.id.textView1);
        mGetAnswerButton = (Button) findViewById(R.id.button1);
        mCrystalBallImage = (ImageView) findViewById(R.id.imageView1);
        
        
        mGetAnswerButton.setOnClickListener(new View.OnClickListener() {

        	public void onClick(View v) {
        		String answer = mCrystalBall.getAnAnswer();
        		
        		// Update the label with our dynamic answer
    			mAnswerLabel.setText(answer);
    			
    			animateCrystalBall();
    			animateAnswer();
    			playSound();
    		}
		});
    }
    
    private void animateCrystalBall() {
    	mCrystalBallImage.setImageResource(R.drawable.ball_animation);
    	AnimationDrawable ballAnimation = (AnimationDrawable) mCrystalBallImage.getDrawable();
    	if (ballAnimation.isRunning()) {
    		ballAnimation.stop();
    	}
    		ballAnimation.start();
    }
    
    private void animateAnswer () {
    	AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
    	fadeInAnimation.setDuration(1500);
    	fadeInAnimation.setFillAfter(true);
    	
    	mAnswerLabel.setAnimation(fadeInAnimation);
    }
    
    private void playSound() {
    	MediaPlayer player = MediaPlayer.create(this, R.raw.crystal_ball);
    	player.start();
    	player.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
