package com.client;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private List<AppBean> beans;
	private Context ctx;
	public MyAdapter(List<AppBean> beans,Context ctx){
		this.beans = beans;
		this.ctx = ctx;
	}
	@Override
	public int getCount() {
		return beans.size();
	}

	@Override
	public Object getItem(int position) {
		return beans.get(position).getAppUrl();
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view  = View.inflate(ctx, R.layout.item, null);
		ImageView app_icon = (ImageView) view.findViewById(R.id.app_icon);
		TextView name = (TextView) view.findViewById(R.id.app_name);
		TextView size = (TextView) view.findViewById(R.id.app_size);
		TextView version = (TextView) view.findViewById(R.id.app_verison);
		TextView description = (TextView) view.findViewById(R.id.app_description);
		app_icon.setTag(beans.get(position).getAppIcon());
		ImageLoader loader = new ImageLoader(app_icon);
		loader.loadImage();
		name.setText(beans.get(position).getName());
		size.setText(beans.get(position).getAppSize());
		version.setText(beans.get(position).getVersionName());
		description.setText(beans.get(position).getAppDescription());
		
		return view;
	}

}
