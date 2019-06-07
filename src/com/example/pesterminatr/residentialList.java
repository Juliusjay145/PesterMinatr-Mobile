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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class residentialList extends Activity {

	ArrayList<bookresidentialList> list = new ArrayList<bookresidentialList>();
	bookresidentialAdapter adapter;
	SharedPreferences prf;
	ListView lv;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_residential);
		
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		
		lv = (ListView) findViewById(R.id.listView1);
		//String id = prf.getString("pestcontrol_id", "");
		String pID = getIntent().getStringExtra("pID");
		
		Toast.makeText(getApplicationContext(), pID, Toast.LENGTH_SHORT).show();
		
//		try{
//			URL url = new URL("http://10.0.2.2/project/index.php/androidcontroller/fetch_residential/" + id);
//	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	        InputStream is=conn.getInputStream();
//	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//	        String s=br.readLine();
//	        
//	        is.close();
//	        conn.disconnect();
//	        
//	        Log.d("json data", s);
//	        JSONObject json=new JSONObject(s);
//	        JSONArray array = json.getJSONArray("residential");
//	        for(int i=0; i<array.length(); i++){
//	        	JSONObject item = array.getJSONObject(i);
//	        	String pestcontrol_name = item.getString("client_id");
//	        	String pestcontrolId = item.getString("pestcontrol_id");
//	        	String CompanyImage = item.getString("path_image");
//	        	list.add(new CompanyList(CompanyImage,pestcontrolId,pestcontrol_name));
//	        	adapter.notifyDataSetChanged();
//	        }
//		}catch (MalformedURLException e){
//			e.printStackTrace();
//		}catch (IOException e){
//			e.printStackTrace();
//		}catch (JSONException e){
//			e.printStackTrace();
//		}
	}
	

}
