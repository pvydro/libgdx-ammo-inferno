import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		try {
			createList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void createList() throws IOException {
		FileInputStream fstream = new FileInputStream("assets/dirs.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		
		String strLine;
		StringBuilder finalSb = new StringBuilder("");

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
		  System.out.println (strLine);
		  finalSb.append("\"" + strLine + "\",");
		}
		
		
		
		System.out.println(finalSb);

		//Close the input stream
		br.close();
	}
	
}
