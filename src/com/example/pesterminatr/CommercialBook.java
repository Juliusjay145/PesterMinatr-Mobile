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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CommercialBook extends Activity implements OnItemClickListener {

	ArrayList<bookcommercialList> list = new ArrayList<bookcommercialList>();
	bookcommercialAdapter adapter;
	SharedPreferences prf;
	ListView lv;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_commercial);
		
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		
		lv = (ListView) findViewById(R.id.listView1);
		lv.setOnItemClickListener(this);
		this.adapter = new bookcommercialAdapter(this, list);
		lv.setAdapter(adapter);
		
		//String id = prf.getString("pestcontrol_id", "");
		String id = prf.getString("pestcontrol_id", "");
		String name = prf.getString("pestcontrol_name", "");
		
		//Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_residential/" +id);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        InputStream is=conn.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        String s=br.readLine();
	        
	        is.close();
	        conn.disconnect();
	        
	        Log.d("json data", s);
	        JSONObject json=new JSONObject(s);
	        JSONArray array = json.getJSONArray("residential");
	        for(int i=0; i<array.length(); i++){
	        	JSONObject item = array.getJSONObject(i);
	        	String c_name = item.getString("client_id");
	        	String c_problem = item.getString("problem");
	        	String c_address = item.getString("residential_address");
	        	String c_date = item.getString("date");
	        	String c_time = item.getString("time");
	        	String c_price = item.getString("price");
	        	String c_status = item.getString("status");
	        	String pest = item.getString("pestcontrol_id");
	        	String r_id = item.getString("residential_id");
	        	list.add(new bookcommercialList(c_name,c_problem,c_address,c_date,c_time,c_price,c_status ,r_id, pest));
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
		
		bookcommercialList selectedItem = list.get(arg2);
		String ID = selectedItem.getId();
		Intent intent = new Intent(this, confirmresidential.class);
		intent.putExtra("c_id", ID);
		startActivityForResult(intent, 1);
		//Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_SHORT).show();
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//getMenuInflater().inflate(R.menu.main, menu);
		getMenuInflater().inflate(R.menu.pestcontrol_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
			case R.id.logout:
				SharedPreferences.Editor editor = prf.edit();
				editor.clear();
				editor.commit();
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				return true;
			case R.id.profile:
				Intent profile = new Intent(this, Profilepest.class);
				startActivity(profile);
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
			case R.id.feedback:
				Intent feedback = new Intent(this, CommentFeedback.class);
				startActivity(feedback);
				return true;
			default:
				return super.onOptionsItemSelected(item);
			
		}
	}
	
	

}
