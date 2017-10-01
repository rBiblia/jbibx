import net.avensome.dev.bibx.model.*;
import net.avensome.dev.bibx.serde.BibxSerde;
import net.avensome.dev.bibx.serde.BibxXmlSerde;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tester {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        BibxSerde gzSerde = new BibxSerde();
        BibxXmlSerde xmlSerde = new BibxXmlSerde();

        About about = new About("pl", true, "Biblia Testowa", "Lorem ipsum dolor sit amet", "BT", "2017");

        Map<Integer, String> verses = new HashMap<>();
        verses.put(2, "Verse 2");
        verses.put(1, "Verse 1");

        Chapter chapter1 = new Chapter(1, verses);
        Chapter chapter2 = new Chapter(2, Arrays.asList("Lorem ipsum", "dolor sit amet"));

        Book book1 = new Book(BookID.OT_GEN, Arrays.asList(chapter2, chapter1));
        Book book2 = new Book(BookID.OT_EXO);
        List<Book> books = Arrays.asList(book1, book2);

        Bible bible = new Bible(about, books);

        OutputStream xmlFile = new FileOutputStream(args[0].replace("bibx", "xml"));
        xmlSerde.serialize(bible, xmlFile);

        gzSerde.serialize(bible, file);
        //noinspection UnusedAssignment
        bible = gzSerde.deserialize(file);
    }
}
