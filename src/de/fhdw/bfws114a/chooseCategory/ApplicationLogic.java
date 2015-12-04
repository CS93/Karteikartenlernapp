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
			mGui.getOverallChallengePerCategory(i).setText(Integer.toString(mData.getStatistik().get(i).getGesamteChallenges()));
			mGui.getDueChallengePerCategory(i).setText(Integer.toString(mData.getStatistik().get(i).getFaelligeChallenges()));
		}		
	}		
	
	public void onCategoryClicked(String category){
		//Start activity challenge (learn session) with category and user 	
		Navigation.startActivityChallenge(mData.getActivity(), mData.getUser(), category);
	}
}