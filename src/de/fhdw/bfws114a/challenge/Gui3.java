package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui3 {
	
	private TextView mQuestion;
	private Activity mActivity;
	private Button mContinue;


	public Gui3(Activity act) {
		mActivity = act;
		act.setContentView(R.layout.activity_challenge_without_option);
		mQuestion = (TextView) act.findViewById(R.id.t_question_challenge_without_option);
		mContinue = (Button) act.findViewById(R.id.b_continue_statistic);
	}

	public void showThisGui(){
		 mActivity.setContentView(R.layout.activity_challenge_without_option);
	}

	public TextView getQuestion() {
		return mQuestion;
	}

	public Activity getActivity() {
		return mActivity;
	}

	public Button getContinue() {
		return mContinue;
	}
}
