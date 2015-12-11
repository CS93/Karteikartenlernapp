package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui2 {
	
	private TextView mQuestionView;
	private EditText mUserAnswerEditText;
	private Activity mActivity;
	private Button mContinueButton;


	public Gui2(Activity act) {
		mActivity = act;
		act.setContentView(R.layout.activity_challenge_text);
		mQuestionView = (TextView) act.findViewById(R.id.t_question_challenge_text);
		mUserAnswerEditText = (EditText) act.findViewById(R.id.et_answer_challenge_text);
		mContinueButton = (Button) act.findViewById(R.id.b_continue_challenge_text);
	}

	public TextView getQuestionView() {
		return mQuestionView;
	}	

	public EditText getUserAnswerEditText() {
		return mUserAnswerEditText;
	}

	public Activity getActivity() {
		return mActivity;
	}

	public Button getContinueButton() {
		return mContinueButton;
	}
}
