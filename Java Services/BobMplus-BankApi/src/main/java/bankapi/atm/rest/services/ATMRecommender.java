package bankapi.atm.rest.services;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import bankapi.atm.ATM;
import banlapi.atm.service.BOBAtmApiService;

@Path("/atmRecommender")
public class ATMRecommender {



	@POST
	@Path("/atmsFromBank/")
	public Response bankApiToGetAtms(@QueryParam("coordinators") String coordinators) {

		String result = "Given Latitude and Logitude : " + coordinators;
		System.out.println("result:" + result);
		BOBAtmApiService bobAtmApi = new BOBAtmApiService();
		List<ATM> atmList = bobAtmApi.getAtms(coordinators);
	//	String mockJson = "[{\"id\":\"4304\",\"Name\":\"Kanjurmarg    \",\"Branch_code\":\"KANJUR\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com\",\"latitude\":\"19.128601\",\"longitude\":\"72.9255764\",\"Area\":\"Kanjurmarg    \",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\"},{\"id\":\"3077\",\"Name\":\"Thakur Village   \",\"Branch_code\":\"THAVIL\",\"address\":\"Kv Sagar, Shop No - 20 - 23, Thakur Village, Kandivali\",\"latitude\":\"19.2102959\",\"longitude\":\"72.8742569\",\"Area\":\"Thakur Village   \",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\"},{\"id\":\"43040\",\"Name\":\"Kanjurmarg0\",\"Branch_code\":\"KANJUR0\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com0\",\"latitude\":\"18.943584\",\"longitude\":\"72.822871\",\"Area\":\"Kanjurmarg    0\",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\"},{\"id\":\"43041\",\"Name\":\"Kanjurmarg1\",\"Branch_code\":\"KANJUR1\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com1\",\"latitude\":\"18.982747\",\"longitude\":\"72.808965\",\"Area\":\"Kanjurmarg    1\",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\"},{\"id\":\"43042\",\"Name\":\"Kanjurmarg2\",\"Branch_code\":\"KANJUR2\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com2\",\"latitude\":\"19.212879\",\"longitude\":\"72.838993\",\"Area\":\"Kanjurmarg    2\",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\"},{\"id\":\"43043\",\"Name\":\"Kanjurmarg3\",\"Branch_code\":\"KANJUR3\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com3\",\"latitude\":\"18.954285\",\"longitude\":\"72.812774\",\"Area\":\"Kanjurmarg    3\",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\"},{\"id\":\"43044\",\"Name\":\"Kanjurmarg4\",\"Branch_code\":\"KANJUR4\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com4\",\"latitude\":\"19.073212\",\"longitude\":\"72.854195\",\"Area\":\"Kanjurmarg    4\",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\"},{\"id\":\"43045\",\"Name\":\"Kanjurmarg5\",\"Branch_code\":\"KANJUR5\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com5\",\"latitude\":\"19.102512\",\"longitude\":\"72.845367\",\"Area\":\"Kanjurmarg    5\",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\"},{\"id\":\"43046\",\"Name\":\"Kanjurmarg6\",\"Branch_code\":\"KANJUR6\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com6\",\"latitude\":\"19.044218\",\"longitude\":\"72.855980\",\"Area\":\"Kanjurmarg    6\",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\"},{\"id\":\"43047\",\"Name\":\"Kanjurmarg7\",\"Branch_code\":\"KANJUR7\",\"address\":\"Wing No 2, Unit No 2 A, Hanuman Silk Mills Com7\",\"latitude\":\"19.0779148\",\"longitude\":\"72.8321822\",\"Area\":\"Kanjurmarg    7\",\"city\":\"Mumbai\",\"district\":\"MUMBAI SUBURBAN\",\"state\":\"MH\"}]";
		 return Response.status(200).entity(new
		 Gson().toJson(atmList)).build();
		//return Response.status(200).entity(mockJson).build();
	}

}