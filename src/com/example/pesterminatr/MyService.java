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
import android.widget.GridView;

public class MyService extends Activity {

	ArrayList<ServiceList> list = new ArrayList<ServiceList>();
	ServiceAdapter adapter;
	GridView gv;
	SharedPreferences prf;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);
		
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		
		gv = (GridView) findViewById(R.id.gridView1);
		this.adapter = new ServiceAdapter(this, list);
		gv.setAdapter(adapter);
		String id = prf.getString("pestcontrol_id", "");
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_service/"+ id);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        InputStream is=conn.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        String s=br.readLine();
	        
	        is.close();
	        conn.disconnect();
	        
	        Log.d("json data", s);
	        JSONObject json=new JSONObject(s);
	        JSONArray array = json.getJSONArray("service");
	        for(int i=0; i<array.length(); i++){
	        	JSONObject item = array.getJSONObject(i);
	        	String serv_name = item.getString("service_name");
	        	String serv_price = item.getString("service_price");
	        	String serv_id = item.getString("service_id");
	        	String ServiceImage = item.getString("path_image");
	        	String pest = item.getString("pestcontrol_id");
	        	list.add(new ServiceList(ServiceImage,serv_id,serv_name,serv_price,pest));
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
