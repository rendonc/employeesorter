package org.application.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"file:./employee.properties"})
@ConfigurationProperties(prefix = "employee")
public class AppConfig {
	private String inputFile;
	public String getInputFile() {
		return inputFile;
	}
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	public String getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String orderBy) {
		this.sort = orderBy;
	}
	private String outputFile;
	private String sort;
}
