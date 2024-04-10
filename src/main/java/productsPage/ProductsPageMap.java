package productsPage;

import java.util.LinkedHashMap;
import java.util.Map;

import util.PagesMaps;

//import org.openqa.selenium.WebDriver;

public class ProductsPageMap extends PagesMaps{
	public Map<String, String> productDescMap;
	public Map<String, String> productNameMap;
	public Map<String, String> productPriceMap;
	public ProductsPageMap() {
		super();
		// Map 1
		productDescMap = new LinkedHashMap<String, String>();
		productDescMap.put("BackPackDesc", "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
		productDescMap.put("BikeLightDesc", "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.");
		productDescMap.put("BoltTShirtDesc", "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.");
		productDescMap.put("JacketDesc", "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.");
		productDescMap.put("OnesieDesc", "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.");
		productDescMap.put("RedTShirtDesc", "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.");
		
		// Map 2
		productNameMap = new LinkedHashMap<String, String>();
		productNameMap.put("BackPackName", "Sauce Labs Backpack");
		productNameMap.put("BikeLightName", "Sauce Labs Bike Light");
		productNameMap.put("BoltTShirtName", "Sauce Labs Bolt T-Shirt");
		productNameMap.put("JacketName", "Sauce Labs Fleece Jacket");
		productNameMap.put("OnesieName", "Sauce Labs Onesie");
		productNameMap.put("RedTShirtName", "Test.allTheThings() T-Shirt (Red)");
		
		// Map 3
		productPriceMap = new LinkedHashMap<String, String>();
		productPriceMap.put("BackPackPrice", "$29.99");
		productPriceMap.put("BikeLightPrice", "$9.99");
		productPriceMap.put("BoltTShirtPrice", "$15.99");
		productPriceMap.put("JacketPrice", "$49.99");
		productPriceMap.put("OnesiePrice", "$7.99");
		productPriceMap.put("RedShirtPrice", "$15.99");
	}
	
	public int getProductNameMapSize() {
		return getStrStrMapSize(productNameMap);
	}
	
	public String getProductNameMapValue(int keyIndex) {
		return getStrStrMapValueByIndex(productNameMap, keyIndex);
	}
	
	public int getProductDescMapSize() {
		return getStrStrMapSize(productDescMap);
	}
	
	public String getProductDescMapKey(int valueIndex) {
		return getStrStrMapKeyByIndex(productDescMap, valueIndex);
	}
	
	public String getProductDescMapValue(int keyIndex) {
		return getStrStrMapValueByIndex(productDescMap, keyIndex);
	}
	
	
	public String getProductPriceMapValue(int keyIndex) {
		return getStrStrMapValueByIndex(productPriceMap, keyIndex);
	}
}
