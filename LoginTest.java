
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        // TODO 1: Create ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // TODO 2: Initialize ChromeDriver
        driver = new ChromeDriver(options);

        // TODO 3: Maximize browser
        driver.manage().window().maximize();

        // TODO 4: Open login application
        Path page = Paths.get("webapp", "index.html").toAbsolutePath();
        driver.get(page.toUri().toString());
    }

    @Test
    public void validLoginShouldShowSuccessMessage() throws Exception {

        // TODO 5: Enter valid username
        driver.findElement(By.id("username")).sendKeys("student");

        // TODO 6: Enter valid password
        driver.findElement(By.id("password")).sendKeys("Selenium@123");

        // TODO 7: Click login button
        driver.findElement(By.id("loginBtn")).click();

        // TODO 8: Retrieve response message
        String message = driver.findElement(By.id("message")).getText();

        // TODO 9: Assert success message
        assertTrue(driver.findElement(By.id("message")).isDisplayed());
        assertEquals("Login successful!", message);

        // TODO 10: Capture screenshot
        saveScreenshot("successful-login.png");
    }

    @Test
    public void invalidLoginShouldShowErrorMessage() throws Exception {

        // TODO 11: Enter invalid credentials
        driver.findElement(By.id("username")).sendKeys("wronguser");
        driver.findElement(By.id("password")).sendKeys("wrongpass");

        driver.findElement(By.id("loginBtn")).click();

        // TODO 12: Retrieve and assert error message
        String message = driver.findElement(By.id("message")).getText();

        assertEquals("Invalid username or password!", message);

        // TODO 13: Capture screenshot
        saveScreenshot("invalid-login.png");
    }

    private void saveScreenshot(String fileName) throws Exception {

        // TODO 14: Capture screenshot
        Files.createDirectories(Paths.get("screenshots"));

        File source = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File destination = new File("screenshots/" + fileName);

        FileHandler.copy(source, destination);
    }

    @AfterEach
    public void tearDown() {

        // TODO 15: Close browser safely
        if (driver != null) {
            driver.quit();
        }
    }
}
