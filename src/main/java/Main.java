import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;

public class Main {
    public static final int MAX_BUTTONS_IN_ROW = 4;
    public static final int MAX_COLORS_IN_ROW = 10;
    public static final int MAX_ICONS_IN_ROW = 4;
    public static final String[] ICONS = new String[]{"face.png", "hair.png", "eye.png", "eyebrow.png", "ear.png", "nose.png", "mouth.png", "beard.png"};
    public static final int NUM_OF_FACES = 11;
    public static final int NUM_OF_HAIRS = 59;
    public static final int NUM_OF_EYES = 9;
    public static final int NUM_OF_MOUTHS = 40;
    public static final int NUM_OF_NOSES = 14;
    public static final int NUM_OF_EARS = 7;
    public static final int NUM_OF_EYEBROWS = 5;
    public static final String[] FACE_COLORS_HEX = new String[]{"#fef9f3", "#fef5eb", "#fdf1e3", "#fdeddc", "#fcead4", "#fce6cc", "#fce2c4", "#fbdebc", "#fbdab5", "#fad6ad",
            "#fad3a5", "#facf9d", "#f9cb96", "#f9c78e", "#f8c386", "#f8c07e", "#f8bc77", "#f7b86f", "#f7b467", "#f6b05f",
            "#f6ac58", "#f6a950", "#f5a548", "#f5a140", "#f49d38", "#f49931", "#f49629", "#f39221", "#f38e19", "#f28a12",
            "#f0860c", "#e8820b", "#e17d0b", "#d9790b", "#d1750a", "#c9700a", "#c26c09", "#ba6809", "#b26309", "#aa5f08",
            "#a35b08", "#9b5607", "#935207", "#8b4e07", "#844906", "#7c4506", "#744105", "#6c3c05", "#643805", "#5d3404",
            "#552f04", "#4d2b03", "#452703", "#3e2203", "#361e02", "#2e1a02", "#261501", "#1f1101", "#170d01", "#0f0800"};
    public static final String[] HAIR_COLORS_HEX = new String[]{"#090806", "#2c222b", "#71635a", "#b7a69e", "#d6c4c2", "#cabfb1", "#dcd0ba", "#fff5e1", "#e6cea8", "#e5c8a8",
            "#debc99", "#b89778", "#a56b46", "#b55239", "#8d4a43", "#91553d", "#533d32", "#3b3024", "#554838", "#4e433f",
            "#504444", "#6a4e42", "#a7856a", "#977961", "#6e5d1d", "#6a591b", "#5e3a05", "#593806", "#624716", "#6e4716",
            "#6e4f09", "#502b04", "#7f5e1b", "#664914", "#4d2a07", "#4a280a", "#6d4610", "#724b12", "#4c2806", "#e6be8a",
            "#ffcc47", "#888785", "#fffffd", "#9dc667", "#f8063e", "#b6652a", "#a97842", "#2afabd", "#8b1e1f", "#64bf85",
            "#c88693", "#b7144c", "#fcd2f7", "#3332c0", "#324bed", "#93be9b", "#5311ca", "#653b4c", "#941519", "#18171d"};
    public static final String[] EYE_COLORS_HEX = new String[]{"#7c4700", "#4b3a26", "#622a0f", "#3a1f04", "#3b270c", "#362312", "#997950", "#2b1700", "#492000", "#5c2c06",
            "#613613", "#43270f", "#48260d", "#402f1d", "#351e10", "#4b382a", "#795c32", "#7e481c", "#4b3619", "#7f461b",
            "#8d9b87", "#a1caf1", "#0d98ba", "#0d5176", "#070b18", "#0f305b", "#1b5675", "#357388", "#528c9e", "#7fb4be",
            "#b8d8e1", "#7dabda", "#e4c2d0", "#2c7c4c", "#648cac", "#b4ccce", "#315741", "#25a22b", "#03920c", "#017101",
            "#035104", "#004200", "#ff0000", "#ffc000", "#ffff00", "#92d050", "#00b050", "#00b0f0", "#0070c0", "#002060",
            "#bfbfbf", "#7f7f7f", "#c5be97", "#948b54", "#e6b9b8", "#d99795", "#b2a1c7", "#60497b", "#fcd5b4", "#fac090"};
    public static final String[] MOUTH_COLORS_HEX = new String[]{"#fc0fc0", "#f81894", "#e0115f", "#f64aba", "#ec5578", "#ff6fff", "#de3163", "#fdb9c8", "#de6fa1", "#ffa6c9",
            "#fca3b7", "#fb607f", "#ff66cc", "#f19cb8", "#fbaed2", "#f9b7c5", "#ff69b4", "#fe5bac", "#f5c3c2", "#df5286",
            "#fe7f9c", "#fdab9f", "#df9288", "#f9859a", "#a34079", "#ff1051", "#e71c18", "#c31e2c", "#8b3e34", "#ae5654",
            "#8d6461", "#c2726b", "#d3827b", "#dc8d88", "#e1a6a1", "#dc3753", "#e56d7e", "#8b160e", "#b8696a", "#ccac9e",
            "#313456", "#66023c", "#7851a9", "#bf00ff", "#800080", "#9370db", "#9f00c5", "#df00ff", "#df73ff", "#f47b8f",
            "#f3aabb", "#f5cdd3", "#ededed", "#a1dcd8", "#51aeae", "#3a262f", "#2e2544", "#6e4972", "#bf80ad", "#804d4a"};
    public static JFrame mainFrame;
    public static Integer[] bodyParts = new Integer[8];
    public static Color[] colors = new Color[8];
    public static int currentFacePart = 0;

