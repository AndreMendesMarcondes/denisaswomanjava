package com.andremmarcondes.denisaswomanjava;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.GenericContainer;

public class Application {

    private final static String EMAIL = "";

    private final static String PASS = "";

    public static void main(final String[] args) throws Exception {
        final GenericContainer<?> container = new GenericContainer<>("selenium/standalone-chrome:3.141.59-20200525")
                .withExposedPorts(4444);
        container.start();

        final WebDriver driver = createDriver(container);

        driver.get("http://www.facebook.com");

        final WebElement element1 = driver.findElement(By.id("email"));
        element1.sendKeys(EMAIL);

        final WebElement element2 = driver.findElement(By.id("pass"));
        element2.sendKeys(PASS);

        final WebElement element3 = driver.findElement(By.id("u_0_b"));
        Thread.sleep(1000);
        element3.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Thread.sleep(5000);

        driver.get("https://m.facebook.com/story.php?story_fbid=2660605310929313&id=100009396100053");

        for (int i = 0; i < 100; i++) {
            comment(driver, "comentário " + i);
        }

        container.stop();
    }

    private static WebDriver createDriver(final GenericContainer<?> container) throws MalformedURLException {
        final URL url = new URL("http://0.0.0.0:" + container.getMappedPort(4444) + "/wd/hub");
        final ChromeOptions chromeOptions = new ChromeOptions();

        return new RemoteWebDriver(url, chromeOptions);
    }

    private static void comment(WebDriver driver, String comment) throws InterruptedException {
        final WebElement commentArea = driver.findElement(By.id("composerInput"));
        commentArea.sendKeys(comment);

        final WebElement submit = driver.findElement(By.name("submit"));
        Thread.sleep(2000);
        submit.click();
        Thread.sleep(2000);

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

}
