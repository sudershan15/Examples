package mongo.indexing;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jxl.*; 
import jxl.read.biff.BiffException;
import jxl.write.*; 
import jxl.write.biff.RowsExceededException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class Populate {

	private ArrayList<String> files = new ArrayList<String>();
	static long startTime = System.currentTimeMillis();

	public Populate() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException, BiffException, RowsExceededException, WriteException {
		
		String path = "testdata/books";
		Populate p = new Populate();
		
		
		try {

			
			// connect to mongoDB, ip and port number
			Mongo mongo = new Mongo("localhost", 27017);

			// get database from MongoDB
			DB db = mongo.getDB("proj2");
			DBCollection booksColl = db.getCollection("books");
			DBCollection textsColl = db.getCollection("texts");
			DBCollection kwColl = db.getCollection("keywords");

			p.bulkGenerate(path, booksColl, textsColl, kwColl);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime-startTime;

		System.out.println("Total execution time: " + totalTime);

		
	}
	
	class TimePair{
		  long elapsedTime;
		  long insertionTime;
		
	}


	public void listFilesForFolder(final File folder) {
//		System.out.println(folder);
		for (final File fileEntry : folder.listFiles()) {
			if(!fileEntry.getName().contains(".DS_Store")){
				if (fileEntry.isDirectory()) {
					listFilesForFolder(fileEntry);
				} else {
//					System.out.println(fileEntry.getName());
					files.add(folder + "/" + fileEntry.getName());
				}
			}
		}
	}
	
	public void readAllBooks(DBCollection booksCollection, DBCollection textsCollection, DBCollection keywordsCollection) {
		
		booksCollection.find();
		long nowTime = System.currentTimeMillis();
		long nowElapsed = nowTime-startTime;
		System.out.println(nowElapsed + " ms have elapsed");
		System.out.println(booksCollection.count() + " books read");

		textsCollection.find();
		nowTime = System.currentTimeMillis();
		nowElapsed = nowTime-startTime;
		System.out.println(nowElapsed + " ms have elapsed");
		System.out.println(booksCollection.count() + " texts read");

		
	}


	public void bulkGenerate(String path, DBCollection booksCollection, DBCollection textsCollection, DBCollection keywordsCollection) 
			throws IOException, BiffException, RowsExceededException, WriteException {
		final File folder = new File(path);
		listFilesForFolder(folder);
		long oldTime = startTime;
		ArrayList<TimePair> timesList = new ArrayList<TimePair>();
		
        long nowTime = 0;
        long nowElapsed = 0;
        long timePerBook = 0;

		
		for (String f: files) {
			System.out.println();
			int prbcount = 0;
			FileInputStream fsStream = new FileInputStream(f);
			DataInputStream diStream = new DataInputStream(fsStream);
			BufferedReader biStream = new BufferedReader(new InputStreamReader(diStream));
			String strLine;
			String[] parsedLine;
	        ArrayList<String> bookTextArr = new ArrayList<String>();
			
			BasicDBObject book = new BasicDBObject();
			BasicDBObject bookText = new BasicDBObject();
			BasicDBObject keyword = new BasicDBObject();
			
			String currentTitle = null;
						
			while((strLine = biStream.readLine()) != null)
			{

				if (strLine.trim().startsWith("Title:"))
				{
					parsedLine = strLine.trim().split(":", 2);
					System.out.println("Title: " + parsedLine[1].trim());
					currentTitle = parsedLine[1].trim();
					book.put("Title", currentTitle);
				}
				else if (strLine.trim().startsWith("Author:"))
				{
					parsedLine = strLine.trim().split(":", 2);
					if (parsedLine.length < 2) {
						parsedLine = new String[2];
						parsedLine[0] = "Author";
						parsedLine[1] = "Anonymous";
						book.put("Author", "Anonymous");
					}
					System.out.println("Author: " + parsedLine[1].trim());
				}
				else if (strLine.trim().startsWith("Release Date:"))
				{
					parsedLine = strLine.trim().split(":", 2);
					System.out.println("Release Date: " + parsedLine[1].trim());
					book.put("Release Date", parsedLine[1].trim());
				}
				else if (strLine.trim().startsWith("Posting Date:"))
				{
					parsedLine = strLine.trim().split(":", 2);
					System.out.println("Posting Date: " + parsedLine[1].trim());
					book.put("Posting Date", parsedLine[1].trim());

				}
				else if (strLine.trim().startsWith("Language:"))
				{
					parsedLine = strLine.trim().split(":", 2);
					System.out.println("Language: " + parsedLine[1].trim());
					book.put("Language", parsedLine[1].trim());

				}
				else if (strLine.trim().startsWith("Translator:"))
				{
					parsedLine = strLine.trim().split(":", 2);
					System.out.println("Translator: " + parsedLine[1].trim());
					book.put("Translator", parsedLine[1].trim());

				}
				else if (strLine.trim().startsWith("***")) { ;	}
				else if (strLine.trim().isEmpty()) { ; }
				else if (strLine.trim().startsWith("Produced by:"))
				{
					if(prbcount == 0) {
						parsedLine = strLine.trim().split("by");
						System.out.println("Produced by: " + parsedLine[1].trim());
						book.put("Producer", parsedLine[1].trim());
						prbcount++;
					}
					
				}
				else
				{
//					String[] words = strLine.split(" ");  
//					for (String word : words) {
//						keyword.put("Keyword", word);
//						keyword.put("Locations", currentTitle);
//						keywordsCollection.update(keyword, keyword, true, false);
//					}
 
					bookTextArr.add(strLine);
				}
				
			}
			// Two collections: one for books, one for the book text
			
			book.put("_id", new ObjectId());
			BasicDBObject bookIndex = new BasicDBObject();
			bookIndex.put("Title", 1);
			booksCollection.ensureIndex(bookIndex, "Title_Index", true); // unique index

			booksCollection.update(book, book, true, false);
			
			bookText.put("Text", bookTextArr);
			String title = book.getString( "Title" );
			bookText.put("TextBook", title);
			ObjectId book_id = (ObjectId) book.get( "_id" );
			bookText.put("Book_id", book_id);
			textsCollection.update(bookText, bookText, true, false);
			biStream.close();
			
			nowTime = System.currentTimeMillis();
			nowElapsed = nowTime-startTime;
			timePerBook = nowTime-oldTime;

			TimePair nowPair = new TimePair();
			nowPair.elapsedTime = nowElapsed;
			nowPair.insertionTime = timePerBook;
			timesList.add(nowPair);			

			oldTime = nowTime;
			System.out.println(nowElapsed + " ms have elapsed");
			System.out.println(timePerBook + " ms to insert this particular book");
			System.out.println(booksCollection.count() + " books inserted so far");
		}
		
		outputExcelSheet(timesList);
		
	}

	private void outputExcelSheet(ArrayList<TimePair> tpList) throws IOException, RowsExceededException, WriteException {
		WritableWorkbook workbook = Workbook.createWorkbook(new File("insertionTimes.xls"));
		WritableSheet sheet1 = workbook.createSheet("First Sheet", 0);
		Label label1 = new Label(0, 0, "Time Elapsed"); 
		Label label2 = new Label(1, 0, "Insertion Time");
		sheet1.addCell(label1);
		sheet1.addCell(label2);
		int height = 1;
		int sheetNum = 0;

		
		WritableSheet currentSheet = sheet1;
		
		for (TimePair tp : tpList) {
			System.out.println("elapsed: " + tp.elapsedTime + " insertion: " + tp.insertionTime);
			if (height < 254) {
				currentSheet.addCell(new Label(0, height, String.valueOf(tp.elapsedTime)));
				currentSheet.addCell(new Label(1, height, String.valueOf(tp.insertionTime)));
			} else {
				sheetNum++;
				String sheetName = "Sheet " + sheetNum;
				WritableSheet sheetNext = workbook.createSheet(sheetName, sheetNum);
				currentSheet = sheetNext;
				currentSheet.addCell(new Label(0, 0, "Time Elapsed"));
				currentSheet.addCell(new Label(1, 0, "Insertion Time"));
				height = 1;			// starting over from top of next sheet 
			}

			height++;
			
		}
		
		workbook.write(); 
		workbook.close();

		
	}
}