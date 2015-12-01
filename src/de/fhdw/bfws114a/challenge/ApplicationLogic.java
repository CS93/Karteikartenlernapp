package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.util.Log;
import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.data.Challenge;
import de.fhdw.bfws114a.dataInterface.DataInterface;

public class ApplicationLogic {
	private Data mData;
	private Gui1 mGui1;
	private Gui2 mGui2;
	private Gui3 mGui3;
	private DataInterface mDataInterface;
	private int currentQuestionType;
	private Activity mActivity;
	
	ApplicationLogic(Data data, Activity act){
		mDataInterface = new DataInterface(act);
		mData=data;
		mActivity = act;
		applyDataToGui();
	}

	private void applyDataToGui(){		
		if(mData.getFaelligeChallenges() == null){
			//Gui3 mit: "Es gibt keine f�lligen Challenges" (continue muss dann disabled sein)
		}		
		Challenge currentChallenge = mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge());
		currentQuestionType = currentChallenge.getFrageTyp();
		if(currentQuestionType == 1){
			//Das Anlegen der Gui geschieht erst an dieser Stelle, damit sie auch erst angezeigt wird, sobald sie ben�tigt wird
			mGui1 = new Gui1(mActivity);
			applyDataToGui1(currentChallenge);
			new EventToListenerMapping(mGui1, this);
		}
		
		if(currentQuestionType == 2){
			//Das Anlegen der Gui geschieht erst an dieser Stelle, damit sie auch erst angezeigt wird, sobald sie ben�tigt wird
			mGui2 = new Gui2(mActivity);
			applyDataToGui2(currentChallenge);
			new EventToListenerMapping(mGui2, this);
		}
		
		if(currentQuestionType == 3){
			//Das Anlegen der Gui geschieht erst an dieser Stelle, damit sie auch erst angezeigt wird, sobald sie ben�tigt wird
			mGui3 = new Gui3(mActivity);
			applyDataToGui3(currentChallenge);
			new EventToListenerMapping(mGui3, this);
		}
	}
	
	//Wenn es FrageTyp 1 ist diese Methode ausf�hren
	private void applyDataToGui1(Challenge currentChallenge) {
		
		mGui1.getQuestion().setText(currentChallenge.getFrage());
		for(int i = 0; i < 6; i++){	
			mGui1.getOption(i).setText(currentChallenge.getAntwort(i));
		}		
	}
	
	//Wenn es FrageTyp 2 ist diese Methode ausf�hren
	private void applyDataToGui2(Challenge currentChallenge) {
		mGui2.getQuestion().setText(currentChallenge.getFrage());
		mGui2.getUserAnswer().setText("");
	}
		
	//Wenn es FrageTyp 3 ist diese Methode ausf�hren
	private void applyDataToGui3(Challenge currentChallenge) {
		mGui3.getQuestion().setText(currentChallenge.getFrage());	
	}
		
	
	
	//�berpr�fen der Antworten aus der entsprechenden Gui (1, 2 oder 3) und einblenden der Solution.	
	public void onContinueClicked(){
		
		
		if(currentQuestionType == 1){
			//Anworten der CheckBoxes im boolean[] speichern
			boolean[] checkboxAnswer = new boolean[6];
			for(int i = 0; i < 6; i++){
				checkboxAnswer[i] = mGui1.getOption(i).isChecked();
			}
			
			// Solution aufrufen mit angkreuzten Anworten (checkboxAnswer) und Kartei 
			Navigation.startActivitySolution(mActivity, checkboxAnswer, mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()), null);
		}			
		
		if(currentQuestionType == 2){
			// Solution aufrufen mit gegebener Anwort (mGui2.getUserAnswer().getText().toString()) und Kartei
			Navigation.startActivitySolution(mActivity, null, mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()), mGui2.getUserAnswer().getText().toString());
			
		}
		
		if(currentQuestionType == 3){
			//Solution aufrufen mit Kartei
			Navigation.startActivitySolution(mActivity, null, mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()), null);
		}
		
	}

	public void answerFromSolution(boolean userAnswerCorrect) {
		Log.d("Applogic 113", ""+userAnswerCorrect);
		//Klasse der Challenge erh�hen wenn Antwort korrekt war und die Challenge nicht bereits in Klasse 6 ist
		if(userAnswerCorrect){
			mData.increaseNumberOfCorrectAnswers();
			if(mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()).getAktuelleKlasse() != 6){
				mDataInterface.increaseClass(mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()));
			}
		} else {
			mData.decreaseNumberOfCorrectAnswers();
			//Klasse der Challenge verringern wenn Antwort falsch war und die Challenge nicht bereits in Klasse 1 ist
			if(mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()).getAktuelleKlasse() != 1){
				mDataInterface.decreaseClass(mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()));
			}
		}
			
		Navigation.startActivityStatistics(mData.getActivity(), mData.getIndexOfCurrentChallenge(), mData.getFaelligeChallenges().size(), mData.getNumberOfCorrectAnswers(), mData.getNumberOfWrongAnswers());
		
		//Zeitstempel aktualisieren
		mDataInterface.setCurrentTimestamp(mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()), mData.getUser());
		
		//Aktuellen index erh�hen und neue Challenge laden
		mData.increaseIndexOfCurrentChallenge();
	}
}

