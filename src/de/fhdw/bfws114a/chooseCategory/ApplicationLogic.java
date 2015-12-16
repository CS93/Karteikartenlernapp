/**
 * @author Carsten Schlender
 */
 
package de.fhdw.bfws114a.chooseCategory;

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
		mDataInterface = new DataInterface(mData.getActivity());
	}

	private void applyDataToGui() {
		for(int i = 0; i < mGui.getCategoryButtons().length; i++){
			mGui.getCategoryButton(i).setText(mData.getCategories().get(i));
			mGui.getOverallChallengePerCategoryView(i).setText(Integer.toString(mData.getStatistics().get(i).getOverAllChallengers()));
			mGui.getDueChallengePerCategoryView(i).setText(Integer.toString(mData.getStatistics().get(i).getDueChallenges()));
		}		
	}		
	
	public void onCategoryClicked(String category){
		//Test whether the chosen category has any due challenges (yes->start challenge | no -> show error message)
		for(int i = 0 ; i < mData.getStatistics().size(); i++){
			if(mData.getStatistics().get(i).getCategory().equals(category)){
				if(mData.getStatistics().get(i).getDueChallenges() == 0){
					mGui.showToastNoDueChallenges(mData.getActivity());
				} else {
					//Start activity challenge (learn session) with category and user 	
					Navigation.startActivityChallenge(mData.getActivity(), mData.getUser(), category);
				}
			}
		}			
	}

	public void onRefreshClicked(){
		//E.g. After finishing the learn session the gui has to be updated
		
		//show a feedback (toast) to user
		mGui.showToastRefresh(mData.getActivity());
		
		//update User Information in Data
		mData.setUser(mDataInterface.getUser(mData.getUser().getName()));
		
		//update Statistics in data
		mData.loadStatistics();
		
		//apply data (updated statistics) to Gui
		applyDataToGui();	
	}
	
	public void onRestart() {
		onRefreshClicked();
	}
}