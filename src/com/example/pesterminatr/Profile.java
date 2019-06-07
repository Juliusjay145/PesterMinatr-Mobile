package com.example.pesterminatr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends Activity implements OnClickListener {
	
	TextView name,address,number;
	Button btnUpdate;
	SharedPreferences prf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		name = (TextView) findViewById(R.id.name);
		address = (TextView) findViewById(R.id.textView2);
		number = (TextView) findViewById(R.id.textView3);
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
		//name.setText("sd");
	}

	@Override
	public void onClick(View arg0) {
		
		Intent intent = new Intent(this, ProfileEdit.class);
		startActivity(intent);
	}
	
}
