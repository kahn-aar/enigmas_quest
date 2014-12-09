package com.client.enigmas_quest.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.client.enigmas_quest.R;

public class DialogEnigmaFragment extends DialogFragment {
	
	public static final String SUCCES = "succes";
	
	private boolean succes;

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Get arguments
		this.succes = getArguments().getBoolean(SUCCES);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (succes) {
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