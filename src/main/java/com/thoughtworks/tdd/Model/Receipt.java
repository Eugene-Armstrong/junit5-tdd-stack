package com.thoughtworks.tdd.Model;

import java.util.Objects;

public class Receipt {
    public Receipt(){

    }
    public Receipt(String receiptUUID){
        setReceiptUUID(receiptUUID);
    }
    public String getReceiptUUID() {
        return receiptUUID;
    }

    public void setReceiptUUID(String receiptUUID) {
        this.receiptUUID = receiptUUID;
    }

    private String receiptUUID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(receiptUUID, receipt.receiptUUID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(receiptUUID);
    }
}
