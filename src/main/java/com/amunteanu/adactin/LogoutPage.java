package com.amunteanu.adactin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amunteanu.helpers.BasicPage;

public class LogoutPage extends BasicPage
{
	@FindBy(xpath = "html/body/table[2]/tbody/tr/td[1]/table/tbody/tr/td")
	private WebElement logoutMessage;

	@FindBy(xpath = "html/body/table[2]/tbody/tr[1]/td[2]/a[2]")
	private WebElement bookedItineraryButton;

	@FindBy(xpath = "html/body/table[2]/tbody/tr[1]/td[2]/a[3]")
	private WebElement changePasswordButton;

	@FindBy(xpath = "html/body/table[2]/tbody/tr[1]/td[2]/a[1]")
	private WebElement searchHotelButton;

	public LogoutPage(WebDriver driver)
	{
		super(driver);
	}

	public String getLogoutMessage()
	{
		return logoutMessage.getText();
	}

	public ChangePasswordPage pressChangePasswordButton()
	{
		changePasswordButton.click();
		return new ChangePasswordPage(getDriver());
	}

	public HotelSearchPage pressSearchHotelButton()
	{
		searchHotelButton.click();
		return new HotelSearchPage(getDriver());
	}

	public ItineraryPage pressBookedItineraryButton()
	{
		bookedItineraryButton.click();
		return new ItineraryPage(getDriver());
	}

}
