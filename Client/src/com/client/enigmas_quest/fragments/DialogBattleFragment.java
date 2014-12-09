package com.client.enigmas_quest.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.client.enigmas_quest.R;

public class DialogBattleFragment extends DialogFragment {
	
	public static final String SUCCES = "succes";
	public static final String TIME = "time";
	
	private boolean succes;
	private boolean time;

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Get arguments
		this.succes = getArguments().getBoolean(SUCCES);
		this.time = getArguments().getBoolean(TIME);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if(time) {
        	builder.setMessage(R.string.dialog_enigma_late)
            .setPositiveButton(R.string.dialog_enigma_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    getActivity().finish();
                }
            });
        }
        else if (succes) {
	        builder.setMessage(R.string.dialog_enigma_success)
	               .setPositiveButton(R.string.dialog_enigma_ok, new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       getActivity().finish();
	                   }
	               });
        } else {
        	builder.setMessage(R.string.dialog_enigma_failure)
            .setPositiveButton(R.string.dialog_enigma_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                	getActivity().finish();
                }
            });
        }
        return builder.create();
    }
}