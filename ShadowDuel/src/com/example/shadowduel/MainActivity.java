package com.example.shadowduel;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	
	   // Valori di email e password inseriti dall'utente
	   private String username;
	   private String password;

	   // GUI
	   private EditText usernameView;
	   private EditText passwordView;
	   
		public URL url;
	    public URLConnection conn;
	    public OutputStreamWriter wr;
	    public String data;
	    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
       
        usernameView=(EditText)this.findViewById(R.id.username);
        passwordView=(EditText)this.findViewById(R.id.password);
        
        addListenerOnButton();
    }
    
    public void addListenerOnButton() {
		 
		final Context context = this;
		Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
		buttonLogin.setOnClickListener(new  OnClickListener() {
				@Override
				public void onClick(View v) {
					
					
//
//					try {
//						url = new URL("http://shadowduel.altervista.org/app/app_connect_login.php");
//					} catch (MalformedURLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//
//					try {
//						data = URLEncoder.encode("username", "UTF-8") 
//						+ "=" + URLEncoder.encode(username, "UTF-8");
//						data += "&" + URLEncoder.encode("password", "UTF-8") 
//								+ "=" + URLEncoder.encode(password, "UTF-8");
//					} catch (UnsupportedEncodingException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//					try {
//						conn = url.openConnection();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} 
//					
//
//					try {
//						wr = new OutputStreamWriter(conn.getOutputStream());
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} 
//					try {
//						wr.write(data);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} 
//					try {
//						BufferedReader reader = new BufferedReader(new 
//						InputStreamReader(conn.getInputStream()));
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					            StringBuilder sb = new StringBuilder();
//		            String line = null;
//		            // Read Server Response
//		            while((line = reader.readLine()) != null)
//		            {
//		               sb.append(line);
//		               break;
//		            }
//		            return sb.toString();
//		         }catch(Exception e){
//		            return new String("Exception: " + e.getMessage());
//		         }
//		      }
//		   }
//					
				    if((usernameView.getText().toString()).equals("admin")){
				           Toast.makeText(MainActivity.this, "Login Successful",Toast.LENGTH_LONG).show();
							
							Intent intent = new Intent(context,ActivityMenu.class);
							startActivity(intent);
							
				          } else{
				           Toast.makeText(MainActivity.this, "Invalid Login",Toast.LENGTH_LONG).show();
				          }

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
