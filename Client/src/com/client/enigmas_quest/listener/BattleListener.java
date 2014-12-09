package com.client.enigmas_quest.listener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.client.enigmas_quest.EnigmaApplication;
import com.client.enigmas_quest.R;
import com.client.enigmas_quest.fragments.DialogBattleFragment;
import com.client.enigmas_quest.fragments.DialogEnigmaFragment;

public class BattleListener implements OnClickListener {

	private String answer;
	private EnigmaApplication application;
	private int enigmaId;
	private Activity activity;
	private String soluce;
	private TextView answerView;
	private int opponentId;
	
	public BattleListener(int id, String answer, String soluce, TextView answerView, EnigmaApplication application, Activity activity, int opponentId) {
		this.enigmaId = id;
		this.answer = answer;
		this.application = application;
		this.activity = activity;
		this.soluce = soluce;
		this.answerView = answerView;
		this.opponentId = opponentId;
	}
	
	@Override
	public void onClick(View arg0) {
		this.application.answerTheBattle(this.answer, this.enigmaId, false, opponentId);
		boolean succes = answer.equals(soluce);
		if (succes) {
			answerView.setBackground(activity.getResources().getDrawable(R.drawable.answer_position_true));
		} else {
			answerView.setBackground(activity.getResources().getDrawable(R.drawable.answer_position_false));
		}

		DialogBattleFragment dialog = new DialogBattleFragment();
		Bundle args = new Bundle();
		args.putBoolean(DialogBattleFragment.SUCCES, succes);
		args.putBoolean(DialogBattleFragment.TIME, false);
		dialog.setArguments(args);
		dialog.show(activity.getFragmentManager(), "Miam");
		
	}

}
