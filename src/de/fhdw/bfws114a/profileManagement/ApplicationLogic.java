package de.fhdw.bfws114a.profileManagement;

import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.profileManagement.Data;
import de.fhdw.bfws114a.profileManagement.Gui;
import de.fhdw.bfws114a.dataInterface.DataInterface;

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
		DataInterface.addUser(mData.getUser(), mGui.getAddUser().getText().toString()); //Am Besten diesen Teil in den Activityaufruf
		//Zurück zum Login
		Navigation.startActivityLogin(mData.getActivity());
		
	}
		
	
	public void onDelUserButtonClicked(){
		DataInterface.delUser(mData.getUser(), mGui.getChoiceList().getSelectedItem().toString());
//		kommt man an das Ausgewählte Element: mGui.getChoiceList().getSelectedItem().toString()
		//Zurück zum Login
		Navigation.startActivityLogin(mData.getActivity());
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
