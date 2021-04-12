package database;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class used to describe content of Diacritics primary key
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
public class DiacriticsEntityPK implements Serializable {
    /**
     * private field holding column label in the table
     */
    private int id;
    /**
     * private field holding column label in the table
     */
    private String diacritic;

    /**
     * Public getter
     * @return id
     */
    @Column(name = "ID", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    /**
     * Public setter
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Public getter
     * @return diacritic
     */
    @Column(name = "DIACRITIC", nullable = false, length = 1)
    @Id
    public String getDiacritic() {
        return diacritic;
    }

    /**
     * Public setter
     * @param diacritic String
     */
    public void setDiacritic(String diacritic) {
        this.diacritic = diacritic;
    }

    /**
     * Public overridden method
     * @param o Object object
     * @return boolean if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiacriticsEntityPK that = (DiacriticsEntityPK) o;
        return id == that.id && Objects.equals(diacritic, that.diacritic);
    }

    /**
     * Public overridden method
     * @return object hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, diacritic);
    }
}
