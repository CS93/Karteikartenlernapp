package de.fhdw.bfws114a.solution;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui3 {
	
	private TextView mQuestionView;
	private TextView mAnswerView;
	private Activity mActivity;
	private Button mContinueCorrectButton, mContinueIncorrectButton;


	public Gui3(Activity act) {
		mActivity = act;
		act.setContentView(R.layout.activity_challenge_without_option_answer);
		mQuestionView = (TextView) act.findViewById(R.id.t_question_challenge_without_option_answer);
		mAnswerView = (TextView) act.findViewById(R.id.t_correct_answer_without_option_answer);
		mContinueCorrectButton = (Button) act.findViewById(R.id.b_continue_challenge_correct_yes_without_option_answer);
		mContinueIncorrectButton = (Button) act.findViewById(R.id.b_continue_challenge_correct_no_without_option_answer);
	}

	public TextView getQuestionView() {
		return mQuestionView;
	}
	
	public TextView getAnswerView() {
		return mAnswerView;
	}

	public Activity getActivity() {
		return mActivity;
	}

	public Button getContinueCorrectButton() {
		return mContinueCorrectButton;
	}
	
	public Button getContinueIncorrectButton() {
		return mContinueIncorrectButton;
	}
	
}
