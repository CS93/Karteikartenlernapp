package de.fhdw.bfws114a.statistics;

import android.app.Activity;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui {
	
	private TextView mNumberOfAnsweredQuestion;
	private TextView mNumberOfCorrectAnsweredQuestion;
	private TextView mPercentageOfCorrectAnsweredQuestion;
	private TextView mNumberOfDueChallenges; 
	private Button mContinueButton;
	
	public Gui(Activity act) {
		act.setContentView(R.layout.activity_statistic);
		mNumberOfAnsweredQuestion = (TextView) act.findViewById(R.id.t_number_amount_answered_statistic);
		mNumberOfCorrectAnsweredQuestion = (TextView) act.findViewById(R.id.t_number_amount_correct_statistic);
		mPercentageOfCorrectAnsweredQuestion = (TextView) act.findViewById(R.id.t_number_amount_percent_correct_statistic);
		mNumberOfDueChallenges = (TextView) act.findViewById(R.id.t_number_amount_due_statistic);
		mContinueButton = (Button) act.findViewById(R.id.b_continue_statistic);
	}

	public Button getContinueButton() {
		return mContinueButton;
	}

	public TextView getNumberOfAnsweredQuestion() {
		return mNumberOfAnsweredQuestion;
	}

	public TextView getNumberOfCorrectAnsweredQuestion() {
		return mNumberOfCorrectAnsweredQuestion;
	}

	public TextView getPercentageOfCorrectAnsweredQuestion() {
		return mPercentageOfCorrectAnsweredQuestion;
	}

	public TextView getNumberOfDueChallenges() {
		return mNumberOfDueChallenges;
	}
	
	public void showToast(Activity activity){
		 Toast toast = Toast.makeText(activity, activity.getString(R.string.end_of_learn_session), Toast.LENGTH_LONG);
		 LinearLayout toastLayout = (LinearLayout) toast.getView();
		 TextView toastTV = (TextView) toastLayout.getChildAt(0);
		 toastTV.setTextSize(30);
		 toastTV.setTextColor(Color.RED);
		 toast.show();
	}
}
