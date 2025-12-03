import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;

public class ShoppingApp1 {

    // Product class
    static class Product {
        String title;
        String desc;
        int price;
        String imagePath;

        Product(String t, String d, int p, String img) {
            title = t;
            desc = d;
            price = p;
            imagePath = img;
        }
    }

    static Map<String, Product[]> brandProducts = new HashMap<>();
    static java.util.List<Product> cart = new ArrayList<>();

    static JLabel[] photoLabels = new JLabel[3];
    static JTextArea[] detailAreas = new JTextArea[3];
    static JButton[] addButtons = new JButton[3];
    static Product[] currentShown = new Product[3];

    public static void main(String[] args) {
        prepareData();

        JFrame frame = new JFrame("Shopping System");
        frame.setSize(920, 620);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE); 

        // Title
        JLabel title = new JLabel("🛒 Simple Gadget Shop", SwingConstants.CENTER);
        title.setBounds(0, 10, 920, 40);
        title.setFont(new Font("Verdana", Font.BOLD, 24));
        title.setForeground(Color.PINK);
        frame.add(title);

        // Cart Button
        JButton cartBtn = new JButton("🛒 View Cart");
        cartBtn.setBounds(760, 65, 120, 35);
        frame.add(cartBtn);

        cartBtn.addActionListener(e -> openCartWindow());

        // Category dropdown
        JLabel catLabel = new JLabel("Select Category:");
        catLabel.setBounds(30, 70, 140, 30);
        frame.add(catLabel);

        String[] categories = {"", "Laptop", "Smartphone", "Headphone"};
        JComboBox<String> categoryBox = new JComboBox<>(categories);
        categoryBox.setBounds(180, 70, 220, 30);
        frame.add(categoryBox);

        // Brand dropdown
        JLabel brandLabel = new JLabel("Select Brand:");
        brandLabel.setBounds(30, 110, 140, 30);
        frame.add(brandLabel);

        JComboBox<String> brandBox = new JComboBox<>();
        brandBox.setBounds(180, 110, 220, 30);
        frame.add(brandBox);

        // Product boxes
        int xStart = 40;
        for (int i = 0; i < 3; i++) {
            JPanel box = new JPanel();
            box.setLayout(null);
            box.setBounds(xStart + (i * 270), 170, 250, 300);
            box.setBorder(new LineBorder(Color.GRAY));
            frame.add(box);

            photoLabels[i] = new JLabel("Select brand", SwingConstants.CENTER);
            photoLabels[i].setBounds(25, 15, 200, 140);
            photoLabels[i].setBorder(new LineBorder(Color.BLACK));
            box.add(photoLabels[i]);

            detailAreas[i] = new JTextArea();
            detailAreas[i].setBounds(20, 160, 210, 70);
            detailAreas[i].setEditable(false);
            detailAreas[i].setFont(new Font("Arial", Font.PLAIN, 13));
            box.add(detailAreas[i]);

            addButtons[i] = new JButton("Add to Cart");
            addButtons[i].setBounds(60, 240, 130, 28);
            box.add(addButtons[i]);

            final int index = i;
            addButtons[i].addActionListener(ev -> {
                if (currentShown[index] != null) {
                    cart.add(currentShown[index]);
                    JOptionPane.showMessageDialog(frame,
                            "Added:\n" +
                                    currentShown[index].title +
                                    " - " + currentShown[index].price + "৳");
                }
            });
        }

        // Category select -> load brands
        categoryBox.addActionListener(e -> {
            brandBox.removeAllItems();
            String cat = (String) categoryBox.getSelectedItem();

            if (cat.equals("Laptop")) {
                brandBox.addItem("HP");
                brandBox.addItem("Dell");
                brandBox.addItem("Lenovo");
            } else if (cat.equals("Smartphone")) {
                brandBox.addItem("Samsung");
                brandBox.addItem("Apple");
                brandBox.addItem("Xiaomi");
            } else if (cat.equals("Headphone")) {
                brandBox.addItem("Sony");
                brandBox.addItem("JBL");
                brandBox.addItem("Bose");
            }
        });

        // Brand select -> load products
        brandBox.addActionListener(e -> {
            String brand = (String) brandBox.getSelectedItem();
            if (brand == null) return;

            Product[] items = brandProducts.get(brand);
            if (items == null) return;

            for (int i = 0; i < 3; i++) {
                currentShown[i] = items[i];

                Product p = items[i];
                detailAreas[i].setText(
                        p.title + "\n" +
                                p.desc + "\nPrice: " + p.price + "৳"
                );

                // Load image
                try {
                    ImageIcon icon = new ImageIcon(p.imagePath);
                    Image scaled = icon.getImage().getScaledInstance(200, 140, Image.SCALE_SMOOTH);
                    photoLabels[i].setIcon(new ImageIcon(scaled));
                    photoLabels[i].setText("");
                } catch (Exception ex) {
                    photoLabels[i].setText("Image not found");
                }
            }
        });

