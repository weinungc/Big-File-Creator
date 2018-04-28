package spellcorrector.spellcorrector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class Spellcorrector {

	public static void main(String[] args)  throws IOException, SAXException, TikaException  {
		// TODO Auto-generated method stub
		String output = "big.txt";
		String htmlfile = args[0];
		File folder = new File(htmlfile);
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));
		
		
		for(File file : folder.listFiles()) {
			System.out.println("Running....");
			
			BodyContentHandler handler = new BodyContentHandler(-1);
			AutoDetectParser parser = new AutoDetectParser();
			ParseContext context = new ParseContext();
			Metadata metadata = new Metadata();
			FileInputStream inputstream= new FileInputStream(file);
			parser.parse(inputstream, handler, metadata,context);
			
			
			for(String s: formateString(handler.toString())){
				if(s.matches("[a-zA-Z]+\\.?"))
					writer.write(s +" ");
			}
		}
		writer.close();

	}
	
	public static String[] formateString(String s) {
		s = s.replaceAll("\t", " ");
		s = s.trim();
		
		return s.split(" ");
		
	}

}
