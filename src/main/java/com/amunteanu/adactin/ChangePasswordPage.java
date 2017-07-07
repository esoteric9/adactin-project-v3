package com.amunteanu.adactin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amunteanu.helpers.BasicPage;

public class ChangePasswordPage extends BasicPage
{
	@FindBy(xpath = ".//*[@id='change_password_form']/table/tbody/tr[1]/td/strong")
	private WebElement pageHeader;

	@FindBy(xpath = "html/body/table[2]/tbody/tr[1]/td[2]/a[1]")
	private WebElement searchHotelButton;

	@FindBy(xpath = "html/body/table[2]/tbody/tr[1]/td[2]/a[2]")
	private WebElement bookedItineraryButton;

	public ChangePasswordPage(WebDriver driver)
	{
		super(driver);
	}

	public String getPageHeaderText()
	{
		return pageHeader.getText().trim();
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
