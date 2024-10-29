package pages.productsPage;

import java.util.ArrayList;
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
	
	@FindBy(css = "span.title")
	public WebElement productPageTitleElement;
	
// --------------------------- METHODS ------------------------------------    
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
	@Step("Count the amount of times an element from the product page appears")
	@Attachment
	public int countOfProductElements(List<WebElement> elements) {
		return countOfElements(elements);
	}
	
	/**
	 * Clicks the add to cart button depending on index
	 * @param index - ordered position of the add to cart button
	 */
	@Step("Click the Add To Cart button")
	public void clickAddToCartButton(int index) {
		clickTheElement(productAddToCartButtonsElements.get(index));
	}
	
	/**
	 * Clicks the remove from cart button depending on index
	 * @param index - ordered position of the remove from cart button
	 */
	@Step("Click and remove the item from the cart")
	public void clickRemoveFromCartButton(int index) {
		clickTheElement(productRemoveButtonsElements.get(index));
	}
	
	/**
	 * Clicks the Product Title Button button depending on the locator
	 * @param element - locator used to identify the element
	 */
	@Step("Click the Name of the product to navigate to page details")
	public void clickProductTitleButton(List<WebElement> elements, int index) {
		clickTheElement(elements.get(index));
	}
	
	/**
     * (Select/Option only) A list of elements to be set within an Array List
	 * @return - ArrayList containing all available options from select dropdown
	 */
	@Step("Collect all available items from the Filter")
	@Attachment
	public ArrayList<String> sortOptionsArrayList() {
		return selectOptionsArrayList(sortContainerElement);
	}
	
	/**
     * (Select/Option only) The select option is selected by Text matching the list of options from the dropdown
     * In this case are the text options are selected from an arraylist
	 * @param index - position of the sort option
	 */
	@Step("Select a filter option by text")
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
	@Step("Get the active/selected option text")
	@Attachment
	public String getActiveSortOptionText() {
		return getActiveSelectedOptionText(sortContainerElement);
	}
	
	/**
	 * Sort the array list from a Map into ascending order
	 * @param map - Map of type Map<String, String>
	 * @return - ArrayList<String> of type 
	 */
	@Step("Sort the Filter options text into Ascending order")
	@Attachment
    public ArrayList<String> sortListAscendingOrder(Map<String, String> map) {
    	ArrayList<String> arrayList = new ArrayList<String>();
    	arrayList.addAll(map.values());
    	Collections.sort(arrayList);
    	return arrayList;
    }
    
	/**
	 * Sort the array list from a Map into descending order
	 * @param map - Map of type Map<String, String>
	 * @return - ArrayList<String> of type 
	 */
	@Step("Sort the Filter options text into Descending order")
	@Attachment
    public ArrayList<String> sortListDescendingOrder(Map<String, String> map) {
    	ArrayList<String> arrayList = new ArrayList<String>();
    	arrayList.addAll(map.values());
    	Collections.sort(arrayList, Collections.reverseOrder());
    	return arrayList;
    }
    
	/**
	 * Sort the array list from a Map into ascending order
	 * @param doubleArrayList - ArrayList of type Double
	 * @return - ArrayList<Double> of type 
	 */
	@Step("Sort the Filter options text into Ascending order")
	@Attachment
    public ArrayList<Double> sortListAscendingOrder(ArrayList<Double> doubleArrayList) {
    	Collections.sort(doubleArrayList);
    	return doubleArrayList;
    }
    
	/**
	 * Sort the array list from a Map into descending order
	 * @param doubleArrayList - ArrayList of type Double
	 * @return - ArrayList<Double> of type 
	 */
	@Step("Sort the Filter options text into Descending order")
	@Attachment
    public ArrayList<Double> sortListDescendingOrder(ArrayList<Double> doubleArrayList) {
    	Collections.sort(doubleArrayList, Collections.reverseOrder());
    	return doubleArrayList;
    }
    
	/**
	 * Creates an ArrayList type Double from a Map
	 * @param map - Map of type Map<String, String>
	 * @return - ArrayList<Double> of type 
	 */
	@Step("Create a List from an existing map")
	@Attachment
    public ArrayList<Double> createArrayListTypeDoubleFromMap(Map<String, String> map) {
    	ArrayList<Double> doubleArrayList = new ArrayList<Double>();
		for (String stringMap : map.values()) {
			Double doubleMap = Double.parseDouble(stringMap.replace("$", ""));
			doubleArrayList.add(doubleMap);
		}
    	return doubleArrayList;
    }
    
    /**
     * Selects to sort by Ascend or Descend order
     * @param index - position for current element in the list
     * @return - sorted ArrayList<String>
     */
	@Step("Sort the Filter options text into Ascending or Descending order")
	@Attachment
    public ArrayList<String> sortStringArrayList(int index) {
    	ArrayList<String> arrayList = null;
    	switch (index) {
		case 0:
			arrayList = sortListAscendingOrder(productMap.productNameMap);
			break;
		case 1:
			arrayList = sortListDescendingOrder(productMap.productNameMap);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + index);
		}
    	
    	return arrayList;
    }
    
    /**
     * Selects to sort by Ascend or Descend order
     * @param index - position for current element in the list
     * @return - sorted ArrayList<Double>
     */
	@Step("Sort the Filter options text into Ascending or Descending order")
	@Attachment
    public ArrayList<Double> sortDoubleArrayList(int index) {
		ArrayList<Double> doubleArrayList = createArrayListTypeDoubleFromMap(productMap.productPriceMap);
		ArrayList<Double> arrayList = null;
    	switch (index) {
		case 2:
			arrayList = sortListAscendingOrder(doubleArrayList);
			break;
		case 3:
			arrayList = sortListDescendingOrder(doubleArrayList);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + index);
		}
    	
    	return arrayList;
    }
	
	/**
	 * Used to make an arrayList with arrayLists for each position, works for comparing
	 * the name, description and price of a product.  For assertion purposes.
	 * @return - ArrayList of type ArrayList(String)
	 */
	public ArrayList<ArrayList<String>> getProductsPageTextArrayList() {
		ArrayList<String> productNameArrayList = new ArrayList<String>();
		ArrayList<String> productDescArrayList = new ArrayList<String>();
		ArrayList<String> productPriceArrayList = new ArrayList<String>();
		
		ArrayList<ArrayList<String>> productArrayList1 = new ArrayList<>();
		for (int i = 0; i < 3; i ++) {
			if (i == 0) {
				for (int j = 0; j < 3; j++) {
					String productText = getProductsPageText(j, i);
					productNameArrayList.add(productText);
				}
			} else if (i == 1) {
				for (int k = 0; k < 3; k++) {
					String productText = getProductsPageText(k, i);
					productDescArrayList.add(productText);
				}
			} else if (i == 2) {
				for (int a = 0; a < 3; a++) {
					String productText = getProductsPageText(a, i);
					productPriceArrayList.add(productText);
				}
			} 
		}

		productArrayList1.add(productNameArrayList);
		productArrayList1.add(productDescArrayList);
		productArrayList1.add(productPriceArrayList);
		return productArrayList1;
	}
	
	/**
	 * Gets the text from the products listed, includes the Name, Description
	 * and Price of the item
	 * @param index - position in which product to obtain the required information
	 * @param number - case number to select which element to get text from
	 * @return - String for either Name, Description or Price
	 */
	public String getProductsPageText(int index, int number) {
		String product;
		switch (number) {
		case 0: 
			product = getProductPageElementsText(productNameElements.get(index));
			break;
		case 1: 
			product = getProductPageElementsText(productDescriptionElements.get(index));
			break;
		case 2: 
			product = getProductPageElementsText(productPriceElements.get(index));
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + number);
		}
		return product;
	}
	
// -----------------------------------------------------------------------------------------	
	SoftAssert softAssert = new SoftAssert();
	/**
	 * Checks to see if all elements are present on the products page
	 * uses two for loops to accomplish this
	 */
	public void areTheProductPageElementsPresent() {
		List<List<WebElement>> listOfElements = new ArrayList<>();
      listOfElements.add(productImgElements);
      listOfElements.add(productNameElements);
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
