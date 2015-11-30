package de.fhdw.bfws114a.statistics;

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
		
		//Um den Prozentsatz korrekt beantworteter Challenges zu errechnen, wird die Anzahl korrekter Antwort + die Anzahl gegebener Antworten geteilt und
		//mit hundert multilpliziert (weil Angabe in %)
		mGui.getPercentageOfCorrectAnsweredQuestion().setText(String.valueOf(mData.getNumberOfCorrectAnswers()*100/(mData.getIndexOfCurrentChallenge()+1)));
		
		//Diese Textview sollte die noch fälligen Challenges dieser Lernsession darstellen --> gesamte Challenges dieser Session - die bisher Bearbeiteten
		mGui.getNumberOfDueChallenges().setText(String.valueOf(mData.getNumberOfDueChallenges()-(mData.getIndexOfCurrentChallenge()+1)));
	}
	
	public void onContinueClicked(){
		//Activity beenden um zur nächsten Challenge zu gelangen  
		mData.getActivity().finish();
	}	
}

