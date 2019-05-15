package com.test.awsLambdaTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

@Component
public class UpperCaseStringHandler implements Function<String, String> {

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Override
	public String apply(String t) {
		String line = this.readFromS3Location();
		System.out.println(line);
		this.writetoS3Location();
		line = this.readFromS3Location();
		System.out.println(line);
		return t.toUpperCase();
	}
	
	
	public String readFromS3Location() {
		Resource resource = resourceLoader.getResource("s3://com.test.dev/test.txt");
		String line = null;
		try {
			InputStream inputStream = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			line = br.readLine();
           
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
		
	}
	
	public void writetoS3Location() {
		Resource resource = resourceLoader.getResource("s3://com.test.dev/test.txt");
		try {
			WritableResource writableResource = (WritableResource) resource;
	        try (OutputStream outputStream = writableResource.getOutputStream()) {
	            outputStream.write("ABC XYZ 121345666".getBytes());
	        }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
