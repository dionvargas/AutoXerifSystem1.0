package br.edu.ifc.autoxerifsystem.axslocal.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.digitalpersona.uareu.*;
import com.digitalpersona.uareu.Fid.Fiv;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;

public class ImagePanel
        extends JPanel {

    private static final long serialVersionUID = 5;
    private BufferedImage m_image;

    private static BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public void showImage(Fid image, Dimension size) {
        Fiv view = image.getViews()[0];
        m_image = new BufferedImage(view.getWidth(), view.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        m_image.getRaster().setDataElements(0, 0, view.getWidth(), view.getHeight(), view.getImageData());
        m_image = resize(m_image, size.width, size.height);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(m_image, 0, 0, null);
    }

}
