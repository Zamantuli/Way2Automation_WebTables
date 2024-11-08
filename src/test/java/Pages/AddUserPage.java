package Pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AddUserPage {
    public WebDriver driver;
    WebDriverWait wait;

    Faker faker = new Faker();

    @FindBy(xpath = "//h3[contains(.,'Add User')]")
    WebElement addUserForm;

    @FindBy(xpath = "//input[@name='FirstName']")
    WebElement firstNameTextField;

    @FindBy(xpath = "//input[@name='LastName']")
    WebElement lastNameTextField;

    @FindBy(xpath = "//input[@name='UserName']")
    WebElement userNameTextField;

    @FindBy(xpath = "//input[@name='Password']")
    WebElement passwordTextField;

    @FindBy(xpath = "//input[contains(@value,'15')]")
    WebElement customerOption;

    @FindBy(xpath = "//select[@name='RoleId']")
    WebElement roleOption;

    @FindBy(xpath = "//input[@type='email']")
    WebElement emailTextField;

    @FindBy(xpath = "//input[@name='Mobilephone']")
    WebElement cellPhoneTextField;

    @FindBy(xpath = "//button[contains(.,'Save')]")
    WebElement save;

    @FindBy(xpath = "//button[contains(.,'Add User')]")
    WebElement addUser;

    public AddUserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void validateAddUserFormIsDisplayed() {
        Assert.assertTrue(addUserForm.isDisplayed(),"Add User form is not displayed");
        Assert.assertEquals(addUserForm.getText(), "Add User");
    }

    public void addRandomUsers(int userCount){
        for (int i = 0; i < userCount; i++) {

            firstNameTextField.clear();
            lastNameTextField.clear();
            userNameTextField.clear();
            passwordTextField.clear();
            emailTextField.clear();
            cellPhoneTextField.clear();

            firstNameTextField.sendKeys(faker.name().firstName());
            lastNameTextField.sendKeys(faker.name().lastName());
            userNameTextField.sendKeys(faker.name().username() + faker.number().digits(3));
            passwordTextField.sendKeys(faker.internet().password(8, 12, true, true, true));
            customerOption.click();
            new Select(roleOption).selectByVisibleText("Customer");
            emailTextField.sendKeys(faker.internet().emailAddress());
            cellPhoneTextField.sendKeys(faker.phoneNumber().cellPhone());
            wait.until(ExpectedConditions.elementToBeClickable(save)).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-backdrop")));
            if (i < userCount - 1) {
                wait.until(ExpectedConditions.elementToBeClickable(addUser)).click();
            } else {
                closeForm();
            }
        }
    }

    public void closeForm(){
        try {
            WebElement closeButton = driver.findElement(By.xpath("//button[contains(.,'Close')]"));
            wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
        } catch (NoSuchElementException e) {
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        }
    }

    public void validateTheUsersAreAdded(){
        addUser.isDisplayed();
        String buttonText = addUser.getText();
        Assert.assertEquals(buttonText, "Add User");
    }
}
