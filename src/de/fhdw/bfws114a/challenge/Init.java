package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import de.fhdw.bfws114a.data.Constants;
import de.fhdw.bfws114asc.counter1.R;

public class Init extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//nur zum Testen
		setContentView(R.layout.activity_challenge_checkbox);
		//Im folgenden Log werden der aktuelle User und die Kategorie aus dem Intent geladen
		Log.d("", getIntent().getStringExtra(Constants.KEY_PAR_CURRENT_USER_VALUE) + getIntent().getStringExtra(Constants.KEY_PAR_CURRENT_CATEGORY_VALUE));

		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
//		mData.saveDataInBundle(outState);
		super.onSaveInstanceState(outState);
	}

}
