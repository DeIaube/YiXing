package baselib.bus;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * EventBus御用
 */
public class BusEvent {
    private int code;
    private String target;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public BusEvent() {

    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BusEvent(int code, Object data) {
        this.code = code;
        this.data = data;
    }
}
