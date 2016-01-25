package guru.demo.pom;

import org.openqa.selenium.remote.RemoteWebDriver;

public class Guru99NewCustomerView extends Guru99BaseView{
	private String title = "Guru99 Bank New Customer Entry Page";
	
	public Guru99NewCustomerView(RemoteWebDriver driver) {
		super(driver);
		String title = driver.getTitle();
		if(!this.title.equals(title)){
			throw new IllegalStateException("Page title: '" + title + "' is not as expected" );
		}
	}

}
