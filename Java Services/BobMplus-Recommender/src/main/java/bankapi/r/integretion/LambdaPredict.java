package bankapi.r.integretion;

import java.io.File;
import java.io.IOException;

public class LambdaPredict {

	public String lamdaPrediction() throws IOException {
		File f = new File(
				"C:\\\\Work\\\\BIG_DATA\\\\BankOfBaroda\\\\BoB_hackathon_workspace\\\\R_Predicting_Lambda\\BOB_Lamda_Prediction_Input.R");
		if (f.exists() && !f.isDirectory()) {

			System.out.println("File exists ");
		}

		System.out.println("predicting the  lambda ");

		Runtime.getRuntime().exec(
				"C:\\Program Files\\R\\R-3.4.2\\bin\\x64\\RScript C:\\\\Work\\\\BIG_DATA\\\\BankOfBaroda\\\\BoB_hackathon_workspace\\\\R_Predicting_Lambda\\BOB_Lamda_Prediction_Input.R");

		System.out.println(" lambda predicited  ");

		return null;

	}

	/*
	 * public static void main(String[] args) throws IOException { // TODO
	 * Auto-generated method stub
	 * 
	 * File f = new File(
	 * "C:\\\\Work\\\\BIG_DATA\\\\BankOfBaroda\\\\BoB_hackathon_workspace\\\\R_Predicting_Lambda\\BOB_Lamda_Prediction_Input.R"
	 * ); if (f.exists() && !f.isDirectory()) {
	 * 
	 * System.out.println("File exists "); }
	 * 
	 * System.out.println("predicting the  lambda ");
	 * 
	 * Runtime.getRuntime().exec(
	 * "C:\\Program Files\\R\\R-3.4.2\\bin\\x64\\RScript C:\\\\Work\\\\BIG_DATA\\\\BankOfBaroda\\\\BoB_hackathon_workspace\\\\R_Predicting_Lambda\\BOB_Lamda_Prediction_Input.R"
	 * );
	 * 
	 * System.out.println(" lambda predicited  ");
	 * 
	 * }
	 */
}