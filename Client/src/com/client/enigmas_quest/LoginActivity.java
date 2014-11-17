package com.client.enigmas_quest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.client.enigmas_quest.application.EnigmaApplication;
import com.client.enigmas_quest.constants.EnigmasConstants;
import com.client.enigmas_quest.data.Player;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	EnigmaApplication application;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		application = (EnigmaApplication) getApplicationContext();
		
		if (savedInstanceState == null) {
		}
		
		//Récupération des champs 
		final EditText pseudo = (EditText) findViewById(R.id.user_email);
        final EditText password = (EditText) findViewById(R.id.user_password);
		
		Button bouton = (Button) findViewById(R.id.connect);
		bouton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//Verification connexion
				//Vérification des champs (Pas trop utiliser la bande passante)
				final String pseudoText = pseudo.getText().toString();
                final String passwordText = password.getText().toString();

                //On teste les valeurs
                //Pattern général
                Pattern p = Pattern.compile(".+");


                // Matchers
                Matcher m = p.matcher(pseudoText);
                Matcher m4 = p.matcher(passwordText);
                // Si l’adresse mail saisie ne correspond au format d’une
                // adresse mail on un affiche un message à l'utilisateur
                if (!m.matches()) {
                    Toast.makeText(LoginActivity.this, R.string.error_account_email, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!m4.matches()) {
                    Toast.makeText(LoginActivity.this, R.string.error_account_password, Toast.LENGTH_SHORT).show();
                    return;
                }
				
				// ENVOYER LE MESSAGE AVEC LES INFORMATIONS
                Player player = application.connexion(pseudo.getText().toString(), password.getText().toString());
                
                if(player != null) {
					Intent intent = new Intent(LoginActivity.this, Map_Activity.class);
	                intent.getIntExtra(EnigmasConstants.INTENT_CONNEXION, 3);
	                startActivity(intent);
                }
                else {
                	Toast.makeText(LoginActivity.this, R.string.error_account_password, Toast.LENGTH_SHORT).show();
                    return;
                }
			}
		});
		
		Button bouton2 = (Button) findViewById(R.id.create_account);
		bouton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