    public static void main(String[] args) {
        mainFrame = new JFrame("Photorobot");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawPanel photorobotDrawPanel = new DrawPanel();
        JPanel photorobotSelectPanel = new JPanel(new GridBagLayout());
        JPanel photorobotIconsPanel = new JPanel(new GridBagLayout());
        JPanel photorobotConcreteSelectPanel = new JPanel(new GridBagLayout());
        JPanel photorobotConcreteIconsPanel = new JPanel(new GridBagLayout());
        setPhotorobotSelectPanelLayout(photorobotSelectPanel, photorobotIconsPanel, photorobotConcreteSelectPanel, photorobotConcreteIconsPanel);

        JButton[] buttons = new JButton[ICONS.length];
        for (int i = 0; i < ICONS.length; i++) {
            buttons[i] = new JButton();
            ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\icons\\" + ICONS[i]);
            buttons[i].setIcon(imageIcon);
        }
        addAllButtons(photorobotIconsPanel, buttons);
        addButtonListeners(buttons, photorobotDrawPanel, photorobotConcreteSelectPanel, photorobotConcreteIconsPanel);
        faceSelect(photorobotDrawPanel, photorobotConcreteSelectPanel, photorobotConcreteIconsPanel);
        draw(photorobotDrawPanel);
        mainFrame.setLayout(new GridLayout(0, 2));
        mainFrame.getContentPane().add(photorobotDrawPanel);
        mainFrame.getContentPane().add(photorobotSelectPanel);
        mainFrame.setBounds(200, 0, 1200, 800);
        mainFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Rectangle r = mainFrame.getBounds();
                photorobotDrawPanel.resizePanel((r.width / 2 - 550) / 2, (r.height - 700) / 2);
            }
        });
        mainFrame.setVisible(true);
    }

    public static void draw(DrawPanel photorobotDrawPanel) {
        photorobotDrawPanel.draw(bodyParts, colors);
    }

    public static void colorSelectListener(DrawPanel photorobotDrawPanel, JButton button) {
        colors[currentFacePart] = button.getBackground();
        draw(photorobotDrawPanel);
    }

    public static void partOfFaceSelectListener(DrawPanel photorobotDrawPanel, JButton button) {
        ImageIcon imageIcon = (ImageIcon) button.getIcon();
        bodyParts[currentFacePart] = Integer.parseInt(imageIcon.getDescription());
        draw(photorobotDrawPanel);
    }

    public static void addAllButtons(JPanel panel, JButton[] buttons) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        for (JButton el : buttons) {
            addNewButton(panel, el, c);
        }
    }

    public static void addNewButton(JPanel panel, JButton button, GridBagConstraints c) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        panel.add(button, c);
        c.gridx++;
        if (c.gridx == MAX_BUTTONS_IN_ROW) {
            c.gridx = 0;
            c.gridy++;
        }
    }

    public static void addButtonListeners(JButton[] buttons, DrawPanel photorobotDrawPanel, JPanel photorobotConcreteSelectPanel, JPanel photorobotConcreteIconsPanel) {
        buttons[0].addActionListener(e -> faceSelect(photorobotDrawPanel, photorobotConcreteSelectPanel, photorobotConcreteIconsPanel));
        buttons[1].addActionListener(e -> hairSelect(photorobotDrawPanel, photorobotConcreteSelectPanel, photorobotConcreteIconsPanel));
        buttons[2].addActionListener(e -> eyeSelect(photorobotDrawPanel, photorobotConcreteSelectPanel, photorobotConcreteIconsPanel));
        buttons[3].addActionListener(e -> eyebrowSelect(photorobotDrawPanel, photorobotConcreteSelectPanel, photorobotConcreteIconsPanel));
        buttons[4].addActionListener(e -> earSelect(photorobotDrawPanel, photorobotConcreteSelectPanel, photorobotConcreteIconsPanel));
        buttons[5].addActionListener(e -> noseSelect(photorobotDrawPanel, photorobotConcreteSelectPanel, photorobotConcreteIconsPanel));
        buttons[6].addActionListener(e -> mouthSelect(photorobotDrawPanel, photorobotConcreteSelectPanel, photorobotConcreteIconsPanel));
        buttons[7].addActionListener(e -> beardSelect(photorobotDrawPanel, photorobotConcreteSelectPanel, photorobotConcreteIconsPanel));
    }

    public static void setPhotorobotSelectPanelLayout(JPanel photorobotSelectPanel, JPanel photorobotIconsPanel, JPanel photorobotConcreteSelectPanel, JPanel photorobotConcreteIconsPanel) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        photorobotSelectPanel.add(photorobotIconsPanel, c);
        c.gridy = 1;
        photorobotSelectPanel.add(photorobotConcreteSelectPanel, c);
        c.weighty = 1;
        c.gridy = 2;
        JScrollPane scrollPane = new JScrollPane(photorobotConcreteIconsPanel);
        photorobotSelectPanel.add(scrollPane, c);
    }

    public static void partOfFaceSelect(JPanel photorobotConcreteSelectPanel, JPanel photorobotConcreteIconsPanel, String name) {
        photorobotConcreteSelectPanel.removeAll();
        photorobotConcreteIconsPanel.removeAll();
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1;
        c.gridwidth = MAX_COLORS_IN_ROW;
        c.gridx = 0;
        c.gridy = 0;
        JLabel title = new JLabel(name);
        Font f = new Font(title.getFont().getFontName(), Font.PLAIN, 36);
        title.setFont(f);
        photorobotConcreteSelectPanel.add(title, c);
    }

    public static void partOfFaceColors(DrawPanel photorobotDrawPanel, JPanel photorobotConcreteSelectPanel, String[] colorsHex) {
        Arrays.sort(colorsHex);
        Color[] colors = new Color[colorsHex.length];
        for (int i = 0; i < colorsHex.length; i++) {
            colors[i] = Color.decode(colorsHex[i]);
        }
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        for (Color el : colors) {
            JButton button = new JButton(" ");
            button.addActionListener(e -> colorSelectListener(photorobotDrawPanel, button));
            button.setFocusPainted(false);
            button.setBackground(el);
            button.setForeground(el);
            photorobotConcreteSelectPanel.add(button, c);
            c.gridx++;
            if (c.gridx == MAX_COLORS_IN_ROW) {
                c.gridx = 0;
                c.gridy++;
            }
        }
        JLabel label = new JLabel("Задать другой цвет:");
        Font f = new Font(label.getFont().getFontName(), Font.PLAIN, 14);
        label.setFont(f);
        c.gridwidth = 6;
        photorobotConcreteSelectPanel.add(label, c);
        c.gridwidth = 1;
        c.gridx = 6;
        JTextField textR = new JTextField("R:0");
        textR.setFont(f);
        photorobotConcreteSelectPanel.add(textR, c);
        c.gridx = 7;
        JTextField textG = new JTextField("G:0");
        textG.setFont(f);
        photorobotConcreteSelectPanel.add(textG, c);
        c.gridx = 8;
        JTextField textB = new JTextField("B:0");
        textB.setFont(f);
        photorobotConcreteSelectPanel.add(textB, c);
        c.gridx = 9;
        JButton button = new JButton(" ");
        button.setFocusPainted(false);
        button.addActionListener(e -> colorSelectListener(photorobotDrawPanel, button));
        Color color = new Color(Integer.parseInt(textR.getText().split(":")[1]),
                Integer.parseInt(textG.getText().split(":")[1]), Integer.parseInt(textB.getText().split(":")[1]));
        button.setBackground(color);
        button.setForeground(color);
        textR.addActionListener(e -> RGBTextCorrector.changeRGB(textR, textG, textB, button));
        textG.addActionListener(e -> RGBTextCorrector.changeRGB(textR, textG, textB, button));
        textB.addActionListener(e -> RGBTextCorrector.changeRGB(textR, textG, textB, button));
        photorobotConcreteSelectPanel.add(button, c);
    }


    public static void typesOfPartOfFace(DrawPanel photorobotDrawPanel, JPanel photorobotConcreteIconsPanel, String path, int num) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        JButton[] buttons = new JButton[num];
        for (int i = 0; i < num; i++) {
            if (path.equals("beards\\beard")) {
                buttons[i] = new JButton("Not implemented");
                buttons[i].setFont(new Font(buttons[i].getFont().getFontName(),Font.PLAIN,30));
                buttons[i].setFocusPainted(false);
                buttons[i].setContentAreaFilled(false);
                photorobotConcreteIconsPanel.add(buttons[i], c);
                return;
            }
            JButton button = new JButton();
            buttons[i] = button;
            buttons[i].setFocusPainted(false);
            buttons[i].setContentAreaFilled(false);
            ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\" + path + (i + 1) + ".png");
            imageIcon.setDescription(String.valueOf(i + 1));
            buttons[i].setIcon(imageIcon);
            buttons[i].addActionListener(e -> partOfFaceSelectListener(photorobotDrawPanel, button));
            photorobotConcreteIconsPanel.add(buttons[i], c);
            c.gridx++;
            if (c.gridx == MAX_ICONS_IN_ROW) {
                c.gridx = 0;
                c.gridy++;
            }
        }
    }

    public static void selectRepaint(JPanel photorobotConcreteSelectPanel, JPanel photorobotConcreteIconsPanel) {
        photorobotConcreteSelectPanel.repaint();
        photorobotConcreteSelectPanel.revalidate();
        photorobotConcreteIconsPanel.repaint();
        photorobotConcreteIconsPanel.revalidate();
    }

    public static void faceSelect(DrawPanel photorobotDrawPanel, JPanel photorobotConcreteSelectPanel, JPanel photorobotConcreteIconsPanel) {
        currentFacePart = 0;
        partOfFaceSelect(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel, "Лицо");
        partOfFaceColors(photorobotDrawPanel, photorobotConcreteSelectPanel, FACE_COLORS_HEX);
        typesOfPartOfFace(photorobotDrawPanel, photorobotConcreteIconsPanel, "faces\\face", NUM_OF_FACES);
        selectRepaint(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel);
    }


    public static void hairSelect(DrawPanel photorobotDrawPanel, JPanel photorobotConcreteSelectPanel, JPanel photorobotConcreteIconsPanel) {
        currentFacePart = 1;
        partOfFaceSelect(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel, "Волосы");
        partOfFaceColors(photorobotDrawPanel, photorobotConcreteSelectPanel, HAIR_COLORS_HEX);
        typesOfPartOfFace(photorobotDrawPanel, photorobotConcreteIconsPanel, "hairs\\hair", NUM_OF_HAIRS);
        selectRepaint(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel);
    }


    public static void eyeSelect(DrawPanel photorobotDrawPanel, JPanel photorobotConcreteSelectPanel, JPanel photorobotConcreteIconsPanel) {
        currentFacePart = 2;
        partOfFaceSelect(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel, "Глаза");
        partOfFaceColors(photorobotDrawPanel, photorobotConcreteSelectPanel, EYE_COLORS_HEX);
        typesOfPartOfFace(photorobotDrawPanel, photorobotConcreteIconsPanel, "eyes\\eye", NUM_OF_EYES);
        selectRepaint(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel);
    }

    public static void eyebrowSelect(DrawPanel photorobotDrawPanel, JPanel photorobotConcreteSelectPanel, JPanel photorobotConcreteIconsPanel) {
        currentFacePart = 3;
        partOfFaceSelect(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel, "Брови");
        partOfFaceColors(photorobotDrawPanel, photorobotConcreteSelectPanel, HAIR_COLORS_HEX);
        typesOfPartOfFace(photorobotDrawPanel, photorobotConcreteIconsPanel, "eyebrows\\eyebrow", NUM_OF_EYEBROWS);
        selectRepaint(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel);
    }

    public static void earSelect(DrawPanel photorobotDrawPanel, JPanel photorobotConcreteSelectPanel, JPanel photorobotConcreteIconsPanel) {
        currentFacePart = 4;
        partOfFaceSelect(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel, "Уши");
        typesOfPartOfFace(photorobotDrawPanel, photorobotConcreteIconsPanel, "ears\\ear", NUM_OF_EARS);
        selectRepaint(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel);
    }

    public static void noseSelect(DrawPanel photorobotDrawPanel, JPanel photorobotConcreteSelectPanel, JPanel photorobotConcreteIconsPanel) {
        currentFacePart = 5;
        partOfFaceSelect(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel, "Нос");
        typesOfPartOfFace(photorobotDrawPanel, photorobotConcreteIconsPanel, "noses\\nose", NUM_OF_NOSES);
        selectRepaint(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel);
    }

    public static void mouthSelect(DrawPanel photorobotDrawPanel, JPanel photorobotConcreteSelectPanel, JPanel photorobotConcreteIconsPanel) {
        currentFacePart = 6;
        partOfFaceSelect(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel, "Рот");
        partOfFaceColors(photorobotDrawPanel, photorobotConcreteSelectPanel, MOUTH_COLORS_HEX);
        typesOfPartOfFace(photorobotDrawPanel, photorobotConcreteIconsPanel, "mouths\\mouth", NUM_OF_MOUTHS);
        selectRepaint(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel);
    }

    public static void beardSelect(DrawPanel photorobotDrawPanel, JPanel photorobotConcreteSelectPanel, JPanel photorobotConcreteIconsPanel) {
        currentFacePart = 7;
        partOfFaceSelect(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel, "Борода");
        partOfFaceColors(photorobotDrawPanel, photorobotConcreteSelectPanel, HAIR_COLORS_HEX);
        typesOfPartOfFace(photorobotDrawPanel, photorobotConcreteIconsPanel, "beards\\beard", 1);
        selectRepaint(photorobotConcreteSelectPanel, photorobotConcreteIconsPanel);
    }
}
