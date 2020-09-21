package org.application.io;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.OutputStreamWriter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.application.employee.Employee;

public class EmployeeFileWriter {
	
	private DateFormat dateFormat;
	private Charset charset;

	public EmployeeFileWriter() {
		this.charset = StandardCharsets.US_ASCII;
		 this.dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);  
	}

	public void write(List<Employee> employees, File out) throws IOException {
		
		FileOutputStream outputStream = new FileOutputStream(out);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, charset);
		BufferedWriter bw = new BufferedWriter(outputStreamWriter);
		
		
		int i=1;
		for (Employee e : employees) {
			bw.write(Integer.toString(i));
			newLine(bw);
			
			writeField(bw,e.getFirstName());
			bw.write(" ");
			writeField(bw,e.getLastName());
			
			bw.write(", (");
			writeField(bw,e.getStartDate());
			bw.write("),");
			
			newLine(bw);
			
			writeField(bw,e.getAddress1());
			bw.write(", ");
			writeField(bw,e.getAddress2());
			
			bw.write(",");
			
			newLine(bw);
			
			writeField(bw,e.getCity());
			bw.write(", ");
			writeField(bw,e.getState());
			bw.write(",");

			newLine(bw);
			
			writeField(bw,e.getCountry());
			bw.write(", ");
			writeField(bw,e.getZip());
			
			bw.newLine();
			
			i++;
			
		}
		bw.newLine();
		bw.write(".");
		bw.close();
	}

	private void writeField(BufferedWriter bw, Date date) throws IOException {
		if(date!=null) {
			bw.write(dateFormat.format(date));
		}else {
			bw.write("");
		}
	}

	private void writeField(BufferedWriter bw, String string) throws IOException {
		if(string!=null) {
			bw.write(string);
		}else {
			bw.write("");
		}
		
	}

	private void newLine(BufferedWriter bw) throws IOException {
		bw.newLine();
		bw.append("\t");
	}

}
