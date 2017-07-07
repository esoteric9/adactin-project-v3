package com.amunteanu.adactin.test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amunteanu.adactin.BookAHotelPage;
import com.amunteanu.adactin.ChangePasswordPage;
import com.amunteanu.adactin.HotelSearchPage;
import com.amunteanu.adactin.HotelSelectPage;
import com.amunteanu.adactin.ItineraryPage;
import com.amunteanu.adactin.LoginPage;
import com.amunteanu.adactin.LogoutPage;
import com.amunteanu.helpers.AutoBasics;
import com.amunteanu.helpers.BasicTest;

public class AdactinTest extends BasicTest
{
	String username = "amunteanu";
	String password = "sqasolution";
	private String firstName = "Alex";
	private String lastName = "Munteanu";
	private String address = "999 John Doe Dr, Daly City, CA, 94132";
	private String creditCardNr = "9999999999999999";
	private String cvvNr = "333";
	private String ccType = "VISA";
	private String ccExpMonth = "1";
	private String ccExpYear = "2022";
	LocalDateTime currentTime = LocalDateTime.now();
	LocalDate currentDate = currentTime.toLocalDate();

	public AdactinTest()
	{
		super("http://www.adactin.com/HotelApp");
	}

	@DataProvider
	public Object[][] dp()
	{
		return new Object[][] { new Object[] { "Sydney", "Hotel Creek", "Standard", "1", "1", "1" } };
	}

	@DataProvider
	public Object[][] dp2()
	{
		return new Object[][] { new Object[] { "Sydney", "Hotel Creek", "Standard", "1", "1", "1", "135", "148.5" } };
	}

	@DataProvider
	public Object[][] dp3()
	{
		return new Object[][] { new Object[] { "Sydney", "Hotel Creek", "Standard", "1", "1", "1" },
				new Object[] { "Sydney", "Hotel Sunshine", "Double", "2", "2", "2" },
				new Object[] { "Brisbane", "Hotel Hervey", "Deluxe", "3", "3", "3" },
				new Object[] { "Adelaide", "Hotel Cornice", "Super Deluxe", "4", "4", "4" } };
	}

