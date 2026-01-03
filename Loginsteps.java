package stepdefinition;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hooks.Hooks;
import io.cucumber.java.en.*;

import java.time.Duration;

public class Loginsteps {

    WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the PHPTravels login page")
    public void i_am_on_the_phptravels_login_page() {
        driver.get("https://phptravels.net/login");
    }

    @When("I enter valid email and valid password")
    public void i_enter_valid_email_and_valid_password() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")))
                .sendKeys("user@phptravels.com");

        driver.findElement(By.id("password"))
                .sendKeys("demouser");
    }

    @When("I enter valid email and invalid password")
    public void i_enter_valid_email_and_invalid_password() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")))
                .sendKeys("user@phptravels.com");

        driver.findElement(By.id("password"))
                .sendKeys("wrong123");
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("submitBTN"))).click();
    }

    @Then("I should be redirected to the user dashboard")
    public void i_should_be_redirected_to_the_user_dashboard() {
        wait.until(ExpectedConditions.urlContains("dashboard"));
    }

    @Then("I should see the welcome message on the dashboard")
    public void i_should_see_the_welcome_message_on_the_dashboard() {
        boolean welcome =
                driver.getPageSource().contains("Hi") ||
                driver.getPageSource().contains("Welcome");

        if (!welcome) {
            throw new AssertionError("Welcome message not displayed");
        }
    }

    @Then("I should see an error message indicating invalid credentials")
    public void i_should_see_an_error_message_indicating_invalid_credentials() {
        boolean error = driver.getPageSource().contains("Invalid");
        if (!error) {
            throw new AssertionError("Invalid credentials message not shown");
        }
    }
}
