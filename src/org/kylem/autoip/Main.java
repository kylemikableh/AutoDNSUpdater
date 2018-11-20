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
		
		//Here we add the domains to be checked
		ipUpdater.addDomain("soupisasalad.com");
		ipUpdater.addDomain("wedgewednesday.us");
		ipUpdater.addDomain("kylem.org");
		ipUpdater.addDomain("gompeicoin.com");
		
		t1.start();
		try 
		{
			System.out.println(IPUpdater.getIp());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
