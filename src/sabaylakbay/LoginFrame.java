package sabaylakbay;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class LoginFrame extends JFrame {

    // ===== Colors =====
    static final Color GREEN       = new Color(0x0B4F3A);
    static final Color GREEN_HOVER = new Color(0x0E6349);
    static final Color TEXT_DARK   = new Color(0x1E2A35);
    static final Color TEXT_GRAY   = new Color(0x6B7280);
    static final Color HINT_GRAY   = new Color(0x9AA3AD);
    static final Color BORDER_GRAY = new Color(0xD9DEE3);
    static final Color LINK_GREEN  = new Color(0x137A4F);
    static final Color WHITE_SOFT  = new Color(255, 255, 255, 215);

    static final String FONT = "Segoe UI";

    private BackgroundPanel root;
    private RoundTextField txtUser;
    private RoundPasswordField txtPass;
    private JCheckBox chkRemember;

    public LoginFrame() {
        setTitle("SabayLakbay - Log In");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        root = new BackgroundPanel();
        root.setLayout(null);
        root.setPreferredSize(new Dimension(1288, 811));
        setContentPane(root);

        // ===== ADD YOUR BACKGROUND LATER =====
        // 1. Put the image in src/images/ (e.g. src/images/mayon.jpg)
        // 2. Uncomment the line below
              setContentPane(root);

        root.setBackgroundImage(new ImageIcon(getClass().getResource("/images/mayon.jpg")).getImage());

        buildLeftSide();
        buildCard();

        buildLeftSide();
        buildCard();

        pack();
        setLocationRelativeTo(null);
    }

    // ================= LEFT SIDE =================
    private void buildLeftSide() {
        LogoIcon logo = new LogoIcon();
        logo.setBounds(58, 46, 34, 30);
        root.add(logo);

        JLabel brand = label("SabayLakbay", Font.BOLD, 26, Color.WHITE);
        brand.setBounds(100, 42, 300, 36);
        root.add(brand);

        JLabel tagline = label("Travel Together • Share Experiences • Create Memories",
                Font.PLAIN, 12, WHITE_SOFT);
        tagline.setBounds(58, 84, 400, 20);
        root.add(tagline);

        JLabel headline = label("<html>Travel together,<br>explore more.</html>", Font.BOLD, 40, Color.WHITE);
        headline.setBounds(58, 318, 600, 100);
        root.add(headline);

        JLabel sub = label("<html><div style='line-height:1.6'>Create or join travel groups, meet new friends, and make"
                + "<br>unforgettable memories — for less.</div></html>", Font.PLAIN, 14, WHITE_SOFT);
        sub.setBounds(58, 428, 500, 50);
        root.add(sub);

        PinIcon pin = new PinIcon();
        pin.setBounds(58, 733, 14, 18);
        root.add(pin);

        JLabel place = label("Mayon Volcano, Albay", Font.BOLD, 12, Color.WHITE);
        place.setBounds(83, 727, 300, 16);
        root.add(place);

        JLabel placeSub = label("A little closer to your next adventure.", Font.PLAIN, 10, WHITE_SOFT);
        placeSub.setBounds(83, 744, 300, 14);
        root.add(placeSub);
    }

    // ================= LOGIN CARD =================
    private void buildCard() {
        RoundPanel card = new RoundPanel(14);
        card.setLayout(null);
        card.setBounds(824, 97, 376, 450);
        root.add(card);

        // Tabs
        JLabel tabLogin = label("Log In", Font.PLAIN, 13, TEXT_DARK);
        tabLogin.setHorizontalAlignment(SwingConstants.CENTER);
        tabLogin.setBounds(28, 24, 160, 30);
        card.add(tabLogin);

        JLabel tabRegister = label("Register", Font.PLAIN, 13, TEXT_GRAY);
        tabRegister.setHorizontalAlignment(SwingConstants.CENTER);
        tabRegister.setBounds(188, 24, 160, 30);
        tabRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tabRegister.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { openRegister(); }
        });
        card.add(tabRegister);

        TabLine tabLine = new TabLine();
        tabLine.setBounds(28, 60, 320, 3);
        card.add(tabLine);

        // Title
        JLabel title = label("Welcome Back!", Font.BOLD, 24, TEXT_DARK);
        title.setBounds(28, 84, 320, 34);
        card.add(title);

        JLabel subtitle = label("Log in to your SabayLakbay account.", Font.PLAIN, 12, TEXT_GRAY);
        subtitle.setBounds(28, 118, 320, 18);
        card.add(subtitle);

        // Username
        JLabel lblUser = label("Email or Username", Font.BOLD, 12, TEXT_DARK);
        lblUser.setBounds(28, 158, 320, 18);
        card.add(lblUser);

        txtUser = new RoundTextField("Enter your email or username");
        txtUser.setBounds(28, 180, 320, 42);
        card.add(txtUser);

        // Password (eye button added first so it stays on top of the field)
        JLabel lblPass = label("Password", Font.BOLD, 12, TEXT_DARK);
        lblPass.setBounds(28, 237, 320, 18);
        card.add(lblPass);

        txtPass = new RoundPasswordField("Enter your password");
        EyeButton eye = new EyeButton(txtPass);
        eye.setBounds(312, 268, 26, 24);
        card.add(eye);
        txtPass.setBounds(28, 259, 320, 42);
        card.add(txtPass);

        // Remember me + forgot password
        chkRemember = new JCheckBox("Remember me");
        chkRemember.setFont(new Font(FONT, Font.PLAIN, 11));
        chkRemember.setForeground(TEXT_GRAY);
        chkRemember.setOpaque(false);
        chkRemember.setFocusPainted(false);
        chkRemember.setBounds(24, 314, 150, 22);
        card.add(chkRemember);

        JLabel forgot = label("Forgot password?", Font.BOLD, 11, LINK_GREEN);
        forgot.setHorizontalAlignment(SwingConstants.RIGHT);
        forgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgot.setBounds(200, 315, 148, 20);
        forgot.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { forgotPassword(); }
        });
        card.add(forgot);

        // Log In button
        RoundButton btnLogin = new RoundButton("Log In");
        btnLogin.setBounds(28, 348, 320, 40);
        btnLogin.addActionListener(e -> doLogin());
        card.add(btnLogin);
        getRootPane().setDefaultButton(btnLogin); // Enter key = Log In

        // Register link
        JLabel regLink = label("<html>Don't have an account? <b><font color='#137A4F'>Register</font></b></html>",
                Font.PLAIN, 11, TEXT_GRAY);
        regLink.setHorizontalAlignment(SwingConstants.CENTER);
        regLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        regLink.setBounds(28, 404, 320, 20);
        regLink.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { openRegister(); }
        });
        card.add(regLink);
    }

    // ================= ACTIONS =================
    private void doLogin() {
        String user = txtUser.getText().trim();
        String pass = new String(txtPass.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your email/username and password.",
                    "Log In", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // TODO: replace with your real account check (database, file, etc.)
        JOptionPane.showMessageDialog(this, "Welcome, " + user + "!", "Log In",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void openRegister() {
        // TODO: open your Register frame here, e.g.
        // new RegisterFrame().setVisible(true);
        // dispose();
        JOptionPane.showMessageDialog(this, "Register page not made yet.");
    }

    private void forgotPassword() {
        // TODO: forgot password screen
        JOptionPane.showMessageDialog(this, "Forgot password not made yet.");
    }

    // ================= HELPERS =================
    static JLabel label(String text, int style, int size, Color color) {
        JLabel l = new JLabel(text);
        l.setFont(new Font(FONT, style, size));
        l.setForeground(color);
        return l;
    }

    static void antialias(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }

    static void paintFieldBox(JComponent c, Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        antialias(g2);
        RoundRectangle2D box = new RoundRectangle2D.Float(0.5f, 0.5f, c.getWidth() - 1, c.getHeight() - 1, 10, 10);
        g2.setColor(Color.WHITE);
        g2.fill(box);
        g2.setColor(c.hasFocus() ? GREEN : BORDER_GRAY);
        g2.draw(box);
        g2.dispose();
    }

    static void paintHint(JTextComponent c, Graphics g, String hint) {
        if (c.getDocument().getLength() > 0) return;
        Graphics2D g2 = (Graphics2D) g.create();
        antialias(g2);
        g2.setFont(c.getFont());
        g2.setColor(HINT_GRAY);
        FontMetrics fm = g2.getFontMetrics();
        int y = (c.getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(hint, c.getInsets().left, y);
        g2.dispose();
    }

    // ================= CUSTOM COMPONENTS =================

    /** Main panel. Plain dark green for now; call setBackgroundImage() later. */
    static class BackgroundPanel extends JPanel {
        private Image bg;

        void setBackgroundImage(Image img) { bg = img; repaint(); }

        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            int w = getWidth(), h = getHeight();
            if (bg != null) {
                // scale to cover the whole panel
                double s = Math.max((double) w / bg.getWidth(null), (double) h / bg.getHeight(null));
                int iw = (int) (bg.getWidth(null) * s), ih = (int) (bg.getHeight(null) * s);
                g2.drawImage(bg, (w - iw) / 2, (h - ih) / 2, iw, ih, null);
                // dark overlay so the white text stays readable
                g2.setPaint(new GradientPaint(0, 0, new Color(0, 0, 0, 140), w * 0.6f, 0, new Color(0, 0, 0, 30)));
                g2.fillRect(0, 0, w, h);
            } else {
                g2.setPaint(new GradientPaint(0, 0, new Color(0x123B2E), w, h, new Color(0x2F5D4A)));
                g2.fillRect(0, 0, w, h);
            }
            g2.dispose();
        }
    }

    /** White rounded card. */
    static class RoundPanel extends JPanel {
        private final int radius;
        RoundPanel(int radius) { this.radius = radius; setOpaque(false); }

        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            antialias(g2);
            g2.setColor(Color.WHITE);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
            g2.dispose();
        }

        // lets the eye button overlap the password field without glitches
        @Override public boolean isOptimizedDrawingEnabled() { return false; }
    }

    static class RoundTextField extends JTextField {
        private final String hint;
        RoundTextField(String hint) {
            this.hint = hint;
            setOpaque(false);
            setBorder(new EmptyBorder(0, 14, 0, 14));
            setFont(new Font(FONT, Font.PLAIN, 13));
            setForeground(TEXT_DARK);
            setCaretColor(TEXT_DARK);
            addFocusListener(new FocusAdapter() {
                @Override public void focusGained(FocusEvent e) { repaint(); }
                @Override public void focusLost(FocusEvent e) { repaint(); }
            });
        }
        @Override protected void paintComponent(Graphics g) {
            paintFieldBox(this, g);
            super.paintComponent(g);
            paintHint(this, g, hint);
        }
    }

    static class RoundPasswordField extends JPasswordField {
        private final String hint;
        RoundPasswordField(String hint) {
            this.hint = hint;
            setOpaque(false);
            setBorder(new EmptyBorder(0, 14, 0, 44)); // room for the eye icon
            setFont(new Font(FONT, Font.PLAIN, 13));
            setForeground(TEXT_DARK);
            setCaretColor(TEXT_DARK);
            setEchoChar('•');
            addFocusListener(new FocusAdapter() {
                @Override public void focusGained(FocusEvent e) { repaint(); }
                @Override public void focusLost(FocusEvent e) { repaint(); }
            });
        }
        @Override protected void paintComponent(Graphics g) {
            paintFieldBox(this, g);
            super.paintComponent(g);
            paintHint(this, g, hint);
        }
    }

    /** Show / hide password toggle. */
    static class EyeButton extends JComponent {
        private boolean visible = false;
        EyeButton(JPasswordField field) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setToolTipText("Show password");
            addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    visible = !visible;
                    field.setEchoChar(visible ? (char) 0 : '•');
                    setToolTipText(visible ? "Hide password" : "Show password");
                    repaint();
                    field.requestFocusInWindow();
                }
            });
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            antialias(g2);
            g2.setColor(TEXT_GRAY);
            g2.setStroke(new BasicStroke(1.6f));
            float cx = getWidth() / 2f, cy = getHeight() / 2f;
            Path2D eye = new Path2D.Float();
            eye.moveTo(cx - 9, cy);
            eye.quadTo(cx, cy - 9, cx + 9, cy);
            eye.quadTo(cx, cy + 9, cx - 9, cy);
            g2.draw(eye);
            g2.draw(new Ellipse2D.Float(cx - 3, cy - 3, 6, 6));
            if (visible) g2.draw(new Line2D.Float(cx - 8, cy + 7, cx + 8, cy - 7));
            g2.dispose();
        }
    }

    /** Dark green rounded button. */
    static class RoundButton extends JButton {
        RoundButton(String text) {
            super(text);
            setFont(new Font(FONT, Font.BOLD, 14));
            setForeground(Color.WHITE);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setRolloverEnabled(true);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            antialias(g2);
            g2.setColor(getModel().isRollover() ? GREEN_HOVER : GREEN);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    /** Line under the Log In / Register tabs. */
    static class TabLine extends JComponent {
        @Override protected void paintComponent(Graphics g) {
            g.setColor(new Color(0xE5E7EB));
            g.fillRect(0, getHeight() - 1, getWidth(), 1);
            g.setColor(TEXT_DARK);
            g.fillRect(0, getHeight() - 2, getWidth() / 2, 2);
        }
    }

    /** Mountain logo. */
    static class LogoIcon extends JComponent {
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            antialias(g2);
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            Path2D m = new Path2D.Float();
            m.moveTo(2, 27);
            m.lineTo(12, 5);
            m.lineTo(19, 18);
            m.lineTo(24, 11);
            m.lineTo(32, 27);
            m.closePath();
            g2.draw(m);
            g2.draw(new Line2D.Float(12, 5, 16, 27));
            g2.dispose();
        }
    }

    /** Location pin icon. */
    static class PinIcon extends JComponent {
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            antialias(g2);
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(1.5f));
            Path2D p = new Path2D.Float();
            p.moveTo(7, 17);
            p.curveTo(2, 11, 1, 9, 1, 7);
            p.curveTo(1, 3.5, 3.7, 1, 7, 1);
            p.curveTo(10.3, 1, 13, 3.5, 13, 7);
            p.curveTo(13, 9, 12, 11, 7, 17);
            g2.draw(p);
            g2.draw(new Ellipse2D.Float(5, 5, 4, 4));
            g2.dispose();
        }
    }

    // ================= MAIN =================
      public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}