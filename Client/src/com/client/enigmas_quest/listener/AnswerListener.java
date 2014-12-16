package com.client.enigmas_quest.listener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.client.enigmas_quest.EnigmaApplication;
import com.client.enigmas_quest.R;
import com.client.enigmas_quest.fragments.DialogEnigmaFragment;

public class AnswerListener implements OnClickListener {

	private String answer;
	private EnigmaApplication application;
	private int enigmaId;
	private Activity activity;
	private String soluce;
	private TextView answerView;
	
	public AnswerListener(int id, String answer, String soluce, TextView answerView, EnigmaApplication application, Activity activity) {
		this.enigmaId = id;
		this.answer = answer;
		this.application = application;
		this.activity = activity;
		this.soluce = soluce;
		this.answerView = answerView;
	}
	
	@Override
	public void onClick(View arg0) {
		boolean succes = answer.equals(soluce);
		this.application.answerTheQuestion(this.answer, this.enigmaId, succes);
		if (succes) {
			answerView.setBackground(activity.getResources().getDrawable(R.drawable.answer_position_true));
		} else {
			answerView.setBackground(activity.getResources().getDrawable(R.drawable.answer_position_false));
		}

		DialogEnigmaFragment dialog = new DialogEnigmaFragment();
		Bundle args = new Bundle();
		args.putBoolean(DialogEnigmaFragment.SUCCES, succes);
		dialog.setArguments(args);
		dialog.show(activity.getFragmentManager(), "Miam");
	}

}
