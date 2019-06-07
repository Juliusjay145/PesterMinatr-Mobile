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

public class confirmresidential extends Activity implements OnClickListener {

	TextView cname,caddress,cdate,ctime,cprice,cstatus,cservice;
	Button btnConfirm,btnCancel,btnReSched,btnPaid,btnComplete;
	SharedPreferences prf;
	//Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_details);
		
		cname = (TextView) findViewById(R.id.textView2);
		caddress = (TextView) findViewById(R.id.textView4);
		cdate = (TextView) findViewById(R.id.textView6);
		ctime = (TextView) findViewById(R.id.textView8);
		cprice = (TextView) findViewById(R.id.textView10);
		cstatus = (TextView) findViewById(R.id.textView13);
		cservice = (TextView) findViewById(R.id.textView7);
		btnConfirm = (Button) findViewById(R.id.button1);
		btnCancel = (Button) findViewById(R.id.button2);
		btnReSched = (Button) findViewById(R.id.button3);
		btnPaid = (Button) findViewById(R.id.button4);
		btnComplete = (Button) findViewById(R.id.button5);
		//intent = new Intent(confirmresidential.this,MainActivity.class);
		btnConfirm.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		btnReSched.setOnClickListener(this);
		btnPaid.setOnClickListener(this);
		btnComplete.setOnClickListener(this);
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		String id = getIntent().getStringExtra("c_id");
		//Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
		
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_selected_residential/"+id);
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
	        	String c_address = item.getString("residential_address");
	        	String c_date = item.getString("date");
	        	String c_time = item.getString("time");
	        	String c_price = item.getString("price");
	        	String c_status = item.getString("status");
	        	String c_service = item.getString("problem");
//	        	Toast.makeText(getApplicationContext(), cn, Toast.LENGTH_LONG).show();
	        	cname.setText(c_name);
	        	caddress.setText(c_address);
	        	cdate.setText(c_date);
	        	ctime.setText(c_time);
	        	cprice.setText(c_price);
	        	cstatus.setText(c_status);
	        	cservice.setText(c_service);
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
		String id = getIntent().getStringExtra("c_id");
		
