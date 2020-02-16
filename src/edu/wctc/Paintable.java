package edu.wctc;

public interface Paintable {
    double PREMIUM_PAINT_COST_PER_GALLON = 65.99;
    double STANDARD_PAINT_COST_PER_GALLON = 25.99;

    // Cost to paint using premium paint?
    public double getPremiumCost();

    // Cost to paint using standard paint?
    public double getStandardCost();
}
