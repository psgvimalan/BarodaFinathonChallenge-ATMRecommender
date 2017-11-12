package bankapi.atm.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;

import bankapi.atm.ATM;

public class BOBAtmApiServiceforR {

	private static String IP_ADDRESS="10.50.6.173:8090";
	//private static String IP_ADDRESS="localhost:8090";


	public String lamdaPrediction() {

		HttpClient client = new HttpClient();

		BufferedReader br = null;
		PostMethod method = null;
		method = new PostMethod("http://"+IP_ADDRESS+"/BobMplus/rest/rService/arrivalRatePrediction/");
		try {
			int returnCode = client.executeMethod(method);

			if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
				System.err.println("The Post method is not implemented by this URI");
				// still consume the response body
				method.getResponseBodyAsString();
			} else {
				br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
				String readLine;
				StringBuffer readLineT = new StringBuffer();

				while (((readLine = br.readLine()) != null)) {
					// System.err.println(readLine);
					readLineT = readLineT.append(readLine);
				}
				System.out.println("Lamda Prediction status:"+readLineT);

				return readLineT.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		} finally {
			method.releaseConnection();
			if (br != null)
				try {
					br.close();
				} catch (Exception fe) {
				}
		}
		return null;
	}

	public String waitingTime() {

		HttpClient client = new HttpClient();

		BufferedReader br = null;
		PostMethod method = null;

		method = new PostMethod("http://"+IP_ADDRESS+"/BobMplus/rest/rService/queueingModelWaitTimePrediction/");

		try {
			int returnCode = client.executeMethod(method);

			if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
				System.err.println("The Post method is not implemented by this URI");
				// still consume the response body
				method.getResponseBodyAsString();
			} else {
				br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
				String readLine;
				StringBuffer readLineT = new StringBuffer();

				while (((readLine = br.readLine()) != null)) {
					// System.err.println(readLine);
					readLineT = readLineT.append(readLine);
				}
			 System.out.println("wating time prediction:"+readLineT);

				return readLineT.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		} finally {
			method.releaseConnection();
			if (br != null)
				try {
					br.close();
				} catch (Exception fe) {
				}
		}
		return null;
	}
}
