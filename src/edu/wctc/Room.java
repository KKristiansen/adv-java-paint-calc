package edu.wctc;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    private ArrayList<Wall> wallList;

    //Added by Kris Kristiansen
    static int roomCount = 0;
    int roomNum;

    //Added by Kris Kristiansen
    public static void setRoomCount(int roomCount) {
        Room.roomCount = roomCount;
    }

    //Added by Kris
    @Override
    public String toString() {
        return "Room Number: " + roomNum + "\nArea: " + getArea() + "\n";
    }

    public Room(double length, double width, double height) throws BadWidthException, BadHeightException {
        wallList = new ArrayList<Wall>();

        Wall wallA = new Wall(length, height);
        wallList.add(wallA);
        Wall wallB = new Wall(length, height);
        wallList.add(wallB);
        Wall wallC = new Wall(width, height);
        wallList.add(wallC);
        Wall wallD = new Wall(width, height);
        wallList.add(wallD);

        //Added by Kris
        roomCount++;
        roomNum = roomCount;
    }

    public double getArea() {
        double area = 0;

        for (int i = 0; i < wallList.size(); i++) {
            Wall w = wallList.get(i);
            area += w.getArea();
        }

        return area;
    }
}
