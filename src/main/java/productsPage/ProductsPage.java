package productsPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import util.BasePage;

public class ProductsPage extends BasePage{
	Select select;
	ProductsPageMap productMap;
	public ProductsPage (WebDriver driver) {
		super(driver);
		productMap = new ProductsPageMap();
	}
	
	@FindBys(@FindBy(css = "div.inventory_item"))
	public List<WebElement> productItemContainerElements;
	
	@FindBys(@FindBy(css = "div.inventory_item_name"))
	public List<WebElement> productNameElements;
	
	@FindBys(@FindBy(css = "div.inventory_item_desc"))
	public List<WebElement> productDescriptionElements;
	
	@FindBys(@FindBy(css = "div.inventory_item_price"))
	public List<WebElement> productPriceElements;
	
	@FindBys(@FindBy(css = "button.btn_primary"))
	public List<WebElement> productAddToCartButtonsElements;
	
	@FindBys(@FindBy(css = "div.inventory_item_img"))
	public List<WebElement> productImgElements;
	
	@FindBys(@FindBy(css = "span.shopping_cart_badge"))
	public List<WebElement> shoppingCartBadgeElements;
	
	@FindBys(@FindBy(css = "button.btn_secondary"))
	public List<WebElement> productRemoveButtonsElements;
	
	@FindBy(css = "span.active_option")
	public WebElement activeSortOptionElement;
	
	@FindBy(css = "select.product_sort_container")
	public WebElement sortContainerElement;
	
	@FindBys(@FindBy(css = "select.product_sort_container > option"))
	private List<WebElement> sortOptionElements;
	
// --------------------------- METHODS ------------------------------------    
	SoftAssert softAssert = new SoftAssert();
	/**
	 * Returns true or false depending on if the element is present
	 * @return - TRUE, if the element is present
	 */
	@Step("Is the Products Element Present?")
	@Attachment
	public boolean isProductPageElementPresent(WebElement element) {
		return isElementPresent(element);
	}
	
	/**
	 * Returns true or false depending on if the element is Not present
	 * @return - False, if the element is present
	 */
	@Step("Does the Products Element Exist?")
	@Attachment
	public boolean doesProductPageElementExist(List<WebElement> elements) {
		return doesElementExist(elements);
	}
	
	/**
     * Returns text from one the footer field items
     * @param element - locator used to identify the element
     * @return - products item text
     */
	@Step("Get the Products Elements text")
	@Attachment
    public String getProductPageElementsText(WebElement element) {
    	return getElementText(element);
    }
	
	/**
     * Counts the total amount of elements
     * @param elements - locator used to identify multiple elements
     * @return - list of elements
	 */
	public int countOfProductElements(List<WebElement> elements) {
		return countOfElements(elements);
	}
	
	/**
	 * Clicks the add to cart button depending on index
	 * @param index - ordered position of the add to cart button
	 */
	public void clickAddToCartButton(int index) {
		clickTheElement(productAddToCartButtonsElements.get(index));
	}
	
	/**
	 * Clicks the remove from cart button depending on index
	 * @param index - ordered position of the remove from cart button
	 */
	public void clickRemoveFromCartButton(int index) {
		clickTheElement(productRemoveButtonsElements.get(index));
	}
	
	/**
     * (Select/Option only) A list of elements to be set within an Array List
	 * @return - ArrayList containing all available options from select dropdown
	 */
	public ArrayList<String> sortOptionsArrayList() {
		return selectOptionsArrayList(sortContainerElement);
	}
	
	/**
     * (Select/Option only) The select option is selected by Text matching the list of options from the dropdown
     * In this case are the text options are selected from an arraylist
	 * @param index - position of the sort option
	 */
	public void selectSortOptionByText(int index) {
		ArrayList<String> sortTextArrayList = sortOptionsArrayList();
		String sortOptionText = sortTextArrayList.get(index);
		selectAnOptionByText(sortContainerElement, sortOptionText);
	}
	
