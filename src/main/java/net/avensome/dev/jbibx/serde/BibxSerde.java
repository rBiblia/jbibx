package net.avensome.dev.jbibx.serde;

import net.avensome.dev.jbibx.model.Bible;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class BibxSerde {
    private BibxXmlSerde xmlSerde = new BibxXmlSerde(false);

    public Bible deserialize(File file) {
        try (FileInputStream fileInput = new FileInputStream(file);
             GZIPInputStream gzipInput = new GZIPInputStream(fileInput)) {
            return xmlSerde.deserialize(gzipInput);
        } catch (Exception e) {
            throw new SerdeException(e);
        }
    }

    public void serialize(Bible bible, File file) {
        try (FileOutputStream fileOutput = new FileOutputStream(file);
             GZIPOutputStream gzipOutput = new GZIPOutputStream(fileOutput)) {
            xmlSerde.serialize(bible, gzipOutput);
        } catch (Exception e) {
            throw new SerdeException(e);
        }
    }
}
