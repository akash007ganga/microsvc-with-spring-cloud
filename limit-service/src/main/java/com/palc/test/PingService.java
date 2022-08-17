package com.palc.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class PingService {
	public static void main(String[] args) {
		HttpURLConnection httpConn = null;
		int statusCode = -1;
		try {
			String scnServiceUri	= "http://"+"localhost"+":"+"7448";
			String context			= "/pingadapter";
			URL url = new URL(scnServiceUri+context);
			//log.info("updateKernelStatusMap >> ADAPTER >> Requesting URI = "+url);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setConnectTimeout(5000);
			httpConn.setReadTimeout(5000);
			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("Accept", "application/json");
			httpConn.connect();
			statusCode = httpConn.getResponseCode();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			httpConn.disconnect();
		}
		
		System.out.println("done. statusCode[" + statusCode + "]");
	}
}
