package com.thoughtworks.tdd;

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

}
