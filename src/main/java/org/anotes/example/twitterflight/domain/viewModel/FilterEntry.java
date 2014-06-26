package org.anotes.example.twitterflight.domain.viewModel;

/**
 * User: gmc
 * Date: 28/05/2014
 */
public class FilterEntry implements java.io.Serializable {
    private String name;
    private String value;

    public FilterEntry() {
    }

    public FilterEntry(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilterEntry)) return false;

        FilterEntry that = (FilterEntry) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
