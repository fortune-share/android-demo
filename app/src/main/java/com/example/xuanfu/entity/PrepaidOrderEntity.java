package com.example.xuanfu.entity;

public class PrepaidOrderEntity {

    /**
     * transactionId : 4f45d8cb62ca46b2b05b071e7325c60b
     * qrCodeUrl : null
     * timestamp : 1552903067582
     * signature : R/daNny9Y2tYpyRYJu06JMb8TN/at4GGCVI8d/8LAAQ2RFvdF6GGMEzMdu2CAO3BP3fYQCNSopdWuWkfLv5OB88mTw saF7Ahh6i500uwwNj9lq3egu365l4nSzbo/bBOwNcc2ZU4dpWnhap+4uFnh3KZE2PWkL217eN+poufcPSIyxgl4MA2fMbrBNGWtZEamO3DNz4 v/Xo+kIXcCp+xmRi72HDkkYiVO1iwJAXxFX7os5f6pjdt9aGVXlm25BOV6DkyIJFt34QPfdXUSKAlNThlEx6QQi1MSSOsbk4FDfJ+acwJplss iPXmIx476lrq9d8gXn4nbu4tKUQpDDZyNA==
     */

    private String transactionId;
    private Object qrCodeUrl;
    private long timestamp;
    private String signature;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Object getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(Object qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
