package edu.wctc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RoomWriter {
    public void writeRoomFile(String fileName, ArrayList<Paintable> paintableList) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Paintable r : paintableList) {
            oos.writeObject(r);
        }
        oos.close();
    }
}
