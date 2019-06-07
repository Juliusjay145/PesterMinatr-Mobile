package com.example.pesterminatr;

import java.util.ArrayList;

import com.example.pesterminatr.ScheduleAdapter.ScheduleHandler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {

	Context context;
	ArrayList<CommentList> list;
	LayoutInflater inflater;
	
	
	
	public CommentAdapter(Context context, ArrayList<CommentList> list) {
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
		
		CommentHandler handler = null;
		if(arg1==null)
		{
			arg1 = inflater.inflate(R.layout.reviews, null);
			handler = new CommentHandler();
			handler.name = (TextView) arg1.findViewById(R.id.textView2);
			handler.comment = (TextView) arg1.findViewById(R.id.textView1);
			handler.rate = (TextView) arg1.findViewById(R.id.textView4);
			arg1.setTag(handler);
		}else
			
			handler=(CommentHandler) arg1.getTag();
			handler.name.setText(list.get(arg0).getUser());
			handler.comment.setText(list.get(arg0).getComment());
			handler.rate.setText(list.get(arg0).getRating());
		
		
		return arg1;
	}
	
	static class CommentHandler
	{
		TextView name,comment,rate;
	}

}
