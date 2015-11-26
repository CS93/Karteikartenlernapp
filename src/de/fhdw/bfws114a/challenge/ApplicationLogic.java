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
			applyDataToGui1(currentChallenge);
		}
		
		if(currentQuestionType == 2){
			applyDataToGui2(currentChallenge);
		}
		
		if(currentQuestionType == 3){
			applyDataToGui3(currentChallenge);
		}
	}
	
	//Wenn es FrageTyp 1 ist diese Methode ausf�hren
	private void applyDataToGui1(Challenge currentChallenge) {
		mGui1.getQuestion().setText(currentChallenge.getFrage());
		for(int i = 0; i < 6; i++){
			mGui1.getOption(i).setText(currentChallenge.getAntwort(i));
		}
		mGui1.showThisGui();
	}
	
	//Wenn es FrageTyp 2 ist diese Methode ausf�hren
	private void applyDataToGui2(Challenge currentChallenge) {
		mGui2.getQuestion().setText(currentChallenge.getFrage());
	}
		
	//Wenn es FrageTyp 3 ist diese Methode ausf�hren
	private void applyDataToGui3(Challenge currentChallenge) {
		mGui3.getQuestion().setText(currentChallenge.getFrage());	
	}
		
	//�berpr�fen der Antworten aus der entsprechenden Gui (1, 2 oder 3) und einblenden der Solution.	
	public void onContinueClicked(){

		if(currentQuestionType == 1){
			// Solution aufrufen mit 
			boolean userAnswerCorrect = true;
			//�berpr�fung f�r jede CheckBox ob sie richtig angeklickt wurde
			for(int i = 0; i<6;i++){
				//Pr�fen ob die Checkbox nur dann angeklickt wurde, wenn die Antwort auch richtig ist
				if(mGui1.getOption(i).isSelected() != mData.getFaelligeChallenges().get(indexOfCurrentChallenge).getKorrekteAnwortenFuerCheckbox()[i]){
					//CheckBox[i] wurde falsch angeklickt
					userAnswerCorrect = false;
				}
			}
			//Solution aufrufen mit Parameter true
			
		}
		
		if(currentQuestionType == 2){
			
		}
		
		if(currentQuestionType == 3){
			
		}

		indexOfCurrentChallenge++;
//		Navigation.startActivityChallenge(mData.getActivity(), mData.getUser(), category);
	}
}

