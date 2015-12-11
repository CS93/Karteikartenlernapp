/**
 * @author Samira Schorre
 */
package de.fhdw.bfws114a.classManagement;

import android.view.View;
import android.view.View.OnClickListener;
import de.fhdw.bfws114a.classManagement.ApplicationLogic;
import de.fhdw.bfws114a.classManagement.Gui;
import de.fhdw.bfws114a.lernKartei.R;

public class EventToListenerMapping  implements OnClickListener {

	private ApplicationLogic mApplicationLogic;

	public EventToListenerMapping(Gui gui, ApplicationLogic applicationLogic){
		mApplicationLogic = applicationLogic;
		gui.getDefaultClassButton().setOnClickListener(this);
		gui.getSaveClassButton().setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId()){
		case R.id.b_default_class_management:
			mApplicationLogic.onDefaultClassClicked();
			break;
		case R.id.b_save_class_management:
			mApplicationLogic.onSaveClassClicked();
			break;
		}
		
	}
	
}
