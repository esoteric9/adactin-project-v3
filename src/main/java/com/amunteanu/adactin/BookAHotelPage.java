package com.amunteanu.adactin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.amunteanu.helpers.BasicPage;

public class BookAHotelPage extends BasicPage
{

	@FindBy(id = "total_price_dis")
	private WebElement totalPrice;

	@FindBy(id = "final_price_dis")
	private WebElement finalPrice;

	@FindBy(id = "first_name")
	private WebElement firstNameField;

	@FindBy(id = "last_name")
	private WebElement lastNameField;

	@FindBy(id = "address")
	private WebElement addressField;

	@FindBy(id = "cc_num")
	private WebElement creditCardNrField;

	@FindBy(id = "cc_type")
	private WebElement creditCardTypeField;

	@FindBy(id = "cc_exp_month")
	private WebElement creditCardExpMonthField;

	@FindBy(id = "cc_exp_year")
	private WebElement creditCardExpYearField;

	@FindBy(id = "cc_cvv")
	private WebElement creditCardCVVField;

	@FindBy(id = "book_now")
	private WebElement bookNowButton;

	@FindBy(id = "logout")
	private WebElement logoutButton;

	@FindBy(id = "my_itinerary")
	private WebElement itineraryButton;

	@FindBy(id = "order_no")
	private WebElement orderNrField;

	public BookAHotelPage(WebDriver driver)
	{
		super(driver);
	}

	public String getTotalPrice()
	{
		return totalPrice.getAttribute("value");
	}

	public String getFinalPrice()
	{
		return finalPrice.getAttribute("value");
	}

	public BookAHotelPage enterFirstName(String name)
	{
		firstNameField.sendKeys(name);
		return this;
	}

	public BookAHotelPage enterLastName(String name)
	{
		lastNameField.sendKeys(name);
		return this;
	}

	public BookAHotelPage enterAddress(String address)
	{
		addressField.sendKeys(address);
		return this;
	}

	public BookAHotelPage enterCreditCardNr(String ccnum)
	{
		creditCardNrField.sendKeys(ccnum);
		return this;
	}

	public BookAHotelPage enterCreditCardType(String cctype)
	{
		new Select(creditCardTypeField).selectByValue(cctype);
		return this;
	}

	public BookAHotelPage enterCreditCardExpMonth(String ccExpMonth)
	{
		new Select(creditCardExpMonthField).selectByValue(ccExpMonth);
		return this;
	}

	public BookAHotelPage enterCreditCardExpYear(String ccExpYear)
	{
		new Select(creditCardExpYearField).selectByValue(ccExpYear);
		return this;
	}

	public BookAHotelPage enterCreditCardCVV(String cvv)
	{
		creditCardCVVField.sendKeys(cvv);
		return this;
	}

	public BookAHotelPage pressBookNow()
	{
		bookNowButton.click();
		return this;
	}

	public LogoutPage pressLogoutBtn()
	{
		logoutButton.click();
		return new LogoutPage(getDriver());
	}

	public void pressItineraryBtn()
	{
		itineraryButton.click();
	}

	public String getOrderNr()
	{
		return orderNrField.getAttribute("value");
	}

}
