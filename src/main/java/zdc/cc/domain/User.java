package zdc.cc.domain;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -3946734305303957850L;
    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
