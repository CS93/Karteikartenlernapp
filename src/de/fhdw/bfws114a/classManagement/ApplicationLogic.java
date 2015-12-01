package de.fhdw.bfws114a.classManagement;

import android.widget.Toast;
import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.classManagement.Data;
import de.fhdw.bfws114a.classManagement.Gui;

public class ApplicationLogic {

	private Data mData;
	private Gui mGui;
	
	ApplicationLogic(Data data, Gui gui){
		mData=data;
		mGui=gui;
		applyDataToGui();
	}

	private void applyDataToGui() {
		mGui.setClassSpinner(mData.getTimeList(), generateTimeconsens());
		mGui.setClass(mData.getTimeOfClasses());
	}
	
	public void onDefaultClassClicked(){
		mData.loadDefaultTimeToClasses();
	}
		
	
	public void onSaveClassClicked(){
//		mData.getDataInterface().delUser(mData.getUser(), mGui.getChoiceList().getSelectedItem().toString());
//		kommt man an das Ausgewählte Element: mGui.getChoiceList().getSelectedItem().toString()
		applyGuiToData();
	}
	
	private int[] generateTimeconsens(){
		int[] position = new int[5];
		for(int i = 0;i<6;i++){
			if((mData.getTimeOfClasses()[i]%1440)== 0){ //es handelt sich um Stunden
				mData.setTimeOfClasses(i+1,mData.getTimeOfClasses()[i]/1440);
				position[i]= 2; //Es handelt sich um Tage
			}
			else if((mData.getTimeOfClasses()[i]%60)== 0){
				mData.setTimeOfClasses(i+1,mData.getTimeOfClasses()[i]/60);
				position[i]= 1;}  //Es handelt sich um Stunden
			else {
				position[i]= 0; //Es handelt sich um Minuten
			}}
			return position;
}
	
	private void applyGuiToData(){
		checkMinutes(generateMinutes());
	}
	
	private int[] generateMinutes(){
		int[] minutes = new int[5];
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
		if (minutes [0] < minutes [1] &&  minutes [1] < minutes [2] && 
			minutes [2] < minutes [3] &&  minutes [3] < minutes [4] &&
			minutes [4] < minutes [5]) {
		//wenn die Zeiten aufsteigend sind kann gespeichert werden
		mData.setTimeOfClasses(minutes);		
		mData.getDataInterface().saveTimeToClasses(mData.getUser(), mData.getTimeOfClasses());
		//Aktvity schließen
		mData.getActivity().finish();
		}
		else {
			Toast.makeText(mData.getContext(), "Bitte geben sie die Zeiten der Klassen aufsteigend an", Toast.LENGTH_LONG).show();
		}
	}
	
}
