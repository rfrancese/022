package com.example.shadowduel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import com.example.shadowduel.MainActivity.UserLoginTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityRegister extends Activity {
	private String connPath="http://shadowduel.altervista.org";
	private String connPathalt="http://shadowduel.netne.net";
	// Valori di username e password inseriti dall'utente dal JSON
	public String username = "";
	public String password = "";
	public String repassword = "";
	public String email = "";
	
	// TextFields dove l'utente inserisce username e password
	public EditText usernameView;
	public EditText passwordView;	
	public EditText repasswordView;
	public EditText emailView;
	

	// Valori di username e password inseriti dall'utente dai TextField
	public String user = "";
	public String pass = "";
	public String repass = "";
	public String mail = "";

	// Variabili di connesione al server e JSON
	public static JSONArray jsArray = null;
	public HttpPost httpPost;
	public DefaultHttpClient httpClient;
	
	//Content dell'Activity
	final Context context = this;

	
		Button buttonRegister;

		@Override
		public void onCreate(Bundle savedInstanceState) {
	    	//connPath=connPathalt;

			super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.activity_register);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	        usernameView=(EditText)this.findViewById(R.id.usernamer);
	        passwordView=(EditText)this.findViewById(R.id.passwordr);
	        repasswordView=(EditText)this.findViewById(R.id.passwordr2);
	        emailView=(EditText)this.findViewById(R.id.emailr);
	        
	        usernameView.setTextColor(Color.BLACK);
	        passwordView.setTextColor(Color.BLACK);
	        repasswordView.setTextColor(Color.BLACK);
	        emailView.setTextColor(Color.BLACK);
	        
			addListenerOnButton();
		    
	    }
		public void addListenerOnButton() {
			 
			final Context context = this;
	 
			buttonRegister = (Button) findViewById(R.id.buttonRegister);

			buttonRegister.setOnClickListener(new OnClickListener() {
	 
				@Override
				public void onClick(View arg0) {
					if((!(usernameView.getText().toString()).equalsIgnoreCase("")) && (!(passwordView.getText().toString()).equalsIgnoreCase("")) && (!(repasswordView.getText().toString()).equalsIgnoreCase("")) && (!(emailView.getText().toString()).equalsIgnoreCase(""))){
						if((repasswordView.getText().toString()).equals((passwordView.getText().toString()))){
							final UserRegTask ult = new UserRegTask();
							ult.execute(); 
						}
				    	else {
					    	Toast.makeText(ActivityRegister.this, "Le password non coincidono.",Toast.LENGTH_LONG).show();
				    	}
					}
			    	else {
				    	Toast.makeText(ActivityRegister.this, "Non hai inserito i dati.",Toast.LENGTH_LONG).show();
			    	}
	 
				}
	 
			});
			
		}
		
		
		
		
	    public String sendRegAndGetJsonFromUrl(String url) {
	        
	    	String json = null;
	 
	        try {

	  			httpClient = new DefaultHttpClient();
	  			httpPost = new HttpPost(url);
	  			
	  			user = cap(usernameView.getText().toString());
	  			pass = passwordView.getText().toString();
	  			repass = repasswordView.getText().toString();
	  			mail = emailView.getText().toString();

	  		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
	  		    nameValuePairs.add(new BasicNameValuePair("username", user));
	  		    nameValuePairs.add(new BasicNameValuePair("password", pass));
	  		    nameValuePairs.add(new BasicNameValuePair("email", mail));
	  		    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	  		    // Execute HTTP Post Request
	  		    HttpResponse httpResponse = httpClient.execute(httpPost);
	            HttpEntity httpEntity = httpResponse.getEntity();
	            json = EntityUtils.toString(httpEntity);
	        
	 
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return json;
	    }
	    
	    
	  public void fetchJSON(JSONArray jsonArray){

	      	try {
	      	   for (int i = 0; i < jsonArray.length(); i++) {
	      	       username = jsonArray.getJSONObject(i).getString("username");
	      	       password = jsonArray.getJSONObject(i).getString("password");
	      	 //    repassword = jsonArray.getJSONObject(i).getString("password");
	      	       email = jsonArray.getJSONObject(i).getString("email");
	      	    }
	      	} catch (Exception e) {
	      	     e.printStackTrace();
	      	}
	      	
	   }
	    
	  public static String md5(String string) {
		    byte[] hash;

		    try {
		        hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		    } catch (NoSuchAlgorithmException e) {
		        throw new RuntimeException("Huh, MD5 should be supported?", e);
		    } catch (UnsupportedEncodingException e) {
		        throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		    }

		    StringBuilder hex = new StringBuilder(hash.length * 2);

		    for (byte b : hash) {
		        int i = (b & 0xFF);
		        if (i < 0x10) hex.append('0');
		        hex.append(Integer.toHexString(i));
		    }

		    return hex.toString();
		}
	  	
	    
	  public String cap(String s) {
	    return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	  }
	  
	    public class UserRegTask extends AsyncTask<Void, Void, Boolean> {
	    	
	    	String json = "";
	    	
	        @Override
	        protected Boolean doInBackground(Void... params) {
	            // TODO: attempt authentication against a network service.

	            try {
	            	String url = "http://shadowduel.altervista.org/app/app_connect_register.php";	
	            	json = sendRegAndGetJsonFromUrl(url);
	            	jsArray = new JSONArray(json);
	            	
	            	// Simulate network access.
	                Thread.sleep(100);
	            }
	            catch (InterruptedException e) {
	                return false;
	            } catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

	            return false;
	        }

	        @Override
	        protected void onPostExecute(final Boolean success) {
	        	
	        	fetchJSON(jsArray);
			    if((usernameView.getText().toString()).equalsIgnoreCase(username) && md5(md5(passwordView.getText().toString())).equals(password) && (emailView.getText().toString()).equals(email)){
			    
			    	Toast.makeText(ActivityRegister.this, "Registrazione effettuata con successo.",Toast.LENGTH_LONG).show();
			        finish();   
			    	
			    	
					
	        	}
			    else {
			    	Toast.makeText(ActivityRegister.this, "Registrazione Fallita.",Toast.LENGTH_LONG).show();
			    }
	        	
	        }

	        @Override
	        protected void onCancelled() {

	        }

	    }   
	    @Override
public void onDestroy(){
			getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	    	super.onDestroy();
	    }
		
	}
