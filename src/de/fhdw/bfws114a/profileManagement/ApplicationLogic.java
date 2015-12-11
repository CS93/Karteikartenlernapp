/**
 * @author Samira Schorre
 */
 
package de.fhdw.bfws114a.profileManagement;

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
		mGui.setChoiceListSpinner(mData.getUser());		
	}
	
	public void onAddUserClicked(){
		//three options: the field is empty, the user already exists or the user can be saved
		if (mGui.getAddUserEditText().getText().toString().length() == 0){
			mGui.showToast(R.string.user_empty_error_message);
		}
		else if ( checkUserExistAlready() == true){
			mGui.showToast(R.string.user_equal_error_message);
		}
		else {
			//add one User to db
			mData.getDataInterface().addUser(mGui.getAddUserEditText().getText().toString()); //Am Besten diesen Teil in den Activityaufruf
			//close activity
			mData.getActivity().finish();	
		}
		
	}	
	
	public void onDelUserButtonClicked(){
		//two options: there exists no user or delete the selected user
		if(mData.getUser().size() == 0){
			mGui.showToast(R.string.exists_no_user_error_message);
		}
		else {
		//delete one User from db
		mData.getDataInterface().delUser(mGui.getChoiceListSpinner().getSelectedItem().toString());
		//close activity
		mData.getActivity().finish();
		}
	}
	
	private boolean checkUserExistAlready() {
		 for (User u : mData.getUser()) {
	        	if (u.getName().equals(mGui.getAddUserEditText().getText().toString()) == true){
	        		return true;
	        	}
		}
		 return false;
	}
	
}
