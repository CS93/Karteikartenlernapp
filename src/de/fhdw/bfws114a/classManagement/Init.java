package de.fhdw.bfws114a.classManagement;

import android.app.Activity;
import android.os.Bundle;
import de.fhdw.bfws114a.lernKartei.R;

public class Init extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//nur zum Testen
		setContentView(R.layout.activity_settings_class_management);

		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
//		mData.saveDataInBundle(outState);
		super.onSaveInstanceState(outState);
	}

}
