package com.client.enigmas_quest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		
		final EditText email = (EditText) findViewById(R.id.createAccoutMail);
        final EditText password = (EditText) findViewById(R.id.CreateAccountPassword);

        Button create = (Button) findViewById(R.id.create_account_btn);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailText = email.getText().toString();
                final String passwordText = password.getText().toString();

                //On teste les valeurs
                //Pattern général
                Pattern p = Pattern.compile(".+");


                // Matchers
                Matcher m = p.matcher(emailText);
                Matcher m4 = p.matcher(passwordText);
                // Si l’adresse mail saisie ne correspond au format d’une
                // adresse mail on un affiche un message à l'utilisateur
                if (!m.matches()) {
                    Toast.makeText(CreateAccountActivity.this, R.string.error_account_email, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!m4.matches()) {
                    Toast.makeText(CreateAccountActivity.this, R.string.error_account_password, Toast.LENGTH_SHORT).show();
                    return;
                }


                // ENVOYER LE MESSAGE AVEC LES INFORMATIONS


                //On retourne sur la page d'accueil
                Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                startActivity(intent);
                CreateAccountActivity.this.finish();
            }

        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

}
