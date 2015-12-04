package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import de.fhdw.bfws114a.data.Constants;
import de.fhdw.bfws114a.data.User;

public class Init extends Activity {

	private Data mData;
	private Gui1 mGui1; //Eine GUI je Fragetyp
	private Gui2 mGui2;
	private Gui3 mGui3; 
	private ApplicationLogic mApplicationLogic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Laden des aktuellen Users und der Kategorie aus dem Intent und an Data weitergegeben
		initData(savedInstanceState, (User) getIntent().getSerializableExtra(Constants.KEY_PAR_CURRENT_USER_VALUE), getIntent().getStringExtra(Constants.KEY_PAR_CURRENT_CATEGORY_VALUE));		
		initApplicationLogic();
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//Aus dem Intent wird der boolean-wert hinsichtlich Korrektheit der Frage zurückgeliefert
		mApplicationLogic.answerFromSolution(data.getBooleanExtra(Constants.KEY_RETURN_CHALLENGE_ANSWER_BOOL, false));
		
	}
	
	@Override
	public void onBackPressed() {
		//Sie muss hier überschrieben werden um nicht zur Karteiauswahl zurückzukehren sondern die Statistics.Init zu öffnen
		mApplicationLogic.FinishLearnSession();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		mData.saveDataInBundle(outState);
		super.onSaveInstanceState(outState);
	}


	private void initData(Bundle savedInstanceState, User user, String category) {
		mData = new Data(savedInstanceState, this, user, category);
		
	}
	
//	private void initGui() {
//		mGui1 = new Gui1(this); //Fragetyp1 = Checkboxes (Auswahl)
//		mGui2 = new Gui2(this); //Fragetyp2 = Text-Antwort
//		mGui3 = new Gui3(this); //Fragetyp3 = Eigenkontrolle
//		
//	}

	private void initApplicationLogic() {
		mApplicationLogic = new ApplicationLogic(mData, this);
	}

	private void initEventToListenerMapping() {
		new EventToListenerMapping(mGui1, mApplicationLogic);
		
	}
}
