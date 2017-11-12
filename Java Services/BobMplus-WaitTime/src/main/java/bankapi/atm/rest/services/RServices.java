package bankapi.atm.rest.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import bankapi.atm.service.BOBAtmApiServiceforR;
import bankapi.r.integretion.LambdaPredict;
import bankapi.r.integretion.WaitingTimePrediction;

@Path("/rService")
public class RServices {

	@POST
	@Path("/arrivalRatePrediction/")
	public Response arrivalRatePrediction() throws IOException {
		System.out.println("lamdaPrediction request received");

		LambdaPredict lambdaPredict = new LambdaPredict();
		lambdaPredict.lamdaPrediction();

		// System.out.println("\n\n\n" + content);

		return Response.status(200).entity("lamdaPrediction Done").build();

	}

	@POST
	@Path("/queueingModelWaitTimePrediction/")
	public Response waitingTime() throws IOException {
		System.out.println("waitingTime request received");

		WaitingTimePrediction waitingTimePrediction = new WaitingTimePrediction();
		waitingTimePrediction.waitingTime();

		return Response.status(200).entity("waitingTime predicted").build();

	}

	@POST
	@Path("/waitTimePrediction/")
	public Response waitTimePrediction(@QueryParam("atmList") String atmList) throws IOException, InterruptedException {
		System.out.println("getWaitingTime request received" + atmList);
		//atmList="43041,2017/11/10,fri,03";
		String src_file_name = "C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_Predicting_Lambda\\Predict_Lambda_for_this.csv";
		String target_file_name = "C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_Predicting_Waiting_Time\\predicited_wait_time.csv";

		File f = new File(src_file_name);
		File dest_file = new File(target_file_name);
		f.delete();
		FileWriter writer = new FileWriter(src_file_name);
		writer.write(atmList);
		writer.close();

		BOBAtmApiServiceforR waitingTime = new BOBAtmApiServiceforR();

		// Calling Lamda prediction
		waitingTime.lamdaPrediction();

		// Calling Waiting Time
		waitingTime.waitingTime();
		if (!dest_file.exists()) {
			System.out.println("File not found");
			Thread.sleep(5000);
		}
		String content = new String(Files.readAllBytes(Paths.get(target_file_name)));
		System.out.println("\n\n\n" + content);

		return Response.status(200).entity(content).build();

	}

	
	
	public static void main(String a[]) throws IOException, InterruptedException {
		RServices r = new RServices();
		//r.waitTimePrediction("43040,1,1,57.5,2,3.6\n3077,1,1,66.6,3,3.6");
	//	r.recommender("4304,1,1,57.5,2,3.6\n3077,1,1,66.6,3,3.6\n43041,1,1,38.8,12,3.6\n43042,1,1,70.6,13,3.6\n43043,1,1,38.0,20,3.6\n43044,1,1,55.7,6,3.6\n43045,1,1,59.5,5,3.6\n43046,1,1,45.9,7,3.6\n43047,1,1,59.2,9,3.6");

	}

}