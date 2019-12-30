public interface Plant {
    /**
     * set the position of the plant
     * @param x sets the x coordinate
     * @param y sets the y coordinate
     */
    public void setPosition(int x, int y);

    /**
     * @return the health of the plant
     */
    public int getHealth();

    /**
     * sets the health of the plant
     * @param health
     */
    public void setHealth(int health);

    /**
     * @return the power of the plant
     */
    public int getPower();

    /** sets the value of power for a plant
     * @param power
     */
    public void setPower(int power);

    /**
     * @return the price for which the plant is available at the shop
     */
    public int getPrice();

    public void setMovement();

    public void removeImage();

    public int getRow();

    public int getColumn();

    public String getName();
}
