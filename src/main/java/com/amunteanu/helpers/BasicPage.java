/**
 * File Name: BasicPage.java<br>
 * Munteanu, Alex<br>
 * Java Boot Camp Exercise<br>
 * Instructor: Jean-francois Nepton<br>
 * Created: Feb 4, 2017
 */
package com.amunteanu.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

/**
 * BasicPage //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Munteanu, Alex
 * @version 1.0.0
 * @since 1.0
 */
public class BasicPage extends Core {

	public BasicPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
}
