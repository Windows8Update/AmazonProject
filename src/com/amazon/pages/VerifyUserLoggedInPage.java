package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyUserLoggedInPage {
 private WebDriver driver;
 
 //Xpath by Prashant
 //@FindBy(xpath = "//span[contains(text(),'Hello,') and @class=\"nav-line-1\"]")
 
 //GG(02/01) : Xpath by GG 
 @FindBy(xpath = "//a[@id='nav-link-accountList']/span[contains(text(),'Hello,')]")
 private WebElement HelloUser;

 @FindBy(xpath = "//a[@id='nav-link-accountList']/span")
 private WebElement GotoLoginPage;



 public VerifyUserLoggedInPage(WebDriver driver) {
  // initializing instance variable with local variable
  this.driver = driver;
  // This initElements method will create all WebElemnts
  PageFactory.initElements(driver, this);
 }

 public String Verifylogin() throws InterruptedException {
  Thread.sleep(2000);
  String HelloUserName = HelloUser.getText();
  return HelloUserName;

 }

 public WebElement HoveroverSign() {
  return HelloUser;
 }

 @FindBy(xpath = "//a[@id='nav-item-signout']/span")
 private WebElement Signoutbtn;
 
 public void Signoutbutton() {
	 if(Signoutbtn.isDisplayed()) {
  Signoutbtn.click();
	 }else {
		 HelloUser.click();
		 Signoutbtn.click();
	 }
 }

 
 @FindBy(id = "ap_email")
 private WebElement IfUsername;
 
 public void GotoLoginClick() throws InterruptedException {
  GotoLoginPage.click();
  try {
	  GotoLoginPage.click();
} catch (Exception e) {
	// TODO: handle exception
}
  
  try {
	  if(IfUsername.isDisplayed()) {
		  
	  }else {
		  try {
			  Thread.sleep(2000);
			  GotoLoginPage.click();
			  GotoLoginPage.click();
			  GotoLoginPage.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		  
	  }
} catch (Exception e) {
	// TODO: handle exception
}
	  
  
  

 }

}
