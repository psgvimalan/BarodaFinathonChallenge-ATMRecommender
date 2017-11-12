package bankapi.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.squareup.okhttp.OkHttpClient;

public class GoogMatrixRequestJava {

	    private static final String API_KEY = "AIzaSyDGILiqAGP-1gj0gju2iL8GQFT7Gogdw3w";

	  OkHttpClient client = new OkHttpClient();


	  public static void main(String[] args) throws IOException {
		  URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC|Seattle&destinations=San+Francisco|Victoria+BC&key=" + API_KEY);
		  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		  conn.setRequestMethod("GET");
		  String line, outputString = "";
		  BufferedReader reader = new BufferedReader(
		  new InputStreamReader(conn.getInputStream()));
		  while ((line = reader.readLine()) != null) {
		       outputString += line;
		  }
		  System.out.println(outputString);
	  }
	}
