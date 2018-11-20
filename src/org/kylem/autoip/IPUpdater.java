package org.kylem.autoip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;

public class IPUpdater implements Runnable
{
	private ArrayList<String> _listOfDomains; //All domain names to be updated
	private String _lastIP; //Last IP, checks to compair
	final int TIME_BETWEEN_CHECKS_IN_MILLIS = 60000; //Time in milliseconds between ip checks.
	final String AUTH_CODE = ""; //DO NOT PUBLISH
	
	public IPUpdater() 
	{
		_listOfDomains = new ArrayList<String>();
		try 
		{
			_lastIP = this.getIp();
		}
		catch (Exception e) 
		{
			System.out.println("Grabbing the IP threw an exception. Will check again in a minute.");
		}
	}
	
	/**
	 * 
	 * @param domain name of domain that will have DNS updated
	 */
	public void addDomain(String domain)
	{
		_listOfDomains.add(domain);
	}

	@Override
	public void run() 
	{
		try
		{
			System.out.println("Checking for IP Change.");
			Thread.sleep(TIME_BETWEEN_CHECKS_IN_MILLIS);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param domain returns domain info for a domain
	 * @throws MalformedURLException 
	 */
	private String getDNS(String domain) throws Exception
	{
		
		final String urlDNSAPI = "https://api.godaddy.com/v1/domains/" + domain + "/records";
		URL url = new URL(String.format(urlDNSAPI));
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		
		if (url.getUserInfo() != null) 
		{
		    String basicAuth = "Basic " + new String(Base64.getUrlEncoder().encode(url.getUserInfo().getBytes()));
		    connection.setRequestProperty("Authorization", AUTH_CODE);
		}
		InputStream in = connection.getInputStream();
		try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      JSONObject json = new JSONObject(jsonText);
		      return json;
		    } finally {
		      is.close();
		    }
		
		return null;
	}
	
	/**
	 * Taken from StackOverflow at https://stackoverflow.com/questions/2939218/getting-the-external-ip-address-in-java
	 * @return String of external IP computer is currently connected to.
	 * @throws Exception
	 */
	public static String getIp() throws Exception
	{
		URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try 
        {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } 
        finally 
        {
            if (in != null) 
            {
                try 
                {
                    in.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
