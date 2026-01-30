package com.deepkeepx.jar.utils.file;

import com.deepkeepx.jar.utils.common.DirectoryUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * This class is about image manipulation.
 *
 * @author deepkeepx
 * @version 1.0.0
 */
public class FileImageUtils {

	/**
	 * Convert the network image to file
	 *
	 * @param imageUrl the url of the network image
	 * @param savePath is the path of the file to be saved
	 * @return  true if the image is converted to file successfully, false otherwise
	 */
	public static Boolean networkPathToFile(String imageUrl, String savePath){
		return base64ToFile(networkPathToBase64(imageUrl), savePath);
	}
	
	/**
	 * Convert the network image to file
	 *
	 * @param imageUrl the url of the network image
	 * @param type is the type of the image, such as jpg, png, etc.
	 * @return  base64 string of the image
	 */
	public static String networkPathToBase64(String imageUrl, String type){
		return "data:image/" + type + ";base64," + networkPathToBase64(imageUrl);
	}
	
	/**
	 * Convert the network image to base64 string
	 *
	 * @param imageUrl the url of the network image
	 * @return the base64 string of the image
	 */
	public static String networkPathToBase64(String imageUrl){
		DataInputStream in = null;
		HttpURLConnection connection = null;
		String base64Str = null;
		try {
			URL url = new URL(imageUrl);
			connection = (HttpURLConnection) url.openConnection();
			in = new DataInputStream(connection.getInputStream());
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			Base64.Encoder encoder = Base64.getEncoder();
			base64Str = encoder.encodeToString(output.toByteArray());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(connection != null){
				connection.disconnect();
			}
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return base64Str;
	}

	/**
	 * Convert the base64 string to file
	 *
	 * @param base64 the base64 string of the image
	 * @param savePath is the path of the file to be saved
	 * @return  true if the base64 string is converted to file successfully, false otherwise
	 */
	public static Boolean base64ToFile(String base64, String savePath) {
		BufferedOutputStream out = null;
		FileOutputStream fos = null;
		try {
			byte[] bytes = Base64.getDecoder().decode(base64);
			File file = new File(savePath);
			fos = new FileOutputStream(file);
			out = new BufferedOutputStream(fos);
			out.write(bytes);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	/**
	 * Convert the local image to base64 string
	 *
	 * @param imagePath the path of the local image
	 * @return the base64 string of the image
	 */
	public static String localImageToBase64(String imagePath) {
		String base64Image = "";
		try {
			Path path = Paths.get(imagePath);
			byte[] imageBytes = Files.readAllBytes(path);
			byte[] base64Bytes = Base64.getEncoder().encode(imageBytes);
			base64Image = new String(base64Bytes);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return base64Image;
	}

	/**
	 * Write the content to IMG file
	 *
	 * @param data the content to be written to the file
	 * @param savePath the path of the file to be saved
	 * @return true if the content is written to the file successfully, false otherwise
	 */
	public static boolean writeByteToFile(byte[] data, String savePath) {
		if(data == null || data.length == 0){
			return false;
		} else {
			FileOutputStream fos = null;
			try {
                DirectoryUtils.mk(savePath);
				fos = new FileOutputStream(savePath);
				fos.write(data);
				fos.flush();
				return true;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return false;
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
	}

}