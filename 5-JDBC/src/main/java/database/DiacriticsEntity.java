package database;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class used to describe content of Diacritics table
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
@Entity
@Table(name = "Diacritics", schema = "APP")
@IdClass(DiacriticsEntityPK.class)
public class DiacriticsEntity {
    /**
     * private field holding column label in the table
     */
    private int id;
    /**
     * private field holding column label in the table
     */
    private String diacritic;
    /**
     * private field holding column label in the table
     */
    private int occurrence;
    /**
     * private field holding object of TextEntity type
     */
    private TextsEntity D_FK;

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
     * @return diacritic
     */
    @Id
    @Column(name = "DIACRITIC", nullable = false, length = 1)
    public String getDiacritic() {
        return diacritic;
    }

    /**
     * Public setter
     * @param diacritic string
     */
    public void setDiacritic(String diacritic) {
        this.diacritic = diacritic;
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
     * @param occurrence string
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
        DiacriticsEntity that = (DiacriticsEntity) o;
        return id == that.id && occurrence == that.occurrence && Objects.equals(diacritic, that.diacritic);
    }

    /**
     * Public overridden method
     * @return object hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, diacritic, occurrence);
    }

    /**
     * Public getter
     * @return D_FK
     */
    @OneToOne(mappedBy = "D_FK")
    public TextsEntity getD_FK() {
        return D_FK;
    }

    /**
     * Public setter
     * @param d_FK TextsEntity object
     */
    public void setD_FK(TextsEntity d_FK) {
        D_FK = d_FK;
    }
}
