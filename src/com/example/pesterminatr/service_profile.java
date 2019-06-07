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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class service_profile extends Activity implements OnClickListener, OnItemClickListener {

	ListView lv;
	Button btnBook;
	ArrayList<ServiceProfileList> list = new ArrayList<ServiceProfileList>();
	ServiceProfileAdapter adapter;
	SharedPreferences prf;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_service);
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		intent = new Intent(service_profile.this,MainActivity.class);
		this.lv = (ListView) findViewById(R.id.listView1);
		btnBook = (Button) findViewById(R.id.button1);
		btnBook.setOnClickListener(this);
		this.adapter = new ServiceProfileAdapter(this, list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		String ID = getIntent().getStringExtra("servID");
		String sID = getIntent().getStringExtra("serviceID");
		String sname = getIntent().getStringExtra("sname");
		String pID = getIntent().getStringExtra("pestID");
		//Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_SHORT).show();
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_profile_service/"+ID);
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
	        	String service_name = item.getString("service_name");
	        	String service_detail = item.getString("service_detail");
	        	String service_price = item.getString("service_price");
	        	String pestcontrolId = item.getString("pestcontrol_id");
	        	String CompanyImage = item.getString("path_image");
	        	String servId = item.getString("pestcontrol_id");
	        	list.add(new ServiceProfileList(CompanyImage,pestcontrolId,service_name,service_detail,service_price,servId));
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
	public void onClick(View arg0) {
		
		String ID = getIntent().getStringExtra("servID");
		String sID = getIntent().getStringExtra("serviceID");
		String sname = getIntent().getStringExtra("sname");
		String pID = getIntent().getStringExtra("pestID");
		
		Intent intent = new Intent(this, residential.class);
		//Intent intent = new Intent(this, service_profile.class);
		intent.putExtra("serviceID", ID);
		intent.putExtra("sname", sname);
		intent.putExtra("pestID", pID);
		intent.putExtra("serviceID", sID);
		//Toast.makeText(getApplicationContext(), sID, Toast.LENGTH_SHORT).show();
		startActivityForResult(intent, 1);
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		ServiceProfileList selectedItem = list.get(arg2);
		String ID = selectedItem.getPrice();
		String sname = selectedItem.getName();
		String PestID = selectedItem.getPestId();
		String sID = selectedItem.getId();
		//Intent intent = new Intent(this, service.class);
		Intent intent = new Intent(this, residential.class);
		//Intent intent = new Intent(this, service_profile.class);
		intent.putExtra("serviceID", ID);
		intent.putExtra("sname", sname);
		intent.putExtra("pestID", PestID);
		intent.putExtra("servID", sID);
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
