package com.deepkeepx.jar.utils.common;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * This class is about network utils.
 *
 * @author deepkeepx
 * @version 1.0.0
 */
public class NetworkUtils {
	
	/**
	 * Get the local ip address
	 *
	 * @return a local ip address or null
	 */
	public static String getLocalIp() {
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface networkInterface = interfaces.nextElement();
				if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
					continue;
				}
				Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress inetAddress = addresses.nextElement();
					if (inetAddress.isSiteLocalAddress()) {
						return inetAddress.getHostAddress();
					}
				}
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
}