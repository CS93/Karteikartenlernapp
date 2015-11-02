package de.fhdw.bfws114a.login;

import android.view.View;
import android.view.View.OnClickListener;
import de.fhdw.bfws114a.he.R;

public class EventToListenerMapping implements OnClickListener {

	private ApplicationLogic mApplicationLogic;

	public EventToListenerMapping(Gui gui, ApplicationLogic applicationLogic){
		mApplicationLogic = applicationLogic;
		gui.getIncrementButton().setOnClickListener(this);
		gui.getStartEditActivityButton().setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId()){
		case R.id.increment:
			mApplicationLogic.onIncrementButtonClicked();
			break;
		case R.id.start_edit_activity:
			mApplicationLogic.onStartEditActivityButtonClicked();
			break;
		}
		
	}
	
}
