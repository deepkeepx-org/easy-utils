package com.deepkeepx.jar.utils.file;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * This class is about file manipulation.
 *
 * @author deepkeepx
 * @version 1.0.0
 */
public class FileUtils {

	/**
	 * Used to create an empty file.
	 *
	 * @param filePath the path of the file to be created
	 * @return true if the file is created successfully, false otherwise
	 */
	public static Boolean createEmptyFile(String filePath){
		try {
			return createEmptyFile(filePath, false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Used to create an empty file.
	 *
	 * @param filePath the path of the file to be created
	 * @param isCover whether to overwrite the file
	 * @return true if the file is created successfully, false otherwise
	 */
	public static Boolean createEmptyFile(String filePath, boolean isCover){
		try {
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    return false;
                }
            }
			if(file.exists()){
				if(isCover){
					return file.delete() && file.createNewFile();
				} else {
					System.out.println("File already exists!");
					return false;
				}
			} else {
				return file.createNewFile();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Used to get the content of a file.
	 *
	 * @param filePath the path of the file to be read
	 * @return the content of the file if it exists, an empty string otherwise
	 */
	public static String getFileContent(String filePath){
        BufferedReader reader = null;
		try {
			StringBuilder contentBuilder = new StringBuilder();
			reader = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = reader.readLine()) != null) {
				contentBuilder.append(line);
			}
			return contentBuilder.toString();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return "";
		} finally {
            if(reader != null){
                try {
                    reader.close();
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
	}

	/**
	 * Used to write content to a file.
	 *
	 * @param filePath the path of the file to be created
	 * @param content the content to be written to the file
	 * @return true if the content is written to the file successfully, false otherwise
	 */
	public static Boolean writerFileContent(String filePath, String content){
		try {
			if(createEmptyFile(filePath, true)){
				FileWriter writer = new FileWriter(filePath);
				writer.write(content);
				writer.close();
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Used to get the name of a file.
	 *
	 * @param filePath the path of the file to be read
	 * @return the name of the file if it exists, an empty string otherwise
	 */
	public static String getName(String filePath){
		try {
			File file = new File(filePath);
			return file.getName();
		} catch (Exception e){
            System.out.println(e.getMessage());
			return "";
		}
	}

	/**
	 * Used to get the type of file.
	 *
	 * @param filePath the path of the file to be read
	 * @return the type of the file if it exists, an empty string otherwise
	 */
	public static String getType(String filePath){
		return filePath.substring(filePath.lastIndexOf(".")).substring(1).toLowerCase(Locale.ROOT);
	}

	/**
	 * Used to get the name of a file without the extension.
	 *
	 * @param filePath the path of the file to be read
	 * @return the name of the file without the extension if it exists, an empty string otherwise
	 */
	public static String getOnlyName(String filePath){
		String name = filePath.substring(0, filePath.lastIndexOf("."));
		if(name.contains("/")){
			name = name.substring(name.lastIndexOf("/") + 1);
		}
		if(name.contains("\\")){
			name = name.substring(name.lastIndexOf("\\") + 1);
		}
		return name;
	}

	/**
	 * Used to delete a file.
	 *
	 * @param filePath the path of the file to be deleted
	 * @return true if the file is deleted successfully, false otherwise
	 */
	public static boolean delete(String filePath){
		try {
			File file = new File(filePath);
			if(file.exists()){
				return file.delete();
			} else {
				return false;
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Used to delete a directory.
	 *
	 * @param filePath the path of the directory to be deleted
	 * @return str
	 */
	public static String readContentToString(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				contentBuilder.append(line).append(System.lineSeparator());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return "";
		}
		return contentBuilder.toString();
	}

	/**
	 * Used to check if a file exists.
	 *
	 * @param filePath the path of the file to be checked
	 * @return true if the file exists, false otherwise
	 */
	public static boolean isExists(String filePath) {
		File f = new File(filePath);
		return f.exists();
	}
	
	/**
	 * Used to download a file.
	 *
	 * @param saveDirPath the directory to save the downloaded file
	 * @param fileUrl the URL of the file to be downloaded
	 * @return name of the downloaded file or an empty string if the download fails
	 */
	public static String download(String saveDirPath, String fileUrl) {
		return "";
	}

    /**
     * 将指定的文件列表压缩到zip文件中
     *
     * @param filePaths 要压缩的文件路径列表
     * @param zipFilePath 目标zip文件的路径
     * @return 压缩操作是否成功，成功返回true，失败返回false
     */
    public static boolean zip(List<String> filePaths, String zipFilePath) {
        try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(Paths.get(zipFilePath)))) {
            for (String filePath : filePaths) {
                File fileToZip = new File(filePath);

                // 检查文件是否存在
                if (!fileToZip.exists()) {
                    System.out.println("File " + filePath + " does not exist.");
                    continue;
                }

                // 创建zip条目，可以添加相对路径
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                // 将文件内容复制到zip输出流
                try (FileInputStream fis = new FileInputStream(fileToZip)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zipOut.write(buffer, 0, length);
                    }
                }

                zipOut.closeEntry();
                System.out.println("File " + filePath + " has been zipped.");
            }
            return true; // 成功完成压缩
        } catch (IOException e) {
            System.out.println("Error during zip operation: " + e.getMessage());
            return false;
        }
    }


}