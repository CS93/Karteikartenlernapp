package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui1 {
	
	private Button mCategories;
	private TextView mQuestion;
	private CheckBox[] mOptions = new CheckBox[6];
	private Activity mActivity;


	public Gui1(Activity act) {
		mActivity = act;
		
		
	}

	public void showThisGui(){
		 mActivity.setContentView(R.layout.activity_challenge_checkbox);
	}
	}
