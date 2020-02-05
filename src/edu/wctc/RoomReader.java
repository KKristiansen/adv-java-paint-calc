package edu.wctc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RoomReader {
    int roomsRead;

    public ArrayList<Room> readRoomFile(String fileName) throws IOException, ClassNotFoundException {
        ArrayList<Room> roomList = new ArrayList<>();
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj;
        roomsRead = 0;
        try {
            while ((obj = ois.readObject()) != null) {
                roomList.add((Room) obj);
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
