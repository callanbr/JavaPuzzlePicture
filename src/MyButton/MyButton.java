package MyButton;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

public class MyButton extends JButton {

    public MyButton() {
        super();
        compute();
    }

    public MyButton(Image image) {
        super(new ImageIcon(image));
        compute();
    }

    private void compute() {
        BorderFactory.createLineBorder(Color.MAGENTA);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("mouseEntered");
            }

            public void mouseExited(MouseEvent e) {
                System.out.println("mouseExited");
            }

        });

    }

}
