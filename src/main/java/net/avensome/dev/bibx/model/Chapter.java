package net.avensome.dev.bibx.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementMap;

import java.util.*;

public class Chapter extends Identifiable<Integer> implements Comparable<Chapter> {
    @Attribute(name = "id")
    private final int number;

    private final SortedMap<Integer, String> verses;
    private final List<String> orderedVerses;

    public Chapter(int number) {
        this(number, Collections.emptyMap());
    }

    public Chapter(int number, List<String> verses) {
        this.number = number;
        this.orderedVerses = Collections.unmodifiableList(new ArrayList<>(verses));
        if (verses.isEmpty()) {
            //noinspection unchecked
            this.verses = Collections.emptySortedMap();
            return;
        }
        SortedMap<Integer, String> verseMap = new TreeMap<>();
        for (int verseNumber = 1; verseNumber <= verses.size(); verseNumber++) {
            String verse = verses.get(verseNumber - 1);
            if (verse != null) {
                verseMap.put(verseNumber, verse);
            }
        }
        this.verses = Collections.unmodifiableSortedMap(verseMap);
    }

    public Chapter(
            @Attribute(name = "id") int number,
            @ElementMap(inline = true, entry = "verse", key = "id", attribute = true, required = false, empty = false)
                    Map<Integer, String> verses
    ) {
        if (number <= 0) {
            throw new SemanticException("Chapter number must be positive");
        }
        this.number = number;
        TreeMap<Integer, String> map = new TreeMap<>(verses);
        List<Integer> nullKeys = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                nullKeys.add(entry.getKey());
            }
        }
        for (Integer nullKey : nullKeys) {
            map.remove(nullKey);
        }
        this.verses = Collections.unmodifiableSortedMap(map);
        if (this.verses.isEmpty()) {
            orderedVerses = Collections.emptyList();
            return;
        }
        TreeSet<Integer> verseOrderedSet = new TreeSet<>(verses.keySet());
        int maxNumber = verseOrderedSet.last();
        List<String> verseList = new ArrayList<>(maxNumber);
        for (int verseNumber = 1; verseNumber <= maxNumber; verseNumber++) {
            String verse = verses.get(verseNumber);  // possibly null
            verseList.add(verse);
        }
        orderedVerses = Collections.unmodifiableList(verseList);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public Integer getId() {
        return number;
    }

    @Override
    public int compareTo(Chapter o) {
        return Integer.compare(number, o.number);
    }

    public List<String> getOrderedVerses() {
        return orderedVerses;
    }

    @ElementMap(inline = true, entry = "verse", key = "id", attribute = true, required = false, empty = false)
    public Map<Integer, String> getVerses() {
        return verses;
    }

    public String getVerse(int number) {
        if (!verses.containsKey(number)) {
            throw new NoSuchElementException(String.format("Chapter doesn't contain verse %d", number));
        }
        return verses.get(number);
    }
}
