package de.fhdw.bfws114a.login;

import android.app.Activity;
import android.os.Bundle;

public class Init extends Activity {
	private Data mData;
	private Gui mGui;
	private ApplicationLogic mApplicationLogic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initData(savedInstanceState);
		initGui();
		initApplicationLogic();
		initEventToListenerMapping();
	}
	
	//Sofern wieder auf diese Activity gewechselt wird (bspw. nachdem ProfileManagement geschlossen wurde), sollen User aktualisiert werden
	@Override
	protected void onRestart() {
		super.onRestart();
		mApplicationLogic.onRestart();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		mData.saveDataInBundle(outState);
		super.onSaveInstanceState(outState);
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
