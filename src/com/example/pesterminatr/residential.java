package com.example.pesterminatr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class residential extends Activity implements OnClickListener {

	EditText problem,date,time,address;
	Spinner squaremeter;
	Button btnbook;
	InputStream is;
	SharedPreferences prf;
	String id;
	DateFormat format=DateFormat.getDateInstance();
	Calendar calendar=Calendar.getInstance();
	int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
	int currentMinute = calendar.get(Calendar.MINUTE);
	TimePickerDialog timePickerDialog;
	String amPm;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.residential);
		
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		intent = new Intent(residential.this,MainActivity.class);
		problem = (EditText) findViewById(R.id.txtcname);
		date = (EditText) findViewById(R.id.txtdate);
		time = (EditText) findViewById(R.id.txttime);
		address = (EditText) findViewById(R.id.raddress);
		squaremeter = (Spinner) findViewById(R.id.spinmeter);
		btnbook = (Button) findViewById(R.id.btnbookresidential);
		btnbook.setOnClickListener(this);
		date.setOnClickListener(this);
		time.setOnClickListener(this);
		//String caddress = prf.getString("client_address", "");
		String id = prf.getString("client_id", "");
		String sID = getIntent().getStringExtra("serviceID");
		String sname = getIntent().getStringExtra("sname");
		String pID = getIntent().getStringExtra("pestID");
		//Toast.makeText(getApplicationContext(), "Service ID: "+sID+", PestControlID:"+pID, Toast.LENGTH_LONG).show();
		//Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
		//Toast.makeText(getApplicationContext(), sname, Toast.LENGTH_LONG).show();
		
		
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
	        	//String cn = item.getString("client_name");
	        	String cd = item.getString("client_address");
	        	//String cc = item.getString("client_contact");
//	        	Toast.makeText(getApplicationContext(), cn, Toast.LENGTH_LONG).show();
	        	//name.setText(cn);
	        	problem.setText(sname);
	        	address.setText(cd);
	        	//number.setText(cc);
	        }
		}catch (MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}catch (JSONException e){
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			calendar.set(Calendar.YEAR, arg1);
			calendar.set(Calendar.MONTH, arg2);
			calendar.set(Calendar.DAY_OF_MONTH, arg3);
			date.setText(format.format(calendar.getTime()));
		}
	};

	@Override
	public void onClick(View arg0) {
		
		timePickerDialog = new TimePickerDialog(residential.this, new TimePickerDialog.OnTimeSetListener() {
	        @Override
	        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
	        	String amPm;
//	        	if (hourOfDay >= 12) {
//	        	        amPm = "PM";
//	        	    } else {
//	        	        amPm = "AM";
//	        	    }
	        	if (hourOfDay > 12) {
	        		hourOfDay -= 12;
	                amPm = "PM";
	              } else if (hourOfDay == 0) {
	            	  hourOfDay += 12;
	                amPm = "AM";
	              } else if (hourOfDay == 12){
	            	  amPm = "PM";
	              }else{
	            	  amPm = "AM";
	              }
	        	time.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
	        }
		}, currentHour, currentMinute, false);
		
		
		
		switch(arg0.getId()){
		case R.id.btnbookresidential:
			String sID = getIntent().getStringExtra("serviceID");
			String pID = getIntent().getStringExtra("pestID");
			//String caddress = prf.getString("client_address", "");
			
			String r_problem = problem.getText().toString();
			String r_date = date.getText().toString();
			String r_time = time.getText().toString();
			String r_address = address.getText().toString();
			String r_meter = squaremeter.getSelectedItem().toString();
			prf = getSharedPreferences("user_details",MODE_PRIVATE);
			String username = prf.getString("client_name", null);
			
			if(r_date.equals("") || r_time.equals(""))
			{
				Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
			}
			else
			{
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
				nameValuePairs.add(new BasicNameValuePair("problem", r_problem));
				nameValuePairs.add(new BasicNameValuePair("pestcontrol_id", pID));
				nameValuePairs.add(new BasicNameValuePair("service_id", sID));
				nameValuePairs.add(new BasicNameValuePair("meter", r_meter));
				nameValuePairs.add(new BasicNameValuePair("client_id", username));
				nameValuePairs.add(new BasicNameValuePair("residential_address", r_address));
				nameValuePairs.add(new BasicNameValuePair("date", r_date));
				nameValuePairs.add(new BasicNameValuePair("time", r_time));
				try{
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/bookresidential");
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse response = httpClient.execute(httpPost);
					HttpEntity entity = response.getEntity();
					is=entity.getContent();
					Toast.makeText(getApplicationContext(), "Booking Transaction Success", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(this, BookingDetails.class);
					startActivity(intent);
		//			txtname.setText("");
		//			address.setText(caddress);
		//			txtcontact.setText("");
		//			txtusername.setText("");
		//			txtpassword.setText("");
					
					
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
			
		break;
		case R.id.txtdate:
				new DatePickerDialog(this,listener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
			break;
		case R.id.txttime:
			timePickerDialog.show();
		break;	
			
			
			
		}
		
		
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		
		switch(item.getItemId()){
				case R.id.logout:
					SharedPreferences.Editor editor = prf.edit();
					editor.clear();
					editor.commit();
					startActivity(intent);
					return true;
				case R.id.profile:
					Intent profile = new Intent(this, Profile.class);
					startActivity(profile);
					return true;
				case R.id.home:
					Intent home = new Intent(this, client.class);
					startActivity(home);
					return true;
				case R.id.bookingdetails:
					Intent details = new Intent(this, BookingDetails.class);
					startActivity(details);
					return true;
	//			case R.id.commercialdetails:
	//				Intent commercial = new Intent(this, CommercialDetails.class);
	//				startActivity(commercial);
	//				return true;
				case R.id.history:
					Intent history = new Intent(this, History.class);
					startActivity(history);
					return true;
	//			case R.id.commercialhistory:
	//				Intent commercialhistory = new Intent(this, CommercialHistory.class);
	//				startActivity(commercialhistory);
	//				return true;	
					
				default:
					return super.onOptionsItemSelected(item);
				
			}		
		
		
		
		}
	

}
