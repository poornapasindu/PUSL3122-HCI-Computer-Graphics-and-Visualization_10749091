import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Circle_2D {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Circle Drawing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use the custom panel that extends JPanel
        CustomPanel panel = new CustomPanel();

        frame.add(panel);

        JPanel controlPanel = new JPanel();
        frame.add(controlPanel, BorderLayout.SOUTH);

        JButton button = new JButton("Set Circle Radius and Color");
        controlPanel.add(button);

        JButton saveButton = new JButton("Save Image");

        controlPanel.add(saveButton);

        frame.setSize(400, 400);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user for the radius of the circle
                String radiusStr = JOptionPane.showInputDialog("Enter the radius of the circle:");

                // Prompt the user to select the color
                Color selectedColor = JColorChooser.showDialog(frame, "Choose Circle Color", Color.BLUE);

                try {
                    int userRadius = Integer.parseInt(radiusStr);
                    panel.setRadiusAndColor(userRadius, selectedColor);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Using the default radius and color.");
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the circle drawing to an image file
                saveImage(panel, "circle.png");
            }
        });
    }

    // Method to save the panel as an image
    private static void saveImage(JPanel panel, String fileName) {
        int width = panel.getWidth();
        int height = panel.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        panel.paint(g2d);
        g2d.dispose();

        try {
            ImageIO.write(image, "PNG", new File(fileName));
            JOptionPane.showMessageDialog(null, "Image saved to " + fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while saving the image.");
        }
    }
}

class CustomPanel extends JPanel {
    private int radius = 50; // Default radius
    private Color color = Color.BLUE; // Default color

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        int x = (width - 2 * radius) / 2;
        int y = (height - 2 * radius) / 2;

        g.setColor(color);
        g.fillOval(x, y, 2 * radius, 2 * radius);
    }

    public void setRadiusAndColor(int newRadius, Color newColor) {
        this.radius = newRadius;
        this.color = newColor;
        repaint();
    }
}
