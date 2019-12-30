public interface Zombie {

    public void setImgPos(double x, double y);

    public double getImagePositionX(); 

    public int getRow();

    public int getCol();

    public double getHealth();

    public void setHealth(double health);

    public int getPower();

    public double getInitialSpeed();

    public void setSpeed(double speed);

    public void setCol(int column);

    public void movement();

    public void removeImage();
}
