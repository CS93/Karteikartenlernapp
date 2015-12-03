package de.fhdw.bfws114a.statistics;

import de.fhdw.bfws114a.lernKartei.R.color;

public class ApplicationLogic {
	private Data mData;
	private Gui mGui;
	
	ApplicationLogic(Data data, Gui gui){
		mData=data;
		mGui=gui;
		applyDataToGui();
	}

	private void applyDataToGui() {
		mGui.getNumberOfAnsweredQuestion().setText(String.valueOf(mData.getIndexOfCurrentChallenge()+1));
		
		mGui.getNumberOfCorrectAnsweredQuestion().setText(String.valueOf(mData.getNumberOfCorrectAnswers()));
		
		//Der index kann -1 betragen, wenn gar keine Challenge bearbeitet wurde und dann direkt der Back-Button gedrückt wird. Diese Division durch 0 muss abgefangen werden
		if(mData.getIndexOfCurrentChallenge()+1 == 0){
			mGui.getPercentageOfCorrectAnsweredQuestion().setText("0");
		} else {
			//Um den Prozentsatz korrekt beantworteter Challenges zu errechnen, wird die Anzahl korrekter Antwort + die Anzahl gegebener Antworten geteilt und
			//mit hundert multilpliziert (weil Angabe in %)			
			mGui.getPercentageOfCorrectAnsweredQuestion().setText(String.valueOf(mData.getNumberOfCorrectAnswers()*100/(mData.getIndexOfCurrentChallenge()+1)));
		}
		
		if((mData.getNumberOfDueChallenges()-(mData.getIndexOfCurrentChallenge()+1)) == 0){
			mGui.getLearnSessionCompleted().setVisibility(1);
		}
		
		//Diese Textview sollte die noch fälligen Challenges dieser Lernsession darstellen --> gesamte Challenges dieser Session - die bisher Bearbeiteten
		mGui.getNumberOfDueChallenges().setText(String.valueOf(mData.getNumberOfDueChallenges()-(mData.getIndexOfCurrentChallenge()+1)));
	}
	
	public void onContinueClicked(){
		//Activity beenden um zur nächsten Challenge zu gelangen  
		mData.getActivity().finish();
	}

	public void OnBackButton() {
		//Activity beenden um zur nächsten Challenge zu gelangen  
		mData.getActivity().finish();
	}	
}

