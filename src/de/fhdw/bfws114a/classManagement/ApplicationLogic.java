package de.fhdw.bfws114a.classManagement;

import de.fhdw.bfws114a.classManagement.Data;
import de.fhdw.bfws114a.classManagement.Gui;
import de.fhdw.bfws114a.lernKartei.R;

public class ApplicationLogic {

	private Data mData;
	private Gui mGui;
	
	ApplicationLogic(Data data, Gui gui){
		mData=data;
		mGui=gui;
		applyDataToGui();
	}

	private void applyDataToGui() {
		mGui.setClassSpinner(mData.getContext(), mData.getTimeList(), generateTimeconsens());
		mGui.setClass(mData.getTimeOfClasses());
	}
	
	public void onDefaultClassClicked(){
		mData.loadDefaultTimeToClasses();
		applyDataToGui();
	}
		
	
	public void onSaveClassClicked(){
		//just continue whether all EditText fields are filed
		if (mGui.checkClassEditText() == true){
			checkMinutes(generateMinutes());
		}
		else {
			mGui.showToast(mData.getContext(), mData.getContext().getString(R.string.missing_input_error_message));
		}
	}
	
	private int[] generateTimeconsens(){
		//get minutes, hours and days out of minutes and set the numbers 
		//(in connection to the time consensus) Spinners
		int[] position = new int[6];
		for(int i = 0;i<6;i++){
			if((mData.getTimeOfClasses()[i]%1440)== 0){ //es handelt sich um Stunden
				mData.setTimeOfClasses(i+1,mData.getTimeOfClasses()[i]/1440);
				position[i]= 2; //deals with days
			}
			else if((mData.getTimeOfClasses()[i]%60)== 0){
				mData.setTimeOfClasses(i+1,mData.getTimeOfClasses()[i]/60);
				position[i]= 1;}  //deals with hours
			else {
				position[i]= 0; //deals with minutes
			}
		}
		return position;
	}
	
	private int[] generateMinutes(){
		//out of days, hours and minutes make just minutes
		int[] minutes = new int[6];
		for (int i = 0; i<6;i++){
			if (mGui.getClassSpinner()[i]==2){
				minutes[i] = mGui.getClassEditText()[i]*1440; //Tage in Minuten umwandeln
			}
			else if(mGui.getClassSpinner()[i]==1){
				minutes[i] = mGui.getClassEditText()[i]*60; //Stunden in Minuten umwandeln
			}
			else {
				minutes[i] = mGui.getClassEditText()[i];
			}
		}	
		return minutes;
	}
	
	private void checkMinutes(int[] minutes){
		//just save and leave the activity whether the minutes are ascending
		if (minutes [0] < minutes [1] &&  minutes [1] < minutes [2] && 
			minutes [2] < minutes [3] &&  minutes [3] < minutes [4] &&
			minutes [4] < minutes [5]) {
		mData.setTimeOfClasses(minutes);
		//save the times (in minutes) of the classes in connection with the user
		mData.getDataInterface().saveTimeToClasses(mData.getUser(), mData.getTimeOfClasses());
		//close activity
		mData.getActivity().finish();
		}
		else {
			mGui.showToast(mData.getContext(), mData.getContext().getString(R.string.class_error_message));
		}
	}
	
}
