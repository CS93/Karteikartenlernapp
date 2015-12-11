package de.fhdw.bfws114a.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.data.User;
import de.fhdw.bfws114a.dataInterface.DataInterface;
import de.fhdw.bfws114a.lernKartei.R;

public class ApplicationLogic {
	private Data mData;
	private Gui mGui;
	
	ApplicationLogic(Data data, Gui gui){
		mData=data;
		mGui=gui;
		applyDataToGui();
	}

	private void applyDataToGui() {
		mGui.setChoiceListSpinner(mData.getUser());		
		
	}
	
	public void onLoginButtonClicked(){
		//Check whether there are Users in Spinner
		if(mData.getUser().size() == 0){
			//Let the GUI show the referring message (no User)
			mGui.showToast(mData.getActivity());
		} else {
			//Check which user has been selected and start UserMenu of the selected User 
			for(User u : mData.getUser()){
				if(u.getName().equals(mGui.getChoiceListSpinner().getSelectedItem().toString())){
					Navigation.startActivityUserMenu(mData.getActivity(), u);
					return;
				}
			}
		}		
	}
		
	
	public void onProfileManagementButtonClicked(){
		//Start ProfileManagement 	
		Navigation.startActivityProfileManagement(mData.getActivity());
	}
	
	public void onInfoButtonClicked(){
		//Show Info-PopupWindow (this Popup contains Information on the developers and the posibility to reset the app)
		LayoutInflater layoutInflater= (LayoutInflater) mData.getActivity().getBaseContext().getSystemService(mData.getActivity().LAYOUT_INFLATER_SERVICE);  
	    View popupView = layoutInflater.inflate(R.layout.popup, null);  
        final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
	             
	       	Button btnDismiss = (Button)popupView.findViewById(R.id.b_dismiss_popup_window);
	        btnDismiss.setOnClickListener(new Button.OnClickListener(){
	        	@Override
	        	public void onClick(View v) {
	        		popupWindow.dismiss();
	        	}});
	        
	        Button btnReset = (Button)popupView.findViewById(R.id.b_reset_popup_window);
	        btnReset.setOnClickListener(new Button.OnClickListener(){
	        	@Override
	        	public void onClick(View v) {
	        		DataInterface dataInterface = new DataInterface(mData.getActivity());
	        		dataInterface.importXMLtoDB();
	        		popupWindow.dismiss();
	        	}});
	    //Avoid that more than one PopupWindow is opened
	    popupWindow.dismiss();    
	    popupWindow.showAsDropDown(mGui.getChoiceListSpinner(), 50, -30);	    
	}
		

	// The acitivty should update the User onRestart (e.g. after finishing ProfileManagement)
	public void onRestart() {
		mData.loadUser();
		applyDataToGui();
	}
}

