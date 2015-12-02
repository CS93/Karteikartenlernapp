package de.fhdw.bfws114a.statistics;

import android.app.Activity;
import android.os.Bundle;
import de.fhdw.bfws114a.data.Constants;

public class Init extends Activity {

	private Data mData;
	private Gui mGui;
	private ApplicationLogic mApplicationLogic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int indexCurrentChallenge = getIntent().getIntExtra(Constants.KEY_INDEX_CURRENT_CHALLENGE, -1);
		int numberOfDueChallenges = getIntent().getIntExtra(Constants.KEY_NUMBER_DUE_CHALLENGES, -1);
		int numberOfCorrectAnswers = getIntent().getIntExtra(Constants.KEY_NUMBER_CORRECT_ANSWERS, -1);
		int numberOfWrongAnswers = getIntent().getIntExtra(Constants.KEY_NUMBER_WRONG_ANSWERS, -1);
		
		initData(savedInstanceState, indexCurrentChallenge, numberOfDueChallenges, numberOfCorrectAnswers, numberOfWrongAnswers);
		initGui();
		initApplicationLogic();
		initEventToListenerMapping();
		
	}
	
	
	@Override
	public void onBackPressed() {
		mApplicationLogic.OnBackButton();
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
//		mData.saveDataInBundle(outState);
		super.onSaveInstanceState(outState);
	}

	private void initData(Bundle savedInstanceState, int indexCurrentChallenge, int numberOfDueChallenges,
			int numberOfCorrectAnswers, int numberOfWrongAnswers) {
		mData = new Data(savedInstanceState, this, indexCurrentChallenge, numberOfDueChallenges, numberOfCorrectAnswers, numberOfWrongAnswers);
	}

	private void initGui() {
		mGui = new Gui(this);	
	}

	private void initApplicationLogic() {
		mApplicationLogic = new ApplicationLogic(mData, mGui);
	}

	private void initEventToListenerMapping() {
		new EventToListenerMapping(mGui, mApplicationLogic);
	}
}
