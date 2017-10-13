package net.avensome.dev.jbibx.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Identifiable<T> {
    public abstract T getId();

    public static <K, O extends Identifiable<K>> Map<K, O> listToMap(List<O> list) {
        Map<K, O> map = new HashMap<>();
        for (O item : list) {
            map.put(item.getId(), item);
        }
        return map;
    }

    public static <K, O extends Identifiable<K>> List<K> listToIdList(List<O> items) {
        List<K> ids = new ArrayList<>(items.size());
        for (O item : items) {
            ids.add(item.getId());
        }
        return ids;
    }
}
