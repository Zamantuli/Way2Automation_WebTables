package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class UserListTablePage {
    public WebDriver driver;

    By tableHeaders = By.cssSelector("thead th");

    @FindBy(xpath = "//button[contains(.,'Add User')]")
    WebElement addUser;

    public UserListTablePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToUserListTablePage() {
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isUserTablePageDisplayed() {
        List<WebElement> headers = driver.findElements(tableHeaders);
        Assert.assertTrue(headers.stream().anyMatch(header -> header.getText().equals("First Name")), "First Name column not found");
        Assert.assertTrue(headers.stream().anyMatch(header -> header.getText().equals("Last Name")), "Last Name column not found");
        Assert.assertTrue(headers.stream().anyMatch(header -> header.getText().equals("E-mail")), "Email column not found");
        return true;
    }

    public void clickAddUserButton() {
        addUser.click();
    }
}
