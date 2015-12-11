/**
 * @author Carsten Schlender
 */
 
package de.fhdw.bfws114a.statistics;

import android.app.Activity;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui {
	
	private TextView mNumberOfAnsweredQuestionView;
	private TextView mNumberOfCorrectAnsweredQuestionView;
	private TextView mPercentageOfCorrectAnsweredQuestionView;
	private TextView mNumberOfDueChallengesView; 
	private Button mContinueButton;
	
	public Gui(Activity act) {
		act.setContentView(R.layout.activity_statistic);
		mNumberOfAnsweredQuestionView = (TextView) act.findViewById(R.id.t_number_amount_answered_statistic);
		mNumberOfCorrectAnsweredQuestionView = (TextView) act.findViewById(R.id.t_number_amount_correct_statistic);
		mPercentageOfCorrectAnsweredQuestionView = (TextView) act.findViewById(R.id.t_number_amount_percent_correct_statistic);
		mNumberOfDueChallengesView = (TextView) act.findViewById(R.id.t_number_amount_due_statistic);
		mContinueButton = (Button) act.findViewById(R.id.b_continue_statistic);
	}

	public Button getContinueButton() {
		return mContinueButton;
	}

	public TextView getNumberOfAnsweredQuestionView() {
		return mNumberOfAnsweredQuestionView;
	}

	public TextView getNumberOfCorrectAnsweredQuestionView() {
		return mNumberOfCorrectAnsweredQuestionView;
	}

	public TextView getPercentageOfCorrectAnsweredQuestionView() {
		return mPercentageOfCorrectAnsweredQuestionView;
	}

	public TextView getNumberOfDueChallengesView() {
		return mNumberOfDueChallengesView;
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
