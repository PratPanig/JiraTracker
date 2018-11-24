package com.prateek.jira.jira_issue_tracker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import junit.framework.Assert;

public class RestAPIInvokeService {
	
	public static final String username = "";
	public static final String password ="";	
	
	public static String InvokeRestGetCall(HashMap<String,String> inputmap){
		try {
			String credential= username+":"+password;
			String authStringEnc = "";
			URL url = new URL(inputmap.get("uristr"));
			System.out.println("GET: "+url);
			byte[] authEncBytes = Base64.encodeBase64(credential.getBytes());
			authStringEnc = new String(authEncBytes);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
		    conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
			if (conn.getResponseCode() == 200 || conn.getResponseCode() == 201) {
			 String output = null;
			 try (final InputStream is = conn.getInputStream()) {
				output = IOUtils.toString(is,"US-ASCII");
				conn.disconnect();
				return output;
			  }
			} else {
				System.err.println("Failed to get response for : "+ conn.getResponseCode());
				conn.disconnect();
				Assert.fail("Error Response Code : "
						+ conn.getResponseCode() + ",Error Response Message: "+conn.getResponseMessage()+" for "+inputmap.get("uristr"));
				return "ERROR-" + conn.getResponseCode();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "ERROR-EXCEPTION DUE TO IO";
		}
	}
	
	public static String InvokeRestPostCall(HashMap<String,String> inputmap){
			try {
				String credential= username+":"+password;		 
				URL url = new URL(inputmap.get("uristr"));
				System.out.println("POST: "+ url); 
				byte[] authEncBytes = Base64.encodeBase64(credential.getBytes());
				String authStringEnc = new String(authEncBytes);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setDoOutput(true);
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
	            conn.setRequestProperty("Content-Type", "application/json");
	            try (final OutputStream os = conn.getOutputStream()) {
					os.write(inputmap.get("payload").getBytes());
					os.flush();
				}
				if (conn.getResponseCode() == 200 || conn.getResponseCode() == 201) {
					String output = null;
					try (final InputStream is = conn.getInputStream()) {
						output = IOUtils.toString(is,"US-ASCII");
						conn.disconnect();
						System.out.println(output);
						return output;
					}
				} else {
					System.err.println("Failed to get response for : "+ conn.getResponseCode());
					conn.disconnect();
					Assert.fail("Error Response Code : "
							+ conn.getResponseCode() + ",Error Response Message: "+conn.getResponseMessage()+" for "+inputmap.get("uristr"));
					return "ERROR-" + conn.getResponseCode();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "ERROR-EXCEPTION";
			}
			
	}	
	
	public static String InvokeRestPutCall(HashMap<String,String> inputmap){
		try {
			String credential= username+":"+password;	 
			URL url = new URL(inputmap.get("uristr"));
			System.out.println("POST: "+ url); 
			byte[] authEncBytes = Base64.encodeBase64(credential.getBytes());
			String authStringEnc = new String(authEncBytes);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
            conn.setRequestProperty("Content-Type", "application/json");
            try (final OutputStream os = conn.getOutputStream()) {
				os.write(inputmap.get("payload").getBytes());
				os.flush();
			}
			if (conn.getResponseCode() == 200 || conn.getResponseCode() == 201) {
				String output = null;
				try (final InputStream is = conn.getInputStream()) {
					output = IOUtils.toString(is,"US-ASCII");
					conn.disconnect();
					return output;
				}
			} else {
				System.err.println("Failed to get response for : " + conn.getResponseCode());
				conn.disconnect();
				Assert.fail("Error Response Code : "
						+ conn.getResponseCode() + ",Error Response Message: "+conn.getResponseMessage()+" for "+inputmap.get("uristr"));
				return "ERROR-" + conn.getResponseCode();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR-EXCEPTION";
		}
	}
	public static String InvokeRestDeleteCall(HashMap<String,String> inputmap){
		try {
			String credential= username+":"+password;
			String authStringEnc = "";
			URL url = new URL(inputmap.get("uristr"));
			System.out.println("DELETE: "+url);
			byte[] authEncBytes = Base64.encodeBase64(credential.getBytes());
			authStringEnc = new String(authEncBytes);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Content-Type", "application/json");
		    conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
			if (conn.getResponseCode() == 204) {
				String output = null;
				try (final InputStream is = conn.getInputStream()) {
				output = IOUtils.toString(is,"US-ASCII");
				conn.disconnect();
				return output;
				}
			} else {
				System.err.println("Failed to get response for :"
						+ conn.getResponseCode());
				conn.disconnect();
				Assert.fail("Error Response Code : "
						+ conn.getResponseCode() + ",Error Response Message: "+conn.getResponseMessage()+" for "+inputmap.get("uristr"));
				return "ERROR-" + conn.getResponseCode();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "ERROR-EXCEPTION DUE TO IO";
		}
	}
}
