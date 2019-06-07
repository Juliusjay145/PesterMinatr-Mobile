package com.example.pesterminatr;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.example.pesterminatr.CompanyAdapter.CompanyHandler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProviderProfileAdapter extends BaseAdapter {

	Context context;
	ArrayList <ProviderProfileList> list;
	LayoutInflater inflater;
	
	
	
	
	public ProviderProfileAdapter(Context context,
			ArrayList<ProviderProfileList> list) {
		super();
		this.context = context;
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		ProfilePestHandler handler = null;
		if(arg1==null)
		{
			arg1 = inflater.inflate(R.layout.provider_profile, null);
			handler = new ProfilePestHandler();
			handler.name = (TextView) arg1.findViewById(R.id.textView2);
			handler.address = (TextView) arg1.findViewById(R.id.textView4);
			handler.contact = (TextView) arg1.findViewById(R.id.textView6);
			handler.details = (TextView) arg1.findViewById(R.id.textView7);
			handler.image = (ImageView) arg1.findViewById(R.id.imageView1);
			arg1.setTag(handler);
		}else
			
			handler=(ProfilePestHandler) arg1.getTag();
			Bitmap bm  = getBitmapFromURL(list.get(arg0).getImage());	
			handler.name.setText(list.get(arg0).getName());
			handler.address.setText(list.get(arg0).getAddress());
			handler.contact.setText(list.get(arg0).getContact());
			handler.details.setText(list.get(arg0).getDetails());
			handler.image.setImageBitmap(bm);
			
		
		
		
		return arg1;
	}
	
	static Bitmap getBitmapFromURL(String src)
	{
		try{
			URL url = new URL(src);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.connect();
			InputStream is = con.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(is);
			Log.e("Bitmap", "returned");
			return myBitmap;
		}catch(IOException e){
			e.printStackTrace();
			Log.e("Exception", e.getMessage());
			return null;
		}
	}
	
	static class ProfilePestHandler
	{
		TextView name,address,contact,details;
		ImageView image;
	}

}
