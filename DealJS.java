package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.*;

public class DealJS {
	DealJS() throws IOException, JSONException ,ArrayIndexOutOfBoundsException{
	
	}
	DealJS(String Currency) throws IOException, JSONException ,ArrayIndexOutOfBoundsException{
		currencyvariable = Currency;
		currency = json.getJSONObject("quotes").get(Currency);
	}
	//set variables
	public JSONObject json = readJsonFromUrl("http://www.apilayer.net/api/live?access_key=01213833cc7e4704d2d2c9f97055f6b2&format=1");
	public Object currency = json.getJSONObject("quotes").get("USDTWD");
	String currencyvariable;
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	//set json method 
	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
	void printAPI(){
		System.out.println(currency.toString());
	}
	void setCurrency(String Currency){
		currencyvariable = Currency;
	}
	String getCurrency(){
		return currencyvariable;
	}
	String getRate(){
		currency = json.getJSONObject("quotes").get(currencyvariable);
		return currency.toString();
	}
}





