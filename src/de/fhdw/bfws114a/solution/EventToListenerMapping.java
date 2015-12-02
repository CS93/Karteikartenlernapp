package de.fhdw.bfws114a.solution;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EventToListenerMapping implements OnClickListener {

	private ApplicationLogic mApplicationLogic;

	public EventToListenerMapping(Gui1 gui, ApplicationLogic applicationLogic){
		mApplicationLogic = applicationLogic;
		gui.getContinue().setOnClickListener(this);		
	}

	public EventToListenerMapping(Gui2 gui, ApplicationLogic applicationLogic){
		mApplicationLogic = applicationLogic;
		gui.getContinue().setOnClickListener(this);		
	}
	
	public EventToListenerMapping(Gui3 gui, ApplicationLogic applicationLogic){
		mApplicationLogic = applicationLogic;
		gui.getContinueCorrect().setOnClickListener(this);
		gui.getContinueIncorrect().setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		String userAnswerCorrection = ((Button) v).getText().toString(); //Nur relevant für Fragetyp 3 (Selbstüberprüfung)
		mApplicationLogic.onContinueClicked(userAnswerCorrection);
	}
	
}
