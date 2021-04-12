package database;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class used to describe content of Texts table
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
@Entity
@Table(name = "Texts", schema = "APP")
public class TextsEntity {
    /**
     * private field holding column label in the table
     */
    private int id;
    /**
     * private field holding column label in the table
     */
    private String text;
    /**
     * private field holding object of DiacriticsEntity type
     */
    private DiacriticsEntity D_FK;
    /**
     * private field holding object of InfoEntity type
     */
    private InfoEntity I_FK;
    /**
     * private field holding object of LastCharEntity type
     */
    private LastCharEntity LC_FK;
    /**
     * private field holding object of PalindromesEntity type
     */
    private PalindromesEntity P_FK;
    /**
     * private field holding object of WordsEntity type
     */
    private WordsEntity W_FK;

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
     * Public overridden method
     * @param o Object object
     * @return boolean if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextsEntity that = (TextsEntity) o;
        return id == that.id && text.equals(that.text);
    }

    /**
     * Public overridden method
     * @return object hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    /**
     * Public getter
     * @return text
     */
    @Basic
    @Column(name = "TEXT", length = 128)
    public String getText() {
        return text;
    }

    /**
     * Public setter
     * @param text String
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Public getter
     * @return D_FK
     */
    @OneToOne
    public DiacriticsEntity getD_FK() {
        return D_FK;
    }

    /**
     * Public setter
     * @param d_FK DiacriticsEntity object
     */
    public void setD_FK(DiacriticsEntity d_FK) {
        D_FK = d_FK;
    }

    /**
     * Public getter
     * @return I_FK
     */
    @OneToOne
    public InfoEntity getI_FK() {
        return I_FK;
    }

    /**
     * Public setter
     * @param i_FK InfoEntity object
     */
    public void setI_FK(InfoEntity i_FK) {
        I_FK = i_FK;
    }

    /**
     * Public getter
     * @return LC_FK
     */
    @OneToOne
    public LastCharEntity getLC_FK() {
        return LC_FK;
    }

    /**
     * Public setter
     * @param LC_FK LastCharEntity object
     */
    public void setLC_FK(LastCharEntity LC_FK) {
        this.LC_FK = LC_FK;
    }

    /**
     * Public getter
     * @return P_FK
     */
    @OneToOne
    public PalindromesEntity getP_FK() {
        return P_FK;
    }

    /**
     * Public setter
     * @param p_FK PalindromesEntity object
     */
    public void setP_FK(PalindromesEntity p_FK) {
        P_FK = p_FK;
    }

    /**
     * Public getter
     * @return W_FK
     */
    @OneToOne
    public WordsEntity getW_FK() {
        return W_FK;
    }

    /**
     * Public setter
     * @param w_FK WordsEntity
     */
    public void setW_FK(WordsEntity w_FK) {
        W_FK = w_FK;
    }
}
