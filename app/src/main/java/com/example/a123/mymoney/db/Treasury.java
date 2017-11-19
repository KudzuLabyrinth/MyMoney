package com.example.a123.mymoney.db;

import org.litepal.crud.DataSupport;

/**
 * Created by KudzuLabyrinth on 2017/11/19.
 */

public class Treasury extends DataSupport {

    /**
     * 紀錄現金流的資產和負債
     */

    private int property;
    private int liabilities;

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public int getLiabilities() {
        return liabilities;
    }

    public void setLiabilities(int liabilities) {
        this.liabilities = liabilities;
    }
}
