package edu.wctc;

import java.io.Serializable;
import java.util.ArrayList;

public class Garage implements Serializable, Paintable {
    private ArrayList<Wall> wallList;

    public ArrayList<Wall> getWallList() {
        return wallList;
    }

    public Garage() {
        wallList = new ArrayList<Wall>();

        try {
            Wall wallA = new Wall(50, 50);
            wallList.add(wallA);
            Wall wallB = new Wall(100, 100);
            wallList.add(wallB);
            Wall wallC = new Wall(50, 50);
            wallList.add(wallC);
            Wall wallD = new Wall(100, 100);
            wallList.add(wallD);
        } catch (Exception e) {
            //do nothing
        }
    }

    public double getArea() {
        double area = 0;

        for (int i = 0; i < wallList.size(); i++) {
            Wall w = wallList.get(i);
            area += w.getArea();
        }
        return area;
    }

    @Override
    public String toString() {
        return "Garage Area: " + getArea() + "\n";
    }

    @Override
    public double getPremiumCost() {
        return Paintable.PREMIUM_PAINT_COST_PER_GALLON + 6;
    }

    @Override
    public double getStandardCost() {
        return Paintable.STANDARD_PAINT_COST_PER_GALLON + 5;
    }
}
