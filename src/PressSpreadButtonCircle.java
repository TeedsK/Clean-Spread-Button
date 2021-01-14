public class PressSpreadButtonCircle {
    int radius = 0;
    int alpha = 0;
    int xMouse = 0;
    int yMouse = 0;
    boolean DoneExpanding = false;
    public PressSpreadButtonCircle(int xMouse, int yMouse) {
        this.xMouse = xMouse;
        this.yMouse = yMouse;
    }
    public PressSpreadButtonCircle(int xMouse, int yMouse, int radius) {
        this.radius = radius;
        this.xMouse = xMouse;
        this.yMouse = yMouse;
    }
    public void setAlpha(int amt) {
        this.alpha = amt;
    }
    public void addRadius(int amt) {
        this.radius+=amt;
    }
    public void removeRadius(int amt) {
        this.radius-=amt;
    }
    public void setExpanding(boolean b) {
        this.DoneExpanding = b;
    }
    public boolean getExpanding() {
        return DoneExpanding;
    }
    public int getAlpha() {
        if(alpha > 255) {
            alpha = 255;
        } else if(alpha < 0) {
            alpha = 0;
        }
        return alpha;
    }
    public int getRadius() {
        return this.radius;
    }
    public int getXMouse() {
        return xMouse;
    }
    public int getYMouse() {
        return yMouse;
    }
}
