package de.fhdw.bfws114a.solution;

import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui1 {
	
	private TextView mQuestionView;
	private CheckBox[] mOptionCheckBoxes = new CheckBox[6];
	private Activity mActivity;
	private Button mContinueButton;


	public Gui1(Activity act) {
		mActivity = act;
		act.setContentView(R.layout.activity_challenge_checkbox_answer);
		mQuestionView = (TextView) act.findViewById(R.id.t_question_challenge_checkbox);
		mOptionCheckBoxes[0] = (CheckBox) act.findViewById(R.id.c_question_one_challenge_checkbox);
		mOptionCheckBoxes[1] = (CheckBox) act.findViewById(R.id.c_question_two_challenge_checkbox);
		mOptionCheckBoxes[2] = (CheckBox) act.findViewById(R.id.c_question_three_challenge_checkbox);
		mOptionCheckBoxes[3] = (CheckBox) act.findViewById(R.id.c_question_four_challenge_checkbox);
		mOptionCheckBoxes[4] = (CheckBox) act.findViewById(R.id.c_question_five_challenge_checkbox);
		mOptionCheckBoxes[5] = (CheckBox) act.findViewById(R.id.c_question_six_challenge_checkbox);
		checkBoxesNotChangeable();
		mContinueButton = (Button) act.findViewById(R.id.b_continue_challenge_checkbox);
		

	}

	private void checkBoxesNotChangeable() {
		for(int i = 0; i < 6 ; i++){
			mOptionCheckBoxes[i].setClickable(false);
		}
	}
	
	public TextView getQuestionView() {
		return mQuestionView;
	}

	public CheckBox getOptionCheckBox(int index){
		return mOptionCheckBoxes[index];
	}
	
	public CheckBox[] getOptionCheckBoxes() {
		return mOptionCheckBoxes;
	}

	public Activity getActivity() {
		return mActivity;
	}

	public Button getContinueButton() {
		return mContinueButton;
	}
}
