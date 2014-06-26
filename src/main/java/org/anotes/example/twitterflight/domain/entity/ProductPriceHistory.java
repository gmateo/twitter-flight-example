package org.anotes.example.twitterflight.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: gmc
 * Date: 02/06/2014
 */
public class ProductPriceHistory {
    private BigDecimal price;
    private Date created;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
