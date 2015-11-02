package de.fhdw.bfws114a.login;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

public class Gui {
	

	private TextView mDisplayTextView;
	private Button mIncrementButton;
	private Button mStartEditActivityButton;

	public Gui(Activity act) {
		act.setContentView(R.layout.login);
		mDisplayTextView = (TextView) act.findViewById(R.id.display);
		mIncrementButton = (Button) act.findViewById(R.id.increment);
		mStartEditActivityButton = (Button) act.findViewById(R.id.start_edit_activity);
	}

	public TextView getDisplayActivity() {
		return mDisplayTextView;
	}

	public Button getIncrementButton() {
		return mIncrementButton;
	}

	public Button getStartEditActivityButton() {
		return mStartEditActivityButton;
	}
	
	public void setCounterValue(int value) {
		mDisplayTextView.setText(String.valueOf(value));
	}
	
}
