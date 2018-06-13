package cn.dubidubi.model.vx;

public class ShareUrl {
    //分享链接id
    private Integer id;
    //分享链接url
    private String url;
    //分享的状态
    private  Integer state;
    //分享链接内容的类型
    private String contentType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "ShareUrl{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", state=" + state +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
