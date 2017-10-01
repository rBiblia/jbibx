package net.avensome.dev.bibx.serde;

import net.avensome.dev.bibx.model.Bible;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class BibxSerde {
    private BibxXmlSerde xmlSerde = new BibxXmlSerde();

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
