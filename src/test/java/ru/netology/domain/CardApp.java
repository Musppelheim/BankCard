package ru.netology.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardApp {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.gecko.driver.", "path/to/geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
    }
    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://github.com/mozilla/geckodriver");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void ShouldSubmit() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Лоран Сика");
        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("+79526007441");
        driver.findElement(By.cssSelector(".checkbox_box")).click();
        driver.findElement(By.cssSelector("span[class='button_text']")).submit();
        String successText = driver.findElement(By.cssSelector("[data-test-id]")).getText();
        assertEquals("Ваша заявка успешно отправлена!  Наш менеджер свяжется с вами в ближайшее время.", successText);

    }
}
