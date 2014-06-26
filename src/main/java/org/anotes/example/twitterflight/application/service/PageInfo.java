package org.anotes.example.twitterflight.application.service;

import java.io.Serializable;

/**
 * User: gmc
 * Date: 27/03/2014
 */
public class PageInfo implements Serializable {
    private int currentPage = 0;
    private int totalPages;
    private int pageSize = 10;
    private int productsNbr;

    public void setProductsNbr(int productsNbr) {
        this.productsNbr = productsNbr;
        totalPages = productsNbr / pageSize;
        int residuo = productsNbr % pageSize;
        if (residuo > 0) {
            totalPages++;
        }
    }

    public int getProductsNbr() {
        return productsNbr;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPage() {
        return currentPage + 1;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getCurrentRangeStr() {
        int rangeEnd = (currentPage + 1) * pageSize;
        return (currentPage * pageSize + 1) + " - " + Math.min(rangeEnd, productsNbr);
    }

    public int getFirstRow() {
        return currentPage * pageSize;
    }

    public int getLastRow() {
        int lastPage = (currentPage + 1) * pageSize;
        return Math.min(lastPage, productsNbr) - 1;
    }
}
