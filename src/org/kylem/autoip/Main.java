/**
 * 
 */
package org.kylem.autoip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Kyle Mikolajczyk
 *
 */
public class Main 
{
	public static void main(String[] args) 
	{
		IPUpdater ipUpdater = new IPUpdater();
		Thread t1 = new Thread(ipUpdater);
		t1.start();
		try 
		{
			System.out.println(getIp());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
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
