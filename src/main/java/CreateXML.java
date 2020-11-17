import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CreateXML {

    public static void main(String [] args){

        try{
            String text = "Hello";
            String path = "D:\\Usuários\\Filippo\\Desktop\\";
            String file = "xmldocument.xml";

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
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource domSource = new DOMSource(d);
            StreamResult streamResult = new StreamResult(new File(path+file));

            t.transform(domSource, streamResult);
            System.out.println("XML criado em: " + path);

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Diretório " + System.getProperty("user.dir"));
        }


    }

}
