package banlapi.atm.service;

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

public class BOBAtmApiService {

	//private static String IP_ADDRESS="10.50.6.173:8090";
	private static String IP_ADDRESS="10.50.6.173:8090";

	public List<ATM> getAtms(String coordinates) {
		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.useragent", "Apache-HttpClient/4.1.1 (java 1.5)");
		client.getParams().setParameter("Accept-Encoding", "gzip,deflate");
		client.getParams().setParameter("Content-Type", "application/json");
		client.getParams().setParameter("Connection", "Keep-Alive");

		String latitude = null, type = null, longitude = null, radius = null, apikey = "384M9075I79182CG";
		if (coordinates != null) {
			latitude = "19.076";
			longitude = "72.8777";
			type = "ATM";
			radius = "5000";
		}
		BufferedReader br = null;

		PostMethod method = new PostMethod("http://104.211.176.248:8080/bob/bobuat/api/RadiusSearch?latitude="
				+ latitude + "&type=" + type + "&longitude=" + longitude + "&radius=" + radius);
		method.setRequestHeader("apikey", apikey);

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
					System.err.println(readLine);
					readLineT = readLineT.append(readLine);
				}
				System.out.println(readLineT);
				ATM[] atm = new Gson().fromJson(readLineT.toString(), ATM[].class);
				List<ATM> atmList = new ArrayList<ATM>();
				atmList = Arrays.asList(atm);
				List<ATM> list = new ArrayList<ATM>();
				list.addAll(atmList);
				String dest = "18.943584,72.822871|18.982747,72.808965|19.212879,72.838993|18.954285,72.812774|19.073212,72.854195|19.102512,72.845367|19.044218,72.855980|19.0779148,72.8321822";
				String[] dests = dest.split("\\|");
				// Mocking more ATMs based on actual BOB atms
				for (int i = 0; i <= 7; i++) {
					ATM a = new ATM();
					a.setId(atmList.get(0).getId() + i);
					a.setName(atmList.get(0).getName().trim() + i);
					a.setBranch_code(atmList.get(0).getBranch_code().trim() + i);
					a.setAddress(atmList.get(0).getAddress().trim() + i);
					a.setLatitude(dests[i].split(",")[0]);
					a.setLongitude(dests[i].split(",")[1]);
					a.setArea(atmList.get(0).getArea() + i);
					a.setCity(atmList.get(0).getCity());
					a.setDistrict(atmList.get(0).getDistrict());
					a.setState(atmList.get(0).getState());
					a.setArea(atmList.get(0).getArea() + i);
					list.add(a);
				}
			//	System.out.println("final ATM Size:" + atmList.size());
			//	String mockJson = new Gson().toJson(atmList);
				
				return list;
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
