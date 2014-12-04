package com.client.enigmas_quest.listener;

import com.client.enigmas_quest.EnigmaActivity;
import com.client.enigmas_quest.EnigmaApplication;

import android.view.View;
import android.view.View.OnClickListener;

public class AnswerListener implements OnClickListener {

	private String answer;
	private EnigmaApplication application;
	private int enigmaId;
	private EnigmaActivity activity;
	
	public AnswerListener(int id, String answer, EnigmaApplication application, EnigmaActivity activity) {
		this.enigmaId = id;
		this.answer = answer;
		this.application = application;
		this.activity = activity;
	}
	
	@Override
	public void onClick(View arg0) {
		this.application.answerTheQuestion(this.answer, this.enigmaId);
		activity.finish();
	}

}
