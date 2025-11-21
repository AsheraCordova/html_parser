//start - license
/*
 * Copyright (c) 2025 Ashera Cordova
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
//end - license
package com.ashera.parser.html;


import java.io.IOException;
import java.io.StringReader;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import repackaged.org.ccil.cowan.tagsoup.HTMLSchema;
import repackaged.org.ccil.cowan.tagsoup.TagSoupParser;

public class HtmlParser {
	private static final HTMLSchema schema = new HTMLSchema();
	public static void parse(ContentHandler handler, String contentHtml) {
		TagSoupParser parser = new TagSoupParser();
	    try {
	        parser.setProperty(TagSoupParser.schemaProperty, schema);
	    } catch (org.xml.sax.SAXNotRecognizedException e) {
	        // Should not happen.
	        throw new RuntimeException(e);
	    } catch (org.xml.sax.SAXNotSupportedException e) {
	        // Should not happen.
	        throw new RuntimeException(e);
	    }
	    
	    parser.setContentHandler(handler);
		try {
			parser.parse(new InputSource(new StringReader(contentHtml)));
		} catch (IOException e) {
			// We are reading from a string. There should not be IO
			// problems.
			throw new RuntimeException(e);
		} catch (SAXException e) {
			// TagSoup doesn't throw parse exceptions.
			throw new RuntimeException(e);
		}
	}
}
