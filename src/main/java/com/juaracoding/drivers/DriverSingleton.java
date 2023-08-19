package com.juaracoding.drivers;

import com.juaracoding.drivers.strategies.DriverStrategy;
import com.juaracoding.drivers.strategies.DriverStrategyImplementer;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static DriverSingleton instance = null;
    private static WebDriver driver;
    private static JavascriptExecutor js;
    private DriverSingleton(String driver){
        instantiate(driver);
    }

    public WebDriver instantiate(String strategy){
        DriverStrategy driverStrategy = DriverStrategyImplementer.chooseStrategy(strategy);
        driver = driverStrategy.setStrategy();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        this.js = (JavascriptExecutor) driver;
        return driver;
    }

    public static DriverSingleton getInstance(String driver){
        if(instance == null){
            instance = new DriverSingleton(driver);
        }
        return instance;
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static void delay(long sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeObjectInstance(){
        instance = null;
        driver.quit();
    }

    // Menambahkan Method assertEqual
    public static void assertEqual(String actual, String expected){
        if(actual.contains(expected)){
            System.out.println("Status : Pass");
        }else{
            System.out.println("Status : Fail");
        }
    }
    // Menambahkan method scroll
    public static void scroll(int a, int b){
        js.executeScript(String.format("window.scrollBy(%d,%d)",a,b));
    }

}

