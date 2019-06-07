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

public class commercialupdatedetails extends Activity implements OnClickListener {

	EditText rproblem,raddress,rdate,rtime,rprice;
	Button btnUpdate,btnCancel;
	SharedPreferences prf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_residential_details);
		
		rproblem = (EditText) findViewById(R.id.editText1);
		raddress = (EditText) findViewById(R.id.editText2);
		rdate = (EditText) findViewById(R.id.editText3);
		rtime = (EditText) findViewById(R.id.editText4);
		
		btnUpdate = (Button) findViewById(R.id.button1);
		btnCancel = (Button) findViewById(R.id.button2);
		
		btnUpdate.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		String id = getIntent().getStringExtra("c_id");
		Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
		
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
	        	String c_name = item.getString("problem");
	        	String c_address = item.getString("company_address");
	        	String c_date = item.getString("date");
	        	String c_time = item.getString("time");
	        	//String c_price = item.getString("price");
//	        	Toast.makeText(getApplicationContext(), cn, Toast.LENGTH_LONG).show();
	        	rproblem.setText(c_name);
	        	raddress.setText(c_address);
	        	rdate.setText(c_date);
	        	rtime.setText(c_time);
	        	//rprice.setText(c_price);
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
		
		String res_problem = rproblem.getText().toString();
		String res_address = raddress.getText().toString();
		String res_date = rdate.getText().toString();
		String res_time = rtime.getText().toString();
		
		    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
	    		nameValuePairs.add(new BasicNameValuePair("problem", res_problem));
	    		nameValuePairs.add(new BasicNameValuePair("residential_address", res_address));
	    		nameValuePairs.add(new BasicNameValuePair("date", res_date));
	    		nameValuePairs.add(new BasicNameValuePair("time", res_time));
	    		
	    		try{
	    			HttpClient httpClient = new DefaultHttpClient();
	    			HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/updateCancelDetailsCommercial/"+id);
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
