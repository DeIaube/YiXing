package baselib.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("Order")
public class Order extends AVObject {

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public String getPreview() {
        return getString("preview");
    }

    public void setPreview(String preview) {
        put("preview", preview);
    }

    public String getType() {
        return getString("type");
    }

    public void setType(String type) {
        put("type", type);
    }

    public double getPay() {
        return getNumber("pay").doubleValue();
    }

    public void setPay(double pay) {
        put("pay", pay);
    }
}
