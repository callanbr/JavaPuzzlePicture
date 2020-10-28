import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Image;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Point;
import javax.swing.AbstractAction;

import MyButton.MyButton;

import java.awt.event.ActionEvent;

public class PuzzlePic extends JFrame {

    private JPanel panel;
    private BufferedImage source;
    private BufferedImage resizedImage;
    private Image image;
    private int width, height;
    private final int myWidth = 300;
    private final int numberOfButtons = 12;
    private List<Point> solution;
    private List<MyButton> buttons;

    public PuzzlePic() {
        compute();
    }

    private void compute() {

        solution = new ArrayList<>(); // 12 in total
        solution.add(new Point(0, 0));
        solution.add(new Point(1, 0));
        solution.add(new Point(2, 0));
        solution.add(new Point(3, 0));
        solution.add(new Point(0, 1));
        solution.add(new Point(1, 1));
        solution.add(new Point(2, 1));
        solution.add(new Point(3, 1));
        solution.add(new Point(0, 2));
        solution.add(new Point(1, 2));
        solution.add(new Point(2, 2));
        solution.add(new Point(3, 2));

        panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panel.setLayout(new GridLayout(4, 3, 0, 0));

        try {
            source = loadImage();
            int h = getNewHeight(source.getWidth(), source.getHeight());
            resizedImage = resizeImage(source, myWidth, h, BufferedImage.TYPE_INT_ARGB);
        } catch (IOException ex) {
            System.out.println("Cannot load image" + ex);
        }

        width = resizedImage.getWidth(null);
        height = resizedImage.getHeight(null);

        add(panel, BorderLayout.CENTER);

        // Collections.shuffle(buttons);

        // for (int i = 0; i < numberOfButtons; i++) {
        // var btn = buttons.get(i);
        // panel.add(btn);
        // btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        // btn.addActionListener(new ClickAction());
        // }

        pack();
        setTitle("Picture Puzzle");
        setResizable(false);
        setLocationRelativeTo(null);

    }

    private int getNewHeight(int w, int h) {
        double ratio = myWidth / (double) w;
        int newHeight = (int) (h * ratio);
        return newHeight;
    }

    private BufferedImage loadImage() throws IOException {
        var myImg = ImageIO.read(new File("src/img.jpg"));
        return myImg;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int w, int h, int type) {
        var resizeImage = new BufferedImage(w, h, type);
        var g = resizeImage.createGraphics();
        g.drawImage(originalImage, 0, 0, w, h, null);
        g.dispose();
        return resizeImage;
    }

    private class ClickAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private void checkSolution() {
        var current = new ArrayList<Point>();

        // for (JComponent btn : buttons) {
        // }

        if (compareList(solution, current)) {
            JOptionPane.showMessageDialog(panel, "Puzzle completed", "You Won!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static boolean compareList(List solution, List current) {
        return solution.toString().contentEquals(current.toString());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var puzzle = new PuzzlePic();
            puzzle.setVisible(true);
        });
    }

}
