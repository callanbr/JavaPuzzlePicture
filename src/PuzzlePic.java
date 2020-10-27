import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.EventQueue;
import java.awt.BorderLayout;

public class PuzzlePic extends JFrame {

    private JPanel panel;
    private BufferedImage source;
    private BufferedImage resizedImage;
    private Image image;
    private int width, height;
    private final int myWidth = 300;

    public PuzzlePic() {
        compute();
    }

    private void compute() {
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

        width = resizedImage.getWidth();
        height = resizedImage.getHeight();

        add(panel, BorderLayout.CENTER);

        pack();
        setTitle("Picture Puzzle");

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
        g.dispose();
        return resizeImage;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var puzzle = new PuzzlePic();
            puzzle.setVisible(true);
        });
    }
}
