package de.fhdw.bfws114a.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import de.fhdw.bfws114asc.counter1.R;
import android.widget.TextView;

public class Init extends Activity implements OnClickListener, OnSeekBarChangeListener {

	private Button mIncrementButton, mDecrementButton;
	private TextView mDisplayTextView, mDisplaySeekBar;
	private SeekBar mSeekBar;
	private int mSeekbBarProgress;
	private int mCounter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mIncrementButton = (Button) findViewById(R.id.increment);
		mDecrementButton = (Button) findViewById(R.id.decrement);
		mSeekBar = (SeekBar) findViewById(R.id.seekbar);		
		mDisplayTextView = (TextView) findViewById(R.id.display);
		mDisplaySeekBar = (TextView) findViewById(R.id.displaySeekBar);
		mIncrementButton.setOnClickListener(this);
		mDecrementButton.setOnClickListener(this);
		mSeekBar.setOnSeekBarChangeListener(this);
		mSeekbBarProgress = 0;
		mCounter = 0;
	}

	@Override
	public void onClick(View v) {
		if(v == mIncrementButton){
			mCounter += mSeekbBarProgress;
		} else {
			mCounter-= mSeekbBarProgress;
		}
		
		mDisplayTextView.setText(String.valueOf(mCounter));
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		mSeekbBarProgress = progress;
		mDisplaySeekBar.setText(String.valueOf(progress));
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		
	}

}
