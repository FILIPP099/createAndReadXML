import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

public class CreateXML {

    public static void main(String [] args){

        try{
            String text = "Hello";
            String path = "";
            String fileName = "xmldocument.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder dc = dbf.newDocumentBuilder();

            Document d = dc.newDocument();

            //Elemento raiz do XML

            Element raiz = d.createElement("Posts");
            d.appendChild(raiz);

            //Elemento post
            Element post = d.createElement("post");
            raiz.appendChild(post);

            //Definindo o atributo do post
            Attr attr = d.createAttribute("id");
            long currentTimeStamp = System.currentTimeMillis();
            attr.setValue(String.valueOf(currentTimeStamp));
            post.setAttributeNode(attr);

            //Definindo valor da postagem na tag text
            Element textoRecebido = d.createElement("Text");
            textoRecebido.appendChild(d.createTextNode(text));
            post.appendChild(textoRecebido);


            //post 2

            Element post2 = d.createElement("post");
            raiz.appendChild(post2);

            Element textoRecebido2 = d.createElement("Text");
            textoRecebido2.appendChild(d.createTextNode(text));
            post2.appendChild(textoRecebido2);

            //Construindo XML

            saveXML(d, path, fileName);
            System.out.println(stringXML(d));

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Diretório " + System.getProperty("user.dir"));
        }


    }

    private static void saveXML(Document document, String path, String fileName) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource domSource = new DOMSource();
        domSource.setNode(document);

        File file = new File(path + fileName);

        StreamResult streamResult = new StreamResult();
        streamResult.setSystemId(file);

        transformer.transform(domSource, streamResult);

        System.out.println("XML criado em: " + path);

    }

    private static String stringXML(Document document) throws TransformerException {

        String xml;

        DOMSource domSource = new DOMSource();
        domSource.setNode(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult();
        streamResult.setWriter(stringWriter);

        transformer.transform(domSource, streamResult);

        xml = stringWriter.toString();
        return xml;

    }

}
