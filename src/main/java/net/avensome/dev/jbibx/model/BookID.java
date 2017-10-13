package net.avensome.dev.jbibx.model;

import java.util.HashMap;
import java.util.Map;

public enum BookID {
    OT_GEN,
    OT_EXO,
    OT_LEV,
    OT_NUM,
    OT_DEU,
    OT_JOS,
    OT_JDG,
    OT_RUT,
    OT_1SA,
    OT_2SA,
    OT_1KI,
    OT_2KI,
    OT_1CH,
    OT_2CH,
    OT_EZR,
    OT_NEH,
    OT_EST,
    OT_JOB,
    OT_PSA,
    OT_PRO,
    OT_ECC,
    OT_SOL,
    OT_ISA,
    OT_JER,
    OT_LAM,
    OT_EZE,
    OT_DAN,
    OT_HOS,
    OT_JOE,
    OT_AMO,
    OT_OBA,
    OT_JON,
    OT_MIC,
    OT_NAH,
    OT_HAB,
    OT_ZEP,
    OT_HAG,
    OT_ZEC,
    OT_MAL,

    NT_MAT,
    NT_MAR,
    NT_LUK,
    NT_JOH,
    NT_ACT,
    NT_ROM,
    NT_1CO,
    NT_2CO,
    NT_GAL,
    NT_EPH,
    NT_PHI,
    NT_COL,
    NT_1TH,
    NT_2TH,
    NT_1TI,
    NT_2TI,
    NT_TIT,
    NT_PHM,
    NT_HEB,
    NT_JAM,
    NT_1PE,
    NT_2PE,
    NT_1JO,
    NT_2JO,
    NT_3JO,
    NT_JUD,
    NT_REV,;

    private static final Map<String, BookID> stringToValue = new HashMap<>();

    static {
        BookID[] ids = BookID.values();
        for (BookID id : ids) {
            stringToValue.put(id.toString(), id);
        }
    }

    @Override
    public String toString() {
        String name = name();
        int underscoreIndex = name.indexOf('_');
        String bookPart = name.substring(underscoreIndex + 1);
        return bookPart.toLowerCase();
    }

    public static BookID fromString(String bookName) {
        BookID value = stringToValue.get(bookName);
        if (value == null) {
            throw new IllegalArgumentException(String.format("Unknown book '%s'", bookName));
        }
        return value;
    }
}
