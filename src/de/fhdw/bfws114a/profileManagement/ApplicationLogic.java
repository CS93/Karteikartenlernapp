package de.fhdw.bfws114a.profileManagement;

import android.util.Log;
import de.fhdw.bfws114a.data.User;
import de.fhdw.bfws114a.lernKartei.R;
import de.fhdw.bfws114a.profileManagement.Data;
import de.fhdw.bfws114a.profileManagement.Gui;

public class ApplicationLogic {

	private Data mData;
	private Gui mGui;
	
	ApplicationLogic(Data data, Gui gui){
		mData=data;
		mGui=gui;
		applyDataToGui();
	}

	private void applyDataToGui() {
		mGui.setChoiceList(mData.getUser());		
		
	}
	
	public void onAddUserClicked(){
		Log.d("DEBUG", "Der AddUser Button wurde gedr√ºckt");
		if (mGui.getAddUser().getText().toString().length() == 0){
			Log.d("DEBUG", "Name ist leer");
			mGui.showToast(R.string.user_empty_error_message);
		}
		else if ( checkUserExistAllready() == true){
			Log.d("DEBUG", "Der Name existiert schon");
			mGui.showToast(R.string.user_equal_error_message);
		}
		else {
//			System.out.println(mGui.getAddUser().getText().toString()+" "+mData.getUser().toString()+" "+mData.getUser().contains(mGui.getAddUser().getText().toString()));
			Log.d("DEBUG", "Name wird gespeichert");
			//add one User to db
			mData.getDataInterface().addUser(mGui.getAddUser().getText().toString()); //Am Besten diesen Teil in den Activityaufruf
			//Aktvity schlieﬂen
			mData.getActivity().finish();	
		}
		
	}	
	
	public void onDelUserButtonClicked(){
		//delete one User from db
		mData.getDataInterface().delUser(mGui.getChoiceList().getSelectedItem().toString());
		//Aktvity schlieﬂen
		mData.getActivity().finish();
	}
	
	private boolean checkUserExistAllready() {
		 for (User u : mData.getUser()) {
	        	if (u.getName().equals(mGui.getAddUser().getText().toString()) == true){
	        		return true;
	        	}
		}
		 return false;
	}
//	public void processActivityReturnValues(int requestCode, int resultCode, Intent intent) {
//		if(resultCode==Activity.RESULT_OK) {
//			if(requestCode==Constants.REQUESTCODE_ACTIVITY_EDIT) {
//				int value;
//				value = intent.getIntExtra(Constants.KEY_RETURN_COUNTER_VALUE, mData.getCounterValue());
//				mData.setCounterValue(value);
//				mGui.setCounterValue(value);
//			}
//		}
//	}
	
}
