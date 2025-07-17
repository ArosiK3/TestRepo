package StepDefination;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefination {

	String prodname = "IPHONE 13 PRO";
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

	@Given("I landed on Ecommerce page")
	public void I_landed_on_ecom_page() {
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void login_with_userCredentials(String username, String password) {
		driver.findElement(By.id("userEmail")).sendKeys(username);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.name("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Automation Practice\"]")));
		System.out.println(driver.getTitle());
	}

	@When("^I add product (.+) to cart$")
	public void add_product_toCart(String productName) {
		List<WebElement> elements = driver.findElements(By.xpath("//button[@class='btn w-10 rounded']"));
		System.out.println(elements.size());
		for (WebElement webElement : elements) {
			webElement.click();
			Sleep(2500);
		}
		Sleep(2000);

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		Sleep(2000);

		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		Sleep(300);

	}

	@And("Checkout and submit the order")
	public void checkou_and_submitOrder() {
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartproducts.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(prodname));
		Assert.assertTrue(match);
		Sleep(300);

		JavascriptExecutor down = (JavascriptExecutor) driver;
		down.executeScript("window.scrollBy(0,500)");
		Sleep(1000);

		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		Sleep(1500);

		JavascriptExecutor up = (JavascriptExecutor) driver;
		up.executeScript("window.scrollBy(0,-200)");
		Sleep(1500);
	}

	@Then("{string} message is displayed")
	public void order_message(String string) {
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//span[normalize-space(text())='India']")).click();
		Sleep(3000);

		driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
		Sleep(1500);
		String actualText = driver.findElement(By.cssSelector(".hero-primary")).getText().trim();
		Assert.assertEquals(actualText, string);
		Sleep(2000);

		driver.quit();
	}

	public static void Sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
