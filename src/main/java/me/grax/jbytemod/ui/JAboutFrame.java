package me.grax.jbytemod.ui;

import me.grax.jbytemod.JByteMod;
import me.grax.jbytemod.utils.TextUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JAboutFrame extends JDialog {

    public JAboutFrame(JByteMod jbm) {
        this.setTitle(JByteMod.res.getResource("about") + " " + jbm.getTitle());
        this.setModal(true);
        setBounds(100, 100, 400, 300);
        JPanel cp = new JPanel();
        cp.setLayout(new BorderLayout());
        cp.setBorder(new EmptyBorder(10, 10, 10, 10));
        setResizable(false);
        JButton close = new JButton(JByteMod.res.getResource("close"));
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JAboutFrame.this.dispose();
            }
        });
        JPanel jp = new JPanel(new GridLayout(1, 4));
        for (int i = 0; i < 3; i++)
            jp.add(new JPanel());

        jp.add(close);
        cp.add(jp, BorderLayout.PAGE_END);

        JTextPane title = new JTextPane();
        title.setContentType("text/html");
        title.setText(TextUtils.toHtml(jbm.getTitle()
                + "<br/>Copyright \u00A9 2022 Storm/Joaoshiozo <br/>Copyright \u00A9 2016-2018 noverify<br/>Copyright \u00A9 2019 Panda<br/>Copyright \u00A9 2020 xBrownieCodez<br/><br/>JByteMod by noverify, Reborn by Panda, Remastered by xBrownieCodez, Customized by Storm & Joaoshiozo<br/><font color=\"#0000EE\"><u>https://github.com/GraxCode/JByteMod-Beta</u></font><br/>Donate LTC: <font color=\"#333333\">LhwXLVASzb6t4vHSssA9FQwq2X5gAg8EKX</font>"));
        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane[Enabled].backgroundPainter", this.getBackground());
        title.putClientProperty("Nimbus.Overrides", defaults);
        title.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        title.setBackground(null);
        title.setEditable(false);
        title.setBorder(null);
        cp.add(title, BorderLayout.CENTER);

        getContentPane().add(cp);
    }
}
