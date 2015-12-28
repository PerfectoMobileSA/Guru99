package guru.demo.pom;

import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This class is part of the demo Guru99 Page Object Model repository.
 * The class represents the view after login was done.
 * @author avner
 *
 */
public class Guru99Manager {
	private RemoteWebDriver driver = null;
	private String title = "Guru99 Bank Manager HomePage";
	
	/**
	 * The constructor verifies the correct page is loaded, by checking the title of the page.
	 * If the wrong title is displayed, an {@link IllegalStateExcetion} exception is thrown.
	 * @param driver
	 */
	public Guru99Manager(RemoteWebDriver driver){
		this.driver = driver;
		String title = driver.getTitle();
		if(!this.title.equals(title)){
			throw new IllegalStateException("Page title: '" + title + "' is not as expected" );
		}
	}
}
