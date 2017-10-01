package net.avensome.dev.bibx.serde.types;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

public class BooleanAsIntConverter implements Converter<Boolean> {
    @Override
    public Boolean read(InputNode node) throws Exception {
        String stringValue = node.getValue();
        int intValue = Integer.parseInt(stringValue);
        return intValue != 0;
    }

    @Override
    public void write(OutputNode node, Boolean value) throws Exception {
        String stringValue = value ? "1" : "0";
        node.setValue(stringValue);
    }
}
