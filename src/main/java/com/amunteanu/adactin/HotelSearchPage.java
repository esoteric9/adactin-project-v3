package com.amunteanu.adactin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.amunteanu.helpers.BasicPage;

public class HotelSearchPage extends BasicPage
{
	@FindBy(id = "location")
	private WebElement locationField;

	@FindBy(id = "hotels")
	private WebElement hotelField;

	@FindBy(id = "room_type")
	private WebElement roomTypeField;

	@FindBy(id = "room_nos")
	private WebElement nrOfRoomsField;

	@FindBy(id = "datepick_in")
	private WebElement checkInDateField;

	@FindBy(id = "checkin_span")
	private WebElement checkInDateError;

	@FindBy(id = "datepick_out")
	private WebElement checkOutDateField;

	@FindBy(id = "checkout_span")
	private WebElement checkOutDateError;

	@FindBy(id = "adult_room")
	private WebElement adultsPerRoomField;

	@FindBy(id = "child_room")
	private WebElement childrenPerRoomField;

	@FindBy(id = "Submit")
	private WebElement searchButton;

	@FindBy(xpath = "html/body/table[2]/tbody/tr[1]/td[2]/a[2]")
	private WebElement bookedItineraryButton;

	@FindBy(xpath = "html/body/table[2]/tbody/tr[1]/td[2]/a[3]")
	private WebElement changePasswordButton;

	@FindBy(xpath = ".//*[@id='search_form']/table/tbody/tr[1]/td")
	private WebElement pageHeader;

	public HotelSearchPage(WebDriver driver)
	{
		super(driver);
	}

	public HotelSearchPage chooseHotel(String hotelName)
	{
		Select hotelsSelect = new Select(this.hotelField);
		hotelsSelect.selectByValue(hotelName);
		return this;
	}

	public HotelSearchPage chooseLocation(String locationName)
	{
		Select locationSelect = new Select(this.locationField);
		locationSelect.selectByValue(locationName);
		return this;
	}

	public HotelSearchPage chooseRoomType(String roomType)
	{
		new Select(this.roomTypeField).selectByValue(roomType);
		return this;
	}

	public HotelSearchPage chooseNrOfRooms(String nrOfRooms)
	{
		new Select(this.nrOfRoomsField).selectByValue(nrOfRooms);
		return this;
	}

	public HotelSearchPage chooseCheckInDate(String date)
	{
		checkInDateField.clear();
		checkInDateField.sendKeys(date);
		return this;
	}

	public HotelSearchPage chooseCheckOutDate(String date)
	{
		checkOutDateField.clear();
		checkOutDateField.sendKeys(date);
		return this;
	}

	public HotelSearchPage chooseNrOfAdults(String nrOfAdults)
	{
		new Select(this.adultsPerRoomField).selectByValue(nrOfAdults);
		return this;
	}

	public HotelSearchPage chooseNrOfChildren(String nrOfChrildren)
	{
		new Select(this.childrenPerRoomField).selectByValue(nrOfChrildren);
		return this;
	}

	public void pressSearch()
	{
		searchButton.click();
	}

	public String getCheckInDateError()
	{

		return checkInDateError.getText();
	}

	public String getCheckOutDateError()
	{

		return checkOutDateError.getText();
	}

	public ItineraryPage pressBookedItineraryButton()
	{
		bookedItineraryButton.click();
		return new ItineraryPage(getDriver());
	}

	public String getPageHeaderText()
	{
		return pageHeader.getText().trim();
	}

}
