package cn.dubidubi.model.vx;

import java.io.Serializable;

public class Menu implements Serializable {
    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }
}
