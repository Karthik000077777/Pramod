/*
 Test-1
1. Add any two products for comparison on product page(Click Compare icon) e.g iPod Nano and iPod Classic
2. Verify successful message. Use Assertion. 
3. Verify Product Compare count should be changed from 0 to 2. Use Assertion.
4. Click on Product Compare. Compare details with details on product catalog- Products name, Unit Price. Use Assertion.

 */

package pageObjects;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
	WebDriver driver;
	static int comp_Count=0;
	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//*[contains(text(),'MP3 Players')])[1]")
	WebElement hoverMp3Player;
	
	@FindBy(xpath = "//*[contains(text(),'Show All MP3 Players')]")
	WebElement showAllMp3Players;
	
	@FindBy(xpath = "(//*[@data-original-title='Compare this Product'])[1]")
	WebElement compare_ipodClassic;
	
	@FindBy(xpath = "(//*[@data-original-title='Compare this Product'])[2]")
	WebElement compare_ipodNano;
	
	@FindBy(xpath = "//*[@class='product-layout product-list col-xs-12']")
	WebElement products_In_List;
	
	@FindBy(xpath = "//*[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']")
	WebElement products_In_Grid;
	
	@FindBy(id = "list-view")
	WebElement listBtn;
	
	@FindBy(id = "grid-view")
	WebElement gridBtn;
	
	@FindBy(xpath = "//*[@class='alert alert-success alert-dismissible']")
	WebElement compareMsg;
	
	@FindBy(id = "compare-total")
	WebElement compareCount;
	
	@FindBy(partialLinkText = "Product Compare")
	WebElement clickProductCompare;
	
	@FindBy(xpath = "//*[@id='content']/table/tbody[1]/tr[1]/td[2]/a/strong")
	WebElement ipodClassic_Name;
	
	@FindBy(xpath = "//*[@id='content']/table/tbody[1]/tr[1]/td[3]/a/strong")
	WebElement ipodNano_Name;
	
	@FindBy(xpath = "//*[@id='content']/table/tbody[1]/tr[3]/td[2]")
	WebElement product_1_Price_C;
	
	@FindBy(xpath = "//*[@id='content']/table/tbody[1]/tr[3]/td[3]")
	WebElement product_2_Price_C;
	
	
	@FindBy(xpath = "(//*[@class='caption'])[1]/h4/a")
	WebElement product_1_Name;
	
	@FindBy(xpath = "(//*[@class='caption'])[2]/h4/a")
	WebElement product_2_Name;
	
	@FindBy(xpath = "(//*[@class='caption'])[1]/p[2]")
	WebElement product_1_Price;
	
	@FindBy(xpath = "(//*[@class='caption'])[2]/p[2]")
	WebElement product_2_Price;
	
	@FindBy(xpath = "(//*[contains(text(),'Remove')])[1]")
	WebElement compare_Remove_ipodClassic;
	
	
	@FindBy(xpath = "//select[@id='input-sort']")
	WebElement selectSort;
	
	@FindBy(xpath = "//div[@class='caption']/h4/a")
	List<WebElement> sortedList ;
	
//	@FindBy(xpath = "")
//	WebElement ;
	
	
	public void move_To_Mp3Player() throws InterruptedException {
		Actions ac = new Actions(driver);
		ac.moveToElement(hoverMp3Player).perform();
		System.out.println(">>>>>>>>>Moved to Mp3 Players present in NAVIGATION\n");
	}
	
	public void click_ShowAll_Mp3Players() {
		showAllMp3Players.click();
		System.out.println(">>>>>>>>>>clicked showAllMp3Players\n");
	}
	
	public void click_compare_ipodClassic() {
		compare_ipodClassic.click();
		System.out.println("CLICKED Classic");
		comp_Count++;
		
	}
	
	public void click_Compare_ipodNano() throws InterruptedException {
		  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",compare_ipodNano);
          Thread.sleep(5000);
		compare_ipodNano.click();
		System.out.println("CLICKED NANO");
		comp_Count++;
	}
	
