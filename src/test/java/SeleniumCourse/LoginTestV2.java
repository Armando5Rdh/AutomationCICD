package SeleniumCourse;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.OrdersPage;
import pageobjects.ProductCataloguePage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LoginTestV2 extends BaseTest{

    String desiredProduct = "ZARA COAT 3";
    String username = "armando@gmail.com";
    String password = "Password!1";
    @Test(dataProvider = "getData",groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
        String confirmationMessage = "Thankyou for the order.";
        String country = "Mexico";

        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        CartPage cartPage = new CartPage(driver);

        landingPage.goTo();
        landingPage.loginApp(input.get("email"), input.get("password"));
        productCataloguePage.addProductToCart(input.get("product"));
        productCataloguePage.clickCartButton();

        Assert.assertTrue(cartPage.verifyProductsInCar(input.get("product")));
        cartPage.clickCheckout();
        cartPage.selectCountryOfResidence(country);
        cartPage.confirmCart();
        Assert.assertTrue(cartPage.verifyConfirmationMessage(confirmationMessage));

    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() {

        landingPage.loginApp(username, password);
        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        OrdersPage ordersPage = productCataloguePage.GoToOrdersPage();
        Assert.assertTrue(ordersPage.VerifyProductNames(desiredProduct));
    }

    @DataProvider
    public Object[][] getData() throws IOException, ParseException {

        List<HashMap<String, String>> data = getJsonDataToMap();
        Object[][] testData = new Object[data.size()][];

        for (int i = 0; i < data.size(); i++) {
            testData[i] = new Object[]{data.get(i)};
        }

        return testData;

//        HashMap map = new HashMap<String,String>();
//        map.put("email",username);
//        map.put("password",password);
//        map.put("product",desiredProduct);
//
//        HashMap map2 = new HashMap<String,String>();
//        map2.put("email","armando2@gmail.com");
//        map2.put("password",password);
//        map2.put("product",desiredProduct);

//        return new Object[][] {{username,password,desiredProduct},{"armando2@gmail.com","Password!1",desiredProduct}};
//        return new Object[][] {{map},{map2}};
    }

}
