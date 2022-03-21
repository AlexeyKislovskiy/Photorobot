import javax.swing.*;
import java.awt.*;

public class RGBTextCorrector {
    public static void changeRGB(JTextField textR, JTextField textG, JTextField textB, JButton button) {
        changeRGB(textR, 'R');
        changeRGB(textG, 'G');
        changeRGB(textB, 'B');
        Color color = new Color(Integer.parseInt(textR.getText().split(":")[1]),
                Integer.parseInt(textG.getText().split(":")[1]), Integer.parseInt(textB.getText().split(":")[1]));
        button.setBackground(color);
        button.setForeground(color);
        button.repaint();
    }

    private static void changeRGB(JTextField text, char first) {
        if (checkCorrectTextRGB(text.getText(), first) == TextRGBErrors.ANOTHER_ERROR) text.setText(first + ":0");
        else if (checkCorrectTextRGB(text.getText(), first) == TextRGBErrors.WITHOUT_PREFIX)
            text.setText(first + ":" + deleteFirstZeroes(text.getText()));
        else if (checkCorrectTextRGB(text.getText(), first) != TextRGBErrors.CORRECT) text.setText(first + ":255");
        else text.setText(deleteFirstZeroes(text.getText()));
    }

    private static TextRGBErrors checkCorrectTextRGB(String text, char first) {
        if (!text.startsWith(first + ":")) {
            try {
                int n = Integer.parseInt(text);
                if (n >= 0 && n <= 255) return TextRGBErrors.WITHOUT_PREFIX;
                else return TextRGBErrors.MORE_THAN_MAX_VALUE;
            } catch (NumberFormatException e) {
                return TextRGBErrors.ANOTHER_ERROR;
            }
        }
        if (text.equals(first + ":")) return TextRGBErrors.ANOTHER_ERROR;
        text = text.split(":")[1];
        try {
            int n = Integer.parseInt(text);
            if (n >= 0 && n <= 255) return TextRGBErrors.CORRECT;
            else return TextRGBErrors.MORE_THAN_MAX_VALUE;
        } catch (NumberFormatException e) {
            return TextRGBErrors.ANOTHER_ERROR;
        }
    }

    private static String deleteFirstZeroes(String s) {
        String pref = "";
        if (s.split(":").length > 1) {
            pref = s.split(":")[0] + ":";
            s = s.split(":")[1];
        }
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') n++;
            else break;
        }
        if (s.substring(n).isEmpty()) return pref + "0";
        else return pref + s.substring(n);
    }
}
