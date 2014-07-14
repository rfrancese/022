package com.example.shadowduel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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



import android.os.AsyncTask;

public class Deck {
	public String intero=null;
	public Integer[] deck={0,11,2,3,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,50};
	public ArrayList<Card> Deck;
	public ArrayList<Card> currDeck;
	private String connPath="http://shadowduel.altervista.org";
	private String connPathalt="http://shadowduel.netne.net";
	private Random rand;
	private int LENGHT;
	private int currlenght;
	private ArrayList<Card> hand;
	private Card provacard= new Card(1, 1, "drago", 3, 2,"acqua");

	public static JSONArray jsArray = null;
	public HttpPost httpPost;
	public DefaultHttpClient httpClient;
	public int receive=0;
    public String username;
    DeckTask ult;
	
	
	
	
	public Deck(String username){
    	connPath=connPathalt;

		Deck=new ArrayList<Card>();
		currDeck=new ArrayList<Card>();
		hand = new ArrayList<Card>();
		intero=username;
		this.username=username;
        ult = new DeckTask();
        ult.execute();	

}
	


	
//	public Deck(int[] deck){
//		deckprova=deck;
//		lettura dati da database e inserimento in Deck
//	
//	}

	public Integer[] getDeckid(){
		return deck;
	}
	
	public ArrayList<Card> getDeck(){
		return currDeck;
	}
	public ArrayList<Card> getFullDeck(){
		return Deck;
	}
	public Card pickRandomCard(){
		Card card;
		currlenght=currDeck.size();
		int c;
		if(currlenght>0){
		rand= new Random();
		c=rand.nextInt(currlenght);
		card=currDeck.remove(c);
		return card;}else{
			return null;
		}
	}
	
	public ArrayList<Card> pickRandomHand(){
//		int c;
//		rand= new Random();
//		currlenght=currDeck.size();
//		if(currlenght>4){
//		for(int i=0;i<5;i++){
//			c=rand.nextInt(currlenght);
//			hand.add(currDeck.remove(c));
//			//decklenght-1
//		}
//		return hand;
//		}else{
//			if(currlenght>0){
//				for(int i=0;i<currlenght;i++){
//					
//					c=rand.nextInt(currlenght);
//					hand.add(currDeck.remove(c));
//				}
//			return hand;
//		}
//			else{
//			return null;
//		}
//	}
		ArrayList<Card> a = new ArrayList<Card>();
		a.add(provacard);
		a.add(provacard);
		a.add(provacard);
		a.add(provacard);
		a.add(provacard);

		return a;
		
	}
	public String provajson(){
		//return deck[0];
		return intero;
	}
    
public class DeckTask extends AsyncTask<Void, Void, Boolean> {
    	
    	String json = "";
    	
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
            	try {
					sendUserAndGetJsonFromUrl();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	// Simulate network access.
                //Thread.sleep(10);
            }
            catch (InterruptedException e) {
                return false;
            } 

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            //intero="Michem2";

        }

        @Override
        protected void onCancelled() {

        }

    }  
    public void sendUserAndGetJsonFromUrl() throws JSONException, InterruptedException {
        
    	String json = null;
    	String url = connPath+"/app/app_user_deck.php";	


	 
        try {

  			httpClient = new DefaultHttpClient();
  			httpPost = new HttpPost(url);
  			

  		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
  		    nameValuePairs.add(new BasicNameValuePair("username", username));
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
    	jsArray = new JSONArray(json);
    	fetchJSONuser(jsArray);

    }
    public void fetchJSONuser(JSONArray jsonArray){

      	try {
      	   for (int i = 0; i < jsonArray.length(); i++) {
      		   
      	       deck[i] = jsonArray.getJSONObject(i).getInt("c_"+(i+1));
      	       intero = jsonArray.getJSONObject(i).getString("name");

      	       Deck.add(new Card(1, 1,
      	    		   jsonArray.getJSONObject(i).getString("name") , 
      	    		   jsonArray.getJSONObject(i).getInt("attack"),
      	    		   jsonArray.getJSONObject(i).getInt("defense"),
      	    		 jsonArray.getJSONObject(i).getString("element")
      	    		   ));
      	    }
      	   currDeck=Deck;
   		LENGHT=deck.length;
		currlenght=LENGHT;


      	} catch (Exception e) {
      	     e.printStackTrace();
      	}
      	
}
	
}
