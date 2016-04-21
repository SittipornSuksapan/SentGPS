package mean.chan.mind.sendgps;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class FirstActivity extends AppCompatActivity {

    //Explicit
    private ListView listView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        listView = (ListView) findViewById(R.id.listView);

        //S
        syncJSON();


    } //main mathod

    @Override
    protected void onRestart() {
        super.onRestart();
        syncJSON();
    }

    private void syncJSON() {
        MySynJSON mySynJSON = new MySynJSON();
        mySynJSON.execute();


    }


    //Create Inder Class
    public class MySynJSON extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url("http://swiftcodingthai.com/watch/php_get_plate.php").build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();




            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        } //doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("21April", "JSON ==>" + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                String[] nameStrings = new String[jsonArray.length()];
                String[] latStrings = new String[jsonArray.length()];
                String[] lngStrings = new String[jsonArray.length()];


                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    nameStrings[i] = jsonObject.getString("Name");
                    latStrings[i] = jsonObject.getString("Lat");
                    lngStrings[i] = jsonObject.getString("Lng");

                }//for
                PlateAdepter plateAdepter = new PlateAdepter(FirstActivity.this, nameStrings);
                listView.setAdapter(plateAdepter);


            } catch (Exception e) {
                e.printStackTrace();
            }


        } //onPos
    } //MySynJSON class


    public void clickAddPlate(View view) {
        startActivity(new Intent(FirstActivity.this, MainActivity.class));
    }
} //main class
