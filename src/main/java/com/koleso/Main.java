package com.koleso;

import com.koleso.java.core.MyStringBuilder;

public class Main {
    public static void main(String[] args) {
        MyStringBuilder msb = new MyStringBuilder();

        System.out.println(msb);

        msb.append('a');
        System.out.println(msb);

        msb.append('b');
        System.out.println(msb);

        msb.append("test");
        System.out.println(msb);

        msb.undo();
        System.out.println(msb);

        msb.undo();
        System.out.println(msb);

        msb.undo();
        System.out.println(msb);

        msb.undo();
        System.out.println(msb);
    }
}
