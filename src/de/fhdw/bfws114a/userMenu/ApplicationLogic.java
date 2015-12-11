package de.fhdw.bfws114a.userMenu;

import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.dataInterface.DataInterface;

public class ApplicationLogic {
	private Data mData;
	private Gui mGui;
	private DataInterface mDataInterface;
	
	ApplicationLogic(Data data, Gui gui){
		mData=data;
		mGui=gui;
		applyDataToGui();
	}

	private void applyDataToGui() {
		//Customize the Welcome Message with the Username
		mGui.setWelcomeUserView(mData.getUser().getName());		
	}

	public void onChooseCategoryButtonClicked() {
		//Start choose category with the current user 
		Navigation.startActivityChooseCategory(mData.getActivity(), mData.getUser());
	}

	public void onClassManagementButtonClicked() {
		//Start class management with the current user 
		Navigation.startActivityClassManagement(mData.getActivity(), mData.getUser());
	}

	//The refreshUser is necessary when class management has been finished and therefor the user-object has to be updated
	public void refreshUser() {
		mDataInterface = new DataInterface(mData.getActivity());
		mData.setUser(mDataInterface.getUser(mData.getUser().getName()));
	}
}

