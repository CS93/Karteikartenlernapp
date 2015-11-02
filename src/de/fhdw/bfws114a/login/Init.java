package de.fhdw.bfws114a.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Init extends Activity {

	private Data mData;
	private Gui mGui;
	private ApplicationLogic mApplicationLogic; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.main);
		initData(savedInstanceState);
		initGui();
		initApplicationLogic();
		initEventToListenerMapping();
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		mData.saveDataInBundle(outState);
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
