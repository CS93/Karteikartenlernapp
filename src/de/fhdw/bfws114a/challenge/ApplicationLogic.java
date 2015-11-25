package de.fhdw.bfws114a.challenge;

import android.util.Log;
import de.fhdw.bfws114a.Navigation.Navigation;

public class ApplicationLogic {
	private Data mData;
	private Gui1 mGui1;
	private Gui2 mGui2;
	private Gui3 mGui3;
	
	ApplicationLogic(Data data, Gui1 gui1, Gui2 gui2, Gui3 gui3){
		mData=data;
		mGui1=gui1;
		mGui2=gui2;
		mGui3=gui3;
		applyDataToGui();
	}

	private void applyDataToGui() {
	
	}
	

		
	
	public void onCategoryClicked(String category){
		//Weiterleitung zur Challenge (in den Lernmodus) mit entsprechender Kartei (category) und User 	
		Navigation.startActivityChallenge(mData.getActivity(), mData.getUser(), category);
	}
}

