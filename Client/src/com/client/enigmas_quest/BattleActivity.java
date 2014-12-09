package com.client.enigmas_quest;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.client.enigmas_quest.constants.EnigmasConstants;
import com.client.enigmas_quest.fragments.DialogBattleFragment;
import com.client.enigmas_quest.listener.BattleListener;
import com.client.enigmas_quest.mappage.Question;

public class BattleActivity extends ActionBarActivity {


	private static final String STATE_ENIGMA_ID = "enigma_id";
	private static final String STATE_ENIGMA_NAME = "enigma_name";
	private static final String STATE_BATTLE_DESCRIPTION = "enigma_description";
	private static final String STATE_BATTLE_ANSWERS = "enigma_answers";
	private static final String STATE_BATTLE_SOLUCE = "enigma_soluce";
	private static final String STATE_BATTLE_OPPONENT_NAME = "battle_opponent name";
	private static final String STATE_BATTLE_OPPONENT_ID = "battle_opponent id";
	private static final String STATE_BATTLE_TIME_LEFT = "battle time";
	
	String opponentName;
	int opponentId;
	int id;
	String name;
	String description;
	List<String> reponses;
	String soluce;
	EnigmaApplication application;
	int timeLeft;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);

		application = (EnigmaApplication) getApplicationContext();
		
		if (savedInstanceState == null) {
			Intent intent = getIntent();
			id = 0;
			if (intent != null) {
	            id = intent.getIntExtra(EnigmasConstants.ENIGMA_ID, -1);
	            opponentId = intent.getIntExtra(EnigmasConstants.OPPONENT_ID, -1);
	            opponentName = intent.getStringExtra(EnigmasConstants.OPPONENT_NAME);
	            //Récupération des données de la quête
	            Question enigme = application.getEngimaById(id);
	    		name = "bonjour";
	    		description = enigme.getQuestion();
	    		reponses = new ArrayList<String>(4);
	    		reponses.add(enigme.getPossible1());
	    		reponses.add(enigme.getPossible2());
	    		reponses.add(enigme.getPossible3());
	    		reponses.add(enigme.getPossible4());
	    		soluce = enigme.getReponse();
	    		timeLeft = 30;
			}
			
		} else {
			id = savedInstanceState.getInt(STATE_ENIGMA_ID);
			name = savedInstanceState.getString(STATE_ENIGMA_NAME);
			description = savedInstanceState.getString(STATE_BATTLE_DESCRIPTION);
			reponses = new ArrayList<String>(4);
			String[] rep = savedInstanceState.getStringArray(STATE_BATTLE_ANSWERS);
			for(int i = 0; i<4; i++) {
				reponses.add(rep[i]);
			}
			soluce = savedInstanceState.getString(STATE_BATTLE_SOLUCE);
			opponentId = savedInstanceState.getInt(STATE_BATTLE_OPPONENT_ID);
            opponentName = savedInstanceState.getString(STATE_BATTLE_OPPONENT_NAME);
			timeLeft = savedInstanceState.getInt(STATE_BATTLE_TIME_LEFT);
		}
		
		float density = getBaseContext().getResources().getDisplayMetrics().density;
		//Placement des différents éléments
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
		
		TextView opponent = (TextView) findViewById(R.id.opponent_name);
		opponent.setText("Versus " + opponentName);
		
		ProgressBar progressBar = (ProgressBar) findViewById(R.id.time_left_bar);
		progressBar.setProgress(timeLeft);
		TextView timeView = (TextView) findViewById(R.id.time_view);
		timeView.setText(String.valueOf(timeLeft));
		doProgress(progressBar, timeView);
		
		TextView answer1 = (TextView) findViewById(R.id.answer1);
		answer1.layout(0, 0, 0, 0);
		answer1.setWidth(halfScreenWidth-marginExt - marginInt - 16);
		answer1.setText(reponses.get(0));
		answer1.setOnClickListener(new BattleListener(id, reponses.get(0), soluce, answer1, application, this, opponentId));
		
		TextView answer2 = (TextView) findViewById(R.id.answer2);
		answer2.layout(0, 0, 0, 0);
		answer2.setWidth(halfScreenWidth-marginExt - marginInt - 16);
		answer2.setText(reponses.get(1));
		answer2.setOnClickListener(new BattleListener(id, reponses.get(1), soluce, answer2, application, this, opponentId));
		
		TextView answer3 = (TextView) findViewById(R.id.answer3);
		answer3.layout(0, 0, 0, 0);
		answer3.setWidth(halfScreenWidth-marginExt - marginInt - 16);
		answer3.setText(reponses.get(2));
		answer3.setOnClickListener(new BattleListener(id, reponses.get(2), soluce, answer3, application, this, opponentId));
		
		TextView answer4 = (TextView) findViewById(R.id.answer4);
		answer4.layout(0, 0, 0, 0);
		answer4.setWidth(halfScreenWidth-marginExt - marginInt - 16);
		answer4.setText(reponses.get(3));
		answer4.setOnClickListener(new BattleListener(id, reponses.get(3), soluce, answer4, application, this, opponentId));
	}

	private void doProgress(final ProgressBar progressBar, final TextView timeView) {
		final Thread t = new Thread(){

		   @Override
		   public void run(){
		 
			   while(timeLeft > 0) {
					timeLeft--;
					progressBar.setProgress(timeLeft);
					//timeView.setText(String.valueOf(timeLeft));
					SystemClock.sleep(1000);
				}
			    //Fin du temps imparti, appel au dialog
				application.answerTheBattle(null, id, true, opponentId);
				DialogBattleFragment dialog = new DialogBattleFragment();
				Bundle args = new Bundle();
				args.putBoolean(DialogBattleFragment.SUCCES, false);
				args.putBoolean(DialogBattleFragment.TIME, true);
				dialog.setArguments(args);
				dialog.show(getFragmentManager(), "miam2");
		   }
		};
		t.start();
		
		
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
		savedInstanceState.putString(STATE_BATTLE_DESCRIPTION, description);
		String[] rep = new String[4];
		rep = reponses.toArray(rep);
		savedInstanceState.putStringArray(STATE_BATTLE_ANSWERS, rep);
		savedInstanceState.putString(STATE_BATTLE_SOLUCE, soluce);
		savedInstanceState.putString(STATE_BATTLE_OPPONENT_NAME, opponentName);
		savedInstanceState.putInt(STATE_BATTLE_OPPONENT_ID, opponentId);
		savedInstanceState.putInt(STATE_BATTLE_TIME_LEFT, timeLeft);
		super.onSaveInstanceState(savedInstanceState);
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	    super.onRestoreInstanceState(savedInstanceState);
	   
	    id = savedInstanceState.getInt(STATE_ENIGMA_ID);
		name = savedInstanceState.getString(STATE_ENIGMA_NAME);
		description = savedInstanceState.getString(STATE_BATTLE_DESCRIPTION);
		reponses = new ArrayList<String>(4);
		String[] rep = savedInstanceState.getStringArray(STATE_BATTLE_ANSWERS);
		for(int i = 0; i<4; i++) {
			reponses.add(rep[i]);
		}
		soluce = savedInstanceState.getString(STATE_BATTLE_SOLUCE);
		opponentId = savedInstanceState.getInt(STATE_BATTLE_OPPONENT_ID);
        opponentName = savedInstanceState.getString(STATE_BATTLE_OPPONENT_NAME);
		timeLeft = savedInstanceState.getInt(STATE_BATTLE_TIME_LEFT);
	}

	
}
