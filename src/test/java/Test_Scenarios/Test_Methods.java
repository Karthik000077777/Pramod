package Test_Scenarios;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Add_To_Cart;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;


/* READ THE QUESTIONS(TEST 1.. TEST 2...... ALONG WITH THE APPROPRIATE CODE
 * FOR BETTER UNDERSTANDING.
 * 
 */

public class Test_Methods {
	WebDriver driver;
	FileReader reader = null;
	Properties props = new Properties();
	LoginPage lp; // creating object of this class to call it's methods in future.
	ProductsPage pp; // same as above
	Add_To_Cart atc; // same as above

	String product_1_Name; // ipod classic is saved in this.
	String product_2_Name; // ipod nano is saved in this.
	protected static String product_1_Price; // Using this variable data in Products class//
	protected static String product_2_Price; // Using this variable data in Products class//

	/*BELOW TEST WILL READ THE PROPERTIES FILE BY USING 
	 * props which is created in below code.
	 * 
	 * TO GET DATA FROM CONFIG FILE WE DO>>>>>>>
	 * 
	 * props.getProperty(KEY_NAME)------------ WE GET THE VALUE WITH THIS.
	 */
	@Test(priority = 0, enabled = true)
	public void readConfig() {
		
		try {
			reader = new FileReader(".//config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			props.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("OPENING URL: "+props.getProperty("URL"));
	}
	
	/*BELOW TEST WILL LAUNCH THE BROWSER
	 * 
	 * 
	 */

	@Test(priority = 1, enabled = true)
	public void open_Browser() {
		System.setProperty("webdriver.chrome.driver", ".//Drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(props.getProperty("URL"));
		System.out.println("URL IS OPENED>>>>>>>>>>>>>>>>>>>>");
	}
	
	/*BELOW TEST WILL LOGIN INTO THE ACCOUNT
	 * 
	 * 
	 */

	@Test(priority = 2, enabled = true)
	public void login() throws InterruptedException {
		lp = new LoginPage(driver); //
		lp.setEmailId(props.getProperty("emial"));
		lp.setPassword(props.getProperty("pass"));
		Thread.sleep(2000);
		lp.clickLoginBtn();
	}
	
	
	/*BELOW TEST WILL ADD THE PRODUCTS TO THE COMPARE LIST
	 * 
	 * 
	 * ALSO COMPARES THE DATA PRESENT IN THE PRODUCT CATALOG WITH THE DATA IN 
	 * COMPARE PRODUCTS PAGE
	 */

	@Test(priority = 3, enabled = true)
	public void compare_Products() throws InterruptedException {
		pp = new ProductsPage(driver);
		pp.move_To_Mp3Player();
		pp.click_ShowAll_Mp3Players();

		product_1_Name = pp.get_Product_1_Name();//saving ipodClassic name
		product_2_Name = pp.get_product_2_Name();//saving ipod nano name
		product_1_Price = pp.get_Product_1_Price().substring(0, 7);//saving ipodClassic price
		product_2_Price = pp.get_Product_2_Price().substring(0, 7);//saving ipod nano price

		Assert.assertTrue(pp.verify_Compare_Count()); //here verifying that compare products count is 0

		pp.click_compare_ipodClassic(); //adding ipodClassic to the compare list
		String comp_Msg_0 = pp.get_Compare_Msg(); // saving the message which is displayed after sending the product to compare list
		System.out.println("IN Main" + comp_Msg_0);
		Assert.assertEquals(comp_Msg_0, props.getProperty("verifyComMsgFor_iPod_Classic")); //verifying message after adding product to compare list

		pp.click_Compare_ipodNano(); //same as above
		String comp_Msg_1 = pp.get_Compare_Msg();//same as above
		System.out.println("IN Main" + comp_Msg_1);//same as above
		Assert.assertEquals(comp_Msg_1, props.getProperty("verifyComMsgFor_iPod_Nano"));//same as above

		Assert.assertTrue(pp.verify_Compare_Count());//here verifying that compare products count is 2

		pp.click_Product_Compare(); //clicking product compare(2) (opening product compare page)

		//.assertEquals(actual string, expected string)
		// expected string is considered as correct and we need this one to be present in actual string
		// actual string is the one we get from runtime
		// pp.get_Product_1_Name_C() returns data from compare product page
		// product_1_Name has value from products catalog page
		// We are comparing both in below lines
		
		Assert.assertEquals(pp.get_Product_1_Name_C(), product_1_Name);
		Assert.assertEquals(pp.get_product_2_Name_C(), product_2_Name);
		Assert.assertEquals(pp.get_Product_1_Price_C(), product_1_Price);
		Assert.assertEquals(pp.get_Product_2_Price_C(), product_2_Price);
	}

	/*BELOW TEST WILL ADD PRODUCTS FROM COMPARE PRODUCTS PAGE TO CART
	 * 
	 * ALSO VERIFIES IF THE PRODUCTS ARE ADDED TO CART
	 */
	@Test(priority = 4, enabled = true)
	public void add_To_Cart() throws InterruptedException {
		atc = new Add_To_Cart(driver);
		atc.click_iPodClassic_AddToCart();//clicking add to cart btn for ipod classic

		String comp_Msg_2 = pp.get_Compare_Msg();//saving message that is displayed after adding the product to cart
		System.out.println("IN Main" + comp_Msg_2);
		Assert.assertEquals(comp_Msg_2, props.getProperty("verifyCartMsgFor_iPod_Classic"));//verifying the message

		atc.click_iPodNano_AddToCart();//clicking add to cart btn for ipod nano
		atc.click_iPodNano_AddToCart();//clicking add to cart btn for ipod nano
		// Here we added TWO 2 ipod nano products to the cart so that we can verify the quantity in other TEST
		
		String comp_Msg_3 = pp.get_Compare_Msg();//same as above
		System.out.println("IN Main" + comp_Msg_3);
		Assert.assertEquals(comp_Msg_3, props.getProperty("verifyCartMsgFor_iPod_Nano"));//same as above

		atc.click_Cart();//clicking cart to open and get the data from it
		//below method compares the data from compare products with the data in cart
		// It also verifies the quantity of products added to the cart
		Assert.assertTrue(atc.cartCompare(product_1_Name, atc.total_Price_Of_iPodClassic()));
		Assert.assertTrue(atc.cartCompare(product_2_Name, atc.total_Price_Of_iPodNano()));
		//below methods verifies total price, it compares total price shown in web with the logic we written to know what's the total price
		Assert.assertTrue(atc.verify_Total_Cart_Price());
	}

	/*BELOW TEST WILL REMOVE IPOD CLASSIC FROM COMPARE PRODUCT PAGE
	 * 
	 * ALSO VERIFIES IF IT IS REMOVED FROM THE COMPARE PRODUCT PAGE
	 * 
	 * ALSO VERIFIES THAT COMPARE PRODUCTS COUNT IS CHANGED AFTER REMOVING IPOD CLASSIC i.e., 2>>1
	 */
	@Test(priority = 5, enabled = true)
	public void remove_Products_From_Compare() throws InterruptedException {
		pp.remove_ipodClassic();//removes iopdClassic from compare page
		Assert.assertEquals(pp.get_Compare_Msg(), props.getProperty("verifyRemoveMsg"));//verifying message that is displayed after removing the product
		Assert.assertFalse(pp.verify_Remove_ipodClassic());//checking if product is removed
		// below two 2 steps are written to get to the page where product compare(1) is available
		pp.move_To_Mp3Player();
		pp.click_ShowAll_Mp3Players();
		
		Assert.assertTrue(pp.verify_Compare_Count());//verifying the compare count shown in web with the logic compare count
	}

	/*BELOW TEST WILL CHANGE THE PRODUCT LAYOUT TO LIST AND GRID
	 * 
	 *ALSO VERIFIES IF THE PRODUCTS LAYOUT IS EITHER LIST OR GRID
	 */
	@Test(priority = 6, enabled = true)
	public void verifyLayout() {
		pp.clickListView();//clicking list view
		Assert.assertTrue(pp.verify_List_Layout());//verifying list view
		// Thing is when we change the layout the class names of div's of all products change, we use this shift to verify if it is in list or grid type
		pp.clickGridView();//clicking grid view
		Assert.assertTrue(pp.verify_Grid_Layout());//verifying grid view
	}

	/*BELOW TEST WILL SORT THE PRODUCT LIST TO NAMES(Z - A)
	 * 
	 * ALSO VERIFIES IF PRODUCT IS SORTED BY NAMES(Z - A)
	 */
	@Test(priority = 7, enabled = true)
	public void verifySortByOrder() {
		pp.selectSort();//clicks sort drop down and selects NAMES(Z - A)
		Assert.assertTrue(pp.verifySort()); //verifies the sorting
	}

}
