/**
 * 
 */
package com.leek.test1;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Specification of a Properties parser.
 * 
 * @author <a href="mailto:andrei.cojocaru@hansenhof.de">Andrei Cojocaru</a>
 *
 */
public interface Properties {

	/**
	 * Reads the given file. Parses the key/value pairs and stores them in an internal structure that could be referenced by key.
	 * 
	 * @param file file to read
	 * @throws IOException if there was some problem when accessing the file.
	 * @throws PropertiesParserException if a non-comment line has no equal sign
	 */
	void read(File file) throws IOException, PropertiesParserException;
	
	/**
	 * Returns the value for the given key
	 * @param key key to check for
	 * @return value for the given key, or null if key not found.
	 */
	String get(String key);
	
	/**
	 * @return collection of all keys
	 */
	Collection<String> keys();
	
	/**
	 * @return number of key/value pairs in the parsed file.
	 */
	int count();
	
	/**
	 * Cleans up any internal structures and states and resets this class as if was newly instantiated.
	 * After invocation, this instance can be reused by calling another {@link #read(File)} 
	 */
	void clear();
	
}
