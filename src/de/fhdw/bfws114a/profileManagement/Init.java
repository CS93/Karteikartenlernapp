package de.fhdw.bfws114a.profileManagement;

import android.app.Activity;
import android.os.Bundle;
import de.fhdw.bfws114a.profileManagement.ApplicationLogic;
import de.fhdw.bfws114a.profileManagement.Data;
import de.fhdw.bfws114a.profileManagement.EventToListenerMapping;
import de.fhdw.bfws114a.profileManagement.Gui;

public class Init extends Activity {

	private Data mData;
	private Gui mGui;
	private ApplicationLogic mApplicationLogic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//nur zum Testen
//		setContentView(R.layout.activity_profile_management);
		initGui();
		initApplicationLogic();
		initEventToListenerMapping();
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
//		mData.saveDataInBundle(outState);
		super.onSaveInstanceState(outState);
	}	

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		mApplicationLogic.processActivityReturnValues(requestCode, resultCode, data);
//		// super.onActivityResult(requestCode, resultCode, data);
//	}

	private void initData(Bundle savedInstanceState) {
		mData = new Data(savedInstanceState, this);
		
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

