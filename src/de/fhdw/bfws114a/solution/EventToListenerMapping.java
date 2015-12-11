package de.fhdw.bfws114a.solution;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EventToListenerMapping implements OnClickListener {

	private ApplicationLogic mApplicationLogic;

	//one constructor per question type
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
		gui.getContinueCorrectButton().setOnClickListener(this);
		gui.getContinueIncorrectButton().setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		String userAnswerCorrection = ((Button) v).getText().toString(); //only relevant for question type 3 (self control)
		mApplicationLogic.onContinueClicked(userAnswerCorrection);
	}
	
}
