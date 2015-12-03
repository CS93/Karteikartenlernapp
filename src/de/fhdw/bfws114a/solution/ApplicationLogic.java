package de.fhdw.bfws114a.solution;

import android.app.Activity;
import android.graphics.Color;
import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.data.Challenge;
import de.fhdw.bfws114a.lernKartei.R.color;

public class ApplicationLogic {
	private Data mData;
	private Gui1 mGui1;
	private Gui2 mGui2;
	private Gui3 mGui3;	
	private int currentQuestionType;
	private Activity mActivity;
	
	ApplicationLogic(Data data, Activity act){
		mData=data;
		mActivity = act;
		applyDataToGui();
	}

	private void applyDataToGui(){		
		Challenge currentChallenge = mData.getCurrentChallenge();
		currentQuestionType = currentChallenge.getFrageTyp();
		if(currentQuestionType == 1){
			//Das Anlegen der Gui geschieht erst an dieser Stelle, damit sie auch erst angezeigt wird, sobald sie benötigt wird
			mGui1 = new Gui1(mActivity);
			applyDataToGui1(currentChallenge, mData.getUserAnswerCheckbox());
			new EventToListenerMapping(mGui1, this);
		}
		
		if(currentQuestionType == 2){
			//Das Anlegen der Gui geschieht erst an dieser Stelle, damit sie auch erst angezeigt wird, sobald sie benötigt wird
			mGui2 = new Gui2(mActivity);
			applyDataToGui2(currentChallenge, mData.getUserAnswerText());
			new EventToListenerMapping(mGui2, this);
		}
		
		if(currentQuestionType == 3){
			//Das Anlegen der Gui geschieht erst an dieser Stelle, damit sie auch erst angezeigt wird, sobald sie benötigt wird
			mGui3 = new Gui3(mActivity);
			applyDataToGui3(currentChallenge);
			new EventToListenerMapping(mGui3, this);
		}
	}
	
	//Wenn es FrageTyp 1 ist diese Methode ausführen
	private void applyDataToGui1(Challenge currentChallenge, boolean[] userAnswer) {
		
		mGui1.getQuestion().setText(currentChallenge.getFrage());
		for(int i = 0; i < 6; i++){
			mGui1.getOption(i).setText(currentChallenge.getAntwort(i));
			mGui1.getOption(i).setChecked(userAnswer[i]);			
			if(userAnswer[i] == currentChallenge.getKorrekteAnwortenFuerCheckbox()[i]){
				//Die CheckBox wurde zu Recht angeklickt bzw. nicht angeklickt
				mGui1.getOption(i).setTextColor(Color.GREEN);
			} else {
				mGui1.getOption(i).setTextColor(Color.RED);				
			}
		}		
	}
	
	//Wenn es FrageTyp 2 ist diese Methode ausführen
	private void applyDataToGui2(Challenge currentChallenge, String userAnswer) {
		mGui2.getQuestion().setText(currentChallenge.getFrage());
		mGui2.getUserAnswer().setText(userAnswer);
		mGui2.getCorrectAnswer().setText(currentChallenge.getAntwort(0));
		
		if(mGui2.getUserAnswer().getText().toString().toLowerCase().equals(mData.getCurrentChallenge().getAntwort(0).toLowerCase())){
			mGui2.getUserAnswerDescription().setTextColor(Color.GREEN);
			mGui2.getUserAnswer().setTextColor(Color.GREEN);
		} else {
			mGui2.getUserAnswerDescription().setTextColor(Color.RED);
			mGui2.getUserAnswer().setTextColor(Color.RED);
		}
		
	}
		
	//Wenn es FrageTyp 3 ist diese Methode ausführen
	private void applyDataToGui3(Challenge currentChallenge) {
		mGui3.getQuestion().setText(currentChallenge.getFrage());	
		mGui3.getAnswer().setText(currentChallenge.getAntwort(0));
	}
		
	
	
	//Überprüfen der Antworten aus der entsprechenden Gui (1, 2 oder 3) und einblenden der Solution.	
	public void onContinueClicked(String buttonText){
		boolean userAnswerCorrect = false;
		if(currentQuestionType == 1){
			
			userAnswerCorrect = true;
			//Überprüfung für jede CheckBox ob sie richtig angeklickt wurde
			for(int i = 0; i<6;i++){
				//Prüfen ob die Checkbox nur dann angeklickt wurde, wenn die Antwort auch richtig ist
				if(mData.getUserAnswerCheckbox()[i] != mData.getCurrentChallenge().getKorrekteAnwortenFuerCheckbox()[i]){
					//CheckBox[i] wurde fälschlicher Weise angeklickt
					userAnswerCorrect = false;
				}
			}
		}			
		
		if(currentQuestionType == 2){
			userAnswerCorrect = false;
			//Überprüfung ob User-Antwort und koreekte Antwort gleich sind			
			if(mGui2.getUserAnswer().getText().toString().toLowerCase().equals(mData.getCurrentChallenge().getAntwort(0).toLowerCase())){
				userAnswerCorrect = true;
			}	
			
		}
		
		if(currentQuestionType == 3){			
			if(buttonText.equals("Ja")){
				//Frage wurde korrekt beantwortet
				userAnswerCorrect = true;
			} else {
				userAnswerCorrect = false;
			}
		}

		//speichern ob user-Antwort korrekt war, Statistics starten, activity beenden
		Navigation.setActivitySolutionReturnValues(mData.getActivity(), userAnswerCorrect);
//		Navigation.startActivityStatistics(mData.getActivity());
		mData.getActivity().finish();
		
	}
}

