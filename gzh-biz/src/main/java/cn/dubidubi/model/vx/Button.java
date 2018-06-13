package cn.dubidubi.model.vx;

import java.io.Serializable;

/**
 * 自定义菜单类
 */
public class Button implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