//	public boolean verify_Compare_Msg() {
//		String comp_Msg = compareMsg.getText();
//		return Assert.assertArrayEquals();
//	}
	
	public String get_Compare_Msg() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("COMPARE MESSAGE: "+compareMsg.getText());
		Thread.sleep(3000);
		return compareMsg.getText();
	}
	
	public String get_Product_1_Name() {
		System.out.println("PRODUCT_1 NAME IN PRODUCT CATALOG: "+product_1_Name.getText()+"\n");
		return product_1_Name.getText();
	}
	
	public String get_product_2_Name() {
		System.out.println("PRODUCT_2 NAME IN PRODUCT CATALOG: "+product_2_Name.getText()+"\n");
		return product_2_Name.getText();
	}
	
	public String get_Product_1_Price() {
		System.out.println("PRODUCT_1 PRICE IN PRODUCT CATALOG: "+product_1_Price.getText()+"\n");
		return product_1_Price.getText();
	}
	
	public String get_Product_2_Price() {
		System.out.println("PRODUCT_2 PRICE IN PRODUCT CATALOG: "+product_2_Price.getText()+"\n");
		return product_2_Price.getText();
	}
	
	public String get_Compare_Count() {
		System.out.println("COUNT OF PRODUCTS IN COMPARE: "+compareCount.getText()+"\n");
		return compareCount.getText();
	}
	
	public void click_Product_Compare() {
		clickProductCompare.click();
		System.out.println(">>>>>>>Clicked product comapare link");
	}
	
	public String get_Product_1_Name_C() {
		System.out.println("PRODUCT NAME 1 COMPARE: "+ipodClassic_Name.getText()+"\n");
		return ipodClassic_Name.getText();
	}
	
	public String get_product_2_Name_C() {
		System.out.println("PRODUCT NAME 2 COMPARE: "+ipodNano_Name.getText()+"\n");
		return ipodNano_Name.getText();
	}
	
	public String get_Product_1_Price_C() {
		System.out.println("PRODUCT PRICE 1: "+product_1_Price_C.getText()+"\n");
		return product_1_Price_C.getText();
	}
	
	public String get_Product_2_Price_C() {
		System.out.println("PRODUCT PRICE 2: "+product_2_Price_C.getText()+"\n");
		return product_2_Price_C.getText();
	}
	
	public boolean verify_Compare_Count() {
		int compare_Count_0 = Integer.parseInt(get_Compare_Count().substring(17, 18));
		System.out.println(">>>>>>>>>>>>>>COUNT_IN_WEB_IS: "+compare_Count_0+"\n"+"COUNT IN LOGIC IS: "+comp_Count+"\n");
		if(compare_Count_0 == comp_Count) {
			return true;
		}else {return false;}
	}
	
	public void remove_ipodClassic() {
		compare_Remove_ipodClassic.click();
		System.out.println("IPOD CLASSIC IS REMOVED FROM COMPARE>>\n");
		comp_Count--;
	}
	
	public boolean verify_Remove_ipodClassic() {
		if(ipodClassic_Name.getText().contains("iPod Classic")) {
			System.out.println("PRODUCT STILL EXITS\n");
			return true;
		}else {
			System.out.println("PRODUCT DOESN'T EXIST....YAHHH\n");
				return false;
				}
	}
	
	public void clickListView() {
		listBtn.click();
		System.out.println(">>>>>Clicked list view\n");
	}
	
	public void clickGridView() {
		gridBtn.click();
		System.out.println(">>>>>Clicked grid view\n");
	}
	
	public boolean verify_List_Layout() {
		return products_In_List.isDisplayed();
	}
	
	public boolean verify_Grid_Layout() {
		return products_In_Grid.isDisplayed();
	}
	public void selectSort() {
		Select s = new Select(selectSort);
		s.selectByIndex(2);;
	}
	public boolean verifySort() {
		boolean flag = false;
		for(int i=0;i<sortedList.size()-1;i++) {
			if((sortedList.get(i).getText().compareTo(sortedList.get(i+1).getText())) > 0) {
				System.out.println("Comparing "+sortedList.get(i).getText()+" with "+sortedList.get(i+1).getText()+" result is: "+sortedList.get(i).getText().compareTo(sortedList.get(i+1).getText())+"\n");
				flag=true;
			}else {flag = false;}
		}
		return flag;
	}
}
