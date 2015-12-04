package de.fhdw.bfws114a.profileManagement;

import android.view.View;
import android.view.View.OnClickListener;
import de.fhdw.bfws114a.profileManagement.ApplicationLogic;
import de.fhdw.bfws114a.profileManagement.Gui;
import de.fhdw.bfws114a.lernKartei.R;

public class EventToListenerMapping  implements OnClickListener {

	private ApplicationLogic mApplicationLogic;

	public EventToListenerMapping(Gui gui, ApplicationLogic applicationLogic){
		mApplicationLogic = applicationLogic;
		gui.getAddUserButton().setOnClickListener(this);
		gui.getDelUserButton().setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId()){
		case R.id.b_adduser_profile_management:
			mApplicationLogic.onAddUserClicked();
			break;
		case R.id.b_deluser_profile_management:
			mApplicationLogic.onDelUserButtonClicked();
			break;
		}
		
	}
	
}
