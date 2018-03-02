/*
package Selenuim.test;

import Selenuim.test.TestBase;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class DemoqaRegistration extends TestBase {


  private String baseUrl;
  private boolean acceptNextAlert = true;
  private static StringBuffer verificationErrors = new StringBuffer();
  private String firstName = "name_3_firstname";
  private final String country = "dropdown_7";



  @Test*/
/**//*

  public void testDemoqaRegistration() throws Exception {
    driver.get(baseUrl + "/registration/");
    setTextById(firstName, "First");
    setTextById("name_3_lastname", "Last");

    driver.findElement(By.xpath("(//input[@name='radio_4[]'])[2]")).click();
    driver.findElement(By.name("checkbox_5[]")).click();

//    setTextById(country, "Russia");
    new Select(driver.findElement(By.id(country))).selectByVisibleText("Russia");
    new Select(driver.findElement(By.id("mm_date_8"))).selectByVisibleText("31");
    new Select(driver.findElement(By.id("dd_date_8"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("yy_date_8"))).selectByVisibleText("1988");
    setTextById("phone_9","0000000000");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("username");
    driver.findElement(By.id("email_1")).clear();
    driver.findElement(By.id("email_1")).sendKeys("test@test.com");
    driver.findElement(By.id("description")).clear();
    driver.findElement(By.id("description")).sendKeys("about me");
    driver.findElement(By.id("password_2")).clear();
    driver.findElement(By.id("password_2")).sendKeys("1qaz@WSX");
    driver.findElement(By.id("confirm_password_password_2")).clear();
    driver.findElement(By.id("confirm_password_password_2")).sendKeys("1qaz@WSX");
    driver.findElement(By.name("pie_submit")).click();
  }

  private void clickFirstName(String first, String firstname) {
    driver.findElement(By.id(firstName)).clear();
    driver.findElement(By.id(firstname)).sendKeys(first);
  }



  private static void fail(String verificationErrorString) {
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
*/
