package com.juaracoding.pages;

import com.juaracoding.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    private WebDriver driver;
    public CheckoutPage(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//button[@id='checkout']") // locator button Checkout
    WebElement btnCheckout;
    @FindBy(xpath = "//input[@id='first-name']") // locator field First Name
    WebElement fieldFirstName;
    @FindBy(xpath = "//input[@id='last-name']") // locator field Last Name
    WebElement fieldLastName;
    @FindBy(xpath = "//input[@id='postal-code']") // locator field Postal Code
    WebElement fieldPostalCode;
    @FindBy(xpath = "//div[@class='error-message-container error']") // locator Message Error di page your information
    WebElement errorMessage;
    @FindBy(xpath = "//input[@id='continue']") // locator button Continue
    WebElement btnContinue;
    @FindBy(xpath = "//button[@id='finish']") // locator button Finish
    WebElement btnFinish;
    @FindBy(xpath = "//h2[normalize-space()='Thank you for your order!']") // locator text success checkout
    WebElement textSuccessCheckout;


    public void checkout(String firstName, String lastName, String postalCode){ // method untuk menjalankan flow dari checkout
        try {
            btnCheckout.click();
        }catch(Exception ignored){}
        try {
            fieldFirstName.sendKeys(firstName);
            fieldLastName.sendKeys(lastName);
            fieldPostalCode.sendKeys(postalCode);
            btnContinue.click();
            btnFinish.click();
        }catch (Exception ignored){}
    }
    public String getErrorMessage(){ // method untuk mendapatkan text error message di your information page
        return errorMessage.getText();
    }
    public String getTextSuccessCheckout(){ // method untuk mendapatkan text success checkout
        return textSuccessCheckout.getText();
    }
}
