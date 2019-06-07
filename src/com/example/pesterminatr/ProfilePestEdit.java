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

public class ProfilePestEdit extends Activity implements OnClickListener {

	EditText name,number,address;
	Button btnUpdate;
	SharedPreferences prf;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pestcontrolupdate);
		
		name = (EditText) findViewById(R.id.editText1);
		address = (EditText) findViewById(R.id.editText2);
		number = (EditText) findViewById(R.id.editText3);
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		String id = prf.getString("pestcontrol_id", "");
		btnUpdate = (Button) findViewById(R.id.button1);
		btnUpdate.setOnClickListener(this);
		Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
		
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_selected_pestcontrol/"+id);
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
	        	String cn = item.getString("pestcontrol_name");
	        	String cd = item.getString("pestcontrol_address");
	        	String cc = item.getString("pestcontrol_contact");
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
		String id = prf.getString("pestcontrol_id", "");
		
		String pestcontrol_name = name.getText().toString();
		String pestcontrol_address = address.getText().toString();
		String pestcontrol_contact = number.getText().toString();
		
		    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
	    		nameValuePairs.add(new BasicNameValuePair("pestcontrol_name", pestcontrol_name));
	    		nameValuePairs.add(new BasicNameValuePair("pestcontrol_address", pestcontrol_address));
	    		nameValuePairs.add(new BasicNameValuePair("pestcontrol_contact", pestcontrol_contact));
	    		
	    		try{
	    			HttpClient httpClient = new DefaultHttpClient();
	    			HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/updatePestControl/"+id);
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
	
	

}
