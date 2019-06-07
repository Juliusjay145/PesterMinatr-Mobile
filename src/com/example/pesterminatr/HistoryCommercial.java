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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class HistoryCommercial extends Activity {

	ArrayList<bookingDetailsList> list = new ArrayList<bookingDetailsList>();
	bookingDetailsAdapter adapter;
	SharedPreferences prf;
	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookingdetails);
		
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		
		lv = (ListView) findViewById(R.id.listView1);
		//lv.setOnItemClickListener(this);
		this.adapter = new bookingDetailsAdapter(this, list);
		lv.setAdapter(adapter);
		String id = prf.getString("c_name", "");
		Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_history_commercial/" +id);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        InputStream is=conn.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        String s=br.readLine();
	        
	        is.close();
	        conn.disconnect();
	        
	        Log.d("json data", s);
	        JSONObject json=new JSONObject(s);
	        JSONArray array = json.getJSONArray("commercial");
	        for(int i=0; i<array.length(); i++){
	        	JSONObject item = array.getJSONObject(i);
	        	String c_name = item.getString("client_id");
	        	String c_problem = item.getString("problem");
	        	String c_address = item.getString("company_address");
	        	String c_date = item.getString("date");
	        	String c_time = item.getString("time");
	        	String c_price = item.getString("price");
	        	String c_status = item.getString("status");
	        	String pest = item.getString("pestcontrol_id");
	        	String r_id = item.getString("commercial_id");
	        	list.add(new bookingDetailsList(c_name,c_problem,c_address,c_date,c_time,c_price,c_status,r_id, pest));
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
	
	

}
