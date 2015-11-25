package de.fhdw.bfws114a.challenge;

import de.fhdw.bfws114a.dataInterface.Challenge;

public class ApplicationLogic {
	private Data mData;
	private Gui1 mGui1;
	private Gui2 mGui2;
	private Gui3 mGui3;
	private int indexOfCurrentChallenge = 0;
	private int currentQuestionType;
	
	ApplicationLogic(Data data, Gui1 gui1, Gui2 gui2, Gui3 gui3){
		mData=data;
		mGui1=gui1;
		mGui2=gui2;
		mGui3=gui3;
		applyDataToGui();
	}

	private void applyDataToGui(){
		Challenge currentChallenge = mData.getFaelligeChallenges().get(indexOfCurrentChallenge);
		currentQuestionType = currentChallenge.getFrageTyp();
		if(currentQuestionType == 1){
			applyDataToGui1();
		}
		
		if(currentQuestionType == 2){
			applyDataToGui2();
		}
		
		if(currentQuestionType == 3){
			applyDataToGui3();
		}
	}
	
	//Wenn es FrageTyp 1 ist diese Methode ausführen
	private void applyDataToGui1() {
		
	}
	
	//Wenn es FrageTyp 2 ist diese Methode ausführen
	private void applyDataToGui2() {
			
	}
		
	//Wenn es FrageTyp 3 ist diese Methode ausführen
	private void applyDataToGui3() {
			
	}
		
	
	public void onContinueClicked(){
		//Überprüfen der Antworten aus der entsprechenden Gui (1, 2 oder 3) und einblenden der Solution.
		
		indexOfCurrentChallenge++;
//		Navigation.startActivityChallenge(mData.getActivity(), mData.getUser(), category);
	}
}

