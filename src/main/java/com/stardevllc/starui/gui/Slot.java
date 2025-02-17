package com.stardevllc.starui.gui;

import com.stardevllc.starui.element.Element;

public class Slot {
    
    protected final int index;
    protected Element element;

    public Slot(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
