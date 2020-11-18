import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadXML {

    public static void main (String[] args){

        try {

            File xmlFile = new File("xmldocument.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            getValueByID(doc,"id", "2");


        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Diretório: " + System.getProperty("user.dir"));
        }
    }

    private static void getValueByID(Document doc, String textNodeName, String textNodeValue){

        NodeList nodes = doc.getElementsByTagName("student");

        for(int i = 0; i < nodes.getLength(); i++){

            Node node = nodes.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                NodeList textNodes = element.getElementsByTagName(textNodeName);
                if(textNodes.getLength() > 0){

                    if(textNodes.item(0).getTextContent().equalsIgnoreCase(textNodeValue)){
                        System.out.println(textNodes.item(0).getTextContent());
                        System.out.println(element.getElementsByTagName("name").item(0).getTextContent());
                    }


                }

            }


        }


    }

}