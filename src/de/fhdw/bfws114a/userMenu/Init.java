package de.fhdw.bfws114a.userMenu;

import android.app.Activity;
import android.os.Bundle;
import de.fhdw.bfws114a.data.Constants;
import de.fhdw.bfws114asc.counter1.R;

public class Init extends Activity {

	private Data mData;
	private Gui mGui;
	private ApplicationLogic mApplicationLogic;
	public static String mUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_menu);		

		//Der zweite Parameter den aktuellen User als String
		initData(savedInstanceState, getIntent().getStringExtra(Constants.KEY_PAR_CURRENT_USER_VALUE);
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

	private void initData(Bundle savedInstanceState, String user) {
		mData = new Data(savedInstanceState, this, user);
		
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
