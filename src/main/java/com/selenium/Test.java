package com.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.util.FileCopyUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        // Set the path to your WebDriver executable
        System.setProperty("web-driver.chrome.driver", "C:\\Selenium\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            driver.get("https://www.amazon.com");
            driver.manage().window().maximize();

      
            WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
            search.sendKeys("Echo Dot");
            search.sendKeys(Keys.RETURN);
            Thread.sleep(3000);

            WebElement product = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[2]/div/div/span/div/div/div/div[1]/div/div[2]/div/span/a/div/img"));
            product.click();
            Thread.sleep(3000);

    
//            WebElement productPriceElement = driver.findElement(By.xpath("//*[@id=\"sw-subtotal\"]/span[2]/span"));
//            //*[@id="sw-subtotal"]/span[2]/span
//            String productPrice = productPriceElement.getText();

            WebElement addToCart = driver.findElement(By.id("add-to-cart-button"));
            addToCart.click();
            Thread.sleep(3000);

            // Go to the shopping cart
            WebElement cartButton = driver.findElement(By.xpath("//*[@id=\"sw-gtc\"]"));
            //*[@id="a-autoid-0-announce"]
            //*[@id="sw-gtc"]
            cartButton.click();
            Thread.sleep(3000);

//            // Verify the price in the cart
//            WebElement cartPriceElement = driver.findElement(By.xpath("//*[@id=\"sc-active-0079cc12-0ad0-44bf-9dba-9977bfaae945\"]/div[4]/div/div[2]/ul/div[1]/div[1]/div[1]/div/span"));
//            String cartPrice = cartPriceElement.getText();
//            assert productPrice.equals(cartPrice) : "Price Not Matched!";
            
            takeScreenshot(driver, "cartPrice.png");

            // 2) Apply Coupon Codes
            try {
                WebElement couponCode = driver.findElement(By.name("couponCode"));
                takeScreenshot(driver, "couponCode.png");
            } catch (Exception e) {
                System.out.println("No coupon code option available");
            }

            // 3) Adjust Quantity
            WebElement Increase = driver.findElement(By.name("quantity"));
            Increase.sendKeys("4");
            Increase.sendKeys(Keys.RETURN);
            Thread.sleep(3000);
            takeScreenshot(driver, "increased.png");
            
            WebElement Decrease = driver.findElement(By.name("quantity"));
            Decrease.sendKeys("2");
            Decrease.sendKeys(Keys.RETURN);
            Thread.sleep(3000);
            takeScreenshot(driver, "decreased.png");

            // Task 4: Remove Product
            WebElement deleteButton = driver.findElement(By.xpath("//*[@id=\"sc-active-db47e54a-1d47-43ec-81e1-d41dced5614f\"]/div[4]/div/div[2]/div[1]/span[2]/span/input"));
            deleteButton.click();
            Thread.sleep(3000);
            takeScreenshot(driver, "emptyCart.png");

            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          
        }
    }

    private static void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileCopyUtils.copy(scrFile, new File(fileName));
    }
}

