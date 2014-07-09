package main.com.myapp;

import java.util.List;
import java.util.Map;

import main.com.myapp.service.CalculateSharePriceService;
import main.com.myapp.service.impl.CalculateSharePriceImpl;

/*
 * @Author: Siddhartha Mukherjee
 * @Description:This class read the test file and get the data for highest share price for each company
 */
public class Main {
	// pass the test_shares_data.csv as argument to run the application
	public static void main(String[] args) {
		// "test" is file name in args[0] pass this in program argument
		if (args.length <= 0) {
			System.err.println("Invalid arguments please give the file name: "
					+ args.length);
			System.exit(1);
		}
		CalculateSharePriceService calculateSharePriceService = new CalculateSharePriceImpl();
		List<Map<String, String>> sharePriceWithCompanyDetails = calculateSharePriceService
				.findMaxSharePriceCompany(args[0]);// passing the file name as
													// first argument to program
													// argument
		calculateSharePriceService
				.printDeatailsOfMaxSharePriceCompany(sharePriceWithCompanyDetails);// print
																					// the
																					// details
																					// of
																					// company
																					// with
																					// highest
																					// share
																					// price

	}
}
