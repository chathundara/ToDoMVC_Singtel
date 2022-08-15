package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.ToDoMVCPage;
import utilities.LoggerHelper;
import utilities.WaitHelper;

public class Steps extends BaseClass {

	@Before 
	public void setup() throws IOException {

		Logger logger = LoggerHelper.getLogger(Steps.class);
		Properties configProp;
		configProp = new Properties();
		FileInputStream configpropFile = new FileInputStream("config.properties");
		configProp.load(configpropFile);

		String browser = configProp.getProperty("browser");

		if (browser.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver();

		} else if (browser.equals("firefox")) {

			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver = new ChromeDriver();

		}
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Given("the URL {string}")
	public void the_url(String url) {
		toDoMVCPage = new ToDoMVCPage(driver);
		driver.get(url);
	}

	@Given("an empty todo list")
	public void an_empty_todo_list() {
		toDoMVCPage.clearTodoInput();
		toDoMVCPage.clearAllTodoItems();
	}

	@When("I add the item {string}")
	public void i_add_the_item(String item) {
		toDoMVCPage.addAnItem(item);
	}

	@Then("only {string} item should be active")
	public void only_item_should_be_listed(String item) {
		Assert.assertTrue(toDoMVCPage.verifyActiveList(item) && !toDoMVCPage.verifyCompletedList(item));
	}

	@Then("{string} message should be displayed")
	public void message_should_be_displayed(String message) {
		String firstNumber = message.split(" ")[0];
		String itemsLeftCount = toDoMVCPage.itemsLeftCount();
		Assert.assertEquals(firstNumber, itemsLeftCount);
	}

	@Then("the {string} and {string} and {string} tabs should be there")
	public void the_and_and_tabs_should_be_there(String all, String active, String completed) {

		if (toDoMVCPage.checkTabsDisplayed(all) && toDoMVCPage.checkTabsDisplayed(active)
				&& toDoMVCPage.checkTabsDisplayed(completed)) {
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}

	@When("I add two items {string} and {string}")
	public void i_add_two_items_and(String item1, String item2) {

		items.add(item1);
		items.add(item2);
		toDoMVCPage.addAnItem(item1);
		toDoMVCPage.addAnItem(item2);

	}

	@Then("only added items should be listed")
	public void only_added_items_should_be_listed() {
		String FirstItem = items.get(0);
		String SecondItem = items.get(1);

		boolean a = toDoMVCPage.verifyActiveList(FirstItem);
		boolean b = toDoMVCPage.verifyActiveList(SecondItem);
		boolean c = toDoMVCPage.verifyCompletedList(FirstItem);
		boolean d = toDoMVCPage.verifyCompletedList(SecondItem);
		if (a && b && !c && !d) {
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}

	@Given("a list with two items {string} and {string}")
	public void a_list_with_two_items_and(String item1, String item2) {
		toDoMVCPage.clearTodoInput();
		toDoMVCPage.clearAllTodoItems();
		items.clear();
		items.add(item1);
		items.add(item2);
		toDoMVCPage.addAnItem(item1);
		toDoMVCPage.addAnItem(item2);

	}

	@When("{string} item is complete")
	public void item_is_complete(String item) {
		toDoMVCPage.completeAnItem(item);

	}

	@Then("the {string} item should only be active")
	public void the_item_should_only_be_active(String item) {
		Assert.assertTrue(toDoMVCPage.verifyActiveList(item) && !toDoMVCPage.verifyCompletedList(item));

	}

	@Then("first item should be inactive")
	public void first_item_should_be_inactive() {
		Assert.assertTrue(!toDoMVCPage.verifyActiveList(items.get(0)) && toDoMVCPage.verifyCompletedList(items.get(0)));

	}

	@Then("{string} link should be appeared")
	public void link_should_be_appeared(String tab) {
		Assert.assertTrue(toDoMVCPage.veryfyClearCompletedLink());

	}

	@When("navigate to the {string} tab")
	public void navigate_to_the_tab(String tab) {
		toDoMVCPage.tabsNavigation(tab);
	}

	@Then("{string} item should not be in the {string} list")
	public void item_should_not_be_in_the_list(String item, String list) {

		if (list.equalsIgnoreCase("Active")) {
			Assert.assertFalse(toDoMVCPage.verifyActiveList(item));
		} else if (list.equalsIgnoreCase("Completed")) {
			Assert.assertFalse(toDoMVCPage.verifyCompletedList(item));
		}
	}

	@When("{string} is done")
	public void is_done(String tab) {
		toDoMVCPage.tabsNavigation(tab);
	}

	@When("Tap on the {string} item check mark")
	public void tap_on_the_1st_item_check_mark(String item) {

		toDoMVCPage.completeAnItem(item);

	}

	@When("change {string} item to {string}")
	public void change_item_to(String initialItem, String editedItem) throws InterruptedException {
		toDoMVCPage.editAndVerifyAnItem(initialItem, editedItem);

	}

	@Then("{string} item should be changed to {string}")
	public void item_should_be_changed_to(String initialItem, String editedItem) {

		boolean a = toDoMVCPage.verifyAllList(initialItem);
		boolean b = toDoMVCPage.verifyAllList(editedItem);


		if (!a && b) {
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}

	@Then("{string} item is still completed")
	public void item_is_still_completed(String editedItem) {
		boolean a = toDoMVCPage.verifyCompletedList(editedItem);
		if (a) {
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}

	@Then("{string} item is still active")
	public void item_is_still_active(String editedItem) {
		boolean a = toDoMVCPage.verifyCompletedList(editedItem);
		if (a) {
			Assert.assertTrue(false);
		} else
			Assert.assertTrue(true);
	}

	@Given("a list with a single item {string}")
	public void a_list_with_a_single_item(String item) {
		toDoMVCPage.addAnItem(item);
	}

	@When("Delete the item {string}")
	public void delete_the_item(String item) throws InterruptedException {
		toDoMVCPage.deleteAnItem(item);
		items.clear();
		items.add(item);

	}

	@Then("an empty todo list should be there")
	public void an_empty_todo_list_should_be_there() {
		Assert.assertFalse(toDoMVCPage.verifyActiveList(items.get(0)));

	}
}