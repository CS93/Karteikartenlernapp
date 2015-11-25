package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui2 {
	
	private TextView mQuestion;
	private EditText mUserAnswer;
	private Activity mActivity;
	private Button mContinue;


	public Gui2(Activity act) {
		mActivity = act;
		mQuestion = (TextView) act.findViewById(R.id.t_question_challenge_text);
		mUserAnswer = (EditText) act.findViewById(R.id.et_answer_challenge_text);
		mContinue = (Button) act.findViewById(R.id.b_continue_challenge_text);
	}

	public void showThisGui(){
		 mActivity.setContentView(R.layout.activity_challenge_text);
	}

	public TextView getQuestion() {
		return mQuestion;
	}	

	public EditText getUserAnswer() {
		return mUserAnswer;
	}

	public Activity getActivity() {
		return mActivity;
	}

	public Button getContinue() {
		return mContinue;
	}
}
