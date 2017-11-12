package bankapi.r.integretion;

import java.io.File;
import java.io.IOException;

public class WaitingTimePrediction {

	public String waitingTime() throws IOException {
		File f = new File(
				"C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_Predicting_Waiting_Time\\BOB_Waiting_Time_Predicition.R");
		if (f.exists() && !f.isDirectory()) {

			System.out.println("File exists ");
		}

		System.out.println("Calculating  the waiting Time  ");

		Runtime.getRuntime().exec(
				"C:\\Program Files\\R\\R-3.4.2\\bin\\x64\\RScript C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_Predicting_Waiting_Time\\BOB_Waiting_Time_Predicition.R");

		System.out.println(" Waiting Time Predicited ");
		return "Done";

	}

//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//
//		File f = new File(
//				"C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_Predicting_Waiting_Time\\BOB_Waiting_Time_Predicition.R");
//		if (f.exists() && !f.isDirectory()) {
//
//			System.out.println("File exists ");
//		}
//
//		System.out.println("Calculating  the waiting Time  ");
//
//		Runtime.getRuntime().exec(
//				"C:\\Program Files\\R\\R-3.4.2\\bin\\x64\\RScript C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_Predicting_Waiting_Time\\BOB_Waiting_Time_Predicition.R");
//
//		System.out.println(" Waiting Time Predicited ");
//
//	}
}