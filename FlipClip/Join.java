package FlipClip;
import javax.swing.*;
import java.awt.*;

public class Join extends JFrame {
    public Join() {
        setTitle("FlipClip");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 160, 20, 20)); // Add space between components

        JPanel Instructionpanel = new JPanel();
        Instructionpanel.setLayout(new BoxLayout(Instructionpanel, BoxLayout.Y_AXIS));

        JLabel modeLabel = new JLabel("On the Other device :");
        JLabel modeLabel1 = new JLabel("Step 1: Choose Bidirectional Mode.");
        JLabel modeLabel2 = new JLabel("Step 2: Click Create.");
        JLabel modeLabel3 = new JLabel("Step 3: Enter IP Address to connect.");

        modeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        modeLabel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        modeLabel2.setAlignmentX(Component.LEFT_ALIGNMENT);
        modeLabel3.setAlignmentX(Component.LEFT_ALIGNMENT);

        modeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        modeLabel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        modeLabel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        modeLabel3.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        modeLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        modeLabel1.setFont(new Font("Serif", Font.PLAIN, 20));
        modeLabel2.setFont(new Font("Serif", Font.PLAIN, 20));
        modeLabel3.setFont(new Font("Serif", Font.PLAIN, 20));

        Instructionpanel.add(modeLabel);
        Instructionpanel.add(modeLabel1);
        Instructionpanel.add(modeLabel2);
        Instructionpanel.add(modeLabel3);

        panel.add(Instructionpanel);

        JLabel ownIpLabel = new JLabel("Enter IP Address:");
        ownIpLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ownIpLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Add space below component
        ownIpLabel.setFont(new Font("Serif", Font.PLAIN, 25)); // Change font and size

        panel.add(ownIpLabel);

        JTextField ipTextField = new JTextField(30); // Set the size of the text field
        ipTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        ipTextField.setAlignmentY(Component.CENTER_ALIGNMENT);
        ipTextField.setMaximumSize(new Dimension(230, 50));
        ipTextField.setFont(new Font("Serif", Font.PLAIN, 20));
        ipTextField.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 8));

        panel.add(ipTextField);

        panel.add(Box.createRigidArea(new Dimension(0, 13))); // Add space between components

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JButton button = new JButton("Connect");
        button.setFont(new Font("Serif", Font.PLAIN, 20));
        button.setPreferredSize(new Dimension(110, 40));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.addActionListener(e -> connectToIpAddress(ipTextField.getText()));

        JButton back = new JButton("Back");
        back.setFont(new Font("Serif", Font.PLAIN, 20));
        back.setPreferredSize(new Dimension(110, 40));
        back.setVerticalTextPosition(SwingConstants.BOTTOM);
        back.setHorizontalTextPosition(SwingConstants.CENTER);
        back.addActionListener(e -> goBack());

        buttonPanel.add(button);
        buttonPanel.add(back);

        panel.add(buttonPanel);
        add(panel);
    }

    private void connectToIpAddress(String ipAddress) {
        // Code to establish a connection with the specified IP address goes here
    }

    private void goBack() {
        // Code to go back to the previous frame goes here
        BiDirectional biDirectionalFrame = new BiDirectional();
        biDirectionalFrame.setVisible(true);
        dispose();

    }


}
