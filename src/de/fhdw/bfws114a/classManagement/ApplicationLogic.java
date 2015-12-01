package de.fhdw.bfws114a.classManagement;
import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.classManagement.Data;
import de.fhdw.bfws114a.classManagement.Gui;
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
		mGui.setClass(mData.getTimeOfClasses());
		mGui.setClassSpinner(mData.getTimeList(), generateTimeconsens());			
	}
	
	private int[] generateTimeconsens(){
		int[] position = new int[5];
		for(int i = 0;i<6;i++){
			if((mData.getTimeOfClasses()[i]%1440)== 0){ //es handelt sich um Stunden
				mData.setTimeOfClasses(i+1,mData.getTimeOfClasses()[i]/1440);
				position[i]= 2;
			}
			else if((mData.getTimeOfClasses()[i]%60)== 0){
				mData.setTimeOfClasses(i+1,mData.getTimeOfClasses()[i]/60);
				position[i]= 1;}
			else {
				position[i]= 0;
			}}
			return position;
}

	public void onDefaultClassClicked(){
//		mData.getDataInterface().addUser(mData.getUser(), mGui.getAddUser().getText().toString()); //Am Besten diesen Teil in den Activityaufruf
		//Zurück zum Login
//		Navigation.startActivityLogin(mData.getActivity());
		
	}
		
	
	public void onSaveClassClicked(){
//		mData.getDataInterface().delUser(mData.getUser(), mGui.getChoiceList().getSelectedItem().toString());
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
