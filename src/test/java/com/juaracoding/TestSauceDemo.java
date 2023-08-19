package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.AddTwoProductToCartPage;
import com.juaracoding.pages.CheckoutPage;
import com.juaracoding.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestSauceDemo {
    WebDriver driver;
    LoginPage login;
    AddTwoProductToCartPage addTwoProductToCart;
    CheckoutPage checkout;

    @BeforeClass
    public void setUp(){ // method untuk set up projek automation testing
        DriverSingleton.getInstance("chrome");
        driver = DriverSingleton.getDriver();
        driver.get("https://www.saucedemo.com");
        login = new LoginPage();
        addTwoProductToCart = new AddTwoProductToCartPage();
        checkout = new CheckoutPage();
    }
    @AfterClass
    public void finish(){ // method untuk menghentikan automation testing
        DriverSingleton.delay(3);
        DriverSingleton.closeObjectInstance();
    }
    @Test(priority = 4)
    public void validLogin(){ // method untuk menjalankan test case login dengan data yang valid
        login.login("standard_user","secret_sauce");
        Assert.assertEquals(login.getTextLoginSuccess(),"Swag Labs");
    }
    @Test(priority = 1)
    public void invalidLoginWithWrongCredential(){ // method untuk menjalankan test case login dengan data yang tidak valid
        login.login("jose","rahasia");
        Assert.assertEquals(login.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service");
        driver.navigate().refresh();
    }
    @Test(priority = 2)
    public void invalidLoginWithoutUsername(){ // method untuk menjalankan test case login tanpa input username
        login.login("","secret_sauce");
        Assert.assertEquals(login.getErrorMessage(),"Epic sadface: Username is required");
        driver.navigate().refresh();
    }
    @Test(priority = 3)
    public void invalidLoginWithoutPassword(){ // method untuk menjalankan test case login tanpa input password
        login.login("standard_user","");
        Assert.assertEquals(login.getErrorMessage(),"Epic sadface: Password is required");
        driver.navigate().refresh();
    }
    @Test(priority = 5)
    public void addTwoProductToCartImageCartValueAndProductList(){ // method untuk menjalankan test case add 2 product to cart
        addTwoProductToCart.addTwoProductToCart();
        Assert.assertEquals(addTwoProductToCart.getAddProductToCartSuccess(), "2");
        Assert.assertEquals(addTwoProductToCart.getTextProductInCartList(), "Sauce Labs Backpack" +
                "Sauce Labs Bike Light");
    }
    @Test(priority = 6)
    public void InvalidCheckoutYourInformationPageWithoutFirstName(){ // method untuk menjalankan test case checkout halaman your information tanpa input first name
        checkout.checkout("","Pedro","78113");
        Assert.assertEquals(checkout.getErrorMessage(),"Error: First Name is required");
        driver.navigate().refresh();
    }
    @Test(priority = 7)
    public void InvalidCheckoutYourInformationPageWithoutLastName(){ // method untuk menjalankan test case checkout halaman your information tanpa input last name
        checkout.checkout("Jose","","78113");
        Assert.assertEquals(checkout.getErrorMessage(),"Error: Last Name is required");
        driver.navigate().refresh();
    }
    @Test(priority = 8)
    public void InvalidCheckoutYourInformationPageWithoutPostalCode(){ // method untuk menjalankan test case checkout halaman your information tanpa input postal code
        checkout.checkout("Jose","Pedro","");
        Assert.assertEquals(checkout.getErrorMessage(),"Error: Postal Code is required");
        driver.navigate().refresh();
    }
    @Test(priority = 9)
    public void validCheckoutYourInformationPageAndFinishCheckout(){ // method untuk menjalankan test case checkout halaman your information dengan data yang valid dan menyelesaikan checkout
        checkout.checkout("Jose","Pedro","78113");
        Assert.assertEquals(checkout.getTextSuccessCheckout(), "Thank you for your order!");
    }
    @Test(priority = 10)
    public void testLogout(){ // method untuk logout
        login.logout();
        Assert.assertEquals(login.getTextLogoSwagLabs(), "Swag Labs");
    }
}
