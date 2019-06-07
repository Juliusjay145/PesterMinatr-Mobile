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

public class BookingDetails extends Activity implements OnItemClickListener {

	ArrayList<bookingDetailsList> list = new ArrayList<bookingDetailsList>();
	bookingDetailsAdapter adapter;
	SharedPreferences prf;
	ListView lv;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookingdetails);
		
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		intent = new Intent(BookingDetails.this,MainActivity.class);
		lv = (ListView) findViewById(R.id.listView1);
		lv.setOnItemClickListener(this);
		this.adapter = new bookingDetailsAdapter(this, list);
		lv.setAdapter(adapter);
		String id = prf.getString("client_name", "");
		//Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_residential_details/" +id);
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		bookingDetailsList selectedItem = list.get(arg2);
		String ID = selectedItem.getId();
		Intent intent = new Intent(this, residentialupdatedetails.class);
		intent.putExtra("c_id", ID);
		startActivityForResult(intent, 1);
		//Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_SHORT).show();
		
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