        frame.setVisible(true);
    }

    // Cart window
    static void openCartWindow() {
        JFrame cartFrame = new JFrame("Your Cart");
        cartFrame.setSize(400, 500);
        cartFrame.setLayout(null);
        cartFrame.setLocationRelativeTo(null);

        JTextArea area = new JTextArea();
        area.setBounds(20, 20, 340, 350);
        area.setEditable(false);

        int total = 0;
        StringBuilder sb = new StringBuilder();

        for (Product p : cart) {
            sb.append(p.title).append(" - ").append(p.price).append("৳\n");
            total += p.price;
        }

        sb.append("\n------------------------");
        sb.append("\nTotal Price: ").append(total).append("৳");

        area.setText(sb.toString());
        cartFrame.add(area);

        //Buy Button
        JButton buyBtn = new JButton("✅ BUY");
        buyBtn.setBounds(100, 390, 100, 35);
        buyBtn.setFont(new Font("Arial", Font.BOLD, 14));
        cartFrame.add(buyBtn);

        buyBtn.addActionListener(b -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(cartFrame, "Cart is Empty! ❌");
                return;
            }
            JOptionPane.showMessageDialog(cartFrame, "🎉 Happy Shoppin! ✅");
            cartFrame.dispose();
        });
        
     // CHECKOUT Button
        JButton checkoutBtn = new JButton("🧾 Checkout");
        checkoutBtn.setBounds(220, 390, 100, 35);  // adjust position if needed
        checkoutBtn.setFont(new Font("Arial", Font.BOLD, 14));
        cartFrame.add(checkoutBtn);

        checkoutBtn.addActionListener(c -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(cartFrame, "Cart is Empty! ❌");
                return;
            }
            int total1 = 0;
            for (Product p : cart) {
                total1 += p.price;
            }
            JOptionPane.showMessageDialog(cartFrame, 
                    "Total Amount: " + total1 + "৳\nProceed to payment.");
        });


        cartFrame.setVisible(true);
    }

    // INITIAL DATA (Brand + Product + Image)
    static void prepareData() {

        brandProducts.put("HP", new Product[]{
                new Product("HP Pavilion 15", "8GB RAM, i5", 65000, "image/hp1.jpg"),
                new Product("HP Envy x360", "Touchscreen", 85000, "image/hp2.jpg"),
                new Product("HP Victus", "Gaming Laptop", 95000, "image/hp1.jpg")
        });

        brandProducts.put("Dell", new Product[]{
                new Product("Dell Inspiron 15", "8GB RAM", 60000, "image/dell.jpg"),
                new Product("Dell XPS 13", "Ultra Slim", 120000, "image/dell.jpg"),
                new Product("Dell G15 Gaming", "Ryzen 7", 105000, "image/dell.jpg")
        });
        
        brandProducts.put("Lenovo", new Product[]{
        	    new Product("Lenovo IdeaPad 3", "8GB RAM, Ryzen 5", 55000, "image/thinkpad.jpg"),
        	    new Product("Lenovo ThinkPad X1 Carbon", "16GB RAM, i7", 125000, "image/thinkpad.jpg"),
        	    new Product("Lenovo Legion 5", "Gaming Laptop, RTX 3050", 110000, "image/thinkpad.jpg")
        	});

        brandProducts.put("Samsung", new Product[]{
                new Product("Samsung A52", "64MP Camera", 35000, "image/samsung.jpg"),
                new Product("Samsung S21", "Flagship", 75000, "image/sm1.jpg"),
                new Product("Samsung S24 Ultra", "200MP", 145000, "image/samsung.jpg")
        });

        brandProducts.put("Apple", new Product[]{
                new Product("iPhone 12", "128GB", 68000, "image/iphone.jpg"),
                new Product("iPhone 13", "A15 Chip", 78000, "image/iphone.jpg"),
                new Product("iPhone 15 Pro Max", "Titanium", 150000, "image/iphone.jpg")
        });

        brandProducts.put("Xiaomi", new Product[]{
        	    new Product("Xiaomi Redmi Note 11", "6GB RAM, 50MP", 22000, "image/xiaomi.jpg"),
        	    new Product("Xiaomi Mi 13", "Leica Camera", 68000, "image/xiaomi.jpg"),
        	    new Product("Xiaomi 14 Ultra", "200MP Camera", 120000, "image/xiaomi.jpg")
        	});

        brandProducts.put("Sony", new Product[]{
                new Product("Sony XM4", "Noise Cancelling", 32000, "image/sony1.jpg"),
                new Product("Sony CH720N", "Wireless", 18000, "image/sony1.jpg"),
                new Product("Sony WF-C500", "Earbuds", 12000, "image/sony1.jpg")
        });

        brandProducts.put("JBL", new Product[]{
                new Product("JBL 510BT", "Bass Boost", 5000, "image/jbl1.jpg"),
                new Product("JBL Quantum 100", "Gaming", 3500, "image/jbl1.jpg"),
                new Product("JBL Live 660NC", "ANC", 15000, "image/jbl1.jpg")
        });

        brandProducts.put("Bose", new Product[]{
                new Product("Bose QC45", "ANC", 35000, "image/bose1.jpg"),
                new Product("Bose 700", "Premium", 45000, "image/bose1.jpg"),
                new Product("Bose Sport Earbuds", "Sweatproof", 19000, "image/bose1.jpg")
        });
    }
}
