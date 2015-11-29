package de.fhdw.bfws114a.solution;

import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui2 {
	
	private TextView mQuestion;
	private TextView mUserAnswer;
	private TextView mCorrectAnswer;
	private Activity mActivity;
	private Button mContinue;


	public Gui2(Activity act) {
		mActivity = act;
		act.setContentView(R.layout.activity_challenge_text_answer);
		mQuestion = (TextView) act.findViewById(R.id.t_question_challenge_text_answer);
		mUserAnswer = (TextView) act.findViewById(R.id.t_user_answer_text_answer);
		mCorrectAnswer = (TextView) act.findViewById(R.id.t_correct_answer_text_answer);
		mContinue = (Button) act.findViewById(R.id.b_continue_challenge_text);
	}

	public void showThisGui(){
		 mActivity.setContentView(R.layout.activity_challenge_text_answer);
	}

	public TextView getQuestion() {
		return mQuestion;
	}		

	public TextView getUserAnswer() {
		return mUserAnswer;
	}

	public TextView getCorrectAnswer() {
		return mCorrectAnswer;
	}

	public Activity getActivity() {
		return mActivity;
	}

	public Button getContinue() {
		return mContinue;
	}
}
