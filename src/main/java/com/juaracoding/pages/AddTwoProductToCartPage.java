package com.juaracoding.pages;

import com.juaracoding.drivers.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddTwoProductToCartPage {
    private WebDriver driver;
    public AddTwoProductToCartPage(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']") // locator button item produk 1
    WebElement btnAddProductItem1;
    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-bike-light']") // locator button item produk 2
    WebElement btnAddProductItem2;
    @FindBy(xpath = "//a[@class='shopping_cart_link']") // locator button cart
    WebElement btnCart;
    @FindBy(xpath = "//span[@class='shopping_cart_badge']") // locator cart image value item
    WebElement cartImageValueItem;

    public void addTwoProductToCart(){ // method untuk flow add 2 produk ke cart
        btnAddProductItem1.click(); // klik button add to cart produk 1
        btnAddProductItem2.click(); // klik button add to cart produk 2
        btnCart.click(); // klik cart
    }
    public  String getAddProductToCartSuccess(){ // method untuk mendapatkan text jumlah produk di cart image
        return cartImageValueItem.getText();
    }
    public String getTextProductInCartList(){ // method untuk mendapatkan text dari list produk di cart
        String products = "";
        List<WebElement> textProducts = driver.findElements(By.xpath("//*[@class=\"cart_item\"]/div[2]/a[1]/div[1]"));
        for (int i = 0; i < textProducts.size(); i++) {
            products += textProducts.get(i).getText();
        }
        return products;
    }

}
