package de.fhdw.bfws114a.login;

import android.app.Activity;
import android.os.Bundle;

public class Init extends Activity {
	private Data mData;
	private Gui mGui;
	private ApplicationLogic mApplicationLogic;
	
	//this onCreate is called when the app starts. The Data, Gui, Applogic and Listenermapping classes are instantiate
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initData(savedInstanceState);
		initGui();
		initApplicationLogic();
		initEventToListenerMapping();
	}
	
	//Whether this activity (login) is restarted e.g. after finishing profile management, the method onRestart() in ApplicationLogic is called
	@Override
	protected void onRestart() {
		super.onRestart();
		mApplicationLogic.onRestart();
	}
	
	//Save the Instance State is called when this activity is destroyed or resumed
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mData.saveDataInBundle(outState);
	}

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
