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
import android.widget.TextView;

public class providerProfileCommercialClient extends Activity implements OnClickListener, OnItemClickListener {

	TextView txtreview;
	ListView lv;
	Button btnBook;
	ArrayList<ProviderProfileList> list = new ArrayList<ProviderProfileList>();
	ProviderProfileAdapter adapter;
	SharedPreferences prf;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.profile_pest);
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		btnBook = (Button) findViewById(R.id.button1);
		txtreview = (TextView) findViewById(R.id.textView1);
		txtreview.setOnClickListener(this);
		btnBook.setOnClickListener(this);
		this.lv = (ListView) findViewById(R.id.listView1);
		this.adapter = new ProviderProfileAdapter(this, list);
		intent = new Intent(providerProfileCommercialClient.this,MainActivity.class);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		String ID = getIntent().getStringExtra("pestcontrol");
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_profile_provider/"+ID);
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
	        	String pestcontrol_address = item.getString("pestcontrol_address");
	        	String pestcontrol_contact = item.getString("pestcontrol_contact");
	        	String pestcontrol_detail = item.getString("pestcontrol_detail");
	        	String pestcontrolId = item.getString("pestcontrol_id");
	        	String CompanyImage = item.getString("path_image");
	        	list.add(new ProviderProfileList(CompanyImage,pestcontrolId,pestcontrol_name,pestcontrol_address,pestcontrol_contact,pestcontrol_detail));
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
		
		if(arg0==btnBook)
		{
			String ID = getIntent().getStringExtra("pestcontrol");
			Intent intent = new Intent(this, service_pestCommercialClient.class);
			intent.putExtra("serviceID", ID);
			startActivityForResult(intent, 1);
		}
		
		if(arg0==txtreview)
		{
//			String pestid = getIntent().getStringExtra("pestcontrol");
//			Intent review = new Intent(this, CommentFeedbackResidential.class);
//			intent.putExtra("pestcontrol_id", pestid);
//			startActivityForResult(review, 1);
			String ID = getIntent().getStringExtra("pestcontrol");
			//String pestcontrolname = getIntent().getStringExtra("pestcontrolname");
			Intent intent = new Intent(this, CommentFeedbackResidential.class);
			intent.putExtra("pestcontrol_id", ID);
			//intent.putExtra("pestName", pestcontrolname);
			startActivityForResult(intent, 1);
		}
		
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		ProviderProfileList selectedItem = list.get(arg2);
		String ID = selectedItem.getId();
		//Intent intent = new Intent(this, PestControlProfile.class);
		Intent intent = new Intent(this, AddFeedbackCommercial.class);	
		intent.putExtra("pestcontrol", ID);
		startActivityForResult(intent, 1);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
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
		case R.id.home:
			Intent home = new Intent(this, ClientCommercial.class);
			startActivity(home);
			return true;
		case R.id.bookingdetails:
			Intent details = new Intent(this, CommercialBookingDetails.class);
			startActivity(details);
			return true;
//		case R.id.commercialdetails:
//			Intent commercial = new Intent(this, CommercialDetails.class);
//			startActivity(commercial);
//			return true;
		case R.id.history:
			Intent history = new Intent(this, History.class);
			startActivity(history);
			return true;
//		case R.id.commercialhistory:
//			Intent commercialhistory = new Intent(this, CommercialHistory.class);
//			startActivity(commercialhistory);
//			return true;	
			
		default:
			return super.onOptionsItemSelected(item);
	}		
		
		
	}
	
	
	
	
	
	

}
