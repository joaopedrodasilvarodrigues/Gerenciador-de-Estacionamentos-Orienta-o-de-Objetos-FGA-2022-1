package EstacionaDF.FileManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class XMLTranslator {
    private Element rootTag;

    public XMLTranslator(String xmlFileName) throws ParserConfigurationException, IOException, SAXException  {
        /* BE AWARE! AN STRING PARAMATER THAT
        GOES DIRECTLY TO CMD IS DANGEROUS!!!
        DONT FORGET TO BRING A BETTER SOLUTION
        TO IT LATER!!!
        */ 
        // prepare to receive xml
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        //get absolute path from PtBr XML file.
        //String command = "find . -type f -name " + languageFile.strip() + ".xml";
        //Process findFile = Runtime.getRuntime().exec(command);
        //Scanner filePathFinder = new Scanner(findFile.getInputStream());
        Scanner filePathFinder;
        filePathFinder = new Scanner("./src/EstacionaDF/Database/" + xmlFileName + ".xml");
        // access xml file
        Document doc = dBuilder.parse(new File(filePathFinder.nextLine()));
        filePathFinder.close();
        this.rootTag = doc.getDocumentElement();
        
    }
    public Element getRootTag() {
        return rootTag;
    }

}
