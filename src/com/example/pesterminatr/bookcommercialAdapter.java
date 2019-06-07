package com.example.pesterminatr;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class bookcommercialAdapter extends BaseAdapter {

	Context context;
	ArrayList<bookcommercialList> list;
	LayoutInflater inflater;
	
	
	
	
	public bookcommercialAdapter(Context context,
			ArrayList<bookcommercialList> list) {
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

		CommercialBookHandler handler = null;
		if(arg1==null)
		{
			arg1 = inflater.inflate(R.layout.book_list, null);
			handler = new CommercialBookHandler();
			handler.name = (TextView) arg1.findViewById(R.id.textView1);
			handler.address = (TextView) arg1.findViewById(R.id.textView2);
			handler.date = (TextView) arg1.findViewById(R.id.textView3);
			handler.time = (TextView) arg1.findViewById(R.id.textView4);
			handler.price = (TextView) arg1.findViewById(R.id.textView5);
			handler.status = (TextView) arg1.findViewById(R.id.textView6);
			handler.service = (TextView) arg1.findViewById(R.id.textView13);
			arg1.setTag(handler);
		}else
			
			handler=(CommercialBookHandler) arg1.getTag();	
			handler.name.setText(list.get(arg0).getName());
			handler.address.setText(list.get(arg0).getAddress());
			handler.date.setText(list.get(arg0).getDate());
			handler.time.setText(list.get(arg0).getTime());
			handler.price.setText(list.get(arg0).getPrice());
			handler.status.setText(list.get(arg0).getCstatus());
			handler.service.setText(list.get(arg0).getProblem());
				
		
		return arg1;
	}
	
	static class CommercialBookHandler
	{
		TextView name,address,date,time,price,status,service;
	}

}
