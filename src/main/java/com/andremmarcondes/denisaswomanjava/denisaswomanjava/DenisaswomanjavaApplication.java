package com.andremmarcondes.denisaswomanjava.denisaswomanjava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class DenisaswomanjavaApplication {

    static String EMAIL = "";
    static String PASS = "";

    public static void main(String[] args) throws InterruptedException {
        String expath = Paths.get("").toAbsolutePath().toString() + "\\chromedriver.exe";
        System.out.println(expath);
        System.setProperty("webdriver.chrome.driver", expath);
        WebDriver driver = new ChromeDriver();

        driver.get("http:\\www.facebook.com");

        WebElement element1 = driver.findElement(By.id("email"));
        element1.sendKeys(EMAIL);

        WebElement element2 = driver.findElement(By.id("pass"));
        element2.sendKeys(PASS);

        WebElement element3 = driver.findElement(By.id("u_0_b"));
        Thread.sleep(1000);
        element3.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Thread.sleep(5000);

        driver.get("https://m.facebook.com/story.php?story_fbid=2660605310929313&id=100009396100053");

        for (int i= 0; i < 100; i++) {
            comment(driver, "comentário " + i);
        }
    }

    private static void comment(WebDriver driver, String comement) throws InterruptedException {
        WebElement commentArea = driver.findElement(By.id("composerInput"));
        commentArea.sendKeys(comement);

        WebElement submit = driver.findElement(By.name("submit"));
        Thread.sleep(2000);
        submit.click();
        Thread.sleep(2000);

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

}
