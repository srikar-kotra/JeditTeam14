package org.gjt.sp.jedit.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicBorders.ButtonBorder;
import org.gjt.sp.jedit.OperatingSystem;

public class RolloverButton extends JButton {
    private ButtonState buttonState;

    public RolloverButton(ButtonState buttonState) {
        this.buttonState = buttonState;
        addMouseListener(new MouseOverHandler());
    }

    public RolloverButton(Icon icon, ButtonState buttonState) {
        this(buttonState);
        setIcon(icon);
    }

    public void updateUI() {
        super.updateUI();
        buttonState.setBorderPainted(false);
        buttonState.setRequestFocusEnabled(false);
        buttonState.setMargin(new Insets(1,1,1,1));
    }

    public void setEnabled(boolean b) {
        super.setEnabled(b);
        buttonState.setBorderPainted(false);
        repaint();
    }

    public void setBorderPainted(boolean b) {
        try {
            buttonState.setRevalidateBlocked(true);
            super.setBorderPainted(b);
            setContentAreaFilled(b);
        } finally {
            buttonState.setRevalidateBlocked(false);
        }
    }

    public void revalidate() {
        if (!buttonState.isRevalidateBlocked())
            super.revalidate();
    }

    public void paint(Graphics g) {
        if (isEnabled())
            super.paint(g);
        else {
            Graphics2D g2 = (Graphics2D)g;
            g2.setComposite(buttonState.getAlphaComposite());
            super.paint(g2);
        }
    }

    class MouseOverHandler extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            buttonState.setContentAreaFilled(true);
            buttonState.setBorderPainted(isEnabled());
        }

        public void mouseExited(MouseEvent e) {
            buttonState.setContentAreaFilled(false);
            buttonState.setBorderPainted(false);
        }
    }
}

class ButtonState {
    private boolean borderPainted;
    private boolean contentAreaFilled;
    private boolean revalidateBlocked;
    private AlphaComposite alphaComposite;

    public ButtonState() {
        alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    }

    public boolean isBorderPainted() {
        return borderPainted;
    }

    public void setBorderPainted(boolean borderPainted) {
        this.borderPainted = borderPainted;
    }

    public boolean isContentAreaFilled() {
        return contentAreaFilled;
    }

    public void setContentAreaFilled(boolean contentAreaFilled) {
        this.contentAreaFilled = contentAreaFilled;
    }

    public boolean isRevalidateBlocked() {
        return revalidateBlocked;
    }

    public void setRevalidateBlocked(boolean revalidateBlocked) {
        this.revalidateBlocked = revalidateBlocked;
    }

    public AlphaComposite getAlphaComposite() {
        return alphaComposite;
    }

    public void setAlphaComposite(AlphaComposite alphaComposite) {
        this.alphaComposite = alphaComposite;
    }

    public void setRequestFocusEnabled(boolean b) {
        this.requestFocusEnabled = b;
    }

    public void setMargin(Insets insets) {
        this.margin = insets;
    }

    // Additional private fields for demonstration purposes
    private boolean requestFocusEnabled;
    private Insets margin;

    // Getter methods for the additional fields
    public boolean isRequestFocusEnabled() {
        return requestFocusEnabled;
    }

    public Insets getMargin() {
        return margin;
    }

}
