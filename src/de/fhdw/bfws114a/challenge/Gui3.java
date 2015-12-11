/**
 * @author Carsten Schlender
 */
 
package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui3 {
	
	private TextView mQuestionView;
	private Activity mActivity;
	private Button mContinueButton;


	public Gui3(Activity act) {
		mActivity = act;
		act.setContentView(R.layout.activity_challenge_without_option);
		mQuestionView = (TextView) act.findViewById(R.id.t_question_challenge_without_option);
		mContinueButton = (Button) act.findViewById(R.id.b_continue_statistic);
	}

	public TextView getQuestionView() {
		return mQuestionView;
	}

	public Activity getActivity() {
		return mActivity;
	}

	public Button getContinue() {
		return mContinueButton;
	}
}
