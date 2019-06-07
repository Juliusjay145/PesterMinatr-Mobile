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
import android.app.AlertDialog;
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

public class ClientCommercial extends Activity implements OnItemClickListener {

	ListView lv;
	ArrayList<CompanyList> list = new ArrayList<CompanyList>();
	//ArrayAdapter<String> adapter;
	CompanyAdapter adapter;
	SharedPreferences prf;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client);
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		this.lv = (ListView) findViewById(R.id.listView1);
		intent = new Intent(ClientCommercial.this,MainActivity.class);
		this.adapter = new CompanyAdapter(this, list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_company");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        InputStream is=conn.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        String s=br.readLine();
	        
	        is.close();
	        conn.disconnect();
	        
	        Log.d("json data", s);
	        JSONObject json=new JSONObject(s);
	        JSONArray array = json.getJSONArray("pestcontrol");
	        for(int i=0; i<array.length(); i++){
	        	JSONObject item = array.getJSONObject(i);
	        	String pestcontrol_name = item.getString("pestcontrol_name");
	        	String pestcontrolId = item.getString("pestcontrol_id");
	        	String CompanyImage = item.getString("path_image");
	        	list.add(new CompanyList(CompanyImage,pestcontrolId,pestcontrol_name));
	        	adapter.notifyDataSetChanged();
	        }
		}catch (MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}catch (JSONException e){
			e.printStackTrace();
		}
		String id = prf.getString("c_name", "");
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_commercial_details/"+id);
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
	        	String status = item.getString("status");
	        	if(status.equals("Confirm"))
	        	{
	        		//Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
	        		AlertDialog dialog = new AlertDialog.Builder(this).create();
	        		dialog.setMessage("Your booking request has been " +status+ " to provider");	        		
	        		dialog.setTitle("Notification");
	        		dialog.show();
	        	}
	        	
	        	

	        	
	        }
		}catch (MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}catch (JSONException e){
			e.printStackTrace();
		}
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_commercial_details_cancel/"+id);
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
	        	String status = item.getString("status");
	        	if(status.equals("Canceled"))
	        	{
	        		//Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
	        		AlertDialog dialog = new AlertDialog.Builder(this).create();
	        		dialog.setMessage("Your booking request has been " +status+ " to provider");	        		
	        		dialog.setTitle("Notification");
	        		dialog.show();
	        	}
	        	
	        	

	        	
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
				Intent profile = new Intent(this, CommericalProfile.class);
				startActivity(profile);
				return true;
			case R.id.bookingdetails:
				Intent details = new Intent(this, CommercialBookingDetails.class);
				//Intent details = new Intent(this, CommercialBooking.class);
				startActivity(details);
				return true;
//			case R.id.commercialdetails:
//				Intent commercial = new Intent(this, CommercialDetails.class);
//				startActivity(commercial);
//				return true;
			case R.id.history:
				Intent history = new Intent(this, HistoryCommercial.class);
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



	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		CompanyList selectedItem = list.get(arg2);
		String ID = selectedItem.getId();
		//Intent intent = new Intent(this, PestControlProfile.class);
		Intent intent = new Intent(this, providerProfileCommercialClient.class);
		intent.putExtra("pestcontrol", ID);
		startActivityForResult(intent, 1);
		
	}
	
	

}
