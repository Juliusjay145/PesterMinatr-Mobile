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

public class CommentFeedbackResidential extends Activity {

	ArrayList<CommentList> list = new ArrayList<CommentList>();
	CommentAdapter adapter;
	SharedPreferences prf;
	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedbacks);
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		
		lv = (ListView) findViewById(R.id.listView1);
		this.adapter = new CommentAdapter(this, list);
		lv.setAdapter(adapter);
		String ID = getIntent().getStringExtra("pestcontrol_id");
		Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_SHORT).show();
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_feedback/"+ID);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        InputStream is=conn.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        String s=br.readLine();
	        
	        is.close();
	        conn.disconnect();
	        
	        Log.d("json data", s);
	        JSONObject json=new JSONObject(s);
	        JSONArray array = json.getJSONArray("comment");
	        for(int i=0; i<array.length(); i++){
	        	JSONObject item = array.getJSONObject(i);
	        	String sched_id = item.getString("comment_id");
	        	String user = item.getString("client_id");
	        	String comment = item.getString("comment");
	        	String rate = item.getString("rating");
	        	list.add(new CommentList(sched_id,user,comment,rate));
	        	adapter.notifyDataSetChanged();
	        	
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
