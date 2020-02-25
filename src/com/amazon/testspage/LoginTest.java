package com.amazon.testspage;

import org.testng.annotations.Test;

import com.amazon.pages.LoginPage;
import com.amazon.pages.RegistrationPage;
import com.amazon.pages.VerifyUserLoggedInPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

//import javax.servlet.Registration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTest {
 ChromeDriver driver;
 RegistrationPage RP;
 LoginPage LP;
 VerifyUserLoggedInPage VP;
 private static final Logger logger = LogManager.getLogger(LoginTest.class);
 Base64.Decoder dec = Base64.getDecoder();
 


 
 
 @Test(dataProvider = "getdatalogin")
 public void ScenarioOne(String usrnm, String pswd, String expstring, String Invldemail, String InvlPass,
   String ValidPhnNo, String InvalidPhnNo) throws InterruptedException {

	//Decode data
	 String decoded = new String(dec.decode(pswd));
	 //System.out.println("decoded value is \t" + decoded);
	 
	 
  logger.trace("Starting Test.");
  LP.EnterUsername(usrnm);
  LP.ContinueAction();

  logger.trace("Username done landed on Next Page");
  LP.EnterPassword(decoded);
  LP.SubmitAction();

  logger.trace("Landed successfully on user home page");
  try {
   Alert alert = driver.switchTo().alert();
   alert.dismiss();

  } catch (Exception e) {
   System.out.println("Alert didn't appear");
  }
  
  String captchaText = "To better protect your account, please re-enter your password and then enter the characters as they are shown in the image below.";
  try {
   while(captchaText.equals(LP.CaptchTxtMatch())) { 
    
      logger.trace("We are on captcha page");

      // prompt user to enter captcha
      String captchaVal1 = JOptionPane.showInputDialog("Please enter the captcha value : ");
      LP.CaptchaEnter(captchaVal1);
      LP.EnterPassword(decoded);
      LP.SubmitAction();  
    
    }
  } catch (Exception e) {
   System.out.println("Captcha didn't appear..");
  }
 
  // VP = new VerifyUserLoggedInPage(driver);
  Thread.sleep(3000);
  String result = VP.Verifylogin();

  Assert.assertEquals(result, expstring);
  logger.trace("Completed test execution");

  Thread.sleep(4000);
  // Hover over 'Account and Lists'
  Actions hover = new Actions(driver);
  Action hoverOverSignin = hover.moveToElement(VP.HoveroverSign()).build();
  hoverOverSignin.perform();
  

  // Click on Sign out button
  VP.Signoutbutton();

 }

 @Test(dataProvider = "getdatalogin")
 public void ScenarioTwo(String usrnm, String pswd, String expstring, String Invldemail, String InvlPass, String ValidPhnNo, 
   String InvalidPhnNo) {

  LP.EnterUsername(Invldemail);
  LP.ContinueAction();
  Assert.assertEquals(LP.InvalidEmlMesage(), "We cannot find an account with that email address");

 }

 @Test(dataProvider = "getdatalogin")
 public void ScenarioThree(String usrnm, String pswd, String expstring, String Invldemail, String InvlPass,
   String ValidPhnNo, String InvalidPhnNo) throws InterruptedException {
  LP.EnterUsername(usrnm);
  LP.ContinueAction();

  logger.trace("Username done landed on Next Page");
  LP.EnterPassword(InvlPass);
  LP.SubmitAction();
  String captchaText = "To better protect your account, please re-enter your password and then enter the characters as they are shown in the image below.";
  try {
   while(captchaText.equals(LP.CaptchTxtMatch())) {
    
    logger.trace("We are on captcha page");

    // prompt user to enter captcha
    String captchaVal1 = JOptionPane.showInputDialog("Please enter the captcha value : ");
    LP.CaptchaEnter(captchaVal1);
    LP.EnterPassword(InvlPass);
    LP.SubmitAction();  
  }

  } catch (Exception e) {
   System.out.println("Captcha didn't appear..");
  }
  
  Thread.sleep(3000);
  Assert.assertEquals(LP.InvalidEmlMesage(), "Your password is incorrect");
 }

 @Test
 public void ScenarioFour() {
  LP.ContinueAction();
  Assert.assertEquals(LP.BlankEmail(), "Enter your email or mobile phone number");
 }

 @Test(dataProvider = "getdatalogin")
 public void ScenarioFive(String usrnm, String pswd, String expstring, String Invldemail, String InvlPass,
   String ValidPhnNo, String InvalidPhnNo) {
  logger.trace("Starting Test.");
  LP.EnterUsername(usrnm);
  LP.ContinueAction();

  LP.Forgotpass();
  // String AssistText = LP.PassAssist();
  Assert.assertEquals(LP.PassAssist(), "Password assistance"); 

 }

 @Test(dataProvider = "getdatalogin")
 public void ScenarioSix(String usrnm, String pswd, String expstring, String Invldemail, String InvlPass,
   String ValidPhnNo, String InvalidPhnNo) throws InterruptedException {
  logger.trace("Starting Test.");
  LP.EnterUsername(ValidPhnNo);
  LP.ContinueAction();

  logger.trace("Username done landed on Next Page");
  LP.EnterPassword(pswd);
  LP.SubmitAction();

  logger.trace("Landed successfully on user home page");
  try {
   Alert alert = driver.switchTo().alert();
   alert.dismiss();

  } catch (Exception e) {
   System.out.println("Alert didn't appear");
  }

  String captchaText = "To better protect your account, please re-enter your password and then enter the characters as they are shown in the image below.";
  try {
   while(captchaText.equals(LP.CaptchTxtMatch())) {
    
    logger.trace("We are on captcha page");

    // prompt user to enter captcha
    String captchaVal1 = JOptionPane.showInputDialog("Please enter the captcha value : ");
    LP.CaptchaEnter(captchaVal1);
    LP.EnterPassword(pswd);
    LP.SubmitAction();

    
   
  
  } 
  } catch (Exception e) {
   System.out.println("Captcha didn't appear..");
  }
  
  
  // VP = new VerifyUserLoggedInPage(driver);
  String result = VP.Verifylogin();

  Assert.assertEquals(result, expstring);
  logger.trace("Completed test execution");

  Thread.sleep(4000);
  // Hover over 'Account and Lists'
  Actions hover = new Actions(driver);
  Action hoverOverSignin = hover.moveToElement(VP.HoveroverSign()).build();
  hoverOverSignin.perform();

  // Click on Sign out button
  VP.Signoutbutton();

 }

 @Test(dataProvider = "getdatalogin")
 public void ScenarioSeven(String usrnm, String pswd, String expstring, String Invldemail, String InvlPass,
   String ValidPhnNo, String InvalidPhnNo) {

  LP.EnterUsername(InvalidPhnNo);
  LP.ContinueAction();
  Assert.assertEquals(LP.InvalidEmlMesage(), "We cannot find an account with that mobile number");
 }

 @BeforeMethod
 public void beforeMethod() throws InterruptedException {
  LP = new LoginPage(driver);
  driver.get("https://www.amazon.in/");
  VP = new VerifyUserLoggedInPage(driver);
  VP.GotoLoginClick();

 }

 @AfterMethod
 public void afterMethod() {
  //String endOfATest = JOptionPane.showInputDialog("Please enter Okay to proceed with further test execution: ");
  //System.out.println("Confirmation from tester to start next test exection: "+endOfATest);
  
 }

 @DataProvider
 public Object[][] getdatalogin() throws IOException {
  String currentDirectory = System.getProperty("user.dir");
  System.out.println(currentDirectory);
  String datafile = currentDirectory + "\\src\\test\\resources\\utils\\Amazone.xlsx";
  System.out.println("Current Directory of test data: " + datafile);
  String sheetname = "LoginTest";
  Object[][] myTestData = ReadExcel.readTestData(datafile, sheetname);

  return myTestData;

 }

 @BeforeClass
 public void beforeClass() {
  System.setProperty("webdriver.chrome.driver",
    "C:\\Training\\Jar Files\\Drivers\\chromedriver.exe");
  driver = new ChromeDriver();
  driver.manage().window().maximize();
  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  
  /*
   * LP = new LoginPage(driver); driver.get("https://www.amazon.in/"); VP = new
   * VerifyUserLoggedInPage(driver); VP.GotoLoginClick();
   */
 }

 @AfterClass
 public void afterClass() {
  driver.quit();
 }

}
