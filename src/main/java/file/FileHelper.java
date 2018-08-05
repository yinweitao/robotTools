package file;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Help to read, write or find files.
 * @author Zhang Rui
 *
 */
public class FileHelper {
	private static final String DEFAULT_ENCODING_STRING = "UTF-8";
	
	// readers
	
	/**
	 * Read .txt or .gzip/.gz files with UTF-8 encoding.
	 * @param filePath
	 * @return buffered reader
	 * @throws IOException
	 */
	public static BufferedReader readFile(String filePath) throws IOException{
		return readFile(filePath, DEFAULT_ENCODING_STRING);
	}
	
	/**
	 * Read .txt or .gzip/.gz files with designated encoding.
	 * @param filePath
	 * @param encodingString, e.g. "utf-8"
	 * @return buffered reader
	 * @throws IOException
	 */
	public static BufferedReader readFile(String filePath, String encodingString) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		InputStreamReader isr;
		if (filePath.endsWith(".gz") || filePath.endsWith(".zip"))
			isr = new InputStreamReader(new GZIPInputStream(fis), encodingString);
		else 
			isr = new InputStreamReader(fis, encodingString);
		return new BufferedReader(isr);
	}
	
	// writers
	
	/**
	 * Write .txt .gzip/.gz files with UTF-8 encoding at the beginning of the file.
	 * @param filePath
	 * @return buffered writer
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static BufferedWriter writeFile(String filePath) throws IOException{
		return writeFile(filePath, DEFAULT_ENCODING_STRING, false);
	}
	
	/**
	 * Write .txt .gzip/.gz files with designated encoding at the beginning of the file.
	 * @param filePath
	 * @param encodingString
	 * @return buffered writer
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static BufferedWriter writeFile(String filePath, String encodingString) throws IOException{
		return writeFile(filePath, encodingString, false);
	}
	
	/**
	 * Write .txt .gzip/.gz files with UTF-8 encoding, rewriting or appending.
	 * @param filePath
	 * @param append set true to append or false to overwrite
	 * @return buffered writer
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static BufferedWriter writeFile(String filePath, boolean append) throws IOException{
		return writeFile(filePath, DEFAULT_ENCODING_STRING, append);
	}
	
	/**
	 * Write .txt .gzip/.gz files with UTF-8 encoding, rewriting or appending.
	 * @param filePath
	 * @param encodingString 
	 * @param append set true to append or false to overwrite
	 * @return buffered writer
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static BufferedWriter writeFile(String filePath, String encodingString, boolean append) throws IOException{
		CreateDirByPath(filePath);
		FileOutputStream fos = new FileOutputStream(filePath, append);
		OutputStreamWriter osw;
		if (filePath.endsWith(".gz") || filePath.endsWith(".zip"))
			osw = new OutputStreamWriter(new GZIPOutputStream(fos), encodingString);
		else 
			osw = new OutputStreamWriter(fos, encodingString);
		return new BufferedWriter(osw);
	}
	
	// delete files

	public static boolean deleteFile(String absoluteFilePath) {
		File file = new File(absoluteFilePath);
		return file.delete();
	}

	/*public static boolean deleteFiles(String dir, String filePattern) {
		File file = new File(dir);
		boolean success = true;
		if (file.isDirectory()) {
			String[] files = file.list(new FileAcceptor(filePattern));
			for (String filePath : files) {
				file = new File(dir + "/" + filePath);
				success = file.delete() & success;
			}
		}
		return success;
	}*/
	
	public static boolean deleteFiles(String dirString){
		File dir = new File(dirString);
		if (dir.isDirectory()) {
			boolean rtn = true;
			for(File file : dir.listFiles())
				rtn &= file.delete();
			return rtn;
		}
		return false;
	}
	
	// create files
	
	public static boolean createFile(String filePath) throws IOException {
		return createFile(filePath, "", DEFAULT_ENCODING_STRING);
	}
	
	public static boolean createFile(String filePath, String encodingString)throws IOException {
		return createFile(filePath, "", encodingString);
	}
	
	private static boolean createFile(String filePath, String text,String encodingString) throws IOException {
		String directory = getDirectory(filePath);
		createDirectory(directory);
		boolean isCreated = false;
		Writer out = new OutputStreamWriter(new FileOutputStream(filePath, false), encodingString);
		File file = new File(filePath);
		isCreated = file.createNewFile();
		out.write(text);
		out.close();

		return isCreated;
	}
	
	// get and create directory
	
	public static String getDirectory(String filePath) {
		File file = new File(filePath);
		return getDirectory(file);
	}
	
	private static String getDirectory(File file) {
		String directory = "";
		if (file.isFile()) {
			String fullFilePath = file.getAbsolutePath();
			String fileName = file.getName();
			directory = fullFilePath.substring(0, fullFilePath.length() - fileName.length());
		} 
		else {
			if (file.isDirectory())
				directory = file.getAbsolutePath();
		}
		return directory;
	}
	
	public static boolean createDirectory(String directory) throws IOException {
		boolean isCreated = false;
		File file = new File(directory);
		if (!file.exists())
			isCreated = file.mkdirs();
		return isCreated;
	}
	
	// get folders
	
	/**
	 * Get all file names matching given pattern in one folder.
	 * @param dir
	 * @param filePattern e.g. (.*?).zip
	 * @return the list of file names, not including the directory path
	 */
	/*public static List<String> getFileAbbreviatedNames(String dir, String filePattern) {
		List<String> fileAbbreviatedNames = new ArrayList<String>();
		File file = new File(dir);
		if (file.isDirectory()) {
			String[] files = file.list(new FileAcceptor(filePattern));
			for (String filePath : files)
				fileAbbreviatedNames.add(filePath);
		}
		Collections.sort(fileAbbreviatedNames);
		return fileAbbreviatedNames;
	}*/
	
	/**
	 * Get all file names in one folder.
	 * @param dir
	 * @return the list of file names, not including the directory path
	 */
	public static List<String> getFileAbbreviateNames(String dir) {
		List<String> fileAbbreviatedNames = new ArrayList<String>();
		File file = new File(dir);
		if (file.isDirectory()) {
			String[] files = file.list();
			for (String filePath : files)
				fileAbbreviatedNames.add(filePath);
		}
		Collections.sort(fileAbbreviatedNames);
		return fileAbbreviatedNames;
	}
	
	// checker
	
	/**
	 * Check if a file exist.
	 * @param filePath
	 * @return true if exist, or false if not exist
	 */
	public static boolean isFileExist(String filePath){
		try {
			return (new File(filePath).exists());
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void CreateDirByPath(String path) throws IOException{
		if(!FileHelper.isFileExist(path)){
			int last = path.lastIndexOf("/");
			String dir = path.substring(0, last);
			FileHelper.createDirectory(dir);
		}
	};
	
	public static String getFileContent(String path) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = FileHelper.readFile(path);
		String line;
		while((line = br.readLine()) != null){
			sb.append(line + "\n");
		}
		br.close();
		return sb.toString();
	}
}
