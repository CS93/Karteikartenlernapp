package de.fhdw.bfws114a.chooseCategory;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import de.fhdw.bfws114asc.counter1.R;

public class Gui {
	
	private TextView mWelcomeUser;


	public Gui(Activity act) {
		act.setContentView(R.layout.activity_login);
		mWelcomeUser = (TextView) act.findViewById(R.id.t_hello_string_user_menu);		
		mWelcomeUser.setText("Willkommen" + Init.mUser);
	}

	public TextView getWelcomeUserView() {
		return mWelcomeUser;
	}
}
