package com.client.enigmas_quest.fragments;

import com.client.enigmas_quest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatsPageFragment extends Fragment {

	public static final String PLAYER_NAME = "player_name";
	public static final String SCORE = "score";
	public static final String NB_ENIGMES = "nb_enigmes";
	
	TextView monNom;
	TextView score;
	TextView enigmes;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

          View view = inflater.inflate(R.layout.fragment_map_two, container,
                      false);

          monNom = (TextView) view.findViewById(R.id.name_player);
          score = (TextView) view.findViewById(R.id.score_stats);
          enigmes = (TextView) view.findViewById(R.id.questions_asked_stats);

          monNom.setText(getArguments().getString(PLAYER_NAME));
          score.setText(String.valueOf(getArguments().getInt(SCORE)));
          enigmes.setText(String.valueOf(getArguments().getInt(NB_ENIGMES)));
          return view;
    }
}
