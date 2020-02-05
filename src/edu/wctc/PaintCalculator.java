package edu.wctc;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PaintCalculator {

    private ArrayList<Room> roomList = new ArrayList<>();
    private Scanner keyboard;

    public static void main(String[] args)
    {
        new PaintCalculator();
    }

    public PaintCalculator() {
        keyboard = new Scanner(System.in);

        int option = 0;

        while (option != 5) {
            printMenu();

            String s = keyboard.nextLine();
            try {
                int choice = Integer.parseInt(s);
                switch (choice) {
                    case 1:
                        createRoom();
                        break;
                    case 2:
                        // writeFile(); -Added by Kris
                        RoomWriter rw  = new RoomWriter();
                        try {
                            rw.writeRoomFile("room.dat", roomList);
                            System.out.println("Rooms saved to file.");
                        } catch (IOException e) {
                            System.out.println("IO Exception Error:");
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        // readFile() -Added by Kris;
                        RoomReader rr = new RoomReader();
                        try {
                            roomList = rr.readRoomFile("room.dat");
                            Room.setRoomCount(rr.getRoomsRead());
                            System.out.println(rr.getRoomsRead() + " room(s) read from file.");
                        } catch (IOException e) {
                            System.out.println("IO Exception Error:");
                            System.out.println(e.getMessage());
                        } catch (ClassNotFoundException e) {
                            System.out.println("Class Not Found Exception Error:");
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        printRooms();
                        break;
                    case 5:
                        System.out.println("Goodbye");
                        System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice");
            }
        }

    }

    private void printRooms() {
        if (roomList.isEmpty()) {
            System.out.println("No rooms yet");
        }

        for (Room room : roomList) {
            System.out.println(room.toString());
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Add room");
        System.out.println("2. Write rooms to file");
        System.out.println("3. Read rooms from file");
        System.out.println("4. View rooms");
        System.out.println("5. Exit");
        System.out.println();
    }

    private int promptForDimension(String name) {
        System.out.print("Enter the room's " + name + ": ");
        String response = keyboard.nextLine();
        try {
            return Integer.parseInt(response);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void createRoom() {
        int length = promptForDimension("length");
        int width = promptForDimension("width");
        int height = promptForDimension("height");

        try {
            Room room = new Room(length, width, height);
            roomList.add(room);

            System.out.println("Room successfully created");
        } catch (BadWidthException | BadHeightException e) {
            System.out.println("Could not create room.");
        }

    }
}
