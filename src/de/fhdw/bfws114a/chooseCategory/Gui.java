package de.fhdw.bfws114a.chooseCategory;

import android.app.Activity;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui {
	
	private Button[] mCategoriesButtons = new Button[8];
	private TextView[] mOverallChallengesPerCategory = new TextView[8];
	private TextView[] mDueChallengesPerCategory = new TextView[8];
	private Button mRefreshButton;


	public Gui(Activity act) {
		act.setContentView(R.layout.activity_category_choose);
		
		mCategoriesButtons[0] = (Button) act.findViewById(R.id.b_cardfile1_category);
		mCategoriesButtons[1] = (Button) act.findViewById(R.id.b_cardfile2_category);
		mCategoriesButtons[2] = (Button) act.findViewById(R.id.b_cardfile3_category);
		mCategoriesButtons[3] = (Button) act.findViewById(R.id.b_cardfile4_category);
		mCategoriesButtons[4] = (Button) act.findViewById(R.id.b_cardfile5_category);
		mCategoriesButtons[5] = (Button) act.findViewById(R.id.b_cardfile6_category);
		mCategoriesButtons[6] = (Button) act.findViewById(R.id.b_cardfile7_category);
		mCategoriesButtons[7] = (Button) act.findViewById(R.id.b_cardfile8_category);
		
		mOverallChallengesPerCategory[0] = (TextView) act.findViewById(R.id.t_cardfile1_overall_statistic_category);
		mOverallChallengesPerCategory[1] = (TextView) act.findViewById(R.id.t_cardfile2_overall_statistic_category);
		mOverallChallengesPerCategory[2] = (TextView) act.findViewById(R.id.t_cardfile3_overall_statistic_category);
		mOverallChallengesPerCategory[3] = (TextView) act.findViewById(R.id.t_cardfile4_overall_statistic_category);
		mOverallChallengesPerCategory[4] = (TextView) act.findViewById(R.id.t_cardfile5_overall_statistic_category);
		mOverallChallengesPerCategory[5] = (TextView) act.findViewById(R.id.t_cardfile6_overall_statistic_category);
		mOverallChallengesPerCategory[6] = (TextView) act.findViewById(R.id.t_cardfile7_overall_statistic_category);
		mOverallChallengesPerCategory[7] = (TextView) act.findViewById(R.id.t_cardfile8_overall_statistic_category);
		
		mDueChallengesPerCategory[0] = (TextView) act.findViewById(R.id.t_cardfile1_due_statistic_category);
		mDueChallengesPerCategory[1] = (TextView) act.findViewById(R.id.t_cardfile2_due_statistic_category);
		mDueChallengesPerCategory[2] = (TextView) act.findViewById(R.id.t_cardfile3_due_statistic_category);
		mDueChallengesPerCategory[3] = (TextView) act.findViewById(R.id.t_cardfile4_due_statistic_category);
		mDueChallengesPerCategory[4] = (TextView) act.findViewById(R.id.t_cardfile5_due_statistic_category);
		mDueChallengesPerCategory[5] = (TextView) act.findViewById(R.id.t_cardfile6_due_statistic_category);
		mDueChallengesPerCategory[6] = (TextView) act.findViewById(R.id.t_cardfile7_due_statistic_category);
		mDueChallengesPerCategory[7] = (TextView) act.findViewById(R.id.t_cardfile8_due_statistic_category);
		
		mRefreshButton = (Button) act.findViewById(R.id.b_refresh_category);
	}

	public Button[] getCategories() {
		return mCategoriesButtons;
	}

	public Button getCategory(int index) {
		return mCategoriesButtons[index];
	}	
	
	public void setCategories(Button[] categories) {
		this.mCategoriesButtons = categories;
	}

	public TextView[] getOverallChallengesPerCategory() {
		return mOverallChallengesPerCategory;
	}

	public TextView getOverallChallengePerCategory(int index) {
		return mOverallChallengesPerCategory[index];
	}
	
	public void setOverallChallengesPerCategory(TextView[] mOverallChallengesPerCategory) {
		this.mOverallChallengesPerCategory = mOverallChallengesPerCategory;
	}
	
	public TextView[] getDueChallengesPerCategory() {
		return mDueChallengesPerCategory;
	}
	
	public TextView getDueChallengePerCategory(int index) {
		return mDueChallengesPerCategory[index];
	}
	
	public void setDueChallengesPerCategory(TextView[] mDueChallengesPerCategory) {
		this.mDueChallengesPerCategory = mDueChallengesPerCategory;
	}
	
	public Button getRefreshButton(){
		return mRefreshButton;
	}
	
	public void showToast(Activity act){
		 Toast toast = Toast.makeText(act, act.getString(R.string.no_due_challenges), Toast.LENGTH_LONG);
		 LinearLayout toastLayout = (LinearLayout) toast.getView();
		 TextView toastTV = (TextView) toastLayout.getChildAt(0);
		 toastTV.setTextSize(30);
		 toastTV.setTextColor(Color.RED);
		 toast.show();	 
	}
}
