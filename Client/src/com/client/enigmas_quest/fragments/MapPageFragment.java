package com.client.enigmas_quest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.enigmas_quest.R;


public class MapPageFragment extends Fragment {

	public static final String ITEM_NAME = "item_name";
	TextView monTexte;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

          View view = inflater.inflate(R.layout.fragment_map_one, container,
                      false);

          monTexte = (TextView) view.findViewById(R.id.section_label_one);

          monTexte.setText(getArguments().getString(ITEM_NAME));
          return view;
    }
	
}
