package com.example.pesterminatr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class pestcontrol extends Activity implements OnClickListener {

	TextView txtnotification,txtnotif;
	Button btnLogout, btnResidential, btnCommercial,btnSchedule,btnService;
	SharedPreferences prf;
	//Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pestcontrolhome);
		
		//intent = new Intent(pestcontrol.this,MainActivity.class);
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		btnLogout = (Button) findViewById(R.id.btnbookresidential);
		btnResidential = (Button) findViewById(R.id.button1);
		//btnCommercial = (Button) findViewById(R.id.button2);
		btnSchedule = (Button) findViewById(R.id.button2);
		btnService = (Button) findViewById(R.id.button3);
		txtnotification = (TextView) findViewById(R.id.textView2);
		txtnotif = (TextView) findViewById(R.id.textView3);
		btnLogout.setOnClickListener(this);
		btnResidential.setOnClickListener(this);
		btnSchedule.setOnClickListener(this);
		btnService.setOnClickListener(this);
		String id = prf.getString("pestcontrol_id", "");
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/residential_notification");
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
	        	String cn = item.getString("notification");
	        	//Toast.makeText(getApplicationContext(), cn, Toast.LENGTH_LONG).show();
	        	txtnotification.setText(cn);
	        }
		}catch (MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}catch (JSONException e){
			e.printStackTrace();
		}
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/commercial_notification");
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
	        	String cn = item.getString("notification");
	        	//Toast.makeText(getApplicationContext(), cn, Toast.LENGTH_LONG).show();
	        	txtnotif.setText(cn);
	        }
		}catch (MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}catch (JSONException e){
			e.printStackTrace();
		}
		
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_residential_cancel/"+id);
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
	        	String status = item.getString("status");
	        	String name = item.getString("client_id");
	        	if(status.equals("Canceled"))
	        	{
	        		Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
	        		AlertDialog dialog = new AlertDialog.Builder(this).create();
	        		dialog.setMessage(name +  "Cancelled Book");	        		
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
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_commercial_cancel/"+id);
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
	        	String name = item.getString("client_id");
	        	if(status.equals("Canceled"))
	        	{
	        		Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
	        		AlertDialog dialog = new AlertDialog.Builder(this).create();
	        		dialog.setMessage(name +  "Cancelled Book");	        		
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
	public void onClick(View arg0) {
		
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		
		if(arg0 == btnLogout){
			
				String commercial_name = txtnotification.getText().toString();
				
				    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			    		nameValuePairs.add(new BasicNameValuePair("notification", commercial_name));
				
				try{
	    			HttpClient httpClient = new DefaultHttpClient();
	    			HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/update_notification_residential");
	    			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    			HttpResponse response = httpClient.execute(httpPost);
	    			HttpEntity entity = response.getEntity();
	    			InputStream is;
	    			is=entity.getContent();
	    			//Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
	    			Intent intent = new Intent(this, ResidentialBook.class);
	    			startActivity(intent);	
	    			
	    			
	    		}
	    		catch(ClientProtocolException e)
	    		{
	    			Log.e("ClientProtocol","Log_tag");
	    			e.printStackTrace();
	    		}
	    		catch(IOException e)
	    		{
	    			Log.e("Log_tag", "IOException");
	    			e.printStackTrace();
	    		}
			
		}
		
		if(arg0==btnResidential){
			
			String commercial_notif = txtnotif.getText().toString();
			
	    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
    		nameValuePairs.add(new BasicNameValuePair("notification", commercial_notif));
    		
    		try{
    			HttpClient httpClient = new DefaultHttpClient();
    			HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/update_notification_commercial");
    			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    			HttpResponse response = httpClient.execute(httpPost);
    			HttpEntity entity = response.getEntity();
    			InputStream is;
    			is=entity.getContent();
    			//Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
//    			Intent intent = new Intent(this, ResidentialBook.class);
//    			startActivity(intent);
    			Intent commercial = new Intent(this, CommercialBook.class);
    			startActivity(commercial);
    			
    			
    		}
    		catch(ClientProtocolException e)
    		{
    			Log.e("ClientProtocol","Log_tag");
    			e.printStackTrace();
    		}
    		catch(IOException e)
    		{
    			Log.e("Log_tag", "IOException");
    			e.printStackTrace();
    		}
			
			
			
		}
		
		if(arg0==btnSchedule)
		{
			Intent listschedule = new Intent(this, Scheduled.class);
			startActivity(listschedule);
		}
		
		if(arg0==btnService)
		{
			Intent service = new Intent(this, MyService.class);
			startActivity(service);
		}
		
		
		
	}

	@Override
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
