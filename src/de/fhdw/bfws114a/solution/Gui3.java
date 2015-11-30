package de.fhdw.bfws114a.solution;

import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui3 {
	
	private TextView mQuestion;
	private TextView mAnswer;
	private Activity mActivity;
	private Button mContinueCorrect, mContinueIncorrect;


	public Gui3(Activity act) {
		mActivity = act;
		act.setContentView(R.layout.activity_challenge_without_option_answer);
		mQuestion = (TextView) act.findViewById(R.id.t_question_challenge_without_option_answer);
		mAnswer = (TextView) act.findViewById(R.id.t_correct_answer_without_option_answer);
		mContinueCorrect = (Button) act.findViewById(R.id.b_continue_challenge_correct_yes_without_option_answer);
		mContinueIncorrect = (Button) act.findViewById(R.id.b_continue_challenge_correct_no_without_option_answer);
	}

	public void showThisGui(){
		 mActivity.setContentView(R.layout.activity_challenge_without_option_answer);
	}

	public TextView getQuestion() {
		return mQuestion;
	}
	
	public TextView getAnswer() {
		return mAnswer;
	}

	public Activity getActivity() {
		return mActivity;
	}

	public Button getContinueCorrect() {
		return mContinueCorrect;
	}
	
	public Button getContinueIncorrect() {
		return mContinueIncorrect;
	}
	
}