		if(arg0 == btnConfirm){
			
			String commercial_name = cname.getText().toString();
			String commercial_address = caddress.getText().toString();
			String commercial_date = cdate.getText().toString();
			String commercial_time = ctime.getText().toString();
			String commercial_price = cprice.getText().toString();
			
			    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		    		nameValuePairs.add(new BasicNameValuePair("client_id", commercial_name));
		    		nameValuePairs.add(new BasicNameValuePair("residential_address", commercial_address));
		    		nameValuePairs.add(new BasicNameValuePair("date", commercial_date));
		    		nameValuePairs.add(new BasicNameValuePair("time", commercial_time));
		    		nameValuePairs.add(new BasicNameValuePair("price", commercial_price));
		    		
		    		try{
		    			HttpClient httpClient = new DefaultHttpClient();
		    			HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/updateconfirmresidential/"+id);
		    			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		    			HttpResponse response = httpClient.execute(httpPost);
		    			HttpEntity entity = response.getEntity();
		    			InputStream is;
		    			is=entity.getContent();
		    			//Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
		    			Intent intent = new Intent(this, CommercialBook.class);
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
		
		if(arg0==btnReSched){
			
//			String commercial_name = cname.getText().toString();
//			String commercial_address = caddress.getText().toString();
//			String commercial_date = cdate.getText().toString();
//			String commercial_time = ctime.getText().toString();
//			String commercial_price = cprice.getText().toString();
//			
//			    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
//		    		nameValuePairs.add(new BasicNameValuePair("client_id", commercial_name));
//		    		nameValuePairs.add(new BasicNameValuePair("residential_address", commercial_address));
//		    		nameValuePairs.add(new BasicNameValuePair("date", commercial_date));
//		    		nameValuePairs.add(new BasicNameValuePair("time", commercial_time));
//		    		nameValuePairs.add(new BasicNameValuePair("price", commercial_price));
//		    		
//		    		try{
//		    			HttpClient httpClient = new DefaultHttpClient();
//		    			HttpPost httpPost = new HttpPost("http://10.0.2.2/project/index.php/androidcontroller/updatereschedresidential/"+id);
//		    			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//		    			HttpResponse response = httpClient.execute(httpPost);
//		    			HttpEntity entity = response.getEntity();
//		    			InputStream is;
//		    			is=entity.getContent();
//		    			Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
//		    			Intent intent = new Intent(this, pestcontrol.class);
//		    			startActivity(intent);
//
//		    			
//		    			
//		    		}
//		    		catch(ClientProtocolException e)
//		    		{
//		    			Log.e("ClientProtocol","Log_tag");
//		    			e.printStackTrace();
//		    		}
//		    		catch(IOException e)
//		    		{
//		    			Log.e("Log_tag", "IOException");
//		    			e.printStackTrace();
//		    		}
			
			Intent intent = new Intent(this, Scheduled.class);
			intent.putExtra("client_id", id);
			startActivityForResult(intent, 1);
			//startActivity(intent);
			//Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
			
		}
		
		if(arg0==btnPaid){
			
			String commercial_name = cname.getText().toString();
			String commercial_address = caddress.getText().toString();
			String commercial_date = cdate.getText().toString();
			String commercial_time = ctime.getText().toString();
			String commercial_price = cprice.getText().toString();
			
			    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		    		nameValuePairs.add(new BasicNameValuePair("client_id", commercial_name));
		    		nameValuePairs.add(new BasicNameValuePair("residential_address", commercial_address));
		    		nameValuePairs.add(new BasicNameValuePair("date", commercial_date));
		    		nameValuePairs.add(new BasicNameValuePair("time", commercial_time));
		    		nameValuePairs.add(new BasicNameValuePair("price", commercial_price));
		    		
		    		try{
		    			HttpClient httpClient = new DefaultHttpClient();
		    			HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/updatepaidresidential/"+id);
		    			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		    			HttpResponse response = httpClient.execute(httpPost);
		    			HttpEntity entity = response.getEntity();
		    			InputStream is;
		    			is=entity.getContent();
		    			Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
		    			Intent intent = new Intent(this, pestcontrol.class);
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
		
		if(arg0==btnComplete){
			
			String commercial_name = cname.getText().toString();
			String commercial_address = caddress.getText().toString();
			String commercial_date = cdate.getText().toString();
			String commercial_time = ctime.getText().toString();
			String commercial_price = cprice.getText().toString();
			
			    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		    		nameValuePairs.add(new BasicNameValuePair("client_id", commercial_name));
		    		nameValuePairs.add(new BasicNameValuePair("residential_address", commercial_address));
		    		nameValuePairs.add(new BasicNameValuePair("date", commercial_date));
		    		nameValuePairs.add(new BasicNameValuePair("time", commercial_time));
		    		nameValuePairs.add(new BasicNameValuePair("price", commercial_price));
		    		
		    		try{
		    			HttpClient httpClient = new DefaultHttpClient();
		    			HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/updatecompleteresidential/"+id);
		    			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		    			HttpResponse response = httpClient.execute(httpPost);
		    			HttpEntity entity = response.getEntity();
		    			InputStream is;
		    			is=entity.getContent();
		    			Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
		    			Intent intent = new Intent(this, pestcontrol.class);
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
		
		if(arg0 == btnCancel){
			
			String commercial_name = cname.getText().toString();
			String commercial_address = caddress.getText().toString();
			String commercial_date = cdate.getText().toString();
			String commercial_time = ctime.getText().toString();
			String commercial_price = cprice.getText().toString();
			
			    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		    		nameValuePairs.add(new BasicNameValuePair("client_id", commercial_name));
		    		nameValuePairs.add(new BasicNameValuePair("residential_address", commercial_address));
		    		nameValuePairs.add(new BasicNameValuePair("date", commercial_date));
		    		nameValuePairs.add(new BasicNameValuePair("time", commercial_time));
		    		nameValuePairs.add(new BasicNameValuePair("price", commercial_price));
		    		
		    		try{
		    			HttpClient httpClient = new DefaultHttpClient();
		    			HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/updatecancelresidential/"+id);
		    			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		    			HttpResponse response = httpClient.execute(httpPost);
		    			HttpEntity entity = response.getEntity();
		    			InputStream is;
		    			is=entity.getContent();
		    			//Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
		    			Intent intent = new Intent(this, CommercialBook.class);
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
		
		
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.options, menu);
		return super.onCreateOptionsMenu(menu);
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		String id = getIntent().getStringExtra("c_id");
		
		switch(item.getItemId()){
				case R.id.update:
					Intent updateDetails = new Intent(this, UpdateBookingDetails.class);
					updateDetails.putExtra("client_residential_id", id);
					//startActivity(updateDetails);
					startActivityForResult(updateDetails, 1);
					return true;
				case R.id.home:
					Intent home = new Intent(this, pestcontrol.class);
					startActivity(home);
					return true;	
					
				default:
					return super.onOptionsItemSelected(item);
				
			}		
		
		
		
		}
	
	

}
