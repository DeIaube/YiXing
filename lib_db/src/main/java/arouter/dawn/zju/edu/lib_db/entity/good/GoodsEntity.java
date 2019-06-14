package arouter.dawn.zju.edu.lib_db.entity.good;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import arouter.dawn.zju.edu.lib_db.entity.BaseEntity;

@Entity(tableName = "goods_class")
public class GoodsEntity extends BaseEntity {

    @ColumnInfo(name = "preview")
    private String preview;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "region")
    private String region;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "city")
    private String city;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "explain")
    private String explain;
    @ColumnInfo(name = "shop")
    private String shop;
    @ColumnInfo(name = "startDate")
    private String startDate;
    @ColumnInfo(name = "endDate")
    private String endDate;
    @ColumnInfo(name = "previewList")
    private String previewList;
    @ColumnInfo(name = "price")
    private double price;

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPreviewList() {
        return previewList;
    }

    public void setPreviewList(String previewList) {
        this.previewList = previewList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
