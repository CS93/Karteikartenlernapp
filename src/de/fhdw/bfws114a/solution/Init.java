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
		//Guis werden von der Applicationlogic initialisiert, da zunächst unbekannt ist, welcher Challenge-Typ anzuzeigen ist
		//Folglich wird auch das EventToListenerMapping erst in der ApplicationLogic durchgeführt
		
		initData(savedInstanceState, (Challenge) getIntent().getSerializableExtra(Constants.KEY_CURRENT_CHALLENGE_VALUE), getIntent().getBooleanArrayExtra(Constants.KEY_USER_ANSWERS_CHECKBOXES), getIntent().getStringExtra(Constants.KEY_USER_ANSWER_TEXT));		
		initApplicationLogic();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
//		mData.saveDataInBundle(outState);
		super.onSaveInstanceState(outState);
	}
	
	
	@Override
	public void onBackPressed() {
		mApplicationLogic.onContinueClicked("Nein");
	}

	private void initData(Bundle savedInstanceState, Challenge currentChallenge, boolean[] userAnswerCheckBox, String answerText) {
		mData = new Data(savedInstanceState, this, currentChallenge, userAnswerCheckBox, answerText);
		
	}
	
	private void initApplicationLogic() {
		mApplicationLogic = new ApplicationLogic(mData, this);
	}
	
	

}
