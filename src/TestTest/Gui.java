package TestTest;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * Created by SretenskyVD on 13.08.2018.
 */


    public class Gui extends JFrame {
        private JButton btnPushMe = new JButton("Push me!");

        public Gui() {
            super("This is my title");
            this.setSize(600, 400);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            JPanel contentPane = (JPanel)this.getContentPane();
            contentPane.setLayout(new FlowLayout());

            contentPane.add(btnPushMe);

            btnPushMe.addActionListener(this::btnPushMe);

            this.setVisible(true);
        }

        private void btnPushMe(ActionEvent event) {
            System.out.println("Button clicked!");
        }

        public static void main(String[] args) {
            new Gui();

        }
    }

