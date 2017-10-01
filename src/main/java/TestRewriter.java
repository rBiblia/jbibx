import net.avensome.dev.bibx.model.Bible;
import net.avensome.dev.bibx.serde.BibxSerde;

import java.io.File;

public class TestRewriter {
    public static void main(String[] args) {
        File file = new File(args[0]);
        BibxSerde serde = new BibxSerde();
        Bible bible = serde.deserialize(file);
        File outFile = new File(args[0] + ".rewrite.bibx");
        serde.serialize(bible, outFile);
    }
}
