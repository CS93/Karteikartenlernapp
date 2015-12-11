/**
 * @author Carsten Schlender
 */
 
package de.fhdw.bfws114a.solution;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui2 {
	
	private TextView mQuestionView;
	private TextView mUserAnswerView;
	private TextView mCorrectAnswerView;
	private Activity mActivity;
	private Button mContinueButton;
	private TextView mUserAnswerDescriptionView;


	public Gui2(Activity act) {
		mActivity = act;
		act.setContentView(R.layout.activity_challenge_text_answer);
		mQuestionView = (TextView) act.findViewById(R.id.t_question_challenge_text_answer);
		mUserAnswerView = (TextView) act.findViewById(R.id.t_user_answer_text_answer);
		mUserAnswerDescriptionView = (TextView) act.findViewById(R.id.t_user_answer_description_text_answer);
		mCorrectAnswerView = (TextView) act.findViewById(R.id.t_correct_answer_text_answer);
		mContinueButton = (Button) act.findViewById(R.id.b_continue_challenge_text_answer);
	}

	public TextView getQuestionView() {
		return mQuestionView;
	}		

	public TextView getUserAnswerView() {
		return mUserAnswerView;
	}

	public TextView getUserAnswerDescriptionView(){
		return mUserAnswerDescriptionView;
	}
	
	public TextView getCorrectAnswerView() {
		return mCorrectAnswerView;
	}

	public Activity getActivity() {
		return mActivity;
	}

	public Button getContinueButton() {
		return mContinueButton;
	}
}
