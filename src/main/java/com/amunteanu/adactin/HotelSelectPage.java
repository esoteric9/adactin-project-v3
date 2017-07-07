package com.amunteanu.adactin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amunteanu.helpers.BasicPage;

public class HotelSelectPage extends BasicPage
{
	@FindBy(id = "radiobutton_0")
	private WebElement radioButton0;

	@FindBy(id = "radiobutton_1")
	private WebElement radioButton1;

	@FindBy(id = "radiobutton_2")
	private WebElement radioButton2;

	@FindBy(id = "radiobutton_3")
	private WebElement radioButton3;

	@FindBy(id = "radiobutton_4")
	private WebElement radioButton4;

	@FindBy(id = "continue")
	private WebElement continueButton;

	@FindBy(id = "location_0")
	private WebElement hotel0LocationField;

	@FindBy(id = "location_1")
	private WebElement hotel1LocationField;

	@FindBy(id = "location_2")
	private WebElement hotel2LocationField;

	@FindBy(id = "location_3")
	private WebElement hotel3LocationField;

	@FindBy(id = "location_4")
	private WebElement hotel4LocationField;

	@FindBy(id = "arr_date_0")
	private WebElement hotel0checkInDateField;

	@FindBy(id = "dep_date_0")
	private WebElement hotel0checkOutDateField;

	@FindBy(id = "rooms_0")
	private WebElement nrOfRoomsField;

	@FindBy(id = "room_type_0")
	private WebElement roomTypeField;

	public HotelSelectPage(WebDriver driver)
	{
		super(driver);
	}

	public HotelSelectPage selectHotel(int hotelNr)
	{
		switch (hotelNr)
		{
		case 0:
			radioButton0.click();
			break;
		case 1:
			radioButton1.click();
			break;
		case 2:
			radioButton2.click();
			break;
		case 3:
			radioButton3.click();
			break;
		case 4:
			radioButton4.click();
			break;
		default:
			break;
		}
		return this;
	}

	public BookAHotelPage pressContinue()
	{
		continueButton.click();
		return new BookAHotelPage(getDriver());
	}

	public String getLocationText(int hotelNr)
	{
		switch (hotelNr)
		{
		case 0:
			return hotel0LocationField.getAttribute("value");
		case 1:
			return hotel1LocationField.getAttribute("value");
		case 2:
			return hotel2LocationField.getAttribute("value");
		case 3:
			return hotel3LocationField.getAttribute("value");
		case 4:
			return hotel4LocationField.getAttribute("value");
		default:
			return " Invalid hotel number entered. ";
		}
	}

	public String getHotel0CheckInDateText()
	{
		return hotel0checkInDateField.getAttribute("value");
	}

	public String getHotel0CheckOutDateText()
	{
		return hotel0checkOutDateField.getAttribute("value");
	}

	public String getRoomTypeText()
	{
		return roomTypeField.getAttribute("value");
	}

	public String getNrOfRoomsText()
	{
		return nrOfRoomsField.getAttribute("value");
	}

}
