import net.avensome.dev.bibx.model.*;
import net.avensome.dev.bibx.serde.BibxXmlSerde;

import java.io.*;
import java.util.*;

public class Tester2 {
    public static void main(String[] args) throws FileNotFoundException {
        BibxXmlSerde xmlSerde = new BibxXmlSerde();

        About about = new About("", true, "", "", "", "");
        Bible bible = new Bible(about, Collections.emptyList());

        OutputStream outputStream = new FileOutputStream(args[0]);
        xmlSerde.serialize(bible, outputStream);

        InputStream inputStream = new FileInputStream(args[0]);
        bible = xmlSerde.deserialize(inputStream);
    }
}
