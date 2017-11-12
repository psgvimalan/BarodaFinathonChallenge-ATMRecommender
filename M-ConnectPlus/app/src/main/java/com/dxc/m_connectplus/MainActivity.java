package com.dxc.m_connectplus;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    ProgressDialog progressDialog;

    Button btn_atmrecomender;
    LinearLayout atmlin;
    TextView txt_currentlctn,txt_atmrecomender;
    ImageView btn_refresh;
    ListView listView;
    private static CustomAdapter adapter;

    //Location listner
    final String TAG = "GPS";
    private long UPDATE_INTERVAL = 2 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btn_atmrecomender = (Button) findViewById(R.id.btn_atmrecomender);
        atmlin = (LinearLayout) findViewById(R.id.atmlist);
        atmlin.setVisibility(View.GONE);
        txt_currentlctn = (TextView) findViewById(R.id.latlng);
        txt_atmrecomender = (TextView) findViewById(R.id.txt_atmrecomender);
        txt_atmrecomender.setVisibility(View.GONE);
        btn_refresh = (ImageView) findViewById(R.id.btn_refresh);
        listView =(ListView)findViewById(R.id.listView);



        // txt_currentlctn.setText("Latitude  : "+gps.getLatitude()+"\nLongitude  : "+gps.getLongitude());
        txt_currentlctn.setText("Latitude  : 18.9220 \nLongitude  : 72.8347\nPlace : Gateway of India");

       /* locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (android.location.LocationListener) this);*/
        btn_atmrecomender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new TestServiceCall().execute();
            }
        });
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, MainActivity.class);
                startActivity(a);
                finish();
            }
        });

    }

    String readLineT;
    List<ATM> atmList =null;




    private class TestServiceCall extends AsyncTask<String, String, String> {

        public void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Submitting..");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {

                 HttpClient client = new HttpClient();
                client.getParams().setParameter("http.useragent", "Apache-HttpClient/4.1.1 (java 1.5)");
                client.getParams().setParameter("Accept-Encoding", "gzip,deflate");
                client.getParams().setParameter("Content-Type", "application/json");
                client.getParams().setParameter("Connection", "Keep-Alive");

               BufferedReader br = null;
                String readLine;
                PostMethod method = new PostMethod("http://10.50.6.173:8082/BobMplus/ATMRecommender?latitude=8.9220&longitude=72.8347");
              //  PostMethod method = new PostMethod("http://localhost:8082/api/ATMRecommender?latitude=8.9220&longitude=72.8347");
              //  method.setParameter("latitude","8.9220");
                //method.setParameter("longitude","72.8347");
                int returnCode = client.executeMethod(method);

                if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
                    System.err.println("The Post method is not implemented by this URI");
                    // still consume the response body
                    method.getResponseBodyAsString();
                } else {
                    br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));

                    readLineT = new String();

                    while (((readLine = br.readLine()) != null)) {
                        System.err.println(readLine);
                        readLineT += readLine;
                    }
                    System.err.println(readLineT);

                }

                // readLineT = "[{\"id\":\"4304\",\"Name\":\"Kanjurmarg    \",\"Branch_code\":\"KANJUR\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com\",\"latitude\":\"19.128601\",\"longitude\":\"72.9255764\",\"Area\":\"Kanjurmarg    \",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\",\"distance\":\"57.5 km\",\"time\":\"2 hours 47 mins\"},{\"id\":\"3077\",\"Name\":\"Thakur Village   \",\"Branch_code\":\"THAVIL\",\"address\":\"Kv Sagar, Shop No - 20 - 23, Thakur Village, Kandivali\",\"latitude\":\"19.2102959\",\"longitude\":\"72.8742569\",\"Area\":\"Thakur Village   \",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\",\"distance\":\"66.6 km\",\"time\":\"3 hours 17 mins\"},{\"id\":\"43040\",\"Name\":\"Kanjurmarg    0\",\"Branch_code\":\"KANJUR0\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com0\",\"latitude\":\"18.943584\",\"longitude\":\"72.822871\",\"Area\":\"Kanjurmarg    0\",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\",\"distance\":\"36.6 km\",\"time\":\"2 hours 29 mins\"},{\"id\":\"43041\",\"Name\":\"Kanjurmarg    1\",\"Branch_code\":\"KANJUR1\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com1\",\"latitude\":\"18.982747\",\"longitude\":\"72.808965\",\"Area\":\"Kanjurmarg    1\",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\",\"distance\":\"39.0 km\",\"time\":\"2 hours 36 mins\"},{\"id\":\"43042\",\"Name\":\"Kanjurmarg    2\",\"Branch_code\":\"KANJUR2\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com2\",\"latitude\":\"19.212879\",\"longitude\":\"72.838993\",\"Area\":\"Kanjurmarg    2\",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\",\"distance\":\"70.6 km\",\"time\":\"3 hours 27 mins\"},{\"id\":\"43043\",\"Name\":\"Kanjurmarg    3\",\"Branch_code\":\"KANJUR3\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com3\",\"latitude\":\"18.954285\",\"longitude\":\"72.812774\",\"Area\":\"Kanjurmarg    3\",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\",\"distance\":\"38.0 km\",\"time\":\"2 hours 29 mins\"}]";
              //  readLineT="[{'id':'1','atmId':'4304','Name':'Kanjurmarg    ','address':'Wing No 2, Unit No 2 A, Hanuman Silk Mills Com'},{'id':'2','atmId':'3077','Name':'Thakur Village   ','address':'Kv Sagar, Shop No - 20 - 23, Thakur Village, Kandivali'},{'id':'3','atmId':'43040','Name':'Kanjurmarg0','address':'Wing No 2, Unit No 2 A, Hanuman Silk Mills Com0'},{'id':'4','atmId':'43042','Name':'Kanjurmarg2','address':'Wing No 2, Unit No 2 A, Hanuman Silk Mills Com2'},{'id':'5','atmId':'43044','Name':'Kanjurmarg4','address':'Wing No 2, Unit No 2 A, Hanuman Silk Mills Com4'},{'id':'6','atmId':'43045','Name':'Kanjurmarg5','address':'Wing No 2, Unit No 2 A, Hanuman Silk Mills Com5'},{'id':'7','atmId':'43046','Name':'Kanjurmarg6','address':'Wing No 2, Unit No 2 A, Hanuman Silk Mills Com6'}]";
                //ATM[] atm = new Gson().fromJson(readLineT.toString(), ATM[].class);
               String fnlStr=readLineT.toString().substring(1,readLineT.length()-3)+"]";
                System.err.println("fnlStr::"+fnlStr);

                 ATM[] atm = new Gson().fromJson(fnlStr, ATM[].class);
                System.err.println("atm.length::"+atm.length);
                atmList= new ArrayList<ATM>();
                atmList = Arrays.asList(atm);
            }
            catch (Exception e) {

                Log.e("ErrorReg", e.toString());


            } finally {
                progressDialog.dismiss();
            }


            return null;
        }

        public void onPostExecute(String params) {
            progressDialog.dismiss();
            try {
                //atmlin.setVisibility(View.VISIBLE);
                btn_atmrecomender.setVisibility(View.GONE);
                txt_atmrecomender.setVisibility(View.VISIBLE);
                txt_atmrecomender.setText("Recommended ATMs for users location");
                adapter = new CustomAdapter( atmList, getApplicationContext());

                listView.setAdapter(adapter);
            } catch (Exception e) {
                AlertDialog.Builder alertBulider = new AlertDialog.Builder(MainActivity.this);
                alertBulider.setTitle("Alert");
                alertBulider.setCancelable(false)
                        .setMessage("Something went wrong....")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.cancel();

                            }
                        });
                AlertDialog alert = alertBulider.create();
                alert.show();
            }

        }
    }



}