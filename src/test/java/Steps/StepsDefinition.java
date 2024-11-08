package Steps;

import Helpers.Screenshots;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class StepsDefinition extends Hooks {

    @Given("I am on the User List Table page")
    public void i_am_on_the_user_list_table_page() {
        userListTablePage.navigateToUserListTablePage();
    }

    @Then("I validate the table headers contain {string}, {string}, and {string}")
    public void i_validate_the_table_headers(String firstName, String lastName, String username) {
        userListTablePage.isUserTablePageDisplayed();
        Assert.assertTrue(userListTablePage.isUserTablePageDisplayed(), "User Table headers are incorrect");
    }

    @And("I take a screenshot of the User List Table")
    public void i_take_a_screenshot_of_the_user_list_table() {
        Screenshots.takeScreenshot(driver, "UserListTable" + System.currentTimeMillis());
    }

    @When("I click on {string}")
    public void i_click_add_user_button(String button) {
        userListTablePage.clickAddUserButton();
    }

    @Then("I should see the Add User screen")
    public void i_should_see_the_add_user_screen() {
        addUserPage.validateAddUserFormIsDisplayed();
    }

    @And("I take a screenshot of the Add User screen")
    public void i_take_a_screenshot_of_the_add_user_screen() {
        Screenshots.takeScreenshot(driver, "AddUserForm" + System.currentTimeMillis());
    }

    @When("I add 3 random users")
    public void i_add_random_users_data() {
        addUserPage.addRandomUsers(3);
        userListTablePage.clickAddUserButton();
        addUserPage.closeForm();
    }

    @Then("I validate the users are added to the table")
    public void i_validate_the_users_added_to_the_table() {
        addUserPage.validateTheUsersAreAdded();
    }

    @And("I take a screenshot of the User Table screen")
    public void i_take_a_screenshot_of_the_user_table_screen() {
        Screenshots.takeScreenshot(driver, "Users Added" + System.currentTimeMillis());
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
