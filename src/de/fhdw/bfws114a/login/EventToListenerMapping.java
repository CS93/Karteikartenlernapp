package de.fhdw.bfws114a.login;

import android.view.View;
import android.view.View.OnClickListener;
import de.fhdw.bfws114a.lernKartei.R;

public class EventToListenerMapping implements OnClickListener {

	private ApplicationLogic mApplicationLogic;

	public EventToListenerMapping(Gui gui, ApplicationLogic applicationLogic){
		mApplicationLogic = applicationLogic;
		gui.getLoginButton().setOnClickListener(this);
		gui.getProfileManagementButton().setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId()){
		case R.id.login:
			mApplicationLogic.onLoginButtonClicked();
			break;
		case R.id.profile_management_start:
			mApplicationLogic.onProfileManagementButtonClicked();
			break;
		}
		
	}
	
}
