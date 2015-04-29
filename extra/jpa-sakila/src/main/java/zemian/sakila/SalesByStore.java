/*
 *  Zemian Deng 2014
 */

package zemian.sakila;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zedeng
 */
@Entity
@Table(name = "sales_by_store")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesByStore.findAll", query = "SELECT s FROM SalesByStore s"),
    @NamedQuery(name = "SalesByStore.findByStore", query = "SELECT s FROM SalesByStore s WHERE s.store = :store"),
    @NamedQuery(name = "SalesByStore.findByManager", query = "SELECT s FROM SalesByStore s WHERE s.manager = :manager"),
    @NamedQuery(name = "SalesByStore.findByTotalSales", query = "SELECT s FROM SalesByStore s WHERE s.totalSales = :totalSales")})
public class SalesByStore implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 101)
    @Column(name = "store")
    private String store;
    @Size(max = 91)
    @Column(name = "manager")
    private String manager;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_sales")
    private BigDecimal totalSales;

    public SalesByStore() {
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

}
