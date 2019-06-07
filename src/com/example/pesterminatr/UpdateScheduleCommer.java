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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateScheduleCommer extends Activity implements OnClickListener {

	EditText name,address,date,time;
	Button btnUpdate;
	SharedPreferences prf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client_details);
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		
		name = (EditText) findViewById(R.id.editText1);
		address = (EditText) findViewById(R.id.editText2);
		date = (EditText) findViewById(R.id.editText3);
		time = (EditText) findViewById(R.id.editText4);
		btnUpdate = (Button) findViewById(R.id.button1);
		btnUpdate.setOnClickListener(this);
		String cdate = getIntent().getStringExtra("cdate");
		String ctime = getIntent().getStringExtra("ctime");
		String id = getIntent().getStringExtra("cid");
		
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_selected_client_details_commercial/"+id);
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
	        	String cn = item.getString("client_id");
	        	String cd = item.getString("company_address");
	        	//String cc = item.getString("client_contact");
	        	//String ct = item.getString("client_contact");
//	        	Toast.makeText(getApplicationContext(), cn, Toast.LENGTH_LONG).show();
	        	name.setText(cn);
	        	address.setText(cd);
	        	date.setText(cdate);
	        	time.setText(ctime);
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
		String id = getIntent().getStringExtra("cid");
		
		//String commercial_name = name.getText().toString();
		String commercial_address = address.getText().toString();
		String commercial_date = date.getText().toString();
		String commercial_time = time.getText().toString();
		//String commercial_price = cprice.getText().toString();
		
		    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
	    		//nameValuePairs.add(new BasicNameValuePair("company_name", commercial_name));
	    		nameValuePairs.add(new BasicNameValuePair("company_address", commercial_address));
	    		nameValuePairs.add(new BasicNameValuePair("date", commercial_date));
	    		nameValuePairs.add(new BasicNameValuePair("time", commercial_time));
	    		//nameValuePairs.add(new BasicNameValuePair("price", commercial_price));
	    		
	    		try{
	    			HttpClient httpClient = new DefaultHttpClient();
	    			HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/updateresched/"+id);
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
	
	

}