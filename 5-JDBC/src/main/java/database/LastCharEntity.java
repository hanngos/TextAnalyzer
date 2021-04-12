package database;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class used to describe content of LastChar table
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
@Entity
@Table(name = "LastChar", schema = "APP")
public class LastCharEntity {
    /**
     * private field holding column label in the table
     */
    private int id;
    /**
     * private field holding column label in the table
     */
    private String lastchar;
    /**
     * private field holding column label in the table
     */
    private int occurrence;
    /**
     * private field holding object of TextEntity type
     */
    private TextsEntity LC_FK;

    /**
     * Public getter
     * @return id
     */
    @Id
    @Column(name = "ID", nullable = false)
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
     * @return lastchar
     */
    @Basic
    @Column(name = "LASTCHAR", nullable = false, length = 1)
    public String getLastchar() {
        return lastchar;
    }

    /**
     * Public setter
     * @param lastchar String
     */
    public void setLastchar(String lastchar) {
        this.lastchar = lastchar;
    }

    /**
     * Public getter
     * @return occurrence
     */
    @Basic
    @Column(name = "OCCURRENCE", nullable = false)
    public int getOccurrence() {
        return occurrence;
    }

    /**
     * Public setter
     * @param occurrence int
     */
    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
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
        LastCharEntity that = (LastCharEntity) o;
        return id == that.id && occurrence == that.occurrence && Objects.equals(lastchar, that.lastchar);
    }

    /**
     * Public overridden method
     * @return object hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, lastchar, occurrence);
    }

    /**
     * Public getter
     * @return LC_FK
     */
    @OneToOne(mappedBy = "LC_FK")
    public TextsEntity getLC_FK() {
        return LC_FK;
    }

    /**
     * Public setter
     * @param LC_FK TextsEntity object
     */
    public void setLC_FK(TextsEntity LC_FK) {
        this.LC_FK = LC_FK;
    }
}
