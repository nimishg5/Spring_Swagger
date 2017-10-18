package guru.springframework.operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JsonResponse {
	public String getResponse() throws IOException{
		URL oracle = new URL("https://jsonplaceholder.typicode.com/photos");
		URLConnection yc = oracle.openConnection();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(
                             yc.getInputStream()));
		String inputLine;
		StringBuilder jsonData = new StringBuilder();
		while ((inputLine = in.readLine()) != null){
			System.out.println(inputLine);
			jsonData.append(inputLine);
		}
		in.close();
		return jsonData.toString();
	}
}
