/**
 * @author Carsten Schlender
 */
 
package de.fhdw.bfws114a.challenge;

import android.view.View;
import android.view.View.OnClickListener;

public class EventToListenerMapping implements OnClickListener {

	private ApplicationLogic mApplicationLogic;

	//Different constructors for the eventToListenerMapping depending on gui (and this depends on question type)
	public EventToListenerMapping(Gui1 gui, ApplicationLogic applicationLogic){
		mApplicationLogic = applicationLogic;
		gui.getContinueButton().setOnClickListener(this);		
	}

	public EventToListenerMapping(Gui2 gui, ApplicationLogic applicationLogic){
		mApplicationLogic = applicationLogic;
		gui.getContinueButton().setOnClickListener(this);		
	}
	
	public EventToListenerMapping(Gui3 gui, ApplicationLogic applicationLogic){
		mApplicationLogic = applicationLogic;
		gui.getContinue().setOnClickListener(this);		
	}
	
	@Override
	public void onClick(View v) {
		mApplicationLogic.onContinueClicked();		
	}
	
}
