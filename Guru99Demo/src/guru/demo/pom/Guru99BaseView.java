package guru.demo.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Guru99BaseView {
	
	RemoteWebDriver driver = null;
	
	private By newCustomerButton = By.xpath("//*[text()='New Customer']");
	private By editCustomerButton = By.xpath("//*[text()='Edit Customer']");
	private By deleteCustomerButton = By.xpath("//*[text()='Delete Customer']");
	
	public Guru99BaseView(RemoteWebDriver driver){
		this.driver = driver;
	}
	
	public Guru99NewCustomerView clickNewCustomerButton(){
		driver.findElement(newCustomerButton).click();
		return new Guru99NewCustomerView(driver);
	}
	
}
