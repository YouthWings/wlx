package cn.dubidubi.model.vx;

/**
 * 一条推文对象
 */
public class Tweets {
    //推文id
    private Integer id;
    //推文链接
    private String url;
    //推文标题
    private String title;
    //推文概述
    private String content;
    //推文封面
    private String img;
    //推文作者
    private String author;
    //推文状态 1 上线展示 0 下架不展示
    private Integer state;
    //推文类型  1 超市 2 旅游 3 考证 4 轰趴 5 跑腿 6 集市
    private String urltype;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUrltype() {
        return urltype;
    }

    public void setUrltype(String urltype) {
        this.urltype = urltype;
    }

    @Override
    public String toString() {
        return "Tweets{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", author='" + author + '\'' +
                ", state=" + state +
                ", urltype='" + urltype + '\'' +
                '}';
    }

}
