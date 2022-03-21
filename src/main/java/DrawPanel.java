import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel {
    private Integer[] bodyParts = new Integer[8];
    private Color[] colors = new Color[]{Color.decode("#fcead4"), Color.BLACK, Color.decode("#7c4700"), Color.BLACK, Color.decode("#fcead4"), Color.BLACK, Color.decode("#fdab9f"), Color.BLACK};
    private Integer lastChangedPart;
    private Integer lastNumber;
    private Color lastColor;
    private int alphaOld = 255;
    private int alphaNew = 0;
    private JSlider slider = new JSlider(1, 20);
    private int xMove = 0;
    private int yMove = 0;

    public DrawPanel() {
        JLabel title = new JLabel("Скорость анимации");
        title.setFont(new Font(title.getFont().getFontName(), Font.PLAIN, 20));
        this.add(title, BorderLayout.NORTH);
        JPanel panel = new JPanel(new GridLayout(0, 3));
        JLabel label1 = new JLabel("Замедлить");
        label1.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(label1);
        panel.add(slider, BorderLayout.NORTH);
        panel.add(new JLabel("Ускорить"));
        this.add(panel, BorderLayout.NORTH);
    }


    public void draw(Integer[] bodyParts, Color[] colors) {
        for (int i = 0; i < bodyParts.length; i++) {
            if (bodyParts[i] != null) {
                if (this.bodyParts[i] == null || !bodyParts[i].equals(this.bodyParts[i])) {
                    lastChangedPart = i;
                    lastColor = this.colors[i];
                    lastNumber = this.bodyParts[i];
                }
            }
            this.bodyParts[i] = bodyParts[i];
        }
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] != null) {
                if (colors[i] != this.colors[i]) {
                    lastChangedPart = i;
                    lastColor = this.colors[i];
                    lastNumber = this.bodyParts[i];
                }
                this.colors[i] = colors[i];
            }
        }
        this.repaint();
        this.revalidate();
    }

    public void resizePanel(int x, int y) {
        xMove = x;
        yMove = y;
        this.repaint();
        this.revalidate();
    }

    private void drawOld() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ignored) {
        }
        alphaOld -= slider.getValue();
        if (alphaOld < 0) alphaOld = 0;
        this.repaint();
        this.revalidate();
    }

    private void drawNew() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ignored) {
        }
        alphaNew += slider.getValue();
        if (alphaNew > 255) alphaNew = 255;
        this.repaint();
        this.revalidate();
    }

    /**
     * Отрисовка основных частей лица. Брови рисуются вручную с помощью fillPolygon из массива точек, остальные части с
     * помощью готовых изображений.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bodyParts == null || colors == null) return;
        drawAllPossibilities(g, 0, "faces\\face");
        drawAllPossibilities(g, 2, "eyes\\eye");
        drawEyebrows((Graphics2D) g, bodyParts[3], colors[3]);
        drawAllPossibilities(g, 4, "ears\\ear");
        drawAllPossibilities(g, 5, "noses\\nose");
        drawAllPossibilities(g, 6, "mouths\\mouth");
        drawAllPossibilities(g, 1, "hairs\\hair");
    }

    private void drawEyebrows(Graphics2D graphics2D, Integer num, Color color) {
        if (num == null) return;
        int[] x = Eyebrow.getEyebrowX(num);
        int[] xm = Eyebrow.getEyebrowXM(num);
        int[] y = Eyebrow.getEyebrowY(num);
        graphics2D.setColor(color);
        int yk = 660;
        if (bodyParts[0] != null && bodyParts[0] == 6) yk = 650;
        graphics2D.translate(xMove, yMove);
        graphics2D.scale(0.4, 0.4);
        graphics2D.translate(475, yk);
        graphics2D.fillPolygon(x, y, x.length);
        graphics2D.translate(380, 0);
        graphics2D.fillPolygon(xm, y, x.length);
        graphics2D.translate(-380, 0);
        graphics2D.translate(-475, -yk);
        graphics2D.scale(2.5, 2.5);
        graphics2D.translate(-xMove, -yMove);
    }

    private void drawAllPossibilities(Graphics g, int num, String path) {
        if (bodyParts[num] != null && colors[num] != null) {
            if (num == 4) colors[4] = colors[0];
            if (lastChangedPart == num && lastColor != null && lastNumber != null) {
                if (num == 2 || num == 4) {
                    BufferedImage bufferedImage = changeColor(path, num, lastNumber, lastColor, alphaOld, Position.LEFT);
                    drawDependsOnType(bufferedImage, g, num, lastNumber, Position.LEFT);
                    bufferedImage = changeColor(path, num, lastNumber, lastColor, alphaOld, Position.RIGHT);
                    drawDependsOnType(bufferedImage, g, num, lastNumber, Position.RIGHT);
                } else {
                    BufferedImage bufferedImage = changeColor(path, num, lastNumber, lastColor, alphaOld, Position.SINGLE);
                    drawDependsOnType(bufferedImage, g, num, lastNumber, Position.SINGLE);
                }
                if (alphaOld > 0) drawOld();
                else alphaOld = 255;
            }
            if (lastChangedPart == num) {
                if (num == 2 || num == 4) {
                    BufferedImage bufferedImage = changeColor(path, num, bodyParts[num], colors[num], alphaNew, Position.LEFT);
                    drawDependsOnType(bufferedImage, g, num, bodyParts[num], Position.LEFT);
                    bufferedImage = changeColor(path, num, bodyParts[num], colors[num], alphaNew, Position.RIGHT);
                    drawDependsOnType(bufferedImage, g, num, bodyParts[num], Position.RIGHT);
                } else {
                    BufferedImage bufferedImage = changeColor(path, num, bodyParts[num], colors[num], alphaNew, Position.SINGLE);
                    drawDependsOnType(bufferedImage, g, num, bodyParts[num], Position.SINGLE);
                }
                if (alphaNew < 255) drawNew();
                else alphaNew = 0;
            }
            if (lastChangedPart != num) {
                if (num == 2 || num == 4) {
                    BufferedImage bufferedImage = changeColor(path, num, bodyParts[num], colors[num], 255, Position.LEFT);
                    drawDependsOnType(bufferedImage, g, num, bodyParts[num], Position.LEFT);
                    bufferedImage = changeColor(path, num, bodyParts[num], colors[num], 255, Position.RIGHT);
                    drawDependsOnType(bufferedImage, g, num, bodyParts[num], Position.RIGHT);
                } else {
                    BufferedImage bufferedImage = changeColor(path, num, bodyParts[num], colors[num], 255, Position.SINGLE);
                    drawDependsOnType(bufferedImage, g, num, bodyParts[num], Position.SINGLE);
                }
            }
        }
    }

    private void drawDependsOnType(BufferedImage bufferedImage, Graphics g, int num, int type, Position position) {
        if (num == 0) {
            drawImage(bufferedImage, g, 2.5, 100 + xMove, 100 + yMove);
        } else if (num == 1) {
            if (type == 2) drawImage(bufferedImage, g, 1.55, 143 + xMove, 120 + yMove);
            else if (type == 3) drawImage(bufferedImage, g, 1.55, 143 + xMove, 115 + yMove);
            else if (type == 4 || type == 6) drawImage(bufferedImage, g, 1.6, 133 + xMove, 120 + yMove);
            else if (type == 7) drawImage(bufferedImage, g, 1.58, 135 + xMove, 110 + yMove);
            else if (type == 10) drawImage(bufferedImage, g, 1.63, 130 + xMove, 120 + yMove);
            else if (type == 12) drawImage(bufferedImage, g, 1.6, 138 + xMove, 120 + yMove);
            else if (type == 15) drawImage(bufferedImage, g, 1.37, 153 + xMove, 130 + yMove);
            else if (type == 16) drawImage(bufferedImage, g, 1.9, 117 + xMove, 90 + yMove);
            else if (type == 20) drawImage(bufferedImage, g, 1.8, 120 + xMove, 100 + yMove);
            else if (type == 22) drawImage(bufferedImage, g, 1.7, 135 + xMove, 70 + yMove);
            else if (type == 23) drawImage(bufferedImage, g, 1.5, 150 + xMove, 120 + yMove);
            else if (type == 24) drawImage(bufferedImage, g, 1.4, 155 + xMove, 130 + yMove);
            else if (type == 25) drawImage(bufferedImage, g, 1.63, 135 + xMove, 120 + yMove);
            else if (type == 26) drawImage(bufferedImage, g, 1.85, 117 + xMove, 105 + yMove);
            else if (type == 27) drawImage(bufferedImage, g, 3.3, xMove, -110 + yMove);
            else if (type == 28) drawImage(bufferedImage, g, 2.5, 67 + xMove, 100 + yMove);
            else if (type == 29) drawImage(bufferedImage, g, 2.4, 73 + xMove, 145 + yMove);
            else if (type == 30) drawImage(bufferedImage, g, 2.6, 58 + xMove, -90 + yMove);
            else if (type == 31) drawImage(bufferedImage, g, 2.2, 80 + xMove, 100 + yMove);
            else if (type == 32) drawImage(bufferedImage, g, 2, 90 + xMove, 90 + yMove);
            else if (type == 33) drawImage(bufferedImage, g, 1.8, 120 + xMove, 120 + yMove);
            else if (type == 34) drawImage(bufferedImage, g, 2, 100 + xMove, 120 + yMove);
            else if (type == 35) drawImage(bufferedImage, g, 2, 105 + xMove, 130 + yMove);
            else if (type == 36) drawImage(bufferedImage, g, 2, 95 + xMove, 110 + yMove);
            else if (type == 37) drawImage(bufferedImage, g, 2, 95 + xMove, 110 + yMove);
            else if (type == 38) drawImage(bufferedImage, g, 1.8, 115 + xMove, 100 + yMove);
            else if (type == 39) drawImage(bufferedImage, g, 2.1, 130 + xMove, 70 + yMove);
            else if (type == 40) drawImage(bufferedImage, g, 1.8, 123 + xMove, 120 + yMove);
            else if (type == 43) drawImage(bufferedImage, g, 2, 90 + xMove, 100 + yMove);
            else if (type == 44) drawImage(bufferedImage, g, 3, 55 + xMove, 137 + yMove);
            else if (type == 45) drawImage(bufferedImage, g, 2.5, 65 + xMove, 135 + yMove);
            else if (type == 46) drawImage(bufferedImage, g, 2.75, 30 + xMove, 130 + yMove);
            else if (type == 47) drawImage(bufferedImage, g, 2.85, 35 + xMove, 110 + yMove);
            else if (type == 48) drawImage(bufferedImage, g, 2.85, 10 + xMove, 120 + yMove);
            else if (type == 49) drawImage(bufferedImage, g, 3.5, -25 + xMove, 150 + yMove);
            else if (type == 50) drawImage(bufferedImage, g, 3, 30 + xMove, 135 + yMove);
            else if (type == 51) drawImage(bufferedImage, g, 2.85, 10 + xMove, 145 + yMove);
            else if (type == 52) drawImage(bufferedImage, g, 3, 25 + xMove, 120 + yMove);
            else if (type == 53) drawImage(bufferedImage, g, 3, 10 + xMove, 120 + yMove);
            else if (type == 54) drawImage(bufferedImage, g, 2.75, -10 + xMove, 135 + yMove);
            else if (type == 55) drawImage(bufferedImage, g, 3.5, -20 + xMove, 120 + yMove);
            else if (type == 56) drawImage(bufferedImage, g, 4.25, -75 + xMove, 125 + yMove);
            else if (type == 57) drawImage(bufferedImage, g, 4.65, -120 + xMove, 120 + yMove);
            else if (type == 58) drawImage(bufferedImage, g, 5, -158 + xMove, 132 + yMove);
            else if (type == 59) drawImage(bufferedImage, g, 4.9, -110 + xMove, 120 + yMove);
            else drawImage(bufferedImage, g, 1.63, 133 + xMove, 120 + yMove);
        } else if (num == 2) {
            int y = 280;
            if (bodyParts[0] != null && bodyParts[0] == 6) y = 275;
            if (position == Position.LEFT) drawImage(bufferedImage, g, 0.3, 185 + xMove, y + yMove);
            else drawImage(bufferedImage, g, 0.3, 285 + xMove, y + yMove);
        } else if (num == 4) {
            int x1 = 145, x2 = 339, y = 280;
            if (bodyParts[0] != null && bodyParts[0] == 2) {
                x1 = 149;
                x2 = 331;
            } else if (bodyParts[0] != null && (bodyParts[0] == 3 || bodyParts[0] == 4)) {
                x1 = 147;
                x2 = 335;
            } else if (bodyParts[0] != null && (bodyParts[0] == 5 || bodyParts[0] == 7)) {
                x1 = 140;
                x2 = 343;
            } else if (bodyParts[0] != null && (bodyParts[0] == 6 || bodyParts[0] == 11)) {
                y = 270;
            } else if (bodyParts[0] != null && bodyParts[0] == 8) {
                x1 = 140;
                x2 = 334;
            }
            if (position == Position.LEFT) drawImage(bufferedImage, g, 0.5, x1 + xMove, y + yMove);
            else drawImage(bufferedImage, g, 0.5, x2 + xMove, y + yMove);
        } else if (num == 5) {
            if (bodyParts[0] != null && bodyParts[0] == 3) drawImage(bufferedImage, g, 0.6, 230 + xMove, 305 + yMove);
            else if (bodyParts[0] != null && bodyParts[0] == 6)
                drawImage(bufferedImage, g, 0.4, 238 + xMove, 305 + yMove);
            else if (bodyParts[0] != null && bodyParts[0] == 7)
                drawImage(bufferedImage, g, 0.6, 227 + xMove, 305 + yMove);
            else drawImage(bufferedImage, g, 0.5, 233 + xMove, 305 + yMove);
        } else if (num == 6) {
            if (bodyParts[0] != null && bodyParts[0] == 3) drawImage(bufferedImage, g, 1, 218 + xMove, 383 + yMove);
            else if (bodyParts[0] != null && bodyParts[0] == 6)
                drawImage(bufferedImage, g, 0.8, 225 + xMove, 360 + yMove);
            else if (bodyParts[0] != null && bodyParts[0] == 7)
                drawImage(bufferedImage, g, 1, 215 + xMove, 385 + yMove);
            else if (bodyParts[0] != null && (bodyParts[0] == 9 || bodyParts[0] == 10 || bodyParts[0] == 11))
                drawImage(bufferedImage, g, 0.9, 220 + xMove, 370 + yMove);
            else drawImage(bufferedImage, g, 0.9, 220 + xMove, 375 + yMove);
        }
    }

    private BufferedImage changeColor(String path, int bodyTypeNum, int bodyPartNum, Color color, int alpha, Position position) {
        BufferedImage bufferedImage = getBufferedImage(path, bodyTypeNum, bodyPartNum, position);
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                if (bodyTypeNum == 0 || bodyTypeNum == 4) {
                    if (bufferedImage.getRGB(i, j) != 0 && bufferedImage.getRGB(i, j) != Color.BLACK.getRGB())
                        bufferedImage.setRGB(i, j, color.getRGB());
                } else if (bodyTypeNum == 2) {
                    if (bufferedImage.getRGB(i, j) == Color.decode("#f768c3").getRGB())
                        bufferedImage.setRGB(i, j, color.getRGB());
                } else {
                    if (bufferedImage.getRGB(i, j) != 0)
                        bufferedImage.setRGB(i, j, color.getRGB());
                }
            }
        }
        setAlpha(bufferedImage, alpha);
        return bufferedImage;
    }

    private BufferedImage getBufferedImage(String path, int bodyTypeNum, int bodyPartNum, Position position) {
        String w = "";
        if (bodyTypeNum == 0) w = "w";
        else if (position == Position.LEFT) w = "l";
        else if (position == Position.RIGHT) w = "r";
        ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\" + path + bodyPartNum + w + ".png");
        Image img = imageIcon.getImage();
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bufferedImage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bufferedImage;
    }

    private void drawImage(BufferedImage bufferedImage, Graphics g, double scale, int x, int y) {
        g.drawImage(bufferedImage.getScaledInstance((int) (bufferedImage.getWidth() * scale), (int) (bufferedImage
                .getHeight() * scale), Image.SCALE_SMOOTH), x, y, null);
    }

    private void setAlpha(BufferedImage bufferedImage, int alpha) {
        byte a = (byte) alpha;
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                int color = bufferedImage.getRGB(i, j);
                int mc = (a << 24) | 0x00ffffff;
                int newColor = color & mc;
                bufferedImage.setRGB(i, j, newColor);
            }
        }
    }

}