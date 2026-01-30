package com.deepkeepx.jar.utils.common;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class is used to handle directory operations.
 *
 * @author deepkeepx
 * @version 1.0.0
 */
public class DirectoryUtils {

	/**
	 * This method is used to create a directory.
	 *
	 * @param dirPath the directory path to be created
	 * @return true if the directory is created successfully, false otherwise
	 */
	public static boolean mk(String dirPath){
		try {
			File f = new File(dirPath);
			if (!f.getParentFile().exists()) {
                return f.getParentFile().mkdirs();
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to move a directory.
	 *
	 * @param originalDirPath the original directory path to be created
	 * @param targetDirPath the target directory path to be created
	 * @return true if the directory is created successfully, false otherwise
	 */
	public static boolean mv(String originalDirPath, String targetDirPath) {
		try {
			File f = new File(targetDirPath);
			if (!f.getParentFile().exists()) {
				if(!f.getParentFile().mkdirs()){
                    return false;
                };
			}
			Path sourceDirectory = Paths.get(originalDirPath);
			Path targetDirectory = Paths.get(targetDirPath);
			Files.move(sourceDirectory, targetDirectory);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to delete a directory.
	 *
	 * @param dirPath the directory path to be deleted
     * @return true if the directory is del successfully, false otherwise
	 */
	public static boolean del(String dirPath) {
        if (dirPath == null || dirPath.trim().isEmpty()) {
            return false;
        }
        File directory = new File(dirPath);
        if (isPathTraversal(dirPath)) { // Check path security to prevent path traversal attacks
            System.err.println("Dangerous path, there may be path traversal attacks: " + dirPath);
            return false;
        }
        if (!directory.exists()) {
            return true;
        }
        if (!directory.isDirectory()) {
            return directory.delete();
        }
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (!del(file.getPath())) {
                        return false;
                    }
                } else {
                    if (!file.delete()) {
                        System.err.println("Failed to delete file: " + file.getPath());
                        return false;
                    }
                }
            }
        }
        boolean dirDeleted = directory.delete();
        if (!dirDeleted) {
            System.err.println("Delete directory failed: " + directory.getPath());
        }
        return dirDeleted;
	}

	/**
	 * This method is used to get the size of a directory.
	 *
	 * @param directory the directory to be calculated
	 * @return the size of the directory in bytes
	 */
	public static long size(File directory) {
		long size = 0;
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        size += file.length();
                    } else {
                        size += size(file);
                    }
                }
            }
        }
		return size;
	}

    private static boolean isPathTraversal(String path) {
        return path.contains("../") || path.contains("..\\") || path.startsWith("..") || path.contains("/..") || path.contains("\\..");
    }

}