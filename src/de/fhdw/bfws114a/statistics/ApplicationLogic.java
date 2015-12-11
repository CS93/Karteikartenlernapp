/**
 * @author Carsten Schlender
 */
 
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
		mGui.getNumberOfAnsweredQuestionView().setText(String.valueOf(mData.getIndexOfCurrentChallenge()+1));
		
		mGui.getNumberOfCorrectAnsweredQuestionView().setText(String.valueOf(mData.getNumberOfCorrectAnswers()));
		
		//index could be -1 (this is the case when no challenge has been answered and back button has been clicked. This could cause a division by zero and has to be avoided
		if(mData.getIndexOfCurrentChallenge()+1 == 0){
			mGui.getPercentageOfCorrectAnsweredQuestionView().setText("0");
		} else {
			//to calculate percentage of correct answerded challenges, the app takes the numberOfCorrectAnswers *100 / numberOfAnswerdedQuestion (index+1)			
			mGui.getPercentageOfCorrectAnsweredQuestionView().setText(String.valueOf(mData.getNumberOfCorrectAnswers()*100/(mData.getIndexOfCurrentChallenge()+1)));
		}
		
		if((mData.getNumberOfDueChallenges()-(mData.getIndexOfCurrentChallenge()+1)) == 0){
			mGui.showToast(mData.getActivity());
		}
		
		//this textview shoul visualize the numberOfdueChallenges of this learn session. This is calculated through allChallenges of thisSession - answeredChallenges
		mGui.getNumberOfDueChallengesView().setText(String.valueOf(mData.getNumberOfDueChallenges()-(mData.getIndexOfCurrentChallenge()+1)));
	}
	
	public void onContinueClicked(){
		//Finish activity to restart activity challenge with next challenge
		mData.getActivity().finish();
	}

	public void OnBackButton() {
		//Activity beenden um zur nächsten Challenge zu gelangen  
		mData.getActivity().finish();
	}	
}

