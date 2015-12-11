/**
 * @author Ricardo La Valle
 */
package de.fhdw.bfws114a.dataInterface;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import de.fhdw.bfws114a.data.Card;
import de.fhdw.bfws114a.data.File;

public class XMLPullParserHandler {

	// Tags used in the XML-File.
	private static final String TAG_FILE = "file";
	private static final String TAG_FILE_ID = "fileID";
	private static final String TAG_FILE_NAME = "fileName";
	private static final String TAG_FILE_CARD = "card";
	private static final String TAG_FILE_CARD_ID = "cardID";	
	private static final String TAG_FILE_CARD_TYPE = "cardType";
	private static final String TAG_FILE_CARD_QUESTION = "question";
	private static final String TAG_FILE_CARD_ANSWER1 = "answer1";
	private static final String TAG_FILE_CARD_ANSWER2 = "answer2";
	private static final String TAG_FILE_CARD_ANSWER3 = "answer3";
	private static final String TAG_FILE_CARD_ANSWER4 = "answer4";
	private static final String TAG_FILE_CARD_ANSWER5 = "answer5";
	private static final String TAG_FILE_CARD_ANSWER6 = "answer6";
	private static final String TAG_FILE_CARD_SOLUTION = "solution";

	private List<File> files;
	private List<Card> cards;
	private File tempfile;
	private Card tempcard;
	private String text;
	
	public XMLPullParserHandler() {
	}
	
	public List<File> parseFiles(InputStream is) {
		// Returns a list of Files, the Parser read from the InputStream / from the XML-File.

		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;
		files = new ArrayList<File>();
		tempfile = new File();

		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			
			parser = factory.newPullParser();
			parser.setInput(is, null);
			
			int eventType = parser.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT) {
				String tagname = parser.getName();
				
				switch (eventType) {
				case XmlPullParser.START_TAG:
					break;
					
				case XmlPullParser.TEXT:
					text = parser.getText();
					break;
					
				case XmlPullParser.END_TAG:
					if(tagname.equalsIgnoreCase(TAG_FILE)) {
						files.add(tempfile);
						tempfile = new File();
					}else if(tagname.equalsIgnoreCase(TAG_FILE_ID)) {
						tempfile.setId(Integer.parseInt(text));
					}else if(tagname.equalsIgnoreCase(TAG_FILE_NAME)) {
						tempfile.setName(text);
					}
					break;
					
				default:
					break;
				}
				eventType = parser.next();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return files;
	}
	
	public List<Card> parseCards(InputStream is) {
		// Returns a list of cards, the Parser read from the InputStream / from the XML-File.
		cards = new ArrayList<Card>();
		tempcard = new Card();
		int tempFileID=99;

		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;
		
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			
			parser = factory.newPullParser();
			parser.setInput(is, null);
			
			int eventType = parser.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT) {
				String tagname = parser.getName();
				switch (eventType) {
				case XmlPullParser.START_TAG:
					break;
					
				case XmlPullParser.TEXT:
					text = parser.getText();
					break;
					
				case XmlPullParser.END_TAG:
					if(tagname.equalsIgnoreCase(TAG_FILE_CARD)) {
						cards.add(tempcard);
						tempcard = new Card();
					}else if(tagname.equalsIgnoreCase(TAG_FILE_ID)) {
						tempFileID = (Integer.parseInt(text));
					}else if(tagname.equalsIgnoreCase(TAG_FILE_CARD_ID)) {
						tempcard.setId(Integer.parseInt(text));
					}else if(tagname.equalsIgnoreCase(TAG_FILE_CARD_TYPE)) {
						tempcard.setType(Integer.parseInt(text));
					}else if(tagname.equalsIgnoreCase(TAG_FILE_CARD_QUESTION)) {
						tempcard.setQuestion(text);
					}else if(tagname.equalsIgnoreCase(TAG_FILE_CARD_ANSWER1)) {
						tempcard.setAnswer1(text);
					}else if(tagname.equalsIgnoreCase(TAG_FILE_CARD_ANSWER2)) {
						tempcard.setAnswer2(text);
					}else if(tagname.equalsIgnoreCase(TAG_FILE_CARD_ANSWER3)) {
						tempcard.setAnswer3(text);
					}else if(tagname.equalsIgnoreCase(TAG_FILE_CARD_ANSWER4)) {
						tempcard.setAnswer4(text);
					}else if(tagname.equalsIgnoreCase(TAG_FILE_CARD_ANSWER5)) {
						tempcard.setAnswer5(text);
					}else if(tagname.equalsIgnoreCase(TAG_FILE_CARD_ANSWER6)) {
						tempcard.setAnswer6(text);
					}else if(tagname.equalsIgnoreCase(TAG_FILE_CARD_SOLUTION)) {
						tempcard.setSolution(text);
					}
					tempcard.setFile(tempFileID);
					break;
					
				default:
					break;
				}
				eventType = parser.next();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cards;
	}

}
