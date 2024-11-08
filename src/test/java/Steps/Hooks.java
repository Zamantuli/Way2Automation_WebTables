package Steps;

import Helpers.BrowserFactory;
import Pages.AddUserPage;
import Pages.UserListTablePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Hooks {
    BrowserFactory browserFactory = new BrowserFactory();
    final WebDriver driver = browserFactory.startBrowser("chrome","http://www.way2automation.com/angularjs-protractor/webtables/");
    UserListTablePage userListTablePage = PageFactory.initElements(driver, UserListTablePage.class);
    AddUserPage addUserPage = PageFactory.initElements(driver, AddUserPage.class);
}
