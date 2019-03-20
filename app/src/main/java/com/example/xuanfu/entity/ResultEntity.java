package com.example.xuanfu.entity;

public class ResultEntity {

    /**
     * appId : cc721b17b6c0411ea2c0dd2a1862b031
     * id : 3094632784
     * transactionId : 023246a067024890bacd98f1adfcdc63
     * attach : 附加数据
     * result : false
     * fee : 100
     * payTime : null
     * timestamp : 1552978808007
     * signature : PJhPsgE7Wi+XSwd/P/AILe92fa7oZ2qJpDHKjfcTdTCs6Mdr43OwXYhNTldmc4Vr+X6N4Oyws5Co82F20K0nl3CJn/ 5iPd/Jsi/1MrMOX2lp2ZL00l/0tZwRYLomUCPY7tCL5HM/b76pwmIUoxTFIpUS001fGkrZrHoNVXfKLhDBsB+8p6bbCLVHgnhZJbclHqmd 7HetdPrOmBw28PQacQBlVQdAcQ/Q7izEg5ks8BvsQc6f9JG9nu0d7tFCukPvYksRWhInlZUHRAFC2pkvS2TcZa0DjutO2TQEWGWODItPKwuCY b6ehNa6P+qTOBe5IuYDJd5zH4MCjM2PvaIC3Q==
     */

    private String appId;
    private String id;
    private String transactionId;
    private String attach;
    private boolean result;
    private int fee;
    private Object payTime;
    private long timestamp;
    private String signature;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Object getPayTime() {
        return payTime;
    }

    public void setPayTime(Object payTime) {
        this.payTime = payTime;
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
