/**
 * @author Carsten Schlender
 */
 
package de.fhdw.bfws114a.solution;

import android.app.Activity;
import android.graphics.Color;
import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.data.Challenge;

public class ApplicationLogic {
	private Data mData;
	private Gui1 mGui1;
	private Gui2 mGui2;
	private Gui3 mGui3;	
	private int mCurrentQuestionType;
	private Activity mActivity;
	
	ApplicationLogic(Data data, Activity act){
		mData=data;
		mActivity = act;
		applyDataToGui();
	}

	private void applyDataToGui(){		
		Challenge currentChallenge = mData.getCurrentChallenge();
		mCurrentQuestionType = currentChallenge.getQuestionType();
		if(mCurrentQuestionType == 1){
			//initiating the gui for question type 1
			mGui1 = new Gui1(mActivity);
			applyDataToGui1(currentChallenge, mData.getUserAnswerCheckbox());
			new EventToListenerMapping(mGui1, this);
		}
		
		if(mCurrentQuestionType == 2){
			//initiating the gui for question type 2
			mGui2 = new Gui2(mActivity);
			applyDataToGui2(currentChallenge, mData.getUserAnswerText());
			new EventToListenerMapping(mGui2, this);
		}
		
		if(mCurrentQuestionType == 3){
			//initiating the gui for question type 3
			mGui3 = new Gui3(mActivity);
			applyDataToGui3(currentChallenge);
			new EventToListenerMapping(mGui3, this);
		}
	}
	
	//Whether its question type onn, this method is called
	private void applyDataToGui1(Challenge currentChallenge, boolean[] userAnswer) {
		
		mGui1.getQuestionView().setText(currentChallenge.getQuestion());
		for(int i = 0; i < 6; i++){
			mGui1.getOptionCheckBox(i).setText(currentChallenge.getAnswer(i));
			mGui1.getOptionCheckBox(i).setChecked(userAnswer[i]);			
			if(currentChallenge.getCorrectAnswersForCheckbox()[i] == true){				
				//this answer is correct for the question and marked green
				mGui1.getOptionCheckBox(i).setTextColor(Color.GREEN);
			} else {
				mGui1.getOptionCheckBox(i).setTextColor(Color.RED);				
			}
		}		
	}
	
	//Whether its question type two, this method is called
	private void applyDataToGui2(Challenge currentChallenge, String userAnswer) {
		mGui2.getQuestionView().setText(currentChallenge.getQuestion());
		mGui2.getUserAnswerView().setText(userAnswer);
		mGui2.getCorrectAnswerView().setText(currentChallenge.getAnswer(0));
		
		if(mGui2.getUserAnswerView().getText().toString().toLowerCase().equals(mData.getCurrentChallenge().getAnswer(0).toLowerCase())){
			//user answer is correct for the question and marked green
			mGui2.getUserAnswerDescriptionView().setTextColor(Color.GREEN);
			mGui2.getUserAnswerView().setTextColor(Color.GREEN);
		} else {
			mGui2.getUserAnswerDescriptionView().setTextColor(Color.RED);
			mGui2.getUserAnswerView().setTextColor(Color.RED);
		}
		
	}

	//Whether its question type three, this method is called
	private void applyDataToGui3(Challenge currentChallenge) {
		mGui3.getQuestionView().setText(currentChallenge.getQuestion());	
		mGui3.getAnswerView().setText(currentChallenge.getAnswer(0));
	}
		
	
	
	//check whether user answer was correct and show statistics	
	public void onContinueClicked(String buttonText){
		boolean userAnswerCorrect = false;
		if(mCurrentQuestionType == 1){
			
			userAnswerCorrect = true;
			//Check whether every checkbox is ticked/unticked correct
			for(int i = 0; i<6;i++){
				 
				if(mData.getUserAnswerCheckbox()[i] != mData.getCurrentChallenge().getCorrectAnswersForCheckbox()[i]){
					//Checkbox was ticked wrong
					userAnswerCorrect = false;
				}
			}
		}			
		
		if(mCurrentQuestionType == 2){
			userAnswerCorrect = false;
			//check if user answer text is equals challenge answer			
			if(mGui2.getUserAnswerView().getText().toString().toLowerCase().equals(mData.getCurrentChallenge().getAnswer(0).toLowerCase())){
				userAnswerCorrect = true;
			}	
			
		}
		
		if(mCurrentQuestionType == 3){			
			if(buttonText.equals("Ja")){
				//user says he knew the answer
				userAnswerCorrect = true;
			} else {
				userAnswerCorrect = false;
			}
		}

		//save whether user answer was correct, start activity statistics and finish this activity
		Navigation.setActivitySolutionReturnValues(mData.getActivity(), userAnswerCorrect);
		mData.getActivity().finish();
		
	}
}

