package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.util.Log;
import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.dataInterface.Challenge;
import de.fhdw.bfws114a.dataInterface.DataInterface;

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
		applyDataToGui();
	}

	private void applyDataToGui(){		
		if(mData.getFaelligeChallenges() == null){
			//Gui3 mit: "Es gibt keine fälligen Challenges" (continue muss dann disabled sein)
		}		
		Challenge currentChallenge = mData.getFaelligeChallenges().get(indexOfCurrentChallenge);
		currentQuestionType = currentChallenge.getFrageTyp();
		if(currentQuestionType == 1){
			//Das Anlegen der Gui geschieht erst an dieser Stelle, damit sie auch erst angezeigt wird, sobald sie benötigt wird
			mGui1 = new Gui1(mActivity);
			applyDataToGui1(currentChallenge);
		}
		
		if(currentQuestionType == 2){
			//Das Anlegen der Gui geschieht erst an dieser Stelle, damit sie auch erst angezeigt wird, sobald sie benötigt wird
			mGui2 = new Gui2(mActivity);
			applyDataToGui2(currentChallenge);
		}
		
		if(currentQuestionType == 3){
			//Das Anlegen der Gui geschieht erst an dieser Stelle, damit sie auch erst angezeigt wird, sobald sie benötigt wird
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
		mGui2.getUserAnswer().setText("");
	}
		
	//Wenn es FrageTyp 3 ist diese Methode ausführen
	private void applyDataToGui3(Challenge currentChallenge) {
		mGui3.getQuestion().setText(currentChallenge.getFrage());	
	}
		
	
	
	//Überprüfen der Antworten aus der entsprechenden Gui (1, 2 oder 3) und einblenden der Solution.	
	public void onContinueClicked(){
		Log.d("", "Button weiter wurde geklickt");
		if(currentQuestionType == 1){
			// Solution aufrufen mit angkreuzten Anworten (mGui1.getOptions), indexOfCurrentChallenge und Kartei 
			Navigation.startActivitySolution(mActivity, mGui1.getOptions(), mData.getFaelligeChallenges().get(indexOfCurrentChallenge));
			
			boolean userAnswerCorrect = true;
			//Überprüfung für jede CheckBox ob sie richtig angeklickt wurde
			for(int i = 0; i<6;i++){
				//Prüfen ob die Checkbox nur dann angeklickt wurde, wenn die Antwort auch richtig ist
				if(mGui1.getOption(i).isSelected() != mData.getFaelligeChallenges().get(indexOfCurrentChallenge).getKorrekteAnwortenFuerCheckbox()[i]){
					//CheckBox[i] wurde falsch angeklickt
					userAnswerCorrect = false;
				}
			}
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
		
	}

	public void answerFromSolution(boolean userAnswerCorrect) {
		//Klasse der Challenge erhöhen wenn Antwort korrekt war und die Challenge nicht bereits in Klasse 6 ist
		if(userAnswerCorrect && mData.getFaelligeChallenges().get(indexOfCurrentChallenge).getAktuelleKlasse() != 6){
			
			DataInterface.increaseClass(mData.getFaelligeChallenges().get(indexOfCurrentChallenge));
		}
		//Klasse der Challenge verringern wenn Antwort falsch war und die Challenge nicht bereits in Klasse 1 ist
		if ((!userAnswerCorrect) && mData.getFaelligeChallenges().get(indexOfCurrentChallenge).getAktuelleKlasse() != 1) {
			DataInterface.decreaseClass(mData.getFaelligeChallenges().get(indexOfCurrentChallenge));
		}
			
		//Zeitstempel aktualisieren
		DataInterface.setCurrentTimestamp(mData.getFaelligeChallenges().get(indexOfCurrentChallenge), mData.getUser());
		
		//Aktuellen index erhöhen und neue Challenge laden
		indexOfCurrentChallenge++;
	}
}

