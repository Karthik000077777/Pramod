/*
Test-2
1. Click on Add to cart
2. Verify successful message. Use Assertion.
3. Verify items count. Use Assertion. 
4. Verify added products should get added on items cart page. Compare details with details on product catalog- Products name, Unit Price, Quantity and Total price. Use Assertion.








*/

package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Test_Scenarios.Test_Methods;

public class Add_To_Cart extends Test_Methods {

	WebDriver driver;
	static int ipodNano_Count;
	static int ipodClassic_Count;
	static double final_Price_2;
	static double final_Price_1;

	public Add_To_Cart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//input[@value='Add to Cart'])[1]")
	WebElement iPodClassic_AddToCart;

	@FindBy(xpath = "(//input[@value='Add to Cart'])[2]")
	WebElement iPodNano_AddToCart;

	@FindBy(xpath = "//*[@class='alert alert-success alert-dismissible']")
	WebElement addToCart_Message;

	@FindBy(xpath = "//*[@id='cart-total']/text()")
	WebElement cartCount;

	@FindBy(xpath = "(//table[@class='table table-bordered'])[1]/tbody/tr[2]/td[2]")
	WebElement Total_Cart_Price;

	

	@FindBy(xpath = "//*[@id='cart-total']/text()")
	WebElement cart_Count;

	@FindBy(xpath = "//table[@class='table table-striped']/tbody/tr")
	List<WebElement> cart_List;

	@FindBy(xpath = "//*[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")
	WebElement click_Cart;

//	@FindBy(xpath = "")
//	WebElement ;

	public void click_iPodClassic_AddToCart() {
		iPodClassic_AddToCart.click();
		ipodClassic_Count++; //we call this method to add this product to cart, so we can use a counter within this method to know how many times it is called, which also means it is same as quantity, called once then it will be 1 and when called again it will be 2.
	}

	public void click_iPodNano_AddToCart() {
		iPodNano_AddToCart.click();
		ipodNano_Count++;

	}

	public String total_Price_Of_iPodNano() {
		double prod2_Price = Double.parseDouble(product_2_Price.substring(1, 7));
		System.out.println("TOTAL PRICE, WebElement price is>>>> "+ prod2_Price+"\n");
		final_Price_2 = ipodNano_Count * prod2_Price;
		System.out.println("Total Price, logic price is>>>> "+final_Price_2+"\n");
		return String.valueOf(final_Price_2);
	}

	public String total_Price_Of_iPodClassic() {
		double prod1_Price = Double.parseDouble(product_1_Price.substring(1, 7));
		System.out.println("TOTAL PRICE, WebElement price is>>>>" + prod1_Price+"\n");
		final_Price_1 = ipodClassic_Count * prod1_Price;
		System.out.println("Total Price, logic price is>>>> "+final_Price_1+"\n");
		return String.valueOf(final_Price_1);
	}

	public boolean verify_Total_Cart_Price() {
		double cart_T = Double.parseDouble(Total_Cart_Price.getText().substring(1, 7));
		if (cart_T == final_Price_1 + final_Price_2){
			System.out.println("TOTAL MONEY IS SAME..............\n");
			return true;
		}
		return false;
	}

	public void click_Cart() {
		click_Cart.click();
		System.out.println("CLICKED ON CART>>>>>\n");
	}

	public List<String> get_Cart_Data() {

		System.out.println("IN CART_DATA");
		List<String> li = new ArrayList<>();
		for (WebElement ele : cart_List) {
			System.out.println("DATA IN CART IS "+ele.getText()+"\n");
			li.add(ele.getText());
		}
//		System.out.println(cart_List.get(0).getText());
//		System.out.println(cart_List.get(1).getText());
//		System.out.println(cart_List.size());
		return li;
	}

	public boolean cartCompare(String compareWithName, String compareWithPrice) {
		// TODO Auto-generated method stub
		//first if() will check for name, if true,
		// second if() will check for price, if true,
		//third if() will check for quantity, in this two 2 if's() to check quantity for ipod classic and ipod_nano
		List<String> cList = get_Cart_Data();
		for (int i = 0; i < cList.size(); i++) {
			if (cList.get(i).contains(compareWithName)) {
				System.out.println(compareWithName + " PRESENT IN " + cList.get(i)+"\n");
				if (cList.get(i).contains(compareWithPrice)) {
					System.out.println(compareWithPrice + " PRESENT IN " + cList.get(i)+"\n");
					if (compareWithName.equals("iPod Classic")) {
						if (cList.get(i).contains(String.valueOf(ipodClassic_Count))) {
							System.out.println(ipodClassic_Count + " PRESENT IN " + cList.get(i)+"\n");
							return true;
						}
					} else if (compareWithName.equals("iPod Nano")) {
						if (cList.get(i).contains(String.valueOf(ipodNano_Count))) {
							System.out.println(ipodNano_Count + " PRESENT IN " + cList.get(i)+"\n");
							return true;
						}
					} else {
						return false;
					}
				}
			}
		}
		return false;
	}
}
