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

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;


public class MainActivity extends Activity implements OnClickListener {

	EditText user,pass;
	Button btnLogin, btnRegister;
	SharedPreferences pref;
	
	
    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(policy);
        getActionBar().hide();
        
        user=(EditText) findViewById(R.id.txtcname);
        pass=(EditText) findViewById(R.id.txtdate);
        btnLogin = (Button) findViewById(R.id.btnEdit);
        btnRegister = (Button) findViewById(R.id.button2);
        Intent intent = new Intent(MainActivity.this,client.class);
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        if(pref.contains("user") && pref.contains("pass"))
        {
        	startActivity(intent);
        }
        
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        
        
    }


	@Override
	public void onClick(View arg0) {
		
		if(arg0==btnRegister)
		{
			 Intent intent = new Intent(this,client_reg.class);
			 startActivity(intent);
		}
		else
		{
			String username = user.getText().toString();
			String password = pass.getText().toString();
			//http://192.168.43.118
			try{
				URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_client");
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
		        	String user_name = item.getString("username");
		        	String user_pass = item.getString("password");
		        	String client_id = item.getString("client_id");
		        	String client_name = item.getString("client_name");
		        	if(username.equals(user_name) && password.equals(user_pass))
		        	{
		        		SharedPreferences.Editor editor=pref.edit();
		        		editor.putString("user", username);
		        		editor.putString("pass", password);
		        		editor.putString("client_id", client_id);
		        		//editor.putString("pestcontrol_id", client_id);
		        		editor.putString("client_name", client_name);
		        		editor.commit();
		        		Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
		        		Intent intent = new Intent(this, MainActivity.class);
		        		startActivity(intent);
		        		user.setText("");
		        		pass.setText("");
		        	}else{
		        		user.setText("");
		        		pass.setText("");
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
				URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_client_commercial");
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        InputStream is=conn.getInputStream();
		        BufferedReader br = new BufferedReader(new InputStreamReader(is));
		        String s=br.readLine();
		        
		        is.close();
		        conn.disconnect();
		        
		        Log.d("json data", s);
		        JSONObject json=new JSONObject(s);
		        JSONArray array = json.getJSONArray("client_commercial");
		        for(int i=0; i<array.length(); i++){
		        	JSONObject item = array.getJSONObject(i);
		        	String user_name = item.getString("username");
		        	String user_pass = item.getString("password");
		        	String client_id = item.getString("commercial_client_id");
		        	String client_name = item.getString("c_name");
		        	if(username.equals(user_name) && password.equals(user_pass))
		        	{
		        		SharedPreferences.Editor editor=pref.edit();
		        		editor.putString("user", username);
		        		editor.putString("pass", password);
		        		editor.putString("commercial_client_id", client_id);
		        		//editor.putString("pestcontrol_id", client_id);
		        		editor.putString("c_name", client_name);
		        		editor.commit();
		        		Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
		        		Intent intent = new Intent(this, ClientCommercial.class);
		        		startActivity(intent);
		        		user.setText("");
		        		pass.setText("");
		        	}else{
		        		user.setText("");
		        		pass.setText("");
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
				URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_client_deactivated");
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
		        	String user_name = item.getString("username");
		        	String user_pass = item.getString("password");
		        	String client_id = item.getString("client_id");
		        	if(username.equals(user_name) && password.equals(user_pass))
		        	{
		        		SharedPreferences.Editor editor=pref.edit();
		        		editor.putString("user", username);
		        		editor.putString("pass", password);
		        		editor.putString("client_id", client_id);
		        		editor.commit();
		        		//Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
		        		//Intent intent = new Intent(this, MainActivity.class);
		        		//startActivity(intent);
		        		AlertDialog dialog = new AlertDialog.Builder(this).create();
		        		dialog.setMessage("Sorry your account has been deactivated to the super admin please contact the admin");
		        		dialog.show();
		        		user.setText("");
		        		pass.setText("");
		        	}else{
		        		user.setText("");
		        		pass.setText("");
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
		        	String user_name = item.getString("username");
		        	String user_pass = item.getString("password");
		        	String pest_id = item.getString("pestcontrol_id");
		        	String pest_name = item.getString("pestcontrol_name");
		        	
		        	if(username.equals(user_name) && password.equals(user_pass))
		        	{
		        		SharedPreferences.Editor editor=pref.edit();
		        		editor.putString("user", username);
		        		editor.putString("pass", password);
		        		editor.putString("pestcontrol_id", pest_id);
		        		editor.putString("pestcontrol_name", pest_name);
		        		editor.commit();
		        		Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
		        		Intent intent = new Intent(this, pestcontrol.class);
		        		startActivity(intent);
		        		user.setText("");
		        		pass.setText("");
		        	}     	
		        	else{
		        		user.setText("");
		        		pass.setText("");
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
				URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_pestcontrol_deactivated");
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
		        	String user_name = item.getString("username");
		        	String user_pass = item.getString("password");
		        	String pest_id = item.getString("pestcontrol_id");
		        	if(username.equals(user_name) && password.equals(user_pass))
		        	{
		        		SharedPreferences.Editor editor=pref.edit();
		        		editor.putString("user", username);
		        		editor.putString("pass", password);
		        		editor.putString("pestcontrol_id", pest_id);
		        		editor.commit();
		        		//Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
		        		//Intent intent = new Intent(this, MainActivity.class);
		        		//startActivity(intent);
		        		AlertDialog dialog = new AlertDialog.Builder(this).create();
		        		dialog.setMessage("Sorry your account has been deactivated to the super admin please contact the admin");
		        		dialog.show();
		        		user.setText("");
		        		pass.setText("");
		        	}else{
		        		user.setText("");
		        		pass.setText("");
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
		
		
		
	}
    
    	
    
    


    
    
}
