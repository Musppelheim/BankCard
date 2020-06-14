package ru.netology.domain;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CardApp {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setUpTest() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void ShouldSubmit() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Лоран Сика");
        driver.findElement(By.cssSelector("[data-test-id=phone] input ")).sendKeys("+79526007441");
        driver.findElement(By.cssSelector("span[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector(".button__text")).submit();
        String text = driver.findElement(By.cssSelector("[data-test-id]")).getText();
        assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
    }

    @Test
    void souldNotSubmitWithWrongName() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Лоран сика");
        driver.findElement(By.cssSelector("[data-test-id=phone] input ")).sendKeys("+79526007441");
        driver.findElement(By.cssSelector("span[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector(".button__text")).submit();
        String text = driver.findElement(By.cssSelector(".input__sub")).getText();
        assertEquals("Укажите точно как в паспорте", text);
    }

    @Test
    void souldNotSubmitWithEmptyNameField() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=phone] input ")).sendKeys("+79526007441");
        driver.findElement(By.cssSelector("span[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector(".button__text")).submit();
        String text = driver.findElement(By.cssSelector(".input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", text);
    }
}
