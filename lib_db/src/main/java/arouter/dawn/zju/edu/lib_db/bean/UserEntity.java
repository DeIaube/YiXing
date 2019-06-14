package arouter.dawn.zju.edu.lib_db.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "user_class")
public class UserEntity extends BaseEntity{

    @ColumnInfo(name = "pickname")
    private String pickname;

    @ColumnInfo(name = "payPassword")
    private String payPassword;

    @ColumnInfo(name = "seretPayment")
    private int seretPayment;

    @ColumnInfo(name = "portrait")
    private String portrait;

    @ColumnInfo(name = "birth")
    private String birth;

    @ColumnInfo(name = "shopPoint")
    private int shopPoint;

    @ColumnInfo(name = "balance")
    private double balance;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "mobilePhoneNumber")
    private String mobilePhoneNumber;

    @ColumnInfo(name = "email")
    private String email;

    public String getPickname() {
        return pickname;
    }

    public void setPickname(String pickname) {
        this.pickname = pickname;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public int getSeretPayment() {
        return seretPayment;
    }

    public void setSeretPayment(int seretPayment) {
        this.seretPayment = seretPayment;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getShopPoint() {
        return shopPoint;
    }

    public void setShopPoint(int shopPoint) {
        this.shopPoint = shopPoint;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
