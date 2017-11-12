package bankapi.atm.rest.services;

import java.io.IOException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import banlapi.atm.service.GoogMatrixRequest;

@Path("/atmRecommender")
public class ATMRecommender {


	@POST
	@Path("/distanceAndDurationWithTrafic/")
	public Response googleApiToGetDistence(@QueryParam("source") String source,
			@QueryParam("destination") String destination) throws IOException {

		String result = "Given Latitude and Logitude : " + destination;
		System.out.println("result:" + result);
		return Response.status(200).entity(GoogMatrixRequest.getDistance(source, destination)).build();
	}
}