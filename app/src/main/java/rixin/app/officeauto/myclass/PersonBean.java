package rixin.app.officeauto.myclass;

/**
 * Created by egguncle on 16.8.5.
 * 人名类
 */
public class PersonBean {

    private String name;
    private String pinYin;
    private String firstPinYin;

    public String getFirstPinYin() {
        return firstPinYin;
    }

    public void setFirstPinYin(String firstPinYin) {
        this.firstPinYin = firstPinYin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public String toString() {
        return "姓名：" + getName() + "   拼音：" + getPinYin() + "    首字母："
                + getFirstPinYin();

    }
}