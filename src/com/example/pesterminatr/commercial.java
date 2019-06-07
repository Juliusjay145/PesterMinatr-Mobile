package com.example.pesterminatr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.StringCharacterIterator;
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

public class commercial extends Activity implements OnClickListener {

	EditText cname,cproblem,caddress,cdate,ctime;
	Spinner cmeter;
	Button cbook;
	InputStream is;
	String id;
	SharedPreferences prf;
	//DatePickerDialog datepicker;
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
		setContentView(R.layout.commercial);
		
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		intent = new Intent(commercial.this,MainActivity.class);
		cname = (EditText) findViewById(R.id.txtcname);
		cproblem = (EditText) findViewById(R.id.raddress);
		caddress = (EditText) findViewById(R.id.caddr);
		cdate  = (EditText) findViewById(R.id.editText2);
		ctime = (EditText) findViewById(R.id.editText3);
		cmeter = (Spinner) findViewById(R.id.spinmeter);
		cbook = (Button) findViewById(R.id.btnbookresidential);
		cbook.setOnClickListener(this);
		cdate.setOnClickListener(this);
		ctime.setOnClickListener(this);
		
		String id = prf.getString("commercial_client_id", "");
		String sID = getIntent().getStringExtra("serviceID");
		String sname = getIntent().getStringExtra("sname");
		String pID = getIntent().getStringExtra("pestID");
		//Toast.makeText(getApplicationContext(), "Service ID: "+sID+", PestControlID:"+pID, Toast.LENGTH_LONG).show();
		//Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
		//Toast.makeText(getApplicationContext(), sname, Toast.LENGTH_LONG).show();
		
		try{
			URL url = new URL("http://192.168.43.118/project/index.php/androidcontroller/fetch_selected_client_commercial/"+id);
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
	        	//String cn = item.getString("client_name");
	        	String cd = item.getString("c_address");
	        	String cnames = item.getString("c_name");
	        	//String cc = item.getString("client_contact");
	        	Toast.makeText(getApplicationContext(), cd, Toast.LENGTH_LONG).show();
	        	//name.setText(cn);
	        	cproblem.setText(sname);
	        	caddress.setText(cd);
	        	cname.setText(cnames);
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
			cdate.setText(format.format(calendar.getTime()));
			
		}
		
		
	};
	

	@Override
	public void onClick(View arg0) {
		
		timePickerDialog = new TimePickerDialog(commercial.this, new TimePickerDialog.OnTimeSetListener() {
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
	        	ctime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
	        }
		}, currentHour, currentMinute, false);
		
		
		
		
		
		
		switch(arg0.getId()){
		case R.id.btnbookresidential:
			String sID = getIntent().getStringExtra("serviceID");
			String pID = getIntent().getStringExtra("pestID");
			
			String c_name = cname.getText().toString();
			String c_problem = cproblem.getText().toString();
			String c_address = caddress.getText().toString();
			String c_date = cdate.getText().toString();
			String c_time = ctime.getText().toString();
			String c_meter = cmeter.getSelectedItem().toString();
			prf = getSharedPreferences("user_details",MODE_PRIVATE);
			String username = prf.getString("c_name", null);
			
			if(c_date.equals("") || c_time.equals(""))
			{
				Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
			}
			else
			{
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
				nameValuePairs.add(new BasicNameValuePair("problem", c_problem));
				nameValuePairs.add(new BasicNameValuePair("company_name", c_name));
				nameValuePairs.add(new BasicNameValuePair("company_address", c_address));
				nameValuePairs.add(new BasicNameValuePair("pestcontrol_id", pID));
				nameValuePairs.add(new BasicNameValuePair("service_id", sID));
				nameValuePairs.add(new BasicNameValuePair("meter", c_meter));
				nameValuePairs.add(new BasicNameValuePair("client_id", username));
				nameValuePairs.add(new BasicNameValuePair("date", c_date));
				nameValuePairs.add(new BasicNameValuePair("time", c_time));
				try{
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/bookCommercial");
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse response = httpClient.execute(httpPost);
					HttpEntity entity = response.getEntity();
					is=entity.getContent();
					Toast.makeText(getApplicationContext(), "Booking Transaction Success", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(this, CommercialBookingDetails.class);
					startActivity(intent);
		//			txtname.setText("");
		//			txtaddress.setText("");
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
				case R.id.editText2:
					new DatePickerDialog(this,listener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
				break;
				
				case R.id.editText3:
					timePickerDialog.show();
				break;	
		
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
			case R.id.logout:
				SharedPreferences.Editor editor = prf.edit();
				editor.clear();
				editor.commit();
				startActivity(intent);
				return true;
			case R.id.profile:
				Intent profile = new Intent(this, CommericalProfile.class);
				startActivity(profile);
				return true;
			case R.id.home:
				Intent home = new Intent(this, ClientCommercial.class);
				startActivity(home);
				return true;
			case R.id.bookingdetails:
				Intent details = new Intent(this, CommercialBookingDetails.class);
				startActivity(details);
				return true;
	//		case R.id.commercialdetails:
	//			Intent commercial = new Intent(this, CommercialDetails.class);
	//			startActivity(commercial);
	//			return true;
			case R.id.history:
				Intent history = new Intent(this, History.class);
				startActivity(history);
				return true;
	//		case R.id.commercialhistory:
	//			Intent commercialhistory = new Intent(this, CommercialHistory.class);
	//			startActivity(commercialhistory);
	//			return true;	
				
			default:
				return super.onOptionsItemSelected(item);
		}
		
		
	}
	
	
	

}
