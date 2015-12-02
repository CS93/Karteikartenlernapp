package de.fhdw.bfws114a.login;

import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.data.User;

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
	
	public void onLoginButtonClicked(){
		if(mData.getUser().size() == 0){
			//Keine User im Spinner mit denen man sich einloggen kann
			
		} else {
			//Weiterleitung zum UserMenu (Auswahl der Kartei) mit entsprechendem User 
			for(User u : mData.getUser()){
				if(u.getName().equals(mGui.getChoiceList().getSelectedItem().toString())){
					Navigation.startActivityUserMenu(mData.getActivity(), u);
					return;
				}
			}
		}		
	}
		
	
	public void onProfileManagementButtonClicked(){
		//Weiterleitung zum UserMenu (Auswahl der Kartei) mit entsprechendem User 	
		Navigation.startActivityProfileManagement(mData.getActivity());
	}

	public void onRestart() {
		mData.ladeUser();
		applyDataToGui();
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

