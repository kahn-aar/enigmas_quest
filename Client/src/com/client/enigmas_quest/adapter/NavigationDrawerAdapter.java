package com.client.enigmas_quest.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.enigmas_quest.R;
import com.client.enigmas_quest.data.DrawerItem;

public class NavigationDrawerAdapter extends ArrayAdapter<DrawerItem> {

	Context context;
    List<DrawerItem> drawerItemList;
    int layoutResID;
	
	public NavigationDrawerAdapter(Context context, int layoutResourceID, List<DrawerItem> listItems) {
		super(context, layoutResourceID, listItems);
		this.context = context;
		this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {

          DrawerItemHolder drawerHolder;
          View view = convertView;

          if (view == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                drawerHolder = new DrawerItemHolder();

                view = inflater.inflate(layoutResID, parent, false);
                drawerHolder.ItemName = (TextView) view
                            .findViewById(R.id.drawer_itemName);
                drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);

                view.setTag(drawerHolder);

          } else {
                drawerHolder = (DrawerItemHolder) view.getTag();

          }

          DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

          drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
                      dItem.getImgResID()));
          drawerHolder.ItemName.setText(dItem.getTitle());

          return view;
    }

    private static class DrawerItemHolder {
          TextView ItemName;
          ImageView icon;
    }

}
