package bankapi.r.integretion;

import java.io.File;
import java.io.IOException;

public class LambdaTrain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		File f = new File("C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_Predicting_Lambda\\BOB_Lambda_Predicition_Training.R");
		if(f.exists() && !f.isDirectory()) { 

			
			
		System.out.println("File exists ");
		}
		
		
		
		System.out.println("Training the model");
		Runtime.getRuntime().exec("C:\\Program Files\\R\\R-3.4.2\\bin\\x64\\RScript C:\\Work\\BIG_DATA\\BankOfBaroda\\BoB_hackathon_workspace\\R_Predicting_Lambda\\BOB_Lambda_Predicition_Training.R") ;
		System.out.println(" Model trained and stored ");
	}

}
