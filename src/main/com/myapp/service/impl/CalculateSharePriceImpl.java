package main.com.myapp.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.com.myapp.bean.CompanyDetails;
import main.com.myapp.service.CalculateSharePriceService;

/*
 * @Author: Siddhartha Mukherjee
 * @Description: implementation of service interface of max share price
 */
public class CalculateSharePriceImpl implements CalculateSharePriceService {

	/**
	 * method to populate a list of each company with highest share value.
	 * 
	 * @param filePath
	 *            file path of csv file.
	 * @return array of list of company details of maximum share price.
	 */
	@Override
	public List<Map<String, String>> findMaxSharePriceCompany(String filePath) {
		// "test" is file name in args[0] pass this in program argument
		// URL url = Main.class.getResource(filePath);// reading demo file path
		// from
		// same location where class
		// exist
		List<Map<String, String>> companySharePriceDetails = new ArrayList<Map<String, String>>();
		File input = new File(filePath);
		try {
			// Below line is object of Bufferreader to read the file line by
			// line
			BufferedReader reader = new BufferedReader(new FileReader(input));
			int count = 0;
			Map<String, String> companySharePrice = new HashMap<String, String>();
			Map<String, String> companyYearMonth = new HashMap<String, String>();
			int company_max = 0;
			List<String> companyNames = new ArrayList<String>();

			try {
				while (reader.ready()) {

					if (count == 0) {
						String firstLine = reader.readLine();
						String[] lineData = firstLine.split(",");// splitting
																	// the line
																	// data with
																	// comma so
																	// that we
																	// can get
																	// array of
																	// header

						for (String header : lineData) {
							if ((header.toLowerCase().indexOf("year") != -1)
									|| (header.toLowerCase().indexOf("month") != -1)) {
							} else {
								companySharePrice.put(header,
										String.valueOf(company_max));
								companyNames.add(header);

							}
						}

					} else {
						String line = reader.readLine();
						String[] lineData = line.split(",");// splitting the
															// line data with
															// comma so that we
															// can get array of
															// each line
						String year = "";
						String month = "";

						for (int i = 0; i < lineData.length; i++) {
							if (i == 0) {
								year = lineData[i];// seperate the year
							} else if (i == 1) {
								month = lineData[i];// seperate the month
							} else {

								String companyshare = lineData[i];// seperate
																	// the
																	// company
																	// price
								Integer company_share_value = Integer
										.valueOf(companyshare);
								String company = companyNames.get(i - 2);
								if (Integer.valueOf(companySharePrice
										.get(company)) < company_share_value) {
									company_max = company_share_value;
									companySharePrice.put(company,
											String.valueOf(company_max));
									companyYearMonth.put(company, year + ","
											+ month);
								}

							}

						}
					}
					count++;
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			companySharePriceDetails.add(companySharePrice);
			companySharePriceDetails.add(companyYearMonth);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return companySharePriceDetails;
	}

	/**
	 * method to print a list of each company details with highest share value
	 * with year and month month and year.
	 * 
	 * @param list
	 *            of company details.
	 */
	@Override
	public void printDeatailsOfMaxSharePriceCompany(
			List<Map<String, String>> companyDetails) {
		Map<String, String> companySharePrice = companyDetails.get(0);
		Map<String, String> companyYearMonth = companyDetails.get(1);
		Set<String> companyNames = companySharePrice.keySet();

		for (String header : companyNames) {
			System.out.println("max of share price of " + header + "="
					+ companySharePrice.get(header) + " "
					+ companyYearMonth.get(header));
		}
	}

	/*
	 * get all the company with their highest share price as List
	 */
	@Override
	public List<CompanyDetails> getCompaniesOfMaxSharePrice(
			List<Map<String, String>> companyDetails) {
		Map<String, String> companySharePrice = companyDetails.get(0);
		Map<String, String> companyYearMonth = companyDetails.get(1);
		Set<String> companyNames = companySharePrice.keySet();
		List<CompanyDetails> companies = new ArrayList<CompanyDetails>();
		for (String header : companyNames) {

			System.out.println("max of share price of " + header + "="
					+ companySharePrice.get(header) + " "
					+ companyYearMonth.get(header));
			CompanyDetails company = new CompanyDetails();
			company.setName(header);
			company.setPrice((String) companySharePrice.get(header));
			String yearMonthWithComma = companyYearMonth.get(header);
			String yearMonth[] = yearMonthWithComma.split(",");
			String year = yearMonth[0];
			String month = yearMonth[1];
			company.setYear(year);
			company.setMonth(month);
			companies.add(company);
		}
		return companies;
	}
}
