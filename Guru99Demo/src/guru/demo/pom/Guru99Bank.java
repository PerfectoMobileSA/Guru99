package guru.demo.pom;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.security.UserAndPassword;


/**
 * This class is part of Guru99 demo Page Object Model repository.
 * This is the base class which goes to the login page.
 * In order to get started, create an istance of this class and then call the {@link init()} method.
 * @author avner
 *
 */
public class Guru99Bank {
	private RemoteWebDriver driver = null;
	private String url = "demo.guru99.com/v4";
	
	private By usernameField = By.xpath("//input[@name='uid']");
	private By passwordField = By.xpath("//input[@type='password']");
	private By loginButton = By.xpath("//input[@name='btnLogin']");
	
	/**
	 *  The constructor initialize the {@link RemoteWebDriver driver} as a class member.
	 * @param driver
	 */
	public Guru99Bank(RemoteWebDriver driver){
		this.driver = driver;
	}
	
	/** The init configures the driver waits and loads the page
	 * 
	 * @return an instance of the calling class
	 */
	public Guru99Bank init(){
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
		
		return this;
	}
	
	/**
	 *  Type the {@link String} parameter in the username text field
	 * @param String
	 * @return an instance of the calling class
	 */
	public Guru99Bank setUser(String username){
		driver.findElement(usernameField).sendKeys(username);
		
		return this;
	}
	
	/**
	 *  Type the {@link String} parameter in the password text field
	 * @param String
	 * @return an instance of the calling class
	 */
	public Guru99Bank setPassword(String password){
		driver.findElement(passwordField).sendKeys(password);
		
		return this;
	}
	
	/**
	 * Clicks the login button
	 * @return an instance of {@link Guru99Manager}.
	 */
	public Guru99Manager login(){
		driver.findElement(loginButton).click();
		return new Guru99Manager(driver);
	}
	
}