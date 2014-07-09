package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Ths FileUtil class is used to centralize common file functions such as
 * reading from a file or checking the file name.
 * 
 * @author siddhartha
 */
public class FileUtil {

	/**
	 * method to scanner of a given file path.
	 * 
	 * @param inFile
	 *            String.
	 * @return scanner for a given file.
	 */
	public static Scanner readFile(String inFile) {
		FileInputStream fis = null;
		Scanner scanner = null;
		final Logger log = Logger.getLogger("FileUtil");
		if (FileUtil.isEmpty(inFile)) {
			log.info("Given File Path is empty ");
			return null;
		}
		try {
			fis = new FileInputStream(inFile);
			scanner = new Scanner(fis);
		} catch (FileNotFoundException e) {
			log.info(e.getMessage());
		}
		return scanner;
	}

	public static boolean isEmpty(String str) {
		if ((str == null) || (str.trim().length() == 0)) {
			return true;
		}
		return false;
	}
}
