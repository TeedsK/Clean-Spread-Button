package Animations.Button;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
@SuppressWarnings("serial")
/**
 * Creates a button that changes color on hover and spreads a color when pressed
 * 
 * @author Teeds - Theo K
 */
public class PressSpreadButton extends JPanel implements MouseListener {
    ArrayList<JPanel> ParentJPanels = new ArrayList<JPanel>();
    ArrayList<PressSpreadButtonCircle> circles = new ArrayList<PressSpreadButtonCircle>();
    int round = 0; // the roundness of JPanel
    JLabel text;
    JLabel image;
    Color Background_Color;
    Color Button_Color;
    Color Hover_Color;
    Color Press_Color;
    int xMouse = 0;
    int yMouse = 0;
    boolean fadeInText = true;
    int[] incrementAmount = {
        5, // hover increment
        10, // pressed increment
        1,  //circle expansion increment
        2
    };
    int[] sleepAmount = {
        5, // hover sleep time
        8, // pressed sleep time
        4, // pressed size increase time
        4  //selected increase time
    };
    // Turns on/off when hovering or pressing JPanel
    boolean[] switches = { 
        false, //background
        false, // hovering
        false, // pressed
        false  //selected_mode
    };
    // The alpha for the colors
    int[] Alphas = { 
        255, // Color of the button Alpha
        255, // Text Alpha
        0, // Hover Alpha
        0, // Press Alpha
        0,  // selected Alpha
        255 //Background of the button alpha
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame("Example");
        JPanel test = new JPanel();
        frame.add(test);
        test.setLayout(new BoxLayout(test, BoxLayout.X_AXIS));
        PressSpreadButton button = new PressSpreadButton("test", new Color(27, 30, 39), new Color(44, 46, 61), new Color(55, 58, 75), new Color(95, 98, 123), 15);
        button.setImage("WhiteStartButton.png");
        button.setPreferredSize(new Dimension(150, 50));
        button.setMaximumSize(new Dimension(150, 50));
        button.setMinimumSize(new Dimension(150, 50));
        test.setBackground(new Color(27, 30, 39));
        test.add(Box.createHorizontalGlue());
        test.add(button);
        test.add(Box.createHorizontalGlue());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * @param text The Text in the button
     */
    public PressSpreadButton(String text) {
        this.text = new JLabel(text);
        setup();
    }

    /**
     * @param text The Text in the button
     * @param background color of panel behind button
     * @param buttonColor color of the button
     */
    public PressSpreadButton(String text, Color background, Color buttonColor) {
        this.text = new JLabel(text);
        this.Button_Color = buttonColor;
        this.Background_Color = background;
        setup();
    }
    /**
     * @param text Text text in the button
     * @param background color of panel behind button
     * @param buttonColor color of the button
     * @param hoverColor Color to change to when hovering
     * @param pressColor Color to change to when pressing
     */
    public PressSpreadButton(String text, Color background, Color buttonColor, Color hoverColor, Color pressColor) {
        this.text = new JLabel(text);
        this.Button_Color = buttonColor;
        this.Background_Color = background;
        this.Hover_Color = hoverColor;
        this.Press_Color = pressColor;
        setup();
    }

    /**
     * 
     * @param text Text text in the button
     * @param background color of panel behind button
     * @param buttonColor color of the button
     * @param hoverColor Color to change to when hovering
     * @param pressColor Color to change to when pressing
     * @param roundness The roundness of the button
     */
    public PressSpreadButton(String text, Color background, Color buttonColor, Color hoverColor, Color pressColor, int roundness) {
        this.text = new JLabel(text);
        this.Button_Color = buttonColor;
        this.Background_Color = background;
        this.Hover_Color = hoverColor;
        this.Press_Color = pressColor;
        this.round = roundness;
        setup();
    }

    /**
     * 
     * @param background color of panel behind button
     * @param buttonColor color of the button
     * @param hoverColor Color to change to when hovering
     * @param pressColor Color to change to when pressing
     * @param roundness The roundness of the button
     */
    public PressSpreadButton(Color background, Color buttonColor, Color hoverColor, Color pressColor, int roundness) {
        this.Button_Color = buttonColor;
        this.Background_Color = background;
        this.Hover_Color = hoverColor;
        this.Press_Color = pressColor;
        this.round = roundness;
        setup();
    }

    /**
     * set the basic color of the button
     * @param color the desired color
     */
    public void setButtonColor(Color color) {
        this.Button_Color = color;
    }

    /**
     * sets up the button
     */
    private void setup() {
        setLayout(new BorderLayout());
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));;
        addMouseListener(this);
        if(text != null) {
            text.setForeground(new Color(255,255,255, Alphas[1]));
            text.setHorizontalAlignment(JLabel.CENTER);
            Font u = new Font("Calibri", Font.PLAIN, 15);
            try {
                u = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/OpenSans/open-sans.semibold.ttf"));
            } catch(Exception err1) {}
            text.setFont(u.deriveFont(Font.PLAIN, 14.3f));
            if(image != null) {
                this.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
                image.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
                this.add(image, BorderLayout.WEST);
            }
            this.add(text, BorderLayout.CENTER);
        }
    }
    
    /**
     * @param size the desired font size
     */
    public void setFontSize(float size) {
        text.setFont(text.getFont().deriveFont(Font.PLAIN, size));
    }

    /**
     * Sets the image for the left side of the button
     * @param image the file location of the image
     */
    public void setImage(String i) {
        Image p1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource(i));
        Image p2 = p1.getScaledInstance(17,17, Image.SCALE_SMOOTH);
        ImageIcon p3 = new ImageIcon(p2);
        image.setIcon(p3);
        
    }

    /**
     * Paints the button and the animations
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(Button_Color != null) {
            Alphas[0] = AlphaCheck(0);
            graphics.setColor(new Color(Button_Color.getRed(), Button_Color.getGreen(), Button_Color.getBlue(), Alphas[0]));
            graphics.fillRoundRect(0, 0, getWidth(), getHeight(), round, round);
        }
        
        if(Hover_Color != null) {
            Alphas[2] = AlphaCheck(2);
            graphics.setColor(new Color(Hover_Color.getRed(), Hover_Color.getGreen(), Hover_Color.getBlue(), Alphas[2]));
            graphics.fillRoundRect(0, 0, getWidth(), getHeight(), round, round);
        }
        
        if(Press_Color != null) {
            try {
                for(PressSpreadButtonCircle c : circles) {
                    c.setAlpha(c.getAlpha());
                    if(c.getAlpha() != 0) {
                        graphics.setColor(new Color(Press_Color.getRed(), Press_Color.getGreen(), Press_Color.getBlue(), c.getAlpha()));
                        int radius = c.getRadius();
                        graphics.fillOval(c.getXMouse() - radius, c.getYMouse() - radius, (radius * 2), (radius * 2));
                    }
                }
            } 
            catch(java.util.ConcurrentModificationException e) {}
            catch(java.util.NoSuchElementException e) {}
        }
        
        if(Press_Color != null) {
            Alphas[4] = AlphaCheck(4);
            graphics.setColor(new Color(Press_Color.getRed(), Press_Color.getGreen(), Press_Color.getBlue(), Alphas[4]));
            graphics.fillRoundRect(0, 0, getWidth(), getHeight(), round, round);
        }
        
        if(Background_Color != null) {
            Alphas[5] = AlphaCheck(5);
            graphics.setColor(new Color(Background_Color.getRed(), Background_Color.getGreen(), Background_Color.getBlue(), Alphas[5]));
            graphics.setStroke(new BasicStroke(6));
            graphics.drawRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
            graphics.drawRoundRect(0, 0, getWidth(), getHeight(), round, round);
        }
    }

    /**
     * Fades out all alphas
     */
    public void fadeOutAll() {
        HoverFadeOut();
        PressFadeOut();

    }

    /**
     * corrects the alpha amount if the alpha is above 255 or below 0 else returns the same
     * @param pos position of the alpha
     * @return a corrected Alpha
     */
    private int AlphaCheck(int pos) {
        if(Alphas[pos] > 255) {
            return 255;
        } else if(Alphas[pos] < 0) {
            return 0;
        }
        return Alphas[pos];
    }
    /**
     * Sets the value of increments to change alphas
     * @param value the amount increased/decreased per increment
     */
    public void setHoverIncrementAmount(int value) {
        this.incrementAmount[0] = value;
    }

    /**
     * Sets the value of increments to change pressed alphas
     * @param value the amount increased/decreased per increment
     */
    public void setPressIncrementAmount(int value) {
        this.incrementAmount[1] = value;
    }

    /**
     * Sets the value of increments to change selected buttons alphas
     * @param value the amount increased/decreased per increment     
     */
    public void setSelectedIncrementAmount(int value) {
        this.incrementAmount[3] = value;
    }

    /**
     * Sets the value of increments to change pressed circle size
     * @param value the amount increased/decreased per increment
     */
    public void setCircleExpansionIncrementAmount(int value) {
        this.incrementAmount[2] = value;
    }
    /**
     * Sets the value of increments to change alphas
     * @param value the amount increased/decreased per increment
     */
    public void setHoverSleepAmount(int value) {
        this.sleepAmount[0] = value;
    }

    /**
     * Sets the value of increments to change pressed alphas
     * @param value the amount increased/decreased per increment
     */
    public void setPressedSleepAmount(int value) {
        this.sleepAmount[1] = value;
    }

    /**
     * Sets any JPanels to repaint while repainting this button
     * @param panels the parent jpanels
     */
    public void setParentJPanels(JPanel... panels) {
        ParentJPanels.clear();
        for(JPanel j : panels) {
            ParentJPanels.add(j);
        }
    }

    /**
     * Sets the Press color
     * @param color desired color of the pressed color
     */
    public void setPressColor(Color color) {
        this.Press_Color = color;
    }
    

    /**
     * Adds any JPanels to repaint while repainting this button
     * @param panels the parent jpanels to be appended
     */
    public void appendParentJPanels(JPanel... panels) {
        for(JPanel j : panels) {
            ParentJPanels.add(j);
        }
    }
    /**
     * Sets the roundness of the button
     * @param round round value
     */
    public void setRoundness(int round) {
        this.round = round;
        update();
    }
    /**
     * Sets the alpha of the hover color
     * @param alpha value of alpha
     */
    public void setHoverAlpha(int alpha) {
        this.Alphas[2] = alpha;
        update();
    }

    /**
     * Sets the alpha of the press color
     * @param alpha value of alpha
     */
    public void setPressAlpha(int alpha) {
        this.Alphas[3] = alpha;
        update();
    }

    /**
     * Sets the alpha of the background color
     * @param alpha value of alpha
     */
    public void setBackgroundAlpha(int alpha) {
        this.Alphas[0] = alpha;
        update();
    }

    /**
     * Get the alphas array
     * @return the Alpha value array
     */
    public int[] getAlphas() {
        return Alphas;
    }

    /**
     * updates any connected jpanels
     */
    private void update() {
        revalidate();
        repaint();
        for(JPanel panel : ParentJPanels) {
            if(panel.isVisible() && panel.getBackground().getAlpha() > 0) {
                panel.revalidate();
                panel.repaint();
            }
        }
    }

    /**
     * Starts fading in the button color
     */
    public void buttonColorFadeIn() {
        changeAlphaValues(0, 0, true);
    }

    /**
     * Starts fading out the button color
     */
    public void buttonColorFadeOut() {
        changeAlphaValues(0, 0, false);
    }

    /**
     * Starts the hovering fade in animation
     */
    public void HoverFadeIn() {
        changeAlphaValues(2, 1, true);
        if(fadeInText) {
            changeAlphaValues(1, 1, true);
        }
    }

    /**
     * Starts the hover fade out animation
     */
    public void HoverFadeOut() {
        changeAlphaValues(2, 1, false);
        if(fadeInText) {
            changeAlphaValues(1, 1, false);
        }
    }

    /**
     * Starts the pressing fade in
     */
    public void PressFadeIn() {
        PressSpreadButtonCircle c = new PressSpreadButtonCircle(xMouse, yMouse);
        circles.add(c);
        fadeCircleIn(c);
        expandCircleSize(c);
    }
    /**
     * Starts the pressed fade out process
     */
    public void PressFadeOut() {
        if(circles.size() - 1 >= 0) {
            fadeOutCircle(circles.get(circles.size() - 1));
        }
    }
    /**
     * Expands the size of the circle
     * @param circle The Circle being edited
     */
    private void expandCircleSize(PressSpreadButtonCircle circle) {
        Thread t = new Thread() {
            public void run() {
                int wantedValue = (int) (getWidth() * 1.1);
                while(circle.getRadius() != wantedValue) {
                    circle.addRadius(incrementAmount[2]);
                    update();
                    try {
                        sleep(sleepAmount[2]);
                    } catch(Exception er) {}
                }
                circle.setExpanding(true);
            }
        }; t.start();
    }
    /**
     * Decreases the alpha of the circle while button being pressed
     * @param circle The Circle being edited
     */
    public void fadeOutCircle(PressSpreadButtonCircle circle) {
        Thread t = new Thread() {
            public void run() {
                while(!circle.getExpanding()) {
                    sleepTime(2);
                }
                while(circle.getAlpha() > 0) {
                    circle.setAlpha(circle.getAlpha() - (incrementAmount[1]));
                    update();
                    sleepTime(sleepAmount[1] * 2);
                }
                for(int x = 0; x < circles.size(); x++) {
                    if(circles.get(x).equals(circle) && (x > 0 && x < circles.size())) {
                        try {
                            circles.remove(x);
                        } catch(java.lang.IndexOutOfBoundsException e) {}
                        break;
                    }
                    x++;
                }
            }
        }; t.start();
    }

    /**
     * Increases the alpha of the circle while button being pressed
     * @param circle The Circle being edited
     */
    private void fadeCircleIn(PressSpreadButtonCircle circle) {
        Thread t = new Thread() {
            public void run() {
                while(switches[2] && circle.getAlpha() < 255) {
                    circle.setAlpha(circle.getAlpha() + incrementAmount[1]);
                    update();
                    sleepTime(sleepAmount[1]);
                }
            }
        }; t.start();
    }

    /**
     * Changes the alpha of the color given
     * @param colorAlphaPos The position of the alphas 0 = background, 1 = text, 2 = hovering, 3 = pressed
     * @param switchPosition boolean based off hovering or pressing, 0 = hovering, 1 = pressing
     * @param active boolean on if alpha should be increasing or decreasing, false = decreasing, true = increasing
     */
    public void changeAlphaValues(int colorAlphaPos, int switchPosition, boolean active) {
        Thread t = new Thread() {
            public void run() {
                switches[switchPosition] = active;
                int wantedValue = 0;
                if(active) {
                    wantedValue = 255;
                }
                while(switches[switchPosition] == active && Alphas[colorAlphaPos] != wantedValue) {
                    if(active) {
                        Alphas[colorAlphaPos] += incrementAmount[switchPosition];
                    } else {
                        Alphas[colorAlphaPos] -= incrementAmount[switchPosition];
                    }
                    if(Math.abs(wantedValue - Alphas[colorAlphaPos]) <= incrementAmount[switchPosition] + 3) {
                        Alphas[colorAlphaPos] = wantedValue;
                    }
                    update();
                    sleepTime(sleepAmount[switchPosition]);
                }
            }
        }; t.start();
    }

    /**
     * Changes the alpha of the color given
     * @param colorAlphaPos The position of the alphas 0 = background, 1 = text, 2 = hovering, 3 = pressed
     * @param switchPosition boolean based off hovering or pressing, 0 = hovering, 1 = pressing
     * @param active boolean on if alpha should be increasing or decreasing, false = decreasing, true = increasing
     * @param multiplier adds a multiplier to the amount the Alpha changes
     */
    public void changeAlphaValues(int colorAlphaPos, int switchPosition, boolean active, double multiplier) {
        Thread t = new Thread() {
            public void run() {
                switches[switchPosition] = active;
                int wantedValue = 0;
                if(active) {
                    wantedValue = 255;
                }
                while(switches[switchPosition] == active && Alphas[colorAlphaPos] != wantedValue) {
                    if(active) {
                        Alphas[colorAlphaPos] += (int) (incrementAmount[switchPosition] * multiplier);
                    } else {
                        Alphas[colorAlphaPos] -= (int) (incrementAmount[switchPosition] * multiplier);
                    }
                    if(Math.abs(wantedValue - Alphas[colorAlphaPos]) <= incrementAmount[switchPosition] + 3) {
                        Alphas[colorAlphaPos] = wantedValue;
                    }
                    update();
                    sleepTime(sleepAmount[switchPosition]);
                }
            }
        }; t.start();
    }

    /**
     * Sleeps for the time given
     * @param time given amount of time to sleep for
     */
    private void sleepTime(int time) {
        try {
            Thread.sleep(time);
        } catch(Exception err1) {}
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        switches[2] = true;
        xMouse = e.getX();
        yMouse = e.getY();
        PressFadeIn();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switches[2] = false;
        PressFadeOut();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        HoverFadeIn();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        HoverFadeOut();
    }
}
