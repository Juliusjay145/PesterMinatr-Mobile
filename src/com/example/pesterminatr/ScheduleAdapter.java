package com.example.pesterminatr;

import java.util.ArrayList;

import com.example.pesterminatr.bookingDetailsAdapter.BookingDetailsHandler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ScheduleAdapter extends BaseAdapter {

	Context context;
	ArrayList<ScheduleList> list;
	LayoutInflater inflater;
	
	
	
	public ScheduleAdapter(Context context, ArrayList<ScheduleList> list) {
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
		
		ScheduleHandler handler = null;
		if(arg1==null)
		{
			arg1 = inflater.inflate(R.layout.schedule_provider_list, null);
			handler = new ScheduleHandler();
			handler.date = (TextView) arg1.findViewById(R.id.textView2);
			handler.time = (TextView) arg1.findViewById(R.id.textView4);
			arg1.setTag(handler);
		}else
			
			handler=(ScheduleHandler) arg1.getTag();
			handler.date.setText(list.get(arg0).getDate());
			handler.time.setText(list.get(arg0).getTime());
		
		
		return arg1;
	}
	
	
	static class ScheduleHandler
	{
		TextView date,time;
	}

}
