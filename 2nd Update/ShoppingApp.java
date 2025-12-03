import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShoppingApp {

    static ArrayList<String> cart = new ArrayList<>();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Shopping System");
        frame.setSize(900, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(230, 245, 255));

        JLabel title = new JLabel("🛒 Simple Gadget Shop", SwingConstants.CENTER);
        title.setBounds(0, 20, 900, 40);
        title.setFont(new Font("Verdana", Font.BOLD, 28));
        frame.add(title);

        // Category
        JLabel catLabel = new JLabel("Select Category:");
        catLabel.setBounds(50, 100, 150, 30);
        frame.add(catLabel);

        String[] categories = {"Laptop", "Smartphone", "Headphone"};
        JComboBox<String> categoryBox = new JComboBox<>(categories);
        categoryBox.setBounds(180, 100, 200, 30);
        frame.add(categoryBox);

        // Brand
        JLabel brandLabel = new JLabel("Select Brand:");
        brandLabel.setBounds(50, 150, 150, 30);
        frame.add(brandLabel);

        JComboBox<String> brandBox = new JComboBox<>();
        brandBox.setBounds(180, 150, 200, 30);
        frame.add(brandBox);

        // Image & description area
        JLabel imgLabel = new JLabel();
        imgLabel.setBounds(500, 120, 300, 200);
        frame.add(imgLabel);

        JTextArea descArea = new JTextArea();
        descArea.setBounds(500, 330, 300, 120);
        descArea.setEditable(false);
        descArea.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(descArea);

        // Update brands based on category
        categoryBox.addActionListener(e -> {
            brandBox.removeAllItems();
            String cat = (String) categoryBox.getSelectedItem();

            switch (cat) {
                case "Laptop":
                    brandBox.addItem("HP");
                    brandBox.addItem("Dell");
                    break;
                case "Smartphone":
                    brandBox.addItem("iPhone");
                    brandBox.addItem("Samsung");
                    break;
                case "Headphone":
                    brandBox.addItem("Sony");
                    brandBox.addItem("JBL");
                    break;
            }
        });

        // Show Image + Description when Brand selected
        brandBox.addActionListener(e -> {
            String cat = (String) categoryBox.getSelectedItem();
            String brand = (String) brandBox.getSelectedItem();

            if (brand == null) return;

            // Image path EMPTY on purpose
            String imagePath = " ";

            imgLabel.setIcon(new ImageIcon(imagePath));

            descArea.setText(
                    "Category: " + cat +
                    "\nBrand: " + brand +
                    "\nPrice: (Image description e dewa thakbe)"
            );
        });

        // Add to cart button
        JButton addCartBtn = new JButton("Add To Cart");
        addCartBtn.setBounds(180, 220, 150, 40);
        frame.add(addCartBtn);

        addCartBtn.addActionListener(e -> {
            String cat = (String) categoryBox.getSelectedItem();
            String brand = (String) brandBox.getSelectedItem();

            if (brand == null) {
                JOptionPane.showMessageDialog(frame, "Please select a brand!");
                return;
            }

            cart.add(cat + " - " + brand);

            JOptionPane.showMessageDialog(frame, "Item added to cart!");
        });

        // View Cart button (opens new window)
        JButton viewCartBtn = new JButton("View Cart");
        viewCartBtn.setBounds(180, 280, 150, 40);
        frame.add(viewCartBtn);

        viewCartBtn.addActionListener(e -> openCartWindow());

        frame.setVisible(true);
    }

    // ----------------------------- CART WINDOW ---------------------------

    public static void openCartWindow() {

        JFrame cartFrame = new JFrame("Your Cart");
        cartFrame.setSize(600, 550);
        cartFrame.setLayout(null);
        cartFrame.setLocationRelativeTo(null);
        cartFrame.getContentPane().setBackground(new Color(255, 240, 240));

        JLabel title = new JLabel("Thank You For Purchase ❤️", SwingConstants.CENTER);
        title.setBounds(0, 10, 600, 40);
        title.setFont(new Font("Verdana", Font.BOLD, 22));
        cartFrame.add(title);

        JTextArea cartArea = new JTextArea();
        cartArea.setFont(new Font("Arial", Font.PLAIN, 16));

        StringBuilder sb = new StringBuilder();
        for (String item : cart) {
            sb.append(item).append("\n");
        }
        cartArea.setText(sb.toString());

        JScrollPane scroll = new JScrollPane(cartArea);
        scroll.setBounds(50, 70, 500, 180);
        cartFrame.add(scroll);

        // Customer Info Fields
        JLabel addLabel = new JLabel("Address:");
        addLabel.setBounds(50, 270, 100, 30);
        cartFrame.add(addLabel);

        JTextField address = new JTextField();
        address.setBounds(150, 270, 300, 30);
        cartFrame.add(address);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 320, 100, 30);
        cartFrame.add(emailLabel);

        JTextField email = new JTextField();
        email.setBounds(150, 320, 300, 30);
        cartFrame.add(email);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 370, 100, 30);
        cartFrame.add(phoneLabel);

        JTextField phone = new JTextField();
        phone.setBounds(150, 370, 300, 30);
        cartFrame.add(phone);

        JLabel totalLabel = new JLabel("Total Bill: (Image Description e Price thakbe)");
        totalLabel.setBounds(50, 420, 400, 30);
        cartFrame.add(totalLabel);

        JButton checkoutBtn = new JButton("Checkout");
        checkoutBtn.setBounds(420, 450, 120, 40);
        cartFrame.add(checkoutBtn);

        checkoutBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(cartFrame,
                    "Order Confirmed!\nThanks for shopping ❤️");
        });

        cartFrame.setVisible(true);
    }
}
