package de.fhdw.bfws114a.solution;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import de.fhdw.bfws114a.data.Constants;
import de.fhdw.bfws114a.dataInterface.Challenge;
import de.fhdw.bfws114a.lernKartei.R;

public class Init extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Challenge currentChallenge = (Challenge) getIntent().getSerializableExtra(Constants.KEY_CURRENT_CHALLENGE_VALUE);
		Log.d("", currentChallenge.getAntwort(1));
		//nur zum Testen
		setContentView(R.layout.activity_challenge_checkbox_answer);

		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
//		mData.saveDataInBundle(outState);
		super.onSaveInstanceState(outState);
	}

}
