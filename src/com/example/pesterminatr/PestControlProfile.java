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
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PestControlProfile extends Activity implements OnClickListener {

	TextView name,address,contact;
	ImageView img;
	Button btnBook;
	ArrayList<CompanyList> list = new ArrayList<CompanyList>();
	CompanyAdapter adapter;
	SharedPreferences prf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.provider_profile);
		
		name = (TextView) findViewById(R.id.textView2);
		address = (TextView) findViewById(R.id.textView4);
		contact = (TextView) findViewById(R.id.textView6);
		img = (ImageView) findViewById(R.id.imageView1);
		btnBook = (Button) findViewById(R.id.button1);
		btnBook.setOnClickListener(this);
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		String ID = getIntent().getStringExtra("pestcontrol");
		Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_SHORT).show();
		
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
	        	String cn = item.getString("pestcontrol_name");
	        	String cd = item.getString("pestcontrol_address");
	        	String cc = item.getString("pestcontrol_contact");
	        	//String image = item.getString("path_image");
//	        	Toast.makeText(getApplicationContext(), cn, Toast.LENGTH_LONG).show();
	        	name.setText(cn);
	        	address.setText(cd);
	        	contact.setText(cc);
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
		
//		CompanyList selectedItem = list.get(arg0);
//		String ID = selectedItem.getId();
//		Intent intent = new Intent(this, service_pest.class);
//		intent.putExtra("service", ID);
//		startActivityForResult(intent, 1);
		//startActivity(intent);
		
		String ID = getIntent().getStringExtra("pestcontrol");
		Intent intent = new Intent(this, service_pest.class);
		intent.putExtra("serviceID", ID);
		startActivityForResult(intent, 1);
		
	}
	
	

}
