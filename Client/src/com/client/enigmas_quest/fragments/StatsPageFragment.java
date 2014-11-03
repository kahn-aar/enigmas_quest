package com.client.enigmas_quest.fragments;

import com.client.enigmas_quest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatsPageFragment extends Fragment {

	TextView monNom;
	TextView score;
	TextView enigmes;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

          View view = inflater.inflate(R.layout.fragment_map_one, container,
                      false);

          monNom = (TextView) view.findViewById(R.id.section_label_one);
          score = (TextView) view.findViewById(R.id.section_label_one);
          enigmes = (TextView) view.findViewById(R.id.section_label_one);

          monNom.setText("Nicolas Martin");
          return view;
    }
}
