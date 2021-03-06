package com.client.enigmas_quest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.client.enigmas_quest.constants.EnigmasConstants;
import com.client.enigmas_quest.listener.AnswerListener;
import com.client.enigmas_quest.mappage.Question;

/**
 * Activity for answer an enigma
 * 
 * @author Nicolas
 *
 */
public class EnigmaActivity extends Activity {

	private static final String STATE_ENIGMA_ID = "enigma_id";
	private static final String STATE_ENIGMA_NAME = "enigma_name";
	private static final String STATE_ENIGMA_DESCRIPTION = "enigma_description";
	private static final String STATE_ENIGMA_ANSWERS = "enigma_answers";
	private static final String STATE_ENIGMA_SOLUCE = "enigma_soluce";
	
	int id;
	String name;
	String description;
	List<String> reponses;
	String soluce;
	EnigmaApplication application;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enigma);

		application = (EnigmaApplication) getApplicationContext();
		
		if (savedInstanceState == null) {
			Intent intent = getIntent();
			id = 0;
			if (intent != null) {
	            id = intent.getIntExtra(EnigmasConstants.ENIGMA_ID, -1);
	            //R�cup�ration des donn�es de la qu�te
	            Question enigme = (Question) application.getEngimaById(id);
	    		name = "bonjour";
	    		description = enigme.getQuestion();
	    		reponses = new ArrayList<String>(4);
	    		reponses.add(enigme.getPossible1());
	    		reponses.add(enigme.getPossible2());
	    		reponses.add(enigme.getPossible3());
	    		reponses.add(enigme.getPossible4());
	    		soluce = enigme.getReponse();
			}
			
		} else {
			id = savedInstanceState.getInt(STATE_ENIGMA_ID);
			name = savedInstanceState.getString(STATE_ENIGMA_NAME);
			description = savedInstanceState.getString(STATE_ENIGMA_DESCRIPTION);
			reponses = new ArrayList<String>(4);
			String[] rep = savedInstanceState.getStringArray(STATE_ENIGMA_ANSWERS);
			for(int i = 0; i<4; i++) {
				reponses.add(rep[i]);
			}
			soluce = savedInstanceState.getString(STATE_ENIGMA_SOLUCE);
		}
		
		float density = getBaseContext().getResources().getDisplayMetrics().density;
		//Placement des diff�rents �l�ments
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		int screenWidth = size.x;
		int halfScreenWidth = (int)(screenWidth *0.5);
		
		int marginExt = (int) ((int) 20 * density + 0.5f);
		int marginInt = (int) ((int) 20 * density + 0.5f);
		if (halfScreenWidth > 150) {
			marginExt = (int) ((int) 20 * density + 0.5f);
		}
		
		TextView title = (TextView) findViewById(R.id.title_enigma);
		title.setText(name);
		TextView subTitle = (TextView) findViewById(R.id.description_enigma);
		subTitle.setText(description);
		
		TextView answer1 = (TextView) findViewById(R.id.answer1);
		answer1.layout(0, 0, 0, 0);
		answer1.setWidth(halfScreenWidth-marginExt - marginInt - 16);
		answer1.setText(reponses.get(0));
		answer1.setOnClickListener(new AnswerListener(id, reponses.get(0), soluce, answer1, application, this));
		
		TextView answer2 = (TextView) findViewById(R.id.answer2);
		answer2.layout(0, 0, 0, 0);
		answer2.setWidth(halfScreenWidth-marginExt - marginInt - 16);
		answer2.setText(reponses.get(1));
		answer2.setOnClickListener(new AnswerListener(id, reponses.get(1), soluce, answer2, application, this));
		
		TextView answer3 = (TextView) findViewById(R.id.answer3);
		answer3.layout(0, 0, 0, 0);
		answer3.setWidth(halfScreenWidth-marginExt - marginInt - 16);
		answer3.setText(reponses.get(2));
		answer3.setOnClickListener(new AnswerListener(id, reponses.get(2), soluce, answer3, application, this));
		
		TextView answer4 = (TextView) findViewById(R.id.answer4);
		answer4.layout(0, 0, 0, 0);
		answer4.setWidth(halfScreenWidth-marginExt - marginInt - 16);
		answer4.setText(reponses.get(3));
		answer4.setOnClickListener(new AnswerListener(id, reponses.get(3), soluce, answer4, application, this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enigma, menu);
		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putInt(STATE_ENIGMA_NAME, id);
		savedInstanceState.putString(STATE_ENIGMA_NAME, name);
		savedInstanceState.putString(STATE_ENIGMA_DESCRIPTION, description);
		String[] rep = new String[4];
		rep = reponses.toArray(rep);
		savedInstanceState.putStringArray(STATE_ENIGMA_ANSWERS, rep);
		savedInstanceState.putString(STATE_ENIGMA_SOLUCE, soluce);
		super.onSaveInstanceState(savedInstanceState);
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	    super.onRestoreInstanceState(savedInstanceState);
	   
	    id = savedInstanceState.getInt(STATE_ENIGMA_ID);
		name = savedInstanceState.getString(STATE_ENIGMA_NAME);
		description = savedInstanceState.getString(STATE_ENIGMA_DESCRIPTION);
		reponses = new ArrayList<String>(4);
		String[] rep = savedInstanceState.getStringArray(STATE_ENIGMA_ANSWERS);
		for(int i = 0; i<4; i++) {
			reponses.add(rep[i]);
		}
		soluce = savedInstanceState.getString(STATE_ENIGMA_SOLUCE);
	}


}
