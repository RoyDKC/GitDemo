package PayLoad;

public class Paloader {

	public static String payload(String name, String phone_number)
	{
	
		String payload = "{\r\n"
				+ "    \"location\": {\r\n"
				+ "        \"lat\": -38.383494,\r\n"
				+ "        \"lng\": 33.427362\r\n"
				+ "    },\r\n"
				+ "    \"accuracy\": 50,\r\n"
				+ "    \"name\": \""+name+"\",\r\n"
				+ "    \"phone_number\": \""+phone_number+"\",\r\n"
				+ "    \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "    \"types\": [\r\n"
				+ "        \"shoe park\",\r\n"
				+ "        \"shop\"\r\n"
				+ "    ],\r\n"
				+ "    \"website\": \"http://google.com\",\r\n"
				+ "    \"language\": \"French-IN\"\r\n"
				+ "}";
		return payload;
	}
	
	public static String delpayload(String placeid)
	{
	
		String delpayload = "{\r\n"
				+ "    \"place_id\": \""+placeid+"\"\r\n"
				+ "}";
		return delpayload;
	}
	
	
}
