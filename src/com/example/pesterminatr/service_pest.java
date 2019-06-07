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
import android.widget.GridView;
import android.widget.Toast;


public class service_pest extends Activity implements OnItemClickListener {

	
	ArrayList<ServiceList> list = new ArrayList<ServiceList>();
	ServiceAdapter adapter;
	SharedPreferences prf;
	GridView gv;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);
		
		gv = (GridView) findViewById(R.id.gridView1);
		gv.setOnItemClickListener(this);
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		intent = new Intent(service_pest.this,MainActivity.class);
		this.adapter = new ServiceAdapter(this, list);
		gv.setAdapter(adapter);
		//String ID = getIntent().getStringExtra("service");
		String pestcontrolname = getIntent().getStringExtra("pestName");
		String ID = getIntent().getStringExtra("serviceID");
		Toast.makeText(getApplicationContext(), pestcontrolname, Toast.LENGTH_SHORT).show();
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_service/"+ ID);
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


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		ServiceList selectedItem = list.get(arg2);
		String ID = selectedItem.getPrice();
		String sname = selectedItem.getName();
		String PestID = selectedItem.getPestid();
		String sID = selectedItem.getId();
		String pestcontrolname = getIntent().getStringExtra("pestName");
		//Intent intent = new Intent(this, service.class);
		//Intent intent = new Intent(this, residential.class);
		Intent intent = new Intent(this, service_profile.class);
		intent.putExtra("serviceID", ID);
		intent.putExtra("sname", sname);
		intent.putExtra("pestID", PestID);
		intent.putExtra("servID", sID);
		intent.putExtra("pestname", pestcontrolname);
		//intent.putExtra("service", ID);
		//intent.putExtra("pestId",pestId);
		startActivityForResult(intent, 1);
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		
		switch(item.getItemId()){
				case R.id.logout:
					SharedPreferences.Editor editor = prf.edit();
					editor.clear();
					editor.commit();
					startActivity(intent);
					return true;
				case R.id.profile:
					Intent profile = new Intent(this, Profile.class);
					startActivity(profile);
					return true;
				case R.id.home:
					Intent home = new Intent(this, client.class);
					startActivity(home);
					return true;
				case R.id.bookingdetails:
					Intent details = new Intent(this, BookingDetails.class);
					startActivity(details);
					return true;
	//			case R.id.commercialdetails:
	//				Intent commercial = new Intent(this, CommercialDetails.class);
	//				startActivity(commercial);
	//				return true;
				case R.id.history:
					Intent history = new Intent(this, History.class);
					startActivity(history);
					return true;
	//			case R.id.commercialhistory:
	//				Intent commercialhistory = new Intent(this, CommercialHistory.class);
	//				startActivity(commercialhistory);
	//				return true;	
					
				default:
					return super.onOptionsItemSelected(item);
				
			}		
		
		
		
		}
	

}
