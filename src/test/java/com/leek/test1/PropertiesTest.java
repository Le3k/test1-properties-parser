package com.leek.test1;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collection;

import junit.framework.TestCase;

public class PropertiesTest extends TestCase {

	private Properties properties;
	
	public PropertiesTest() {
		// TODO instantiate the Properties reference with your implementation:
//		properties = 
	}
	
	public void testRead() throws URISyntaxException {
		properties.clear();
		
		IOException ioe = null;
		PropertiesParserException ppe = null;
		File file = Paths.get("/unexisting-path/unexisting-file.properties").toFile();
		try {
			properties.read(file);
		} catch (IOException e) {
			ioe = e;
		} catch (PropertiesParserException e) {
			ppe = e;
		}
		assertNotNull(ioe);
		assertNull(ppe);
	}

	public void testGet() throws URISyntaxException {
		properties.clear();

		File file = getClasspathFile("/com/leek/test1/test1.properties");
		try {
			properties.read(file);
		} catch (IOException | PropertiesParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String value1 = properties.get("key1");
		assertNotNull(value1);
		assertEquals("value1", value1);
		
		String value2 = properties.get("key2");
		assertNotNull(value2);
		assertEquals("Another crazy value", value2);
		
		String unexistingValue = properties.get("unexisting.key");
		assertNull(unexistingValue);
	}
	
	public void testKeys() throws URISyntaxException {
		properties.clear();
		
		File file = getClasspathFile("/com/leek/test1/test1.properties");
		try {
			properties.read(file);
		} catch (IOException | PropertiesParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Collection<String> keys = properties.keys();
		assertEquals(2, keys.size());
		assertTrue(keys.contains("key1"));
		assertTrue(keys.contains("key2"));
	}

	public void testCount() throws URISyntaxException {
		properties.clear();
		
		File file = getClasspathFile("/com/leek/test1/test1.properties");
		try {
			properties.read(file);
		} catch (IOException | PropertiesParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Collection<String> keys = properties.keys();
		assertEquals(2, keys.size());
	}

	public void testClear() {
		properties.clear();
		
		// Read file:
		try {
			properties.read(getClasspathFile("/com/leek/test1/test1.properties"));
			properties.clear();
			properties.read(getClasspathFile("/com/leek/test1/test2.properties"));
		} catch (IOException | PropertiesParserException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, properties.count());
	}

	private static File getClasspathFile(String classpath) throws URISyntaxException {
		URL fileUrl = PropertiesTest.class.getResource(classpath);
		return Paths.get(fileUrl.toURI()).toFile();
	}
}
