package file;

import java.io.BufferedReader;
import java.io.IOException;

public class TestMerge {

	public static void main(String[] args) throws IOException {
		String url = "D:/sheet4.txt";
		BufferedReader readFile = FileHelper.readFile(url);
		String line;
		String value = "";
		String flag = "";
		while(null !=(line=readFile.readLine())) {
			value += flag + "'" + line + "'";
			flag = ",";
		}
		readFile.close();
		System.out.println(value);
	}


//	public static void main(String[] args) throws IOException {
//		String targetFile = "D:/target.xlsx";
//
//		String iteyePath = "D:/iteye.txt";
//		String csdnPath = "D:/csdnUserName.txt";
//
//		Map<String, String> iteyeMap = new HashMap<String,String>();
//		Map<String, String> csdnMap = new HashMap<String,String>();
//
//		BufferedReader readFile = FileHelper.readFile(iteyePath);
//		String line;
//		while(null !=(line=readFile.readLine())) {
//			String[] split = line.split("\t");
//			String name = split[0].replace("\"", "");
//			String email = split[1].replaceAll("\"", "");
//			iteyeMap.put(email, name);
//		}
//
//		readFile.close();
//
//		readFile = FileHelper.readFile(csdnPath);
//		while(null !=(line=readFile.readLine())) {
//			String[] split = line.split("\t");
//			String name = split[1].replace("\"", "");
//			String email = split[2].replaceAll("\"", "");
//			csdnMap.put(email, name);
//		}
//		readFile.close();
//
//		BufferedWriter writeFile = FileHelper.writeFile(targetFile);
//		Set<String> keySet = csdnMap.keySet();
//		for (String key : keySet) {
//			String name = csdnMap.get(key);
//			writeFile.append(name + "\t" + iteyeMap.get(key) + "\t" + key + "\n");
//		}
//		writeFile.close();
//	}
//
	
}
