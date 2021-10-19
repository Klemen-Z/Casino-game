package main;

import java.io.IOException;

public final class Main {

    public static void main(String[] args) {
        try {
            new JackManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
