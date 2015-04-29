/*
 *  Zemian Deng 2014
 */

package zemian.sakila;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zedeng
 */
@Entity
@Table(name = "sales_by_film_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesByFilmCategory.findAll", query = "SELECT s FROM SalesByFilmCategory s"),
    @NamedQuery(name = "SalesByFilmCategory.findByCategory", query = "SELECT s FROM SalesByFilmCategory s WHERE s.category = :category"),
    @NamedQuery(name = "SalesByFilmCategory.findByTotalSales", query = "SELECT s FROM SalesByFilmCategory s WHERE s.totalSales = :totalSales")})
public class SalesByFilmCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "category")
    private String category;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_sales")
    private BigDecimal totalSales;

    public SalesByFilmCategory() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

}
