package main.com.myapp.service;

import java.util.List;
import java.util.Map;

import main.com.myapp.bean.CompanyDetails;

/*
 * @Author: Siddhartha Mukherjee
 * @Description: interface methods for calculation of company share price
 */
public interface CalculateSharePriceService {
	public List<Map<String, String>> findMaxSharePriceCompany(String filePath);

	public void printDeatailsOfMaxSharePriceCompany(

			List<Map<String, String>> companies);

	public List<CompanyDetails> getCompaniesOfMaxSharePrice(
			List<Map<String, String>> companyDetails);

}
