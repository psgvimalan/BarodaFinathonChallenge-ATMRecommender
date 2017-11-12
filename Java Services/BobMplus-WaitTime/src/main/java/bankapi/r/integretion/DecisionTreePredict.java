package bankapi.r.integretion;

import java.io.File;
import java.io.IOException;

public class DecisionTreePredict {
	
	public void decisionTreePredict() throws IOException
	{
		File f = new File(
				"C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_DT_Recommend\\BOB_DT_Predict.R");
		if (f.exists() && !f.isDirectory()) {

			System.out.println("File exists ");
		}

		System.out.println("prediciting the outcome");

		Runtime.getRuntime().exec(
				"C:\\Program Files\\R\\R-3.4.2\\bin\\x64\\RScript C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_DT_Recommend\\BOB_DT_Predict.R");

		System.out.println(" Created the output  ");
		
		
	}

	/*public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File f = new File(
				"C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_DT_Recommend\\BOB_DT_Predict.R");
		if (f.exists() && !f.isDirectory()) {

			System.out.println("File exists ");
		}

		System.out.println("prediciting the outcome");

		Runtime.getRuntime().exec(
				"C:\\Program Files\\R\\R-3.4.2\\bin\\x64\\RScript C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_DT_Recommend\\BOB_DT_Predict.R");

		System.out.println(" Created the output  ");

	}*/
}