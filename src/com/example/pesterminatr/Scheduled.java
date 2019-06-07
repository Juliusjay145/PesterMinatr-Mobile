package com.example.pesterminatr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Scheduled extends Activity implements OnItemClickListener {

	ListView lv;
	ArrayList<ScheduleList> list = new ArrayList<ScheduleList>();
	ScheduleAdapter adapter;
	SharedPreferences prf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedule_provider);
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		this.lv = (ListView) findViewById(R.id.listView1);
		this.adapter = new ScheduleAdapter(this, list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		String id = prf.getString("pestcontrol_id", "");
		String pID = getIntent().getStringExtra("client_id");
		//String cID = getIntent().getStringExtra("clientCommerical_id");
		//String client_id = prf.getString("c_id", "");
		//Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
		//Toast.makeText(getApplicationContext(), cID, Toast.LENGTH_SHORT).show();
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_schedule/"+ id);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        InputStream is=conn.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        String s=br.readLine();
	        
	        is.close();
	        conn.disconnect();
	        
	        Log.d("json data", s);
	        JSONObject json=new JSONObject(s);
	        JSONArray array = json.getJSONArray("schedule");
	        for(int i=0; i<array.length(); i++){
	        	JSONObject item = array.getJSONObject(i);
	        	String sched_id = item.getString("schedule_id");
	        	String sched_date = item.getString("date");
	        	String sched_time = item.getString("time");
	        	list.add(new ScheduleList(sched_id,sched_date,sched_time));
	        	adapter.notifyDataSetChanged();
	        	
	        }
		}catch (MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}catch (JSONException e){
			e.printStackTrace();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		ScheduleList selectedItem = list.get(arg2);
		String date = selectedItem.getDate();
		String time = selectedItem.getTime();
		String id = getIntent().getStringExtra("client_id");
		//String commercial_id = getIntent().getStringExtra("clientCommerical_id");
		//Intent intent = new Intent(this, PestControlProfile.class);
		Intent intent = new Intent(this, UpdateSchedule.class);
		intent.putExtra("cdate", date);
		intent.putExtra("ctime", time);
		intent.putExtra("cid", id);
		startActivityForResult(intent, 1);
		//Toast.makeText(getApplicationContext(), "Date :" + date + "Time :" + time + "Client id: " + commercial_id, Toast.LENGTH_SHORT).show();
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//getMenuInflater().inflate(R.menu.main, menu);
		getMenuInflater().inflate(R.menu.schedule, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
//			case R.id.logout:
//				SharedPreferences.Editor editor = prf.edit();
//				editor.clear();
//				editor.commit();
//				Intent intent = new Intent(this, MainActivity.class);
//				startActivity(intent);
//				return true;
			case R.id.addschedule:
				Intent schedule = new Intent(this, AddSchedule.class);
				startActivity(schedule);
				return true;
			case R.id.home:
				Intent home = new Intent(this, pestcontrol.class);
				startActivity(home);
				return true;	
//			case R.id.schedule:
//				Intent schedule = new Intent(this, AddSchedule.class);
//				startActivity(schedule);
//				return true;
//			case R.id.listschedule:
//				Intent listschedule = new Intent(this, Scheduled.class);
//				startActivity(listschedule);
//				return true;
//			case R.id.feedback:
//				Intent feedback = new Intent(this, CommentFeedback.class);
//				startActivity(feedback);
//				return true;
			default:
				return super.onOptionsItemSelected(item);
			
		}
	}
	
	

}
