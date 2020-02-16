package edu.wctc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RoomReader {
    int roomsRead;

    public ArrayList<Paintable> readRoomFile(String fileName) throws IOException, ClassNotFoundException {
        ArrayList<Paintable> roomList = new ArrayList<>();
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj;
        roomsRead = 0;
        try {
            while ((obj = ois.readObject()) != null) {
                roomList.add((Paintable) obj);
                roomsRead++;
            }
        } catch (IOException e) {
            //Do nothing
        }
        ois.close();
        return roomList;
    }

    public int getRoomsRead() {
        return roomsRead;
    }
}
