import java.io.IOException;

import org.json.JSONException;

public class TestDealJS {
	public static void main(String[] args) throws ArrayIndexOutOfBoundsException, JSONException, IOException{
		DealJS test = new DealJS("USDTWD") ;
		System.out.println(test.getRate());
		test.setCurrency("USDSEK");
		System.out.println(test.getRate());
		test.setCurrency("USDSOS");
		System.out.println(test.getRate());

	}
	
}
