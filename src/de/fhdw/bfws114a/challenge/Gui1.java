package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui1 {
	
	private TextView mQuestion;
	private CheckBox[] mOptions = new CheckBox[6];
	private Activity mActivity;
	private Button mContinue;


	public Gui1(Activity act) {
		mActivity = act;
		mQuestion = (TextView) act.findViewById(R.id.t_question_challenge_checkbox);
		mOptions[0] = (CheckBox) act.findViewById(R.id.t_answer_one_challenge_checkbox);
		mOptions[1] = (CheckBox) act.findViewById(R.id.t_answer_two_challenge_checkbox);
		mOptions[2] = (CheckBox) act.findViewById(R.id.t_answer_three_challenge_checkbox);
		mOptions[3] = (CheckBox) act.findViewById(R.id.t_answer_four_challenge_checkbox);
		mOptions[4] = (CheckBox) act.findViewById(R.id.t_answer_five_challenge_checkbox);
		mOptions[5] = (CheckBox) act.findViewById(R.id.t_answer_six_challenge_checkbox);
		mContinue = (Button) act.findViewById(R.id.b_continue_challenge_checkbox);
	}

	public void showThisGui(){
		 mActivity.setContentView(R.layout.activity_challenge_checkbox);
	}

	public TextView getQuestion() {
		return mQuestion;
	}

	public CheckBox getOption(int index){
		return mOptions[index];
	}
	
	public CheckBox[] getOptions() {
		return mOptions;
	}

	public Activity getActivity() {
		return mActivity;
	}

	public Button getContinue() {
		return mContinue;
	}
}
