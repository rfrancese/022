
//  _________.__                .___              ________                .__   
// /   _____/|  |__ _____     __| _/______  _  __ \______ \  __ __   ____ |  |  
// \_____  \ |  |  \\__  \   / __ |/  _ \ \/ \/ /  |    |  \|  |  \_/ __ \|  |  
// /        \|   Y  \/ __ \_/ /_/ (  <_> )     /   |    `   \  |  /\  ___/|  |__
///_______  /|___|  (____  /\____ |\____/ \/\_/   /_______  /____/  \___  >____/
//        \/      \/     \/      \/                       \/            \/  

package com.example.shadowduel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	ProgressDialog progressBar;

	// Valori di username e password inseriti dall'utente dal JSON
	public String username = "";
	public String password = "";
	
	// TextFields dove l'utente inserisce username e password
	public EditText usernameView;
	public EditText passwordView;	

	// Valori di username e password inseriti dall'utente dai TextField
	public String user = "";
	public String pass = "";
	private int progressStatus = 0;

	// Variabili di connesione al server e JSON
	public static JSONArray jsArray = null;
	public HttpPost httpPost;
	public DefaultHttpClient httpClient;
	private String connPath="http://shadowduel.altervista.org";
	private String connPathalt="http://shadowduel.netne.net";
	//Content dell'Activity
	final Context context = this;
    public static final String PREFS_NAME = "storage_var";
    private int entered=0;
    private String userload;
    private String passload;
    private String usernameload;
    private String passwordload;
	public ArrayList<Card> Deck=new ArrayList<Card>();;
	public ArrayList<Card> currDeck=new ArrayList<Card>();;
	private ArrayList<Card> hand=new ArrayList<Card>();;
	Button buttonLogin;
	Button buttonRegister;
	private Random rand;
	private int LENGHT;
	private int currlenght;
	public static JSONArray jsArrayd = null;
	public HttpPost httpPostd;
	public DefaultHttpClient httpClientd;
	float scale;
	public MediaPlayer player;
    protected void onCreate(Bundle savedInstanceState) {
    	//connPath=connPathalt;
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //this.getResources().newTheme();
    	
    	player = MediaPlayer.create(this, R.raw.battle);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        player.start();
                super.onCreate(savedInstanceState);
                
                
    	setContentView(R.layout.activity_main);
    	
        load();
		scale = getResources().getDisplayMetrics().density;
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        usernameView=(EditText)this.findViewById(R.id.username);
        passwordView=(EditText)this.findViewById(R.id.password);
        LayoutParams userpar = usernameView.getLayoutParams();
        LayoutParams passpar = passwordView.getLayoutParams();
        LayoutParams loginpar = findViewById(R.id.buttonLogin).getLayoutParams();
        LayoutParams escipar = findViewById(R.id.buttonRegister).getLayoutParams();
        LayoutParams holdpar = findViewById(R.id.holderlogin).getLayoutParams();
        LayoutParams holdpar2 = findViewById(R.id.holdermain1).getLayoutParams();
        LayoutParams holdpar3 = findViewById(R.id.holdermain2).getLayoutParams();
        LayoutParams holdpar4 = findViewById(R.id.holdermain3).getLayoutParams();

        holdpar2.height=(int) (50*scale+0.5f);
        holdpar3.height=(int) (50*scale+0.5f);
        holdpar4.height=(int) (20*scale+0.5f);

        holdpar.height=(int) (80*scale+0.5f);
        holdpar.width=(int) (300*scale+0.5f);

        userpar.height=(int) (50*scale+0.5f);
        passpar.height=(int) (50*scale+0.5f);
        loginpar.height=(int) (60*scale+0.5f);
        escipar.height=(int) (60*scale+0.5f);
        userpar.width=(int) (250*scale+0.5f);
        passpar.width=(int) (200*scale+0.5f);
        loginpar.width=(int) (150*scale+0.5f);
        escipar.width=(int) (200*scale+0.5f);
        usernameView.setText(getUsername());
        passwordView.setText(getPassword());
        usernameView.setTextColor(Color.BLACK);
        passwordView.setTextColor(Color.BLACK);
        
        addListenerOnButtonLogin();
        addListenerOnButtonRegister();
    }   
    
    public void stopmusic(){
    	player.stop();
    }
    private void save(){
        SharedPreferences u = getSharedPreferences(PREFS_NAME, 0);
       SharedPreferences p = getSharedPreferences(PREFS_NAME, 1);

       SharedPreferences.Editor editoru = u.edit();
       editoru.putString("username", user);
       editoru.commit();

       SharedPreferences.Editor editorp = p.edit();
       editorp.putString("password", pass);
       editorp.commit();
       

    }
    private void savedeck(String jsondeck){
        SharedPreferences d = getSharedPreferences(PREFS_NAME, 3);

       SharedPreferences.Editor editord = d.edit();
       editord.putString("jsondeck", jsondeck);
       editord.commit();


       

    }
    
    private void savecatalogo(String jsoncatalogo){
        SharedPreferences c = getSharedPreferences(PREFS_NAME, 4);

       SharedPreferences.Editor editorc = c.edit();
       editorc.putString("jsoncatalogo", jsoncatalogo);
       editorc.commit();

    }
    private void savemycatalogo(String jsonmycatalogo){
        SharedPreferences mc = getSharedPreferences(PREFS_NAME, 5);

       SharedPreferences.Editor editord = mc.edit();
       editord.putString("jsonmycatalogo", jsonmycatalogo);
       editord.commit();

    }
    private void load(){
        SharedPreferences u = getSharedPreferences(PREFS_NAME, 0);
        usernameload = u.getString("username", userload);
       SharedPreferences p = getSharedPreferences(PREFS_NAME, 1);
      passwordload = p.getString("password", passload);

    }
    public String getUsername(){
    	return usernameload;
    }
    public String getPassword(){
    	return passwordload;
    }

    public void addListenerOnButtonLogin() {

		buttonLogin = (Button) findViewById(R.id.buttonLogin);
		buttonLogin.setOnClickListener(new  OnClickListener() {
				@Override
				public void onClick(View v) {
					buttonLogin.setOnClickListener(null);
					buttonRegister.setOnClickListener(null);
				if((!(usernameView.getText().toString()).equalsIgnoreCase("")) && (!(passwordView.getText().toString()).equalsIgnoreCase(""))){
					progressBar = new ProgressDialog(context);
					
					
					progressBar.setCancelable(true);
					
					progressBar.setMessage("Login in corso...");
					progressBar.setProgress(0);
					progressBar.setMax(100);
					progressBar.show();
				
					UserLoginTask ult = new UserLoginTask();
			        ult.execute();
//					Intent intent = new Intent(context,ActivityRegister.class);
//					startActivity(intent);
				}
				else {

			    	Toast.makeText(MainActivity.this, "Login non valido.",Toast.LENGTH_LONG).show();
			    	progressBar.dismiss();
			    	progressBar.cancel();

				}
				}
		});
    }
    
    public void addListenerOnButtonRegister() {

		buttonRegister = (Button) findViewById(R.id.buttonRegister);
		buttonRegister.setOnClickListener(new  OnClickListener() {
			
				@Override
				public void onClick(View v) {

			    		Intent intent = new Intent(context,ActivityRegister.class);
						startActivity(intent);

				}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// Handle action bar item clicks here. The action bar will
    	// automatically handle clicks on the Home/Up button, so long
    	// as you specify a parent activity in AndroidManifest.xml.
    	int id = item.getItemId();
    		if (id == R.id.action_settings) {
    			return true;
    		}
    	return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

    	public PlaceholderFragment() {
    	}

    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    	}
    }
    
    public String sendLoginAndGetJsonFromUrl(String url) {
        
    	String json = null;
 
        try {
  			httpClient = new DefaultHttpClient();
  			httpPost = new HttpPost(url);
  			
  			user = cap(usernameView.getText().toString());
  			pass = passwordView.getText().toString();

  		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
  		    nameValuePairs.add(new BasicNameValuePair("username", user));
  		    nameValuePairs.add(new BasicNameValuePair("password", pass));
  		    save();
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
      	    }
      	} catch (Exception e) {
      	     e.printStackTrace();
      	}
      	
   }
    
  public String cap(String s) {
    return Character.toUpperCase(s.charAt(0)) + s.substring(1);
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
  	
    
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
    	
    	String json = "";
    	
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
            	
        		 progressBar.setProgress(1);
            	String url = "http://shadowduel.altervista.org/app/app_connect_login.php";	
            	json = sendLoginAndGetJsonFromUrl(url);
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
        	
		    if((usernameView.getText().toString()).equalsIgnoreCase(username) && md5(md5(passwordView.getText().toString())).equals(password)){
		    	
		    	CatalogoTask catt=new CatalogoTask();

		        
		        catt.execute();
		    	

		    }
		    else {
		    	Toast.makeText(MainActivity.this, "Login non valido.",Toast.LENGTH_LONG).show();
		    	addListenerOnButtonLogin();
		    	addListenerOnButtonRegister();
		    }
        	
        }

        @Override
        protected void onCancelled() {

        }

    }   
    
    public class DeckTask extends AsyncTask<Void, Void, Boolean> {
    	
    	String json = "";
    	
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
            	try {
					json=sendUserAndGetJsonFromUrl();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
         
            }
            catch (InterruptedException e) {
                return false;
            } 

            return true;
        }
        
        @Override
        protected void onPostExecute(final Boolean success) {
        	
        	savedeck(json);
		    if((usernameView.getText().toString()).equalsIgnoreCase(username) && md5(md5(passwordView.getText().toString())).equals(password)){
		    	Toast.makeText(MainActivity.this, "Login effettuato con successo.",Toast.LENGTH_LONG).show();
		    	
		    	//finish();
		    	Intent intent = new Intent(context,ActivityMenu.class);
				startActivity(intent);

		    }
		    progressBar.cancel();
        }

        @Override
        protected void onCancelled() {

        }

    }  
   public class CatalogoTask extends AsyncTask<Void, Void, Boolean> {
    	
    	String jsoncatalogo = "";
    	
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
            	try {
					jsoncatalogo=sendUserAndGetJsonCatalogoFromUrl();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
         
            }
            catch (InterruptedException e) {
                return false;
            } 

            return true;
        }
        
        @Override
        protected void onPostExecute(final Boolean success) {
        	DeckTask deckt=new DeckTask();
        	savecatalogo(jsoncatalogo);
        	deckt.execute();
     
        }

        @Override
        protected void onCancelled() {

        }

    } 
    
   
   
   public class myCatalogoTask extends AsyncTask<Void, Void, Boolean> {
   	
   	String jsonmycatalogo = "";
   	
       @Override
       protected Boolean doInBackground(Void... params) {
           // TODO: attempt authentication against a network service.

           try {
           	try {
					jsonmycatalogo=sendUserAndGetJsonMyCatalogoFromUrl();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
           	
        
           }
           catch (InterruptedException e) {
               return false;
           } 

           return true;
       }
       
       @Override
       protected void onPostExecute(final Boolean success) {

       	savemycatalogo(jsonmycatalogo);
    
       }

       @Override
       protected void onCancelled() {

       }

   } 
   	

   public String sendUserAndGetJsonCatalogoFromUrl() throws JSONException, InterruptedException {
        
    	String json = null;
    	String url = "http://shadowduel.altervista.org/app/app_user_catalogue.php";	


	 
        try {

  			httpClientd = new DefaultHttpClient();
  			httpPostd = new HttpPost(url);
  			

  		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
  		    nameValuePairs.add(new BasicNameValuePair("username", username));
  		    nameValuePairs.add(new BasicNameValuePair("password", password));

  		    httpPostd.setEntity(new UrlEncodedFormEntity(nameValuePairs));

  		    // Execute HTTP Post Request
  		    HttpResponse httpResponse = httpClientd.execute(httpPostd);
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
 
 
 
 public String sendUserAndGetJsonMyCatalogoFromUrl() throws JSONException, InterruptedException {
     
 	String json = null;
 	String url = "http://shadowduel.altervista.org/app/app_user_catalogue.php";	


	 
     try {

			httpClientd = new DefaultHttpClient();
			httpPostd = new HttpPost(url);
			

		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		    nameValuePairs.add(new BasicNameValuePair("username", username));
		    nameValuePairs.add(new BasicNameValuePair("password", password));

		    httpPostd.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		    // Execute HTTP Post Request
		    HttpResponse httpResponse = httpClientd.execute(httpPostd);
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
    public String sendUserAndGetJsonFromUrl() throws JSONException, InterruptedException {
        
    	String json = null;
    	String url = "http://shadowduel.altervista.org/app/app_user_deck.php";	


	 
        try {

  			httpClientd = new DefaultHttpClient();
  			httpPostd = new HttpPost(url);
  			

  		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
  		    nameValuePairs.add(new BasicNameValuePair("username", username));
  		    nameValuePairs.add(new BasicNameValuePair("password", password));

  		    httpPostd.setEntity(new UrlEncodedFormEntity(nameValuePairs));

  		    // Execute HTTP Post Request
  		    HttpResponse httpResponse = httpClientd.execute(httpPostd);
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
    
    
    
    
    
    
    
	@Override
	public void onResume() {
		super.onResume();
		
		addListenerOnButtonLogin();
		addListenerOnButtonRegister();

}

	@Override
	public void onDestroy() {
		getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		player.stop();
		super.onDestroy();
		
		
		
		
}
	
	
	
	
}
