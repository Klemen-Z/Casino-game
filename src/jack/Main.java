package jack;

import java.io.IOException;

public final class Main {
    public static void main(String[] args) {
        //run code if IO error is encountered print error log
        try {
            new JackManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}