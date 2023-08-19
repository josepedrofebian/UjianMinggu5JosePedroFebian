package com.juaracoding.pages;

import com.juaracoding.drivers.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;
    public LoginPage(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    // Locator @FindBy
    @FindBy(xpath = "//input[@id='user-name']") // locator field username
    WebElement username;
    @FindBy(xpath = "//input[@id='password']") // locator field password
    WebElement password;
    @FindBy(xpath = "//input[@id='login-button']") // locator button login
    WebElement btnLogin;
    @FindBy(xpath = "//div[@class='app_logo']") // locator text Login success
    WebElement textLogoSwagLab;
    @FindBy(xpath = "//div[@class='error-message-container error']") // locator text error message
    WebElement textErrorMessage;
    @FindBy(xpath = "//button[@id='react-burger-menu-btn']") // locator button burger
    WebElement btnBurger;
    @FindBy(xpath = "//a[@id='logout_sidebar_link']") // locator button logout
    WebElement btnLogout;
    @FindBy(xpath = "//div[@class='login_logo']") // locator text logo
    WebElement textLogo;


    // Custom Method
    public void login(String username, String password){ // method untuk menjalankann flow dari login
        this.username.sendKeys(username); // input ke dalam field username
        this.password.sendKeys(password); // input ke dalam field password
        btnLogin.click(); // click button login
    }

    public void logout(){ // method untuk flow logout
        btnBurger.click(); // klik button burger
        btnLogout.click(); // klik button logout
    }
    public String getTextLoginSuccess(){ // method untuk mendapatkan text logo swag lab di dashboard
        return textLogoSwagLab.getText();
    }
    public String getErrorMessage(){ // method untuk mendapatkan text error message di login
        return textErrorMessage.getText();
    }
    public String getTextLogoSwagLabs(){ // method untuk mendapatkan text logo swsag lab di login
        return textLogo.getText();
    }
}
