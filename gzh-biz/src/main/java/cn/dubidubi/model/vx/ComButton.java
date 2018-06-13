package cn.dubidubi.model.vx;

import java.io.Serializable;

public class ComButton extends Button  implements Serializable {
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
