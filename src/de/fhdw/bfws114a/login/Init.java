package de.fhdw.bfws114a.login;

import android.app.Activity;
import android.os.Bundle;
import de.fhdw.bfws114a.dataInterface.DatabaseHandler;

public class Init extends Activity {

	private Data mData;
	private Gui mGui;
	private ApplicationLogic mApplicationLogic;
	public static DatabaseHandler dbHand; //zum Testen
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData(savedInstanceState);
		initGui();
		initApplicationLogic();
		initEventToListenerMapping();
		
		//@Ricardo: zum Testen des Db-Handlers
		dbHand = new DatabaseHandler(this);
		
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
