package de.fhdw.bfws114a.chooseCategory;

import android.app.Activity;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui {
	
	private Button[] mCategoryButtons = new Button[8];
	private TextView[] mOverallChallengesPerCategoryViews = new TextView[8];
	private TextView[] mDueChallengesPerCategoryViews = new TextView[8];
	private Button mRefreshButton;


	public Gui(Activity act) {
		act.setContentView(R.layout.activity_category_choose);
		
		mCategoryButtons[0] = (Button) act.findViewById(R.id.b_cardfile1_category);
		mCategoryButtons[1] = (Button) act.findViewById(R.id.b_cardfile2_category);
		mCategoryButtons[2] = (Button) act.findViewById(R.id.b_cardfile3_category);
		mCategoryButtons[3] = (Button) act.findViewById(R.id.b_cardfile4_category);
		mCategoryButtons[4] = (Button) act.findViewById(R.id.b_cardfile5_category);
		mCategoryButtons[5] = (Button) act.findViewById(R.id.b_cardfile6_category);
		mCategoryButtons[6] = (Button) act.findViewById(R.id.b_cardfile7_category);
		mCategoryButtons[7] = (Button) act.findViewById(R.id.b_cardfile8_category);
		
		mOverallChallengesPerCategoryViews[0] = (TextView) act.findViewById(R.id.t_cardfile1_overall_statistic_category);
		mOverallChallengesPerCategoryViews[1] = (TextView) act.findViewById(R.id.t_cardfile2_overall_statistic_category);
		mOverallChallengesPerCategoryViews[2] = (TextView) act.findViewById(R.id.t_cardfile3_overall_statistic_category);
		mOverallChallengesPerCategoryViews[3] = (TextView) act.findViewById(R.id.t_cardfile4_overall_statistic_category);
		mOverallChallengesPerCategoryViews[4] = (TextView) act.findViewById(R.id.t_cardfile5_overall_statistic_category);
		mOverallChallengesPerCategoryViews[5] = (TextView) act.findViewById(R.id.t_cardfile6_overall_statistic_category);
		mOverallChallengesPerCategoryViews[6] = (TextView) act.findViewById(R.id.t_cardfile7_overall_statistic_category);
		mOverallChallengesPerCategoryViews[7] = (TextView) act.findViewById(R.id.t_cardfile8_overall_statistic_category);
		
		mDueChallengesPerCategoryViews[0] = (TextView) act.findViewById(R.id.t_cardfile1_due_statistic_category);
		mDueChallengesPerCategoryViews[1] = (TextView) act.findViewById(R.id.t_cardfile2_due_statistic_category);
		mDueChallengesPerCategoryViews[2] = (TextView) act.findViewById(R.id.t_cardfile3_due_statistic_category);
		mDueChallengesPerCategoryViews[3] = (TextView) act.findViewById(R.id.t_cardfile4_due_statistic_category);
		mDueChallengesPerCategoryViews[4] = (TextView) act.findViewById(R.id.t_cardfile5_due_statistic_category);
		mDueChallengesPerCategoryViews[5] = (TextView) act.findViewById(R.id.t_cardfile6_due_statistic_category);
		mDueChallengesPerCategoryViews[6] = (TextView) act.findViewById(R.id.t_cardfile7_due_statistic_category);
		mDueChallengesPerCategoryViews[7] = (TextView) act.findViewById(R.id.t_cardfile8_due_statistic_category);
		
		mRefreshButton = (Button) act.findViewById(R.id.b_refresh_category);
	}

	public Button[] getCategoryButtons() {
		return mCategoryButtons;
	}

	public Button getCategoryButton(int index) {
		return mCategoryButtons[index];
	}	
	
	public void setCategoryButtons(Button[] categories) {
		this.mCategoryButtons = categories;
	}

	public TextView[] getOverallChallengesPerCategoryViews() {
		return mOverallChallengesPerCategoryViews;
	}

	public TextView getOverallChallengePerCategoryView(int index) {
		return mOverallChallengesPerCategoryViews[index];
	}
	
	public void setOverallChallengesPerCategoryViews(TextView[] mOverallChallengesPerCategory) {
		this.mOverallChallengesPerCategoryViews = mOverallChallengesPerCategory;
	}
	
	public TextView[] getDueChallengesPerCategoryViews() {
		return mDueChallengesPerCategoryViews;
	}
	
	public TextView getDueChallengePerCategoryView(int index) {
		return mDueChallengesPerCategoryViews[index];
	}
	
	public void setDueChallengesPerCategoryViews(TextView[] mDueChallengesPerCategory) {
		this.mDueChallengesPerCategoryViews = mDueChallengesPerCategory;
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
