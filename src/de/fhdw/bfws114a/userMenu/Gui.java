package de.fhdw.bfws114a.userMenu;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui {
	
	private TextView mWelcomeUserView;
	private Button mChooseCategoryButton, mClassManagementButton;


	public Gui(Activity act) {
		act.setContentView(R.layout.activity_user_menu);
		
		mWelcomeUserView = (TextView) act.findViewById(R.id.t_username_user_menu);		
		mChooseCategoryButton = (Button) act.findViewById(R.id.b_category_user_menu);
		mClassManagementButton = (Button) act.findViewById(R.id.b_class_management_user_menu);
	}

	public TextView getWelcomeUserView() {
		return mWelcomeUserView;
	}
	
	public void setWelcomeUserView(String user) {
		mWelcomeUserView.setText(user);
	}

	public Button getChooseCategoryButton() {
		return mChooseCategoryButton;
	}

	public Button getClassManagementButton() {
		return mClassManagementButton;
	}
	
	
}
