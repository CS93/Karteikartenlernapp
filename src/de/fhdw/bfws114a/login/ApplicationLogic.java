package de.fhdw.bfws114a.login;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.data.User;
import de.fhdw.bfws114a.lernKartei.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class ApplicationLogic {
	private Data mData;
	private Gui mGui;
	
	ApplicationLogic(Data data, Gui gui){
		mData=data;
		mGui=gui;
		applyDataToGui();
	}

	private void applyDataToGui() {
		mGui.setChoiceList(mData.getUser());		
		
	}
	
	public void onLoginButtonClicked(){
		if(mData.getUser().size() == 0){
			//Keine User im Spinner mit denen man sich einloggen kann
			mGui.showToast(mData.getActivity());
		} else {
			//Weiterleitung zum UserMenu (Auswahl der Kartei) mit entsprechendem User 
			for(User u : mData.getUser()){
				if(u.getName().equals(mGui.getChoiceList().getSelectedItem().toString())){
					Navigation.startActivityUserMenu(mData.getActivity(), u);
					return;
				}
			}
		}		
	}
		
	
	public void onProfileManagementButtonClicked(){
		//Weiterleitung zum UserMenu (Auswahl der Kartei) mit entsprechendem User 	
		Navigation.startActivityProfileManagement(mData.getActivity());
	}
	
	public void onInfoButtonClicked(){
		//Popup einblenden
		LayoutInflater layoutInflater= (LayoutInflater) mData.getActivity().getBaseContext().getSystemService(mData.getActivity().LAYOUT_INFLATER_SERVICE);  
	    View popupView = layoutInflater.inflate(R.layout.popup, null);  
        final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
	             
	             Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
	             btnDismiss.setOnClickListener(new Button.OnClickListener(){
	         @Override
	         public void onClick(View v) {
	         popupWindow.dismiss();
	         }});
	    popupWindow.showAsDropDown(mGui.getChoiceList(), 50, -30);	    
	}
		


	public void onRestart() {
		mData.ladeUser();
		applyDataToGui();
	}
	
//	public void processActivityReturnValues(int requestCode, int resultCode, Intent intent) {
//		if(resultCode==Activity.RESULT_OK) {
//			if(requestCode==Constants.REQUESTCODE_ACTIVITY_EDIT) {
//				int value;
//				value = intent.getIntExtra(Constants.KEY_RETURN_COUNTER_VALUE, mData.getCounterValue());
//				mData.setCounterValue(value);
//				mGui.setCounterValue(value);
//			}
//		}
//	}

}

