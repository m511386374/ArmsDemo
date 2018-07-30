package www.goodycom.com.armsdemo.net;


import java.io.Serializable;

public class LzyResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;


    //    public int ret;
    public boolean code;
    public T results;
    @Override
    public String toString() {
        return "LzyResponse{" +
                "error=" + code +
                ", results=" + results +
                '}';
    }
}