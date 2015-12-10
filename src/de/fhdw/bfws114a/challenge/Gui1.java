package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui1 {
	
	private TextView mQuestion;
	private CheckBox[] mOptionsCheckboxes = new CheckBox[6];
	private Activity mActivity;
	private Button mContinueButton;


	public Gui1(Activity act) {
		mActivity = act;
		act.setContentView(R.layout.activity_challenge_checkbox);
		mQuestion = (TextView) act.findViewById(R.id.t_question_challenge_checkbox);
		mOptionsCheckboxes[0] = (CheckBox) act.findViewById(R.id.c_question_one_challenge_checkbox);
		mOptionsCheckboxes[1] = (CheckBox) act.findViewById(R.id.c_question_two_challenge_checkbox);
		mOptionsCheckboxes[2] = (CheckBox) act.findViewById(R.id.c_question_three_challenge_checkbox);
		mOptionsCheckboxes[3] = (CheckBox) act.findViewById(R.id.c_question_four_challenge_checkbox);
		mOptionsCheckboxes[4] = (CheckBox) act.findViewById(R.id.c_question_five_challenge_checkbox);
		mOptionsCheckboxes[5] = (CheckBox) act.findViewById(R.id.c_question_six_challenge_checkbox);
		mContinueButton = (Button) act.findViewById(R.id.b_continue_challenge_checkbox);

	}

	public void showThisGui(){
		 mActivity.setContentView(R.layout.activity_challenge_checkbox);
	}

	public TextView getQuestion() {
		return mQuestion;
	}

	public CheckBox getOption(int index){
		return mOptionsCheckboxes[index];
	}
	
	public CheckBox[] getOptions() {
		return mOptionsCheckboxes;
	}

	public Activity getActivity() {
		return mActivity;
	}

	public Button getContinue() {
		return mContinueButton;
	}
}
