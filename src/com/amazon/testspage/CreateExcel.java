package com.amazon.testspage;

//package net.codejava.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.chrome.ChromeDriver;
 
/**
 * A very simple program that writes some data to an Excel file
 * using the Apache POI library.
 * @author www.codejava.net
 *
 */
public class CreateExcel {
 
    public static void main(String[] args) throws IOException {
    	ChromeDriver driver;
    	//
    	 System.setProperty("webdriver.chrome.driver",
    			    "C:\\Training\\Jar Files\\Drivers\\chromedriver.exe");
    			  driver = new ChromeDriver();
    			  
    			  driver.manage().window().maximize();
    			  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    			  
    	
    	
    	//put intput box and take input and save in 
    	String captchaVal1 = JOptionPane.showInputDialog("Please enter the password value : ");
    	
    	// call encoder
    	Base64.Encoder enc = Base64.getEncoder();
    	
    	//Using encoder save the encoded script in String 'encoded'
    	String encoded = enc.encodeToString(captchaVal1.getBytes());
        System.out.println("encoded value is \t" + encoded);
    	
    	XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");
         
        Object[][] bookData = {
                {encoded}
                //"Kathy Serria", 79},
                //{"Effective Java", "Joshua Bloch", 36},
                //{"Clean Code", "Robert martin", 42},
                //{"Thinking in Java", "Bruce Eckel", 35},
                
        };
 
        int rowCount = 0;
         
        for (Object[] aBook : bookData) {
            Row row = sheet.createRow(++rowCount);
             
            int columnCount = 0;
             
            for (Object field : aBook) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
             
        }
         
         
        try (FileOutputStream outputStream = new FileOutputStream("JavaBooks.xlsx")) {
            workbook.write(outputStream);
        }
    }
 
}