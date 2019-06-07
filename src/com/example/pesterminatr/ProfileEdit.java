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
import android.widget.TextView;
import android.widget.Toast;

public class ProfileEdit extends Activity implements OnClickListener {

	EditText name,number,address;
	Button btnUpdate;
	SharedPreferences prf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_profile);
		name = (EditText) findViewById(R.id.txtcname);
		address = (EditText) findViewById(R.id.txtdate);
		number = (EditText) findViewById(R.id.txttime);
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		String id = prf.getString("client_id", "");
		btnUpdate = (Button) findViewById(R.id.btnEdit);
		btnUpdate.setOnClickListener(this);
		Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
		
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_selected_client/"+id);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        InputStream is=conn.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        String s=br.readLine();
	        
	        is.close();
	        conn.disconnect();
	        
	        Log.d("json data", s);
	        JSONObject json=new JSONObject(s);
	        JSONArray array = json.getJSONArray("client");
	        for(int i=0; i<array.length(); i++){
	        	JSONObject item = array.getJSONObject(i);
	        	String cn = item.getString("client_name");
	        	String cd = item.getString("client_address");
	        	String cc = item.getString("client_contact");
//	        	Toast.makeText(getApplicationContext(), cn, Toast.LENGTH_LONG).show();
	        	name.setText(cn);
	        	address.setText(cd);
	        	number.setText(cc);
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
		String id = prf.getString("client_id", "");
		
		String client_name = name.getText().toString();
		String client_address = address.getText().toString();
		String client_contact = number.getText().toString();
		
		    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
	    		nameValuePairs.add(new BasicNameValuePair("client_name", client_name));
	    		nameValuePairs.add(new BasicNameValuePair("client_address", client_address));
	    		nameValuePairs.add(new BasicNameValuePair("client_contact", client_contact));
	    		
	    		try{
	    			HttpClient httpClient = new DefaultHttpClient();
	    			HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/updateClient/"+id);
	    			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    			HttpResponse response = httpClient.execute(httpPost);
	    			HttpEntity entity = response.getEntity();
	    			InputStream is;
	    			is=entity.getContent();
	    			Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
	    			Intent intent = new Intent(this, client.class);
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
