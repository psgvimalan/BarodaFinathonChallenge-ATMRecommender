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

import bankapi.r.integretion.DecisionTreePredict;

@Path("/rService")
public class RServices {

	@POST
	@Path("/recommender/")
	public Response recommender(@QueryParam("atmList") String atmList) throws IOException, InterruptedException {
		System.out.println("request received" + atmList);

		String src_file_name = "C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_DT_Recommend\\DT_predict_this.csv";
		String target_file_name = "C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_DT_Recommend\\DT_predict_output.csv";

		File dest_file = new File(target_file_name);
		File f = new File(src_file_name);
		f.delete();
		FileWriter writer = new FileWriter(src_file_name);
		writer.write(atmList);
		writer.close();

		
		DecisionTreePredict decisionTreePredict = new DecisionTreePredict();

		decisionTreePredict.decisionTreePredict();
		/*if (!dest_file.exists()) {
			System.out.println("File not found");
			Thread.sleep(5000);
		}*/
		 try{Thread.sleep(6000);}
		 catch(InterruptedException e){System.out.println(e);}
		 
		String content = new String(Files.readAllBytes(Paths.get(target_file_name)));
		/*StringBuffer sb=new StringBuffer("[");
		String aa[]=content.split("\n");
		for (String atmR:aa)
		{
			String atmRecord[]=atmR.split(",");
			sb=sb.append("\"id\":"+atmRecord[atmRecord.length]+"");
			for (String singleR:atmRecord)
			{
				System.out.print(singleR);
				sb=sb.append("")
	
			}			System.out.println();
			
		}
		System.out.println("\n\n\n" );
*/
		System.out.println("\n\n\n" + content);
//String s = "[{\"id\":1,\"Name\":\"ASDF MH\",\"address\":\"Address\"},{\"id\":1,\"Name\":\"ASDF MH\",\"address\":\"Address\"},{\"id\":1,\"Name\":\"ASDF MH\",\"address\":\"Address\"}]";
		return Response.status(200).entity(content).build();

	}

	
	public static void main(String a[]) throws IOException, InterruptedException {
		RServices r = new RServices();
		//r.waitTimePrediction("43040,1,1,57.5,2,3.6\n3077,1,1,66.6,3,3.6");
		r.recommender("4304,1,1,57.5,2,3.6\n3077,1,1,66.6,3,3.6\n43041,1,1,38.8,12,3.6\n43042,1,1,70.6,13,3.6\n43043,1,1,38.0,20,3.6\n43044,1,1,55.7,6,3.6\n43045,1,1,59.5,5,3.6\n43046,1,1,45.9,7,3.6\n43047,1,1,59.2,9,3.6");

	}

}