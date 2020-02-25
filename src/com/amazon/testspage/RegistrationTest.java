package com.amazon.testspage;

import org.testng.annotations.Test;

import com.amazon.pages.LoginPage;
import com.amazon.pages.RegistrationPage;
import com.amazon.pages.VerifyUserLoggedInPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class RegistrationTest {
 ChromeDriver driver;
 private static final Logger logger = LogManager.getLogger(RegistrationTest.class);
 LoginTest LT;
 RegistrationPage RP;

 VerifyUserLoggedInPage VPOne;

 @Test(dataProvider = "getdata")
 public void ScenarioOne(String fullnm, String phno, String eml, String pwd, String expstring)throws IOException, InterruptedException {

  logger.trace("Starting Test.");
  RegistrationTest.screencapture(driver, "./Screenshots/LoginPage.png");// screenshot code line
  RP.EnterUserDetails(fullnm, phno, eml, pwd);
  RegistrationTest.screencapture(driver, "./Screenshots/RegistrationDetailScreen.png"); // Screenshot code line
  // FileUtils.copyFile(source, new File("./Screenshots/EnteredDetails.png")); //
  // screenshot code line
  Thread.sleep(2000);
  RP.SubmitAction();
  RegistrationTest.screencapture(driver, "./Screenshots/DetailsSubmitedScreen.png");

  logger.trace("All details entered and submitted");

  String captchaText = "Enter the characters as they are shown in the image.";

  try {

   while (captchaText.equals(RP.CaptchTxtMatch())) {

    logger.trace("We are on captcha page");

    // prompt user to enter captcha
    String captchaVal1 = JOptionPane.showInputDialog("Please enter the captcha value : ");
    RP.EnterCaptcha(captchaVal1);
    RP.EnterPassOnly(pwd);
    RegistrationTest.screencapture(driver, "./Screenshots/CaptchaAndPassword.png");
    RP.SubmitAction();

    System.out.println("Captcha appear..");
   }

  } catch (Exception e) {
   System.out.println("Captcha didn't Appear");
  }

  // OnVerify mobile number page
  // Entering OTP by taking input from user by using input pop up box
  String OTPcal = JOptionPane.showInputDialog("Please enter the OTP : ");
  RP.EnterOTP(OTPcal);
  RegistrationTest.screencapture(driver, "./Screenshots/VerifyMobNumberPage.png");
  RP.HitCreate();

 }

 @Test(dataProvider = "getdata")
 public void ScenarioTwo(String fullnm, String phno, String eml, String pwd, String expstring) {
  logger.trace("Starting Test.");

  RP.EnterUserDetails(fullnm, phno, eml, pwd);

  RP.SubmitAction();

  logger.trace("All details entered and submitted");

  String captchaText = "Enter the characters as they are shown in the image.";

  try {

   while (captchaText.equals(RP.CaptchTxtMatch())) {

    logger.trace("We are on captcha page");

    // prompt user to enter captcha
    String captchaVal1 = JOptionPane.showInputDialog("Please enter the captcha value : ");
    RP.EnterCaptcha(captchaVal1);
    RP.EnterPassOnly(pwd);
    RegistrationTest.screencapture(driver, "./Screenshots/CaptchaAndPassword.png");
    RP.SubmitAction();

    System.out.println("Captcha appear..");
   }

  } catch (Exception e) {
   System.out.println("Captcha didn't Appear");
  }

  // click on change
  RP.ClickChange();

  // verify if name, mob no. and email are already filled
  RP.CheckPrefilledVals();
  Assert.assertEquals(RP.CheckPrefilledVals()[0], fullnm);
  Assert.assertEquals(RP.CheckPrefilledVals()[1], phno);
  Assert.assertEquals(RP.CheckPrefilledVals()[2], eml);

 }

 @Test(dataProvider = "getdata")
 public void ScenarioThree(String fullnm, String phno, String eml, String pwd, String expstring) throws InterruptedException {
  logger.trace("Starting Test.");

  RP.EnterUserDetails(fullnm, phno, eml, pwd);

  RP.SubmitAction();

  logger.trace("All details entered and submitted");

  String captchaText = "Enter the characters as they are shown in the image.";

  try {

   while (captchaText.equals(RP.CaptchTxtMatch())) {

    logger.trace("We are on captcha page");

    // prompt user to enter captcha
    String captchaVal1 = JOptionPane.showInputDialog("Please enter the captcha value : ");
    RP.EnterCaptcha(captchaVal1);
    RP.EnterPassOnly(pwd);
    RegistrationTest.screencapture(driver, "./Screenshots/CaptchaAndPassword.png");
    RP.SubmitAction();

    System.out.println("Captcha appear..");
   }

  } catch (Exception e) {
   System.out.println("Captcha didn't Appear");
  }
  
  RP.ClickResendOTP();
  Thread.sleep(4000);
  String SuccessMsg = "We've sent a new OTP by SMS.";
  Assert.assertEquals(RP.ChekSuccessMsg(), SuccessMsg);
 }

 
 @Test (dataProvider = "getdata")
 public void ScenarioFour(String fullnm, String phno, String eml, String pwd, String expstring) {
  logger.trace("Starting 4th Test.");

  RP.EnterUserDetails("", "", "", "");

  RP.SubmitAction();

  String BlankEmailMsg = "Enter your name";
  Assert.assertEquals(RP.CheckBlankEmailMsg(), BlankEmailMsg);

  String BlankMobMsg = "Enter your mobile number";
  Assert.assertEquals(RP.CheckBlankMobMsg(), BlankMobMsg);
  
  String BlankPassMsg = "Enter your password";
  Assert.assertEquals(RP.CheckBlankPassMsg(), BlankPassMsg);
 }
 
 @Test (dataProvider = "getdata")
 public void ScenarioFive(String fullnm, String phno, String eml, String pwd, String expstring) {
  logger.trace("Starting 4th Test.");

  RP.EnterUserDetails(fullnm, phno, "g.gaurav2812@gmail.com", pwd);

  RP.SubmitAction();
  
  String RegisEmailmsg = "Your provided Email g.gaurav2812@gmail.com has already been used. Please use another Email address.";
  
  Assert.assertEquals(RP.RegisEmail(), RegisEmailmsg);
  
 }
 
 @Test (dataProvider = "getdata")
 public void ScenarioSix(String fullnm, String phno, String eml, String pwd, String expstring) {
  logger.trace("Starting 6th Test.");

  RP.EnterUserDetails(fullnm, "7385502840", eml, pwd);

  RP.SubmitAction();
  
  String Regismobmsg = "You indicated you are a new customer, but an account already exists with the mobile number +91 7385502840";
  
  Assert.assertEquals(RP.RegisMob(), Regismobmsg);
  
 }
 
 @Test (dataProvider = "getdata")
 public void ScenarioSeven(String fullnm, String phno, String eml, String pwd, String expstring) {
  logger.trace("Starting 7th Test.");

  RP.EnterUserDetails(fullnm, "90115", eml, pwd);

  RP.SubmitAction();
  
  String captchaText = "Enter the characters as they are shown in the image.";
  try {

   while (captchaText.equals(RP.CaptchTxtMatch())) {

    logger.trace("We are on captcha page");

    // prompt user to enter captcha
    String captchaVal1 = JOptionPane.showInputDialog("Please enter the captcha value : ");
    RP.EnterCaptcha(captchaVal1);
    RP.EnterPassOnly(pwd);
    RegistrationTest.screencapture(driver, "./Screenshots/CaptchaAndPassword.png");
    RP.SubmitAction();

    System.out.println("Captcha appear..");
   }

  } catch (Exception e) {
   System.out.println("Captcha didn't Appear");
  }

  
  String InvMob = "Please enter a valid mobile phone number with area code.";
  
  Assert.assertEquals(RP.RegisEmail(), InvMob);
  
 }
 
 @BeforeMethod
 public void beforeMethod() {
  driver.get("https://www.amazon.in/");
  driver.manage().window().maximize();
  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

  RP = new RegistrationPage(driver);
  RP.ClickGotoLoginP();
  // RegistrationTest.screencapture(driver,"./Screenshots/HomePage.png");
  RP.CreateNewAccount();
 }

 @AfterMethod
 public void afterMethod() {

 }

 @DataProvider
 public Object[][] getdata() throws IOException {

  String currentDirectory = System.getProperty("user.dir");
  // System.out.println(currentDirectory);
  
  String datafile = currentDirectory + "\\src\\test\\resources\\utils\\Amazone.xlsx";
  System.out.println("Current Directory of test data:" + datafile);
  String sheetname = "RegistrationData";
  Object[][] myTestData = ReadExcel.readTestData(datafile, sheetname);

  return myTestData;
 }

 @BeforeClass
 public void beforeClass() throws IOException {
  System.setProperty("webdriver.chrome.driver",
    "C:\\Training\\Jar Files\\Drivers\\chromedriver.exe");
  driver = new ChromeDriver();
  /*
   * driver.get("https://www.amazon.in/"); driver.manage().window().maximize();
   * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
   * 
   * RP = new RegistrationPage(driver); RP.ClickGotoLoginP();
   * RegistrationTest.screencapture(driver,"./Screenshots/HomePage.png");
   * RP.CreateNewAccount();
   */

 }

 @AfterClass
 public void afterClass() {
  // driver.quit();
 }

 public static void screencapture(WebDriver driver, String fname) throws IOException {
  TakesScreenshot ts = (TakesScreenshot) driver;
  File source = ts.getScreenshotAs(OutputType.FILE);
  FileUtils.copyFile(source, new File(fname));

  // FileUtils.copyFile(source, new File("./Screenshots/HomePage.png"));

 }

}