	@Test()
	public void TC101()
	{
		/** 
		 * Purpose: To verify valid login Details
		 */
		getLogger().info("Adactin login test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password);
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		takeScreenshot("TC-101");
	}

	@Test(dataProvider = "dp")
	public void TC102(String location, String hotel, String roomType, String nrOfRooms, String nrOfAdults,
			String nrOfChildren)
	{
		/** 
		 * Purpose: To verify whether the check-in date field accepts a later date than check-out date
		 */
		getLogger().info("Adactin later checkout date test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password).chooseLocation(location).chooseHotel(hotel)
				.chooseCheckInDate(AutoBasics.convertDateToDayMonthYearFormat(currentDate.plusDays(7)))
				.chooseCheckOutDate(AutoBasics.convertDateToDayMonthYearFormat(currentDate.plusDays(5)))
				.chooseNrOfAdults(nrOfAdults).chooseNrOfChildren(nrOfChildren).pressSearch();
		HotelSearchPage hotelSearch = new HotelSearchPage(getDriver());
		Assert.assertEquals(hotelSearch.getCheckInDateError(), "Check-In Date shall be before than Check-Out Date");
		Assert.assertEquals(hotelSearch.getCheckOutDateError(), "Check-Out Date shall be after than Check-In Date");
		takeScreenshot("TC-102");
	}

	@Test(dataProvider = "dp")
	public void TC103(String location, String hotel, String roomType, String nrOfRooms, String nrOfAdults,
			String nrOfChildren)
	{
		/** 
		 * Purpose: To verify if error is reported if check-out date field is in the past
		 * Note: Currently fails, is a bug
		 */
		getLogger().info("Adactin dates in the past test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password).chooseLocation(location).chooseHotel(hotel)
				.chooseCheckInDate(AutoBasics.convertDateToDayMonthYearFormat(currentDate.minusDays(5)))
				.chooseCheckOutDate(AutoBasics.convertDateToDayMonthYearFormat(currentDate.minusDays(3)))
				.chooseNrOfAdults(nrOfAdults).chooseNrOfChildren(nrOfChildren).pressSearch();
		HotelSearchPage hotelSearch = new HotelSearchPage(getDriver());
		Assert.assertEquals(hotelSearch.getCheckInDateError(), "Enter Valid Dates");
		Assert.assertEquals(hotelSearch.getCheckOutDateError(), "Enter Valid Dates");
		takeScreenshot("TC-103");
	}

	@Test(dataProvider = "dp")
	public void TC104(String location, String hotel, String roomType, String nrOfRooms, String nrOfAdults,
			String nrOfChildren)
	{
		/** 
		 * Purpose: To verify whether locations in Select Hotel page are displayed according to the location selected on Search Hotel page
		 */
		getLogger().info("Adactin hotel location test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password).chooseLocation(location).chooseHotel(hotel).chooseRoomType(roomType)
				.chooseCheckInDate(AutoBasics.convertDateToDayMonthYearFormat(currentDate.minusDays(5)))
				.chooseCheckOutDate(AutoBasics.convertDateToDayMonthYearFormat(currentDate.minusDays(3)))
				.chooseNrOfAdults(nrOfAdults).chooseNrOfChildren(nrOfChildren).pressSearch();
		HotelSelectPage hotelSelect = new HotelSelectPage(getDriver());
		Assert.assertEquals(hotelSelect.getLocationText(0), location);
		takeScreenshot("TC-104");
	}

	@Test(dataProvider = "dp")
	public void TC105(String location, String hotel, String roomType, String nrOfRooms, String nrOfAdults,
			String nrOfChildren)
	{
		/** 
		 * Purpose: To verify whether check-in date and check-out date are displayed on Select Hotel page according to the dates selected on Search Hotel page
		 */
		getLogger().info("Adactin check-in and check-out dates same as selected test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password).chooseLocation(location).chooseHotel(hotel).chooseRoomType(roomType)
				.chooseNrOfAdults(nrOfAdults).chooseNrOfChildren(nrOfChildren).pressSearch();
		HotelSelectPage hotelSelect = new HotelSelectPage(getDriver());
		Assert.assertEquals(hotelSelect.getHotel0CheckInDateText(),
				AutoBasics.convertDateToDayMonthYearFormat(this.currentDate));
		Assert.assertEquals(hotelSelect.getHotel0CheckOutDateText(),
				AutoBasics.convertDateToDayMonthYearFormat(currentDate.plusDays(1)));
		takeScreenshot("TC-105");
	}

	@Test(dataProvider = "dp")
	public void TC106(String location, String hotel, String roomType, String nrOfRooms, String nrOfAdults,
			String nrOfChildren)
	{
		/** 
		 * Purpose: To verify whether number of rooms on Select Hotel page is the same as the nr. of rooms selected on Search Hotel page
		 */
		getLogger().info("Adactin nr of rooms same as selected test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password).chooseLocation(location).chooseHotel(hotel).chooseRoomType(roomType)
				.chooseNrOfAdults(nrOfAdults).chooseNrOfChildren(nrOfChildren).pressSearch();
		HotelSelectPage hotelSelect = new HotelSelectPage(getDriver());
		String[] stringArray = hotelSelect.getNrOfRoomsText().split(" ");
		Assert.assertEquals(stringArray[0], "1");
		takeScreenshot("TC-106");
	}

	@Test(dataProvider = "dp")
	public void TC107(String location, String hotel, String roomType, String nrOfRooms, String nrOfAdults,
			String nrOfChildren)
	{
		/** 
		 * Purpose: To verify whether room type on Select Hotel page is the same as room type selected on Search Hotel page
		 */
		getLogger().info("Adactin room type same as selected test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password).chooseLocation(location).chooseHotel(hotel).chooseRoomType(roomType)
				.chooseNrOfAdults(nrOfAdults).chooseNrOfChildren(nrOfChildren).pressSearch();
		HotelSelectPage hotelSelect = new HotelSelectPage(getDriver());
		Assert.assertEquals(hotelSelect.getRoomTypeText(), roomType);
		takeScreenshot("TC-107");
	}

	@Test(dataProvider = "dp2")
	public void TC108(String location, String hotel, String roomType, String nrOfRooms, String nrOfAdults,
			String nrOfChildren, String totalPrice, String finalPrice)
	{
		/** 
		 * Purpose: To verify whether the total price is calculated as "price per night * nr od nights * nr of rooms"
		 */
		getLogger().info("Adactin total price test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password).chooseLocation(location).chooseHotel(hotel).chooseRoomType(roomType)
				.chooseNrOfAdults(nrOfAdults).chooseNrOfChildren(nrOfChildren).pressSearch();
		HotelSelectPage hotelSelect = new HotelSelectPage(getDriver());
		BookAHotelPage bookAHotel = hotelSelect.selectHotel(0).pressContinue();
		Assert.assertEquals(bookAHotel.getTotalPrice().split(" ")[2], totalPrice);
		takeScreenshot("TC-108");
	}

	@Test(dataProvider = "dp")
	public void TC109(String location, String hotel, String roomType, String nrOfRooms, String nrOfAdults,
			String nrOfChildren)
	{
		/** 
		 * Purpose: To verify that pressing the Logout button after completing booking logs the user out of the application
		 */
		getLogger().info("Adactin logout after booking test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password).chooseLocation(location).chooseHotel(hotel).chooseRoomType(roomType)
				.chooseNrOfAdults(nrOfAdults).chooseNrOfChildren(nrOfChildren).pressSearch();
		HotelSelectPage hotelSelect = new HotelSelectPage(getDriver());
		BookAHotelPage bookAHotel = hotelSelect.selectHotel(0).pressContinue();
		bookAHotel.enterFirstName(firstName).enterLastName(lastName).enterAddress(address)
				.enterCreditCardNr(creditCardNr).enterCreditCardType(ccType).enterCreditCardExpMonth(ccExpMonth)
				.enterCreditCardExpYear(ccExpYear).enterCreditCardCVV(cvvNr).pressBookNow();
		(new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("logout")));
		AutoBasics.checkPageIsReady(getDriver());
		bookAHotel.pressLogoutBtn();
		LogoutPage logout = new LogoutPage(getDriver());
		Assert.assertEquals(logout.getLogoutMessage(), "You have successfully logged out. Click here to login again");
		takeScreenshot("TC-109");
	}

	@Test(dataProvider = "dp2")
	public void TC110(String location, String hotel, String roomType, String nrOfRooms, String nrOfAdults,
			String nrOfChildren, String totalPrice, String finalPrice)
	{
		/** 
		 * Purpose: To verify whether the final price is calculated as "price per night * nr od nights * nr of rooms + GST"
		 */
		getLogger().info("Adactin final price test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password).chooseLocation(location).chooseHotel(hotel).chooseRoomType(roomType)
				.chooseNrOfAdults(nrOfAdults).chooseNrOfChildren(nrOfChildren).pressSearch();
		HotelSelectPage hotelSelect = new HotelSelectPage(getDriver());
		BookAHotelPage bookAHotel = hotelSelect.selectHotel(0).pressContinue();
		Assert.assertEquals(bookAHotel.getFinalPrice().split(" ")[2], finalPrice);
		takeScreenshot("TC-110");
	}

	@Test(dataProvider = "dp")
	public void TC114(String location, String hotel, String roomType, String nrOfRooms, String nrOfAdults,
			String nrOfChildren)
	{
		/** 
		 * Purpose: To verify that an order number is generated in booking confirmation page
		 */
		getLogger().info("Adactin booking confirmation number test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password).chooseLocation(location).chooseHotel(hotel).chooseRoomType(roomType)
				.chooseNrOfAdults(nrOfAdults).chooseNrOfChildren(nrOfChildren).pressSearch();
		HotelSelectPage hotelSelect = new HotelSelectPage(getDriver());
		BookAHotelPage bookAHotel = hotelSelect.selectHotel(0).pressContinue();
		bookAHotel.enterFirstName(firstName).enterLastName(lastName).enterAddress(address)
				.enterCreditCardNr(creditCardNr).enterCreditCardType(ccType).enterCreditCardExpMonth(ccExpMonth)
				.enterCreditCardExpYear(ccExpYear).enterCreditCardCVV(cvvNr).pressBookNow();
		(new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("logout")));
		Assert.assertNotNull(bookAHotel.getOrderNr());
		Assert.assertNotEquals(bookAHotel.getOrderNr(), "");
		Assert.assertNotEquals(bookAHotel.getOrderNr(), " ");
		takeScreenshot("TC-114");
	}

	@Test(dataProvider = "dp")
	public void TC115(String location, String hotel, String roomType, String nrOfRooms, String nrOfAdults,
			String nrOfChildren)
	{
		/** 
		 * Purpose: To verify whether the booked itinerary details are not editable
		 * Note: fails, is a bug
		 */
		getLogger().info("Adactin itinerary details not editable test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password).chooseLocation(location).chooseHotel(hotel).chooseRoomType(roomType)
				.chooseNrOfAdults(nrOfAdults).chooseNrOfChildren(nrOfChildren).pressSearch();
		HotelSelectPage hotelSelect = new HotelSelectPage(getDriver());
		BookAHotelPage bookAHotel = hotelSelect.selectHotel(0).pressContinue();
		bookAHotel.enterFirstName(firstName).enterLastName(lastName).enterAddress(address)
				.enterCreditCardNr(creditCardNr).enterCreditCardType(ccType).enterCreditCardExpMonth(ccExpMonth)
				.enterCreditCardExpYear(ccExpYear).enterCreditCardCVV(cvvNr).pressBookNow();
		(new WebDriverWait(getDriver(), 15)).until(ExpectedConditions.presenceOfElementLocated(By.id("my_itinerary")));
		AutoBasics.checkPageIsReady(getDriver());
		bookAHotel.pressItineraryBtn();
		ItineraryPage itinerary = new ItineraryPage(getDriver());
		Assert.assertEquals(itinerary.getFirstRowOrderNr().isEnabled(), false);
		takeScreenshot("TC-115");
	}

	@Test(dataProvider = "dp")
	public void TC119(String location, String hotel, String roomType, String nrOfRooms, String nrOfAdults,
			String nrOfChildren)
	{
		/** 
		 * Purpose: To verify that the order gets cancelled after click on Cancel order button
		 */
		getLogger().info("Adactin cancel order test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password).chooseLocation(location).chooseHotel(hotel).chooseRoomType(roomType)
				.chooseNrOfAdults(nrOfAdults).chooseNrOfChildren(nrOfChildren).pressSearch();
		HotelSelectPage hotelSelect = new HotelSelectPage(getDriver());
		BookAHotelPage bookAHotel = hotelSelect.selectHotel(0).pressContinue();
		bookAHotel.enterFirstName(firstName).enterLastName(lastName).enterAddress(address)
				.enterCreditCardNr(creditCardNr).enterCreditCardType(ccType).enterCreditCardExpMonth(ccExpMonth)
				.enterCreditCardExpYear(ccExpYear).enterCreditCardCVV(cvvNr).pressBookNow();
		(new WebDriverWait(getDriver(), 15)).until(ExpectedConditions.presenceOfElementLocated(By.id("my_itinerary")));
		String orderNr = bookAHotel.getOrderNr();
		AutoBasics.checkPageIsReady(getDriver());
		bookAHotel.pressItineraryBtn();
		ItineraryPage itinerary = new ItineraryPage(getDriver());
		itinerary.enterOrderNrinSearchField(orderNr).pressSearchBtn().pressFirstRowCancelBtn();
		getDriver().switchTo().alert().accept();
		Assert.assertEquals(itinerary.getErrorMessageText(), "The booking has been cancelled.");
		itinerary.enterOrderNrinSearchField(orderNr).pressSearchBtn();
		Assert.assertEquals(itinerary.getErrorMessageText(), "0 result(s) found. Show all");
		takeScreenshot("TC-119");
	}

	@Test()
	public void TC120()
	{
		/** 
		 * Purpose: To verify the title of every page
		 */
		getLogger().info("Adactin page title test");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password).pressBookedItineraryButton();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Select Hotel");
		ItineraryPage itinerary = new ItineraryPage(getDriver());
		Assert.assertEquals(itinerary.getPageHeaderText(), "Booked Itinerary");
		Assert.assertEquals(itinerary.pressChangePasswordButton().getPageHeaderText(), "Change Password");
		HotelSearchPage searchHotel = new ChangePasswordPage(getDriver()).pressSearchHotelButton();
		Assert.assertEquals(searchHotel.getPageHeaderText(),
				"Search Hotel (Fields marked with Red asterix (*) are mandatory)");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		takeScreenshot("TC-120");
	}

}
