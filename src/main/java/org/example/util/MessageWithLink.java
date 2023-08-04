package org.example.util;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;

public class MessageWithLink extends JEditorPane {

    private static final long serialVersionUID = 1L;

    public static void openLink() {
        JOptionPane.showMessageDialog(
                null,
                new MessageWithLink("You must have received an " +
                        "<br> auth token from <a href=\"https://jprq.io/auth\">jprq.io</a>")

        );
    }

    public static int okLink(String link, String linkName, String message) {
        String[] response = {"OK"};

        return JOptionPane.showOptionDialog(
                null,
                new MessageWithLink(messageAddUrlLink(link, linkName, message)),
                "Info",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                response,
                0);
    }

    public MessageWithLink(String htmlBody) {
        super("text/html", htmlBuild(htmlBody));
        addHyperlinkListener(e -> {
            if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                try {
                    java.awt.Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setEditable(false);
        setBorder(null);
    }

    static StringBuffer getStyle() {

        // for copying style
        JLabel label = new JLabel();
        Font font = label.getFont();
        Color color = label.getBackground();

        // create some css from the label's font
        StringBuffer style = new StringBuffer("font-family:" + font.getFamily() + ";");
        style.append("font-weight:" + (font.isBold() ? "bold" : "normal") + ";");
        style.append("font-size:" + font.getSize() + "pt;");
        style.append("background-color: rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");");
        return style;
    }

    private static String messageAddUrlLink(String link, String linkName, String message) {
        return message.replace(linkName, "<a href=" + link + ">" + linkName + "</a>");
    }

    private static String htmlBuild(String title) {

        return
                "<html>" +
                        "</head>\n" +
                        "<body style=\"" + getStyle() + "\">" +
                        "    <p style=\"width: 400px;" +
                        " background-color: transparent; " +
                        "padding: 10px; font-size: 17px; " +
                        "font-family: sans-serif;\" " +
                        "color: #00ad00" +
                        ">\n" +
                        title +
                        "    </p>\n" +
                        "</body>\n" +
                        "</html>";
    }
}
