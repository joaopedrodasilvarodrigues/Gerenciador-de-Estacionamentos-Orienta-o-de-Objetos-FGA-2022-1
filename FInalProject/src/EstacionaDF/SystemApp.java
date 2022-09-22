package EstacionaDF;

import java.io.IOException;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import EstacionaDF.FileManager.XMLTranslator;
import EstacionaDF.Screens.DefaultScreen;
import EstacionaDF.Screens.HomeScreen;

public class SystemApp extends JFrame{
    private DefaultScreen page;
    private XMLTranslator textContent;

    public SystemApp() throws ParserConfigurationException, IOException, SAXException {
        // Essencial
        super();
        this.textContent = new XMLTranslator("PortugueseBrasil");
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        setVisible(true);
        setTitle(this.textContent.getRootTag().getAttribute("title"));
        setSize(500, 500);
        // setIconImage(image);
        page = new HomeScreen(this);

    }
    public DefaultScreen getPage() {
        return page;
    } public void setPage(DefaultScreen page) {
        this.page = page;
    } public String getTextContent(String tagName, int appearanceIndex) {
        return this.textContent.getRootTag().getElementsByTagName(tagName).item(appearanceIndex).getTextContent();
    }
    
    public void setTextContent(XMLTranslator textContent) {
        this.textContent = textContent;
    }

}
