package hkr.da216a.medicine.model;

import java.io.Serializable;

public class Medicine implements Serializable {
    public final static String TABLE_NAME = "medicine";
    public final static String NAME_COLUMN = "name";

    private String name;

    public Medicine() {
    }

    public Medicine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                '}';
    }
}
