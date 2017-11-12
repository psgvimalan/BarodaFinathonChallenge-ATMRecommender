package bankapi.atm.rest.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import bankapi.atm.AtmStatus;

@Path("/atmRecommender")
public class ATMRecommender {

	@POST
	@Path("/atmCashNoCashStatus/")
	public Response isCashAvailable(@QueryParam("atmIds") String atmIds) {

		String result = "Given atmId : " + atmIds;
		System.out.println("result:" + result);

		String atms[] = atmIds.split(",");
		StringBuffer sb = new StringBuffer("[");
		for (String atmId : atms) {
			sb = sb.append(
					"{\"atmid\":" + atmId + ",\"status\":" + AtmStatus.isCashAvailable(Integer.parseInt(atmId)) + "},");
		}
		String finalstr = sb.toString().substring(0, sb.length()-1 );
		System.out.println(finalstr);

		return Response.status(200).entity(finalstr+"]".toString()).build();
	}

}