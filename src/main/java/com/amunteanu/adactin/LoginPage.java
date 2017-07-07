package com.amunteanu.adactin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.amunteanu.helpers.BasicPage;

public class LoginPage extends BasicPage
{
	@FindBy(id = "login")
	private WebElement loginButton;

	@FindBy(id = "username")
	private WebElement usernameField;

	@FindBy(how = How.ID, using = "password")
	private WebElement passwordField;

	public LoginPage(WebDriver driver)
	{
		super(driver);
	}

	public HotelSearchPage login(String username, String password)
	{
		// Clear login field of text
		this.usernameField.clear();
		this.usernameField.sendKeys(username);
		// Clear password field of text
		this.passwordField.clear();
		this.passwordField.sendKeys(password);
		// Click login button
		this.loginButton.click();
		// loginField.submit();
		return new HotelSearchPage(getDriver());
	}

}
