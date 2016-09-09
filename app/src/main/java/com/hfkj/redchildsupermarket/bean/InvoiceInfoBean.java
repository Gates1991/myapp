package com.hfkj.redchildsupermarket.bean;

import java.io.Serializable;

/**
 * Created by wf on 2016/9/8.
 */
public class InvoiceInfoBean implements Serializable{
    public String invoiceType;
    public String invoiceTop;
    public String invoiceContent;

    @Override
    public String toString() {
        return "InvoiceInfoBean{" +
                "invoiceType='" + invoiceType + '\'' +
                ", invoiceTop='" + invoiceTop + '\'' +
                ", invoiceContent='" + invoiceContent + '\'' +
                '}';
    }
}