	/**
     * (Select/Option only) The first and currently active element found in the select element will 
     * be obtained to get its text
     * @return - currently active element
	 */
	public String getActiveSortOptionText() {
		return getActiveSelectedOptionText(sortContainerElement);
	}
	
	
    public ArrayList<String> sortListAscendingOrder(Map<String, String> map) {
    	ArrayList<String> arrayList = new ArrayList<String>();
    	arrayList.addAll(map.values());
    	Collections.sort(arrayList);
    	return arrayList;
    }
    
    public ArrayList<String> sortListDescendingOrder(Map<String, String> map) {
    	ArrayList<String> arrayList = new ArrayList<String>();
    	arrayList.addAll(map.values());
    	Collections.sort(arrayList, Collections.reverseOrder());
    	return arrayList;
    }
    
    public ArrayList<Double> sortListAscendingOrder(ArrayList<Double> doubleArrayList) {
    	Collections.sort(doubleArrayList);
    	return doubleArrayList;
    }
    
    public ArrayList<Double> sortListDescendingOrder(ArrayList<Double> doubleArrayList) {
    	Collections.sort(doubleArrayList, Collections.reverseOrder());
    	return doubleArrayList;
    }
    
    public ArrayList<Double> createArrayListTypeDoubleFromMap(Map<String, String> map) {
    	ArrayList<Double> doubleArrayList = new ArrayList<Double>();
		for (String stringMap : map.values()) {
			Double doubleMap = Double.parseDouble(stringMap.replace("$", ""));
			doubleArrayList.add(doubleMap);
		}
    	return doubleArrayList;
    }
    
    
    
    public ArrayList<String> testSwitch(int number, Map<String, String> map, ArrayList<Double> doubleArrayList) {
    	ArrayList<String> arrayList = null;
    	switch (number) {
		case 0:
			arrayList = sortListAscendingOrder(map); //productMap.productNameMap
			break;
		case 1:
			arrayList = sortListDescendingOrder(map);
			break;
		case 2:
			arrayList = sortListAscendingOrder(doubleArrayList);
			break;
		case 3:
			arrayList = sortListDescendingOrder(doubleArrayList);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value1: " + number);
		}
    	return arrayList;
    }
    
    public Map<String, String> testSwitch2(int number) {
    	Map<String, String> map;
    	switch (number) {
		case 0:
		case 1:
			map = productMap.productNameMap;
			break;
		case 2:
		case 3:
			map = productMap.productPriceMap;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value2: " + number);
		}
    	return map;
    }
    
    public List<WebElement> testSwitch3(int number) {
    	List<WebElement> elements;
    	switch (number) {
		case 0:
		case 1:
			elements = productNameElements;
			break;
		case 2:
		case 3:
			elements = productPriceElements;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value3: " + number);
		}
    	return elements;
    }
    
//    public void testTest(int i) {
//    	ArrayList<String> arrayList = testSwitch(i, testSwitch2(i));
//		int arrayListSize = arrayList.size();
//		for (int j = 0; j < arrayListSize; j++) {
//			System.out.println(testSwitch3(i).get(j).getText());
//			System.out.println(arrayList.get(j));
//			softAssert.assertEquals(testSwitch3(i).get(j).getText(), arrayList.get(j));
//		}
//		System.out.println("\n");
//		softAssert.assertAll();
//    }
	
	
// -----------------------------------------------------------------------------------------	
	/**
	 * Checks to see if all elements are present on the products page
	 * uses two for loops to accomplish this
	 */
	public void areTheProductPageElementsPresent() {
		List<List<WebElement>> listOfElements = List.of(productImgElements, productNameElements);
		boolean isPresent = false;
		for (int i = 0; i < listOfElements.size(); i++) {
			for (int j = 0; j < listOfElements.get(i).size(); j++) {
				isPresent = isProductPageElementPresent(listOfElements.get(i).get(j));
				softAssert.assertTrue(isPresent, "Position [" + i + "," + j + "] --> Element Locator: " +  listOfElements.get(i).get(j));
			}
		}
		softAssert.assertAll();
	}
}
