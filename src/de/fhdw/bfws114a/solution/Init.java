package de.fhdw.bfws114a.solution;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import de.fhdw.bfws114a.data.Challenge;
import de.fhdw.bfws114a.data.Constants;

public class Init extends Activity {

	private Data mData;
	private ApplicationLogic mApplicationLogic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Load current Challenge, answer from checkeboxes and user text answer from Intent to hand it over to Data
		initData(savedInstanceState, (Challenge) getIntent().getSerializableExtra(Constants.KEY_CURRENT_CHALLENGE_VALUE), getIntent().getBooleanArrayExtra(Constants.KEY_USER_ANSWERS_CHECKBOXES), getIntent().getStringExtra(Constants.KEY_USER_ANSWER_TEXT));		
		//Instantiation of GUI and EventToListenerMapping is delegated to applogic, because applogic know which gui-type (depending on question type) should be shown
		initApplicationLogic();
	}
	
	@Override
	public void onBackPressed() {
		//Whether user presses the back-button: his answer is wrong (whether its self control) and onContinueClicked checks if the answer was right (whether it was question type 1/2) --> so its the same as would he had clicked "Nein" (No)
		mApplicationLogic.onContinueClicked("Nein");
	}

	private void initData(Bundle savedInstanceState, Challenge currentChallenge, boolean[] userAnswerCheckBox, String answerText) {
		mData = new Data(savedInstanceState, this, currentChallenge, userAnswerCheckBox, answerText);
		
	}
	
	private void initApplicationLogic() {
		mApplicationLogic = new ApplicationLogic(mData, this);
	}
	
	

}
