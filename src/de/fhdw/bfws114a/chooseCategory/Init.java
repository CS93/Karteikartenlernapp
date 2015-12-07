package de.fhdw.bfws114a.chooseCategory;

import android.app.Activity;
import android.os.Bundle;
import de.fhdw.bfws114a.data.Constants;
import de.fhdw.bfws114a.data.User;

public class Init extends Activity {

	private Data mData;
	private Gui mGui;
	private ApplicationLogic mApplicationLogic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		

		//The User gets loaded through the Serializable Interface from the intent
		initData(savedInstanceState, (User) getIntent().getSerializableExtra(Constants.KEY_PAR_CURRENT_USER_VALUE));		
		initGui();
		initApplicationLogic();
		initEventToListenerMapping();					
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mData.saveDataInBundle(outState);
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		mApplicationLogic.onResume();
	}
	

	private void initData(Bundle savedInstanceState, User user) {
		mData = new Data(savedInstanceState, this,  user);
		
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
