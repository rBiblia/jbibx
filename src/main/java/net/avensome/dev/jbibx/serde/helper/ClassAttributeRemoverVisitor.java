package net.avensome.dev.jbibx.serde.helper;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Visitor;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

public class ClassAttributeRemoverVisitor implements Visitor {
    @Override
    public void read(Type type, NodeMap<InputNode> node) throws Exception {
        // Do nothing, framework will guess appropriate class on its own
    }

    @Override
    public void write(Type type, NodeMap<OutputNode> node) throws Exception {
        OutputNode element = node.getNode();
        element.getAttributes().remove("class");
    }
}
