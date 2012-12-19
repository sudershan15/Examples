package mongo.tests;

import java.io.FileInputStream;
import java.io.InputStream;

import mongo.app.App;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PopulateTest {

	public PopulateTest() {
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testLoadDocs() throws Exception {
		App a = new App();
		a.insert();
	}
	
	@Test
	public void searchLoadDocs() throws Exception {
		App a = new App();
		String keyword = "provision";
		a.search(keyword);
	}
}
