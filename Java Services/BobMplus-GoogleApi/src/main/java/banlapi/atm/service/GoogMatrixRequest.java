package banlapi.atm.service;

import java.io.IOException;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class GoogMatrixRequest {

	//Need to generate API-KEY from google site
	    private static final String API_KEY = "AIzaSyAd14oXVg3ULdL94aJAqadut4ravD83dvw";

	  OkHttpClient client = new OkHttpClient();

	  public String run(String url) throws IOException {
	    Request request = new Request.Builder()
	        .url(url)
	        .build();

	    Response response = client.newCall(request).execute();
	    return response.body().string();
	  }

	  public static String getDistance(String source,String destinations) throws IOException {
		  System.out.println("destinations:"+destinations);
		    GoogMatrixRequest request = new GoogMatrixRequest();
		   // String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=18.9220,72.8347&destinations=19.0913,72.91335&language=en-EN&travelMode:DRIVING&key=" + API_KEY;
		    String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+source+"&destinations="+destinations+"&language=en-EN&travelMode:DRIVING&key=" + API_KEY;

			return request.run(url_request);
		    
		  }
	  

	}
