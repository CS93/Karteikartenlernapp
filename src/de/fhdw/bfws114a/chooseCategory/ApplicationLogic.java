package de.fhdw.bfws114a.chooseCategory;

import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.dataInterface.DataInterface;

public class ApplicationLogic {
	private Data mData;
	private Gui mGui;
	private DataInterface dataInterface;
	
	ApplicationLogic(Data data, Gui gui){
		mData=data;
		mGui=gui;
		applyDataToGui();
		dataInterface = new DataInterface(mData.getActivity());
	}

	private void applyDataToGui() {
		for(int i = 0; i < mGui.getCategories().length; i++){
			mGui.getCategory(i).setText(mData.getKarteien().get(i));
			mGui.getOverallChallengePerCategory(i).setText(Integer.toString(mData.getStatistik().get(i).getGesamteChallenges()));
			mGui.getDueChallengePerCategory(i).setText(Integer.toString(mData.getStatistik().get(i).getFaelligeChallenges()));
		}		
	}		
	
	public void onCategoryClicked(String category){
		//Test whether the chosen category has any due challenges (yes->start challenge | no -> show error message)
		for(int i = 0 ; i < mData.getStatistik().size(); i++){
			if(mData.getStatistik().get(i).getKartei().equals(category)){
				if(mData.getStatistik().get(i).getFaelligeChallenges() == 0){
					mGui.showToast(mData.getActivity());
				} else {
					//Start activity challenge (learn session) with category and user 	
					Navigation.startActivityChallenge(mData.getActivity(), mData.getUser(), category);
				}
			}
		}			
	}

	public void onRefreshClicked(){
		//After finishing the learn session the gui has to be updated
		
		//update User Information in Data
		mData.setUser(dataInterface.getUser(mData.getUser().getName()));
		
		//update Statistics in data
		mData.loadStatistics();
		
		//apply data (updated statistics) to Gui
		applyDataToGui();	
	}
	
	public void onRestart() {
		onRefreshClicked();
	}
}