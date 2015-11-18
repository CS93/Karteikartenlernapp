package de.fhdw.bfws114a.chooseCategory;

import de.fhdw.bfws114a.Navigation.Navigation;

public class ApplicationLogic {
	private Data mData;
	private Gui mGui;
	
	ApplicationLogic(Data data, Gui gui){
		mData=data;
		mGui=gui;
		applyDataToGui();
	}

	private void applyDataToGui() {
		for(int i = 0; i < mGui.getCategories().length; i++){			
			mGui.getCategory(i).setText(mData.getKarteien().get(i));
		}

		
	}
	

		
	
	public void onProfileManagementButtonClicked(){
		//Weiterleitung zum UserMenu (Auswahl der Kartei) mit entsprechendem User 	
		Navigation.startActivityProfileManagement(mData.getActivity());
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

