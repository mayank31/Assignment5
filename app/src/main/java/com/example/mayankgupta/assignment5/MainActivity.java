package com.example.mayankgupta.assignment5;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


TextView value;
    Button download;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        value=(TextView)findViewById(R.id.name);
        if (savedInstanceState != null) {
          title=  savedInstanceState.get("value").toString();
value.setText(title);
        }
        download=(Button)findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new about().execute();
             //   value.setText(responseStr);
            }
        });

    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("value",value.getText().toString());
    }
    public class about extends AsyncTask<String,String,Void>
    {
        HttpEntity resEntity;
        public void onPreExecute()

        {
            super.onPreExecute();

        }
        @Override
        protected Void doInBackground(String... arg)
        {

            try{

             //   imageURL = new URL("https://www.iiitd.ac.in/about");
                String url="https://www.iiitd.ac.in/about";
                org.jsoup.nodes.Document document = Jsoup.connect(url).get();
                System.out.println(document);
                title = document.body().text();
              /*  HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
                connection.setDoInput(true);
                connection.connect();
                String postReceiverUrl=imageURL.toString();
                List<NameValuePair> params = new ArrayList<NameValuePair>();

                //       Log.v(TAG, "postURL: " + postReceiverUrl);
                HttpClient httpClient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(postReceiverUrl);
                httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse response = httpClient.execute(httpPost);
                resEntity = response.getEntity();

                if (resEntity != null) {
                    responseStr = EntityUtils.toString(resEntity).trim();

                    Log.v(TAG, "Response: " + responseStr);
                    System.out.println(responseStr);
                }
                else
                {
                    responseStr = EntityUtils.toString(resEntity).trim();

                    Log.v(TAG, "Response: " + responseStr);
                }*/
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            value.setText( title);
    
        }
    }
}
