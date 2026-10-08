package ECommerce;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SouceLabs {
	public static void main(String[] args) throws InterruptedException {
		//open Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		Thread.sleep(3000);
		
		//Login Flow
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(3000);
		
		WebElement dropdown = driver.findElement(By.className("product_sort_container"));
		Select sort = new Select(dropdown);

		List<WebElement> options = sort.getOptions();
		for(WebElement e : options) {
			System.out.println(e.getText());
			}
		sort.selectByVisibleText("Price (low to high)");
		Thread.sleep(3000);
		
		dropdown = driver.findElement(By.className("product_sort_container"));
		Select newsort = new Select(dropdown);

		WebElement selected = newsort.getFirstSelectedOption();

		if(selected.getText().equals("Price (low to high)")) {
			System.out.println("Sucessfully Selected"); 
			System.out.println("Selected option : "+selected.getText());
		}else { 
			System.out.println("Wrong Option Selected");
			}

		List<WebElement> products = driver.findElements(By.className("inventory_item_description"));

		String name = "";
		double price = Double.MAX_VALUE;
		WebElement element = null;

		for(WebElement e : products) {
			String productName = e.findElement(By.className("inventory_item_name")).getText();
			String productPrice = e.findElement(By.className("inventory_item_price")).getText();
			productPrice = productPrice.replace("$","");
			double p = Double.parseDouble(productPrice);
			System.out.println(productName + " "+productPrice);
			
			if(price>p) {
				price=p;
				element=e;
				name=productName;
				}
			}
		element.findElement(By.className("btn_inventory")).click();
		driver.findElement(By.className("shopping_cart_link")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.className("checkout_button")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.id("first-name")).sendKeys("Shalini");
		driver.findElement(By.id("last-name")).sendKeys("Sundararajan");
		driver.findElement(By.id("postal-code")).sendKeys("636107");
        Thread.sleep(3000);
        
		driver.findElement(By.id("continue")).click();
		Thread.sleep(3000);
		
		String checkoutProductName = driver.findElement(By.className("inventory_item_name")).getText();

		if(checkoutProductName.equals(name)) {
			System.out.println("Test Passed : "+name);
			}else {
			System.out.println("Test Faied Different Product Selected");
			}
		
		String tax = driver.findElement(By.className("summary_tax_label")).getText();
		String Total = driver.findElement(By.className("summary_total_label")).getText();

		System.out.println("ORDER PLACED");
		System.out.println("Prduct Description :");
		System.out.println("Name : "+name);
		System.out.println("Product Price : "+price);
		System.out.println("Tax : "+tax);
		System.out.println("Total : "+Total);
		
		driver.findElement(By.id("finish")).click();
		Thread.sleep(3000);
		
		driver.quit();
		}
	}