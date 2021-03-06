package com.jayfella.devkit.properties.component;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.jme3.math.Vector3f;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Vector3fComponent extends ReflectedSdkComponent<Vector3f> {

    private JPanel contentPanel;
    private JLabel propertyNameLabel;
    private JFormattedTextField xTextField;
    private JFormattedTextField yTextField;
    private JFormattedTextField zTextField;
    private JButton nullButton;
    private JButton normalizeButton;

    public Vector3fComponent() {
        this(null, null, null, false);
    }

    public Vector3fComponent(boolean nullable) {
        this(null, null, null, true);
    }

    public Vector3fComponent(Object object, String declaredGetter, String declaredSetter) throws NoSuchMethodException {
        this(object,
                object.getClass().getDeclaredMethod(declaredGetter),
                object.getClass().getDeclaredMethod(declaredSetter, float.class));
    }

    public Vector3fComponent(Object parent, Method getter, Method setter) {
        this(parent, getter, setter, false);
    }

    public Vector3fComponent(Object parent, Method getter, Method setter, boolean nullable) {
        super(parent, getter, setter);

        setNullable(nullable);

        FloatFormatFactory floatFormatFactory = new FloatFormatFactory();

        xTextField.setFormatterFactory(floatFormatFactory);
        yTextField.setFormatterFactory(floatFormatFactory);
        zTextField.setFormatterFactory(floatFormatFactory);

        if (getter != null) {
            try {
                setValue((Vector3f) getter.invoke(parent));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        normalizeButton.addActionListener(e -> {

            Vector3f value = getInputValue();

            if (value != null) {
                Vector3f normalized = value.normalize();
                // setValue(normalized);
                xTextField.setValue(normalized.x);
                yTextField.setValue(normalized.y);
                zTextField.setValue(normalized.z);
            }

        });

        nullButton.setVisible(nullable);
        nullButton.addActionListener(e -> {
            if (isNullable()) {
                xTextField.setText("");
                yTextField.setText("");
                zTextField.setText("");
            }
        });

    }

    @Override
    public void setValue(Vector3f value) {
        super.setValue(value);

        if (!isBinded()) {

            SwingUtilities.invokeLater(() -> {

                if (value != null) {

                    this.xTextField.setText("" + value.x);
                    this.yTextField.setText("" + value.y);
                    this.zTextField.setText("" + value.z);

                } else {
                    this.xTextField.setText("");
                    this.yTextField.setText("");
                    this.zTextField.setText("");
                }

                bind();

            });
        }
    }

    @Override
    public JComponent getJComponent() {
        return contentPanel;
    }

    @Override
    public void bind() {
        super.bind();
        xTextField.getDocument().addDocumentListener(changeListener);
        yTextField.getDocument().addDocumentListener(changeListener);
        zTextField.getDocument().addDocumentListener(changeListener);
    }

    @Override
    public void setPropertyName(String propertyName) {
        super.setPropertyName(propertyName);
        propertyNameLabel.setText("Vector3f: " + propertyName);
    }

    private Vector3f getInputValue() {

        String x = xTextField.getText().trim();
        String y = yTextField.getText().trim();
        String z = zTextField.getText().trim();

        if (x.isEmpty() && y.isEmpty() && z.isEmpty()) {

            return isNullable()
                    ? null
                    : new Vector3f();

        }

        if (x.isEmpty()) x = "0";
        if (y.isEmpty()) y = "0";
        if (z.isEmpty()) z = "0";

        return new Vector3f(
                Float.parseFloat(x),
                Float.parseFloat(y),
                Float.parseFloat(z)
        );

    }

    private final DocumentListener changeListener = new DocumentListener() {

        private void set() {
            Vector3f newValue = getInputValue();
            setValue(newValue);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            set();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            set();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            set();
        }

    };

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JSeparator separator1 = new JSeparator();
        contentPanel.add(separator1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        contentPanel.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        normalizeButton = new JButton();
        normalizeButton.setBorderPainted(true);
        normalizeButton.setText("normalize");
        panel1.add(normalizeButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nullButton = new JButton();
        nullButton.setBorderPainted(true);
        nullButton.setContentAreaFilled(true);
        nullButton.setHorizontalAlignment(0);
        nullButton.setHorizontalTextPosition(11);
        nullButton.setText("null");
        panel1.add(nullButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        propertyNameLabel = new JLabel();
        propertyNameLabel.setHorizontalAlignment(4);
        propertyNameLabel.setText("Vector3f");
        panel1.add(propertyNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPanel.add(panel2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("x");
        panel2.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("y");
        panel2.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("z");
        panel2.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        xTextField = new JFormattedTextField();
        panel2.add(xTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        yTextField = new JFormattedTextField();
        panel2.add(yTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        zTextField = new JFormattedTextField();
        panel2.add(zTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPanel;
    }

}
