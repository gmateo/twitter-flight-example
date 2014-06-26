package org.anotes.example.twitterflight.domain;

import org.anotes.example.twitterflight.domain.entity.Product;

/**
 * User: gmc
 * Date: 26/03/2014
 */
public interface Visitor {
    public void visit(Product product);
}
