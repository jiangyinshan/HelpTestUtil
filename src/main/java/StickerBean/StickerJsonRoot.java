
package StickerBean;


public class StickerJsonRoot {

    private int errorCode;
    private String errorMsg;
    private Data data;

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

}