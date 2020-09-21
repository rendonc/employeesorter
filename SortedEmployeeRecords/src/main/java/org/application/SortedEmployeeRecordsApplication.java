package org.application;

import java.io.File;

import java.io.IOException;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.List;

import org.application.conf.AppConfig;
import org.application.employee.Employee;
import org.application.employee.EmployeeOrder;
import org.application.employee.EmployeeSorter;
import org.application.exception.InvalidFileFormatException;
import org.application.exception.InvalidRecordFormat;
import org.application.io.EmployeeFileReader;
import org.application.io.EmployeeFileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SortedEmployeeRecordsApplication implements ApplicationRunner {

	private static Logger log = LoggerFactory.getLogger(SortedEmployeeRecordsApplication.class);

	/*
	 * Spring boot configuration based on the file ./employee.properties
	 */
	@Autowired
	private AppConfig config;

	public static void main(String[] args) {
		new SpringApplicationBuilder(SortedEmployeeRecordsApplication.class).web(WebApplicationType.NONE).run(args);
	}

	/*
	 * Program's execution entry method
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {

		String inputFile = config.getInputFile().trim();

		String outputFile = config.getOutputFile().trim();
		
		EmployeeOrder order = getEmployeeOrder();
		
		try {

			if (inputFile == null || inputFile.isEmpty()) {
				throw new InvalidParameterException("Input file name is null or empty");
			}

			if (outputFile == null || outputFile.isEmpty()) {
				throw new InvalidParameterException("Output file name is null or empty");
			}
			
			if (order == null) {
				throw new InvalidParameterException("Invalid employee order criteria");
			}

			File in = new File(inputFile);

			File out = new File(outputFile);

			List<Employee> employees = readEmployees(in);

			if (employees.isEmpty()) {
				log.warn("The employee input file is empty, no data to process");
				return;
			}

			sortEmployees(employees, order);

			writeEmployees(out, employees);

		} catch (Exception e) {
			log.error("Error while executing SortedEmployeeRecordsApplication", e);
		}

	}

	private EmployeeOrder getEmployeeOrder() {
		int intOrder=Integer.parseInt(config.getSort());
		
		if(EmployeeOrder.isValidValue(intOrder)) {
			return EmployeeOrder.valueOf(intOrder);
		}
			return null;
		
	}

	private List<Employee> readEmployees(File in)
			throws InvalidFileFormatException, IOException, InvalidRecordFormat, ParseException {

		EmployeeFileReader reader = new EmployeeFileReader();

		List<Employee> employees = reader.read(in);
		return employees;
	}

	private void writeEmployees(File out, List<Employee> employees) throws IOException {

		try {
			if (!out.exists()) {
				log.info("Creating directory: {}", out.getParent());
				File dirs = new File(out.getParent());
				dirs.mkdirs();
			}
			if (out.createNewFile()) {
				log.info("File created: {}", out.getAbsoluteFile());
			} else {
				log.warn("File already exists: {}", out.getAbsoluteFile());
			}
		} catch (IOException e) {
			log.error("Error while creating output file: " + out.getAbsoluteFile());
			throw e;
		}

		EmployeeFileWriter writer = new EmployeeFileWriter();

		writer.write(employees, out);
	}

	private void sortEmployees(List<Employee> employees, EmployeeOrder order) {
		EmployeeSorter sorter = new EmployeeSorter();

		sorter.sort(employees, order);
	}
}
