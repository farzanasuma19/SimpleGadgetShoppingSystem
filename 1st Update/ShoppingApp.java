import javax.swing.*;
import java.awt.*;

public class ShoppingApp {
    public static void main(String[] args) {

        JFrame frame = new JFrame("🛍️ Simple Shopping System");
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background color
        frame.getContentPane().setBackground(new Color(240, 245, 255));

        // Title
        JLabel title = new JLabel("Welcome to Simple Shopping System");
        title.setBounds(100, 20, 350, 30);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(title);

        // Buttons (just visible, no function yet)
        JButton addBtn = new JButton("Add to Cart");
        addBtn.setBounds(180, 100, 130, 30);
        frame.add(addBtn);

        JButton viewBtn = new JButton("View Cart");
        viewBtn.setBounds(180, 150, 130, 30);
        frame.add(viewBtn);

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(180, 200, 130, 30);
        frame.add(exitBtn);

        frame.setVisible(true);
    }
}
