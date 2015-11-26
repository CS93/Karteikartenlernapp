package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.util.Log;
import de.fhdw.bfws114a.dataInterface.Challenge;

public class ApplicationLogic {
	private Data mData;
	private Gui1 mGui1;
	private Gui2 mGui2;
	private Gui3 mGui3;
	private int indexOfCurrentChallenge = 0;
	private int currentQuestionType;
	private Activity mActivity;
	
	ApplicationLogic(Data data, Activity act){
		mData=data;
		mActivity = act;
//		mGui1=gui1;
//		mGui2=gui2;
//		mGui3=gui3;
		applyDataToGui();
	}

	private void applyDataToGui(){
		//FälligeChallenges ist null	
		Challenge currentChallenge = mData.getFaelligeChallenges().get(indexOfCurrentChallenge);
		currentQuestionType = currentChallenge.getFrageTyp();
		if(currentQuestionType == 1){
			//Das Anlegen der Gui geschieht erst an dieser Stelle, damit sie auch erst angezeigt wird, sobald sie benötigt wird
			mGui1 = new Gui1(mActivity);
			applyDataToGui1(currentChallenge);
		}
		
		if(currentQuestionType == 2){
			mGui2 = new Gui2(mActivity);
			applyDataToGui2(currentChallenge);
		}
		
		if(currentQuestionType == 3){
			mGui3 = new Gui3(mActivity);
			applyDataToGui3(currentChallenge);
		}
	}
	
	//Wenn es FrageTyp 1 ist diese Methode ausführen
	private void applyDataToGui1(Challenge currentChallenge) {
		
		mGui1.getQuestion().setText(currentChallenge.getFrage());
		for(int i = 0; i < 6; i++){	
			mGui1.getOption(i).setText(currentChallenge.getAntwort(i));
		}
		
	}
	
	//Wenn es FrageTyp 2 ist diese Methode ausführen
	private void applyDataToGui2(Challenge currentChallenge) {
		mGui2.getQuestion().setText(currentChallenge.getFrage());
	}
		
	//Wenn es FrageTyp 3 ist diese Methode ausführen
	private void applyDataToGui3(Challenge currentChallenge) {
		mGui3.getQuestion().setText(currentChallenge.getFrage());	
	}
		
	//Überprüfen der Antworten aus der entsprechenden Gui (1, 2 oder 3) und einblenden der Solution.	
	public void onContinueClicked(){
		Log.d("", "Es wurde auf weiter geklickt");
		if(currentQuestionType == 1){
			// Solution aufrufen mit angkreuzten Anworten (mGui1.getOptions), indexOfCurrentChallenge und Kartei bzw
			// Korrekte Antworten auf entsprechende Frage: mData.getFaelligeChallenges().get(indexOfCurrentChallenge).getKorrekteAnwortenFuerCheckbox()[i]
			
			
			boolean userAnswerCorrect = true;
			//Überprüfung für jede CheckBox ob sie richtig angeklickt wurde
			for(int i = 0; i<6;i++){
				//Prüfen ob die Checkbox nur dann angeklickt wurde, wenn die Antwort auch richtig ist
				if(mGui1.getOption(i).isSelected() != mData.getFaelligeChallenges().get(indexOfCurrentChallenge).getKorrekteAnwortenFuerCheckbox()[i]){
					//CheckBox[i] wurde falsch angeklickt
					userAnswerCorrect = false;
				}
			}
			
			if(userAnswerCorrect){
				//Klasse der Challenge erhöhen (Wenn nicht 6)
			} else {
				//Klasse verringern (wenn nicht 1)
			}
				//Zeitstempel aktualisieren
		}
		
		if(currentQuestionType == 2){
			// Solution aufrufen mit gegebener Anwort (mGui2.getUserAnswer().getText().toString()), indexOfCurrentChallenge und Kartei bzw.
			// Korrekter Antwort auf entsprechende Frage: mData.getFaelligeChallenges().get(indexOfCurrentChallenge).getAntwort(0)
						
			
			//Überprüfung ob User-Antwort und koreekte Antwort gleich sind			
			if(mGui2.getUserAnswer().getText().toString().equals(mData.getFaelligeChallenges().get(indexOfCurrentChallenge).getAntwort(0))){
				boolean userAnswerCorrect = true;
			}
			
		}
		
		if(currentQuestionType == 3){
			//Solution aufrufen mit Kartei
		}

		indexOfCurrentChallenge++;
//		Navigation.startActivityChallenge(mData.getActivity(), mData.getUser(), category);
	}
}

