package com.amunteanu.adactin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amunteanu.helpers.BasicPage;

public class ItineraryPage extends BasicPage
{

	@FindBy(xpath = "html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input")
	private WebElement firstRowOrderNr;

	@FindBy(xpath = "html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]/input")
	private WebElement firstRowCancelBtn;

	@FindBy(id = "order_id_text")
	private WebElement searchField;

	@FindBy(id = "search_hotel_id")
	private WebElement searchBtn;

	@FindBy(id = "search_result_error")
	private WebElement errorMessage;

	@FindBy(xpath = "html/body/table[2]/tbody/tr[1]/td[2]/a[3]")
	private WebElement changePasswordButton;

	@FindBy(xpath = ".//*[@id='booked_form']/table/tbody/tr[1]/td/table/tbody/tr/td[1]")
	private WebElement pageHeader;

	@FindBy(xpath = "html/body/table[2]/tbody/tr[1]/td[2]/a[1]")
	private WebElement searchHotelButton;

	public ItineraryPage(WebDriver driver)
	{
		super(driver);
	}

	public WebElement getFirstRowOrderNr()
	{
		return firstRowOrderNr;
	}

	public ItineraryPage pressFirstRowCancelBtn()
	{
		firstRowCancelBtn.click();
		return this;
	}

	public ItineraryPage enterOrderNrinSearchField(String orderNr)
	{
		searchField.sendKeys(orderNr);
		return this;
	}

	public ItineraryPage pressSearchBtn()
	{
		searchBtn.click();
		return this;
	}

	public String getErrorMessageText()
	{
		return errorMessage.getText().trim();
	}

	public String getPageHeaderText()
	{
		return pageHeader.getText().trim();
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

}
