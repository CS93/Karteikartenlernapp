package de.fhdw.bfws114a.userMenu;

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
		//Willkommensnachricht auf den aktuellen User anpassen
		mGui.setWelcomeUserView(mData.getUser());		
	}

	public void onChooseCategoryButtonClicked() {
		//Weiterleitung zur Kategorie bzw Karteiauswahl mit entsprechendem User 
		Navigation.startActivityChooseCategory(mData.getActivity(), mData.getUser());
	}

	public void onClassManagementButtonClicked() {
		//Weiterleitung zum UserMenu (Auswahl der Kartei) mit entsprechendem User 
		Navigation.startActivityClassManagement(mData.getActivity(), mData.getUser());
	}
}

