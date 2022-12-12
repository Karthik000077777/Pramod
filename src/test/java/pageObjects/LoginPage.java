package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailId;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginBtn;
	
	public void setEmailId(String email) {
		emailId.clear();
		emailId.sendKeys(email);
		System.out.println("Emial is entered into testField\n");
	}
	
	public void setPassword(String password) {
		this.password.clear();
	    this.password.sendKeys(password);
	    System.out.println("Password is entered into testField\n");
	}
	public void clickLoginBtn() {
		loginBtn.click();
		System.out.println("Login Btn is clicked\n");
	}
}
