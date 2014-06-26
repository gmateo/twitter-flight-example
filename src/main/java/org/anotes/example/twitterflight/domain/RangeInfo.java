package org.anotes.example.twitterflight.domain;

/**
 * User: gmc
 * Date: 29/03/2014
 */
public class RangeInfo implements java.io.Serializable {
    private int id;
    private String description;
    private Integer nbr = 0;

    public RangeInfo() {
    }

    public RangeInfo(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNbr() {
        return nbr;
    }

    public void setNbr(Integer nbr) {
        this.nbr = nbr;
    }

    public void addNbr(Integer nbrToAdd) {
        nbr += nbrToAdd;
    }

    public void incrementNbr() {
        nbr++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RangeInfo)) return false;

        RangeInfo rangeInfo = (RangeInfo) o;

        if (id != rangeInfo.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public static RangeInfo forSearching(int id) {
        RangeInfo rangeInfo = new RangeInfo();
        rangeInfo.setId(id);
        return rangeInfo;
    }
}
