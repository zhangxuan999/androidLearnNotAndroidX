package com.chujian.ups.mtatest.common;

public class TestUpsCenter {
    private static TestUpsCenter INS = new TestUpsCenter();

    public static TestUpsCenter getInstance() {
        return INS;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getUmid() {
        return umid;
    }

    public void setUmid(String umid) {
        this.umid = umid;
    }

    String client_id;
    String umid;
}
