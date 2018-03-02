package ru.maistrenko.addressbook.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by maistrenko on 25.07.2017.
 */
public class RegistrationPage extends AbstractPage {

    public Integer random = (int) (Math.random()*1000);
    private String first = "First" + random;
    private String last = "Last"+ random;
    private String phoneNumber = "0000000000" + random;
    private String userNameValue = "username" + random;



    private  String emailValue = "test" + random  + "@test.com";
    private String descriptionInfo = "about me";
    private String passwordValue = "1qaz@WSXTYfrdghkf";

    private final String country = "dropdown_7";


    @FindBy(id = "name_3_firstname")
    protected WebElement firstName;

    @FindBy(id = "name_3_lastname")
    protected WebElement lastName;

    @FindBy(id = "phone_9")
    protected WebElement phone;

    @FindBy(id = "username")
    protected WebElement userName;

    @FindBy(id = "email_1")
    protected WebElement email;

    @FindBy(id = "description")
    protected WebElement description;

    @FindBy(id = "password_2")
    protected WebElement password;

    @FindBy(id = "confirm_password_password_2")
    protected WebElement confirmPassword;


    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillRegistrationForm() {


            firstName.sendKeys(first);
            lastName.sendKeys(last);
            driver.findElement(By.xpath("(//input[@name='radio_4[]'])[2]")).click();
            driver.findElement(By.name("checkbox_5[]")).click();

            new Select(driver.findElement(By.id(country))).selectByVisibleText("Russia");
            if (isElementPresent(By.id("mm_date_8"))) {
                new Select(driver.findElement(By.id("mm_date_8"))).selectByVisibleText("6");
            }

            new Select(driver.findElement(By.id("dd_date_8"))).selectByVisibleText("11");
            new Select(driver.findElement(By.id("yy_date_8"))).selectByVisibleText("1988");

            phone.sendKeys(phoneNumber);
            userName.sendKeys(userNameValue);
            email.sendKeys(emailValue);
            //description.sendKeys(descriptionInfo);
            setTextById("description", null);
            password.sendKeys(passwordValue);
            confirmPassword.sendKeys(passwordValue);

            driver.findElement(By.name("pie_submit")).click();

        //Assert.assertTrue(driver.findElement(By.className("piereg_message")).getText().equals("Thank you for your registration"));
        }


    }

