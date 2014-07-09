package com.myapp.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import junit.framework.TestCase;
import main.com.myapp.bean.CompanyDetails;
import main.com.myapp.service.CalculateSharePriceService;
import main.com.myapp.service.impl.CalculateSharePriceImpl;

import org.junit.Test;

import util.FileUtil;

/**
 * JUnit Test class for CompanyServiceImpl
 * 
 * @author Siddhartha
 * 
 */
public class CompanyServiceImplTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Test for FindMaxShareCompany
	 */
	@Test
	public void testFindMaxShareCompany() {
		String filePath = null;
		CalculateSharePriceService companyService = new CalculateSharePriceImpl();
		Properties p = new Properties();
		try {
			try {
				p.load(new FileReader(new File(CompanyServiceImplTest.class
						.getResource("file.properties").toURI())));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO Exception " + e.getMessage());
		}
		assertNotNull(companyService.findMaxSharePriceCompany(p
				.getProperty("validPath")));
		assertNull(companyService.findMaxSharePriceCompany(filePath));
		List<Map<String, String>> maxCompanyDetails = companyService
				.findMaxSharePriceCompany(p.getProperty("validPath"));
		List<CompanyDetails> maxData = companyService
				.getCompaniesOfMaxSharePrice(maxCompanyDetails);
		assertNotNull(maxData);
		List<CompanyDetails> dummyData = createMaxCompanyObj();
		for (int i = 0; i < dummyData.size(); i++) {

			assertTrue(matchCompany(maxData.get(i), dummyData.get(i)));
		}

		List<CompanyDetails> randomData = createRandomCompanyObj();
		for (int i = 0; i < maxData.size(); i++) {

			assertFalse(matchCompany(maxData.get(i), randomData.get(i)));
		}
		List<Map<String, String>> tmpCompany = companyService
				.findMaxSharePriceCompany(p.getProperty("inValidPath"));
		assertNull(tmpCompany);
	}

	/**
	 * Test for ReadFile
	 */
	@Test
	public void testReadFile() {
		Properties p = new Properties();
		try {
			try {
				p.load(new FileReader(new File(CompanyServiceImplTest.class
						.getResource("file.properties").toURI())));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO Exception " + e.getMessage());
		}

		String filename = null;
		assertNotNull(FileUtil.readFile(p.getProperty("validPath")));
		assertNull(FileUtil.readFile(filename));
	}

	/**
	 * Helper function to check the company objects equality
	 */
	private boolean matchCompany(CompanyDetails maxCompany,
			CompanyDetails dummyCompany) {
		if (maxCompany.getName().equals(dummyCompany.getName())
				&& maxCompany.getMonth().equals(dummyCompany.getMonth())
				&& maxCompany.getPrice() == dummyCompany.getPrice()
				&& maxCompany.getYear().equals(dummyCompany.getYear())) {
			return true;
		}
		return false;
	}

	/**
	 * Helper function to return the max company objects list
	 */
	public List<CompanyDetails> createMaxCompanyObj() {
		List<CompanyDetails> companies = new ArrayList<CompanyDetails>();

		companies.add(new CompanyDetails("Company-A", "2013", "Dec", "1100"));
		companies.add(new CompanyDetails("Company-B", "1991", "Apr", "1418"));
		companies.add(new CompanyDetails("Company-C", "1993", "Jun", "995"));
		companies.add(new CompanyDetails("Company-D", "2002", "Apr", "999"));
		companies.add(new CompanyDetails("Company-E", "1998", "Dec", "3000"));
		return companies;
	}

	// creating randon object of companydetails with different-2 data
	public List<CompanyDetails> createRandomCompanyObj() {
		List<CompanyDetails> companies = new ArrayList<CompanyDetails>();

		companies.add(new CompanyDetails("Company-A", "1992", "Apr", "751"));
		companies.add(new CompanyDetails("Company-B", "1993", "Jan", "522"));
		companies.add(new CompanyDetails("Company-C", "1997", "Feb", "807"));
		companies.add(new CompanyDetails("Company-D", "1995", "Feb", "902"));
		companies.add(new CompanyDetails("Company-E", "1998", "Feb", "891"));
		return companies;
	}

}
