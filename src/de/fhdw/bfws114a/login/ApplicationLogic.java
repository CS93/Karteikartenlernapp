package de.fhdw.bfws114a.login;

import android.app.Activity;
import android.content.Intent;
import de.fhdw.bfws114a.he.data.Constants;
import de.fhdw.bfws114a.he.navigation.Navigation;

public class ApplicationLogic {
	private Data mData;
	private Gui mGui;
	
	ApplicationLogic(Data data, Gui gui){
		mData=data;
		mGui=gui;
		applyDataToGui();
	}

	private void applyDataToGui() {
		mGui.setCounterValue(mData.getCounterValue());
		
	}
	
	public void onIncrementButtonClicked(){
		mData.incrementCounterValue();
		applyDataToGui();
	}
	
	public void onStartEditActivityButtonClicked(){
		Navigation.startActivityEdit(mData.getActivity(), mData.getCounterValue());
	}
	
	public void processActivityReturnValues(int requestCode, int resultCode, Intent intent) {
		if(resultCode==Activity.RESULT_OK) {
			if(requestCode==Constants.REQUESTCODE_ACTIVITY_EDIT) {
				int value;
				value = intent.getIntExtra(Constants.KEY_RETURN_COUNTER_VALUE, mData.getCounterValue());
				mData.setCounterValue(value);
				mGui.setCounterValue(value);
			}
		}
	}

}

