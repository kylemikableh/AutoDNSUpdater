package org.kylem.autoip;

import java.util.ArrayList;

public class IPUpdater implements Runnable
{
	private ArrayList<String> _listOfDomains;
	
	public IPUpdater() 
	{
		_listOfDomains = new ArrayList<String>();
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
			Thread.sleep(60000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
