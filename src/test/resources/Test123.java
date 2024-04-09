import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class Test123 {
	String teString = "abc123";
	
	// Practice Code - ArrayLists and Lists
	public ArrayList<WebElement> testScript3(List<WebElement> elements) {
		int elementSize = elements.size();
		ArrayList<WebElement> testList = new ArrayList<>();
		for (int i = 0; i < elementSize; i++) {
			testList.add(elements.get(i));
		}		
		return testList;
	}
	
	public void testScript5() {
		List<List<WebElement>> test0 = List.of(productImgElements, productNameElements);
//		System.out.println(test0.size());
		boolean isPresent = false;
		for (int i = 0; i < test0.size(); i++) {
//			System.out.println(test0.get(i).size());
//			System.out.println(test0.get(i));
			for (int j = 0; j < test0.get(i).size(); j++) {
				isPresent = isProductPageElementPresent(test0.get(i).get(j));
//				System.out.println("\nisPresent = " + isPresent + ", i = " + i + ", j = " + j + "\n--------------------------" + "\n\n------------------------" + test0.get(i).get(j));
				softAssert.assertFalse(isPresent, "Position [" + i + "," + j + "] --> Element Locator: " +  test0.get(i).get(j));
			}
		}
		softAssert.assertAll();
//		ArrayList<WebElement> test1 = testScript3(productImgElements);
//		ArrayList<WebElement> test2 = testScript3(productNameElements);
//		ArrayList<ArrayList<WebElement>> test3 = new ArrayList<ArrayList<WebElement>>();
//		test3.add(test1);
//		test3.add(test2);
//		System.out.println("\n" + test3.toString() + "\n--------------------------");
	}
}
