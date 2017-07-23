package info.chhaileng.app.model;

public class AccKitPostRequest {
	private String csrf;
    private String code;

    public AccKitPostRequest(){}

    public AccKitPostRequest(String csrf,String code){
        this.csrf = csrf;
        this.code = code;
    }

    public String getCsrf() {
        return csrf;
    }

    public void setCsrf(String csrf) {
        this.csrf = csrf;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
