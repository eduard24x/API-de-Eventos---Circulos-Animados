package circulos_animados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Eduard
 */
public class Programa extends Thread {

    JFrame ventana = new JFrame();
    JPanel panel = new JPanel();
    JButton crear = new JButton("Crear");
    JButton eliminar = new JButton("Eliminar");
    JTextField campo_medida = new JTextField(2);
    JLabel etiqueta_medida = new JLabel("Medida: ");
    JLabel etiqueta_contador = new JLabel("   ");
   
    Contenedor_Bolas contenedor = new Contenedor_Bolas();

    Programa() {
        ventana.setSize(750, 550);
        ventana.getContentPane().setLayout(new FlowLayout());
        etiqueta_contador.setBorder(BorderFactory.createBevelBorder(1));
        crear.addActionListener(new Acciones());
        eliminar.addActionListener(new Acciones());
        panel.add(etiqueta_medida);
        panel.add(campo_medida);
        panel.add(crear);
        panel.add(eliminar);
        panel.add(etiqueta_contador);
        ventana.add(contenedor);
        ventana.add(panel);
        ventana.getContentPane().setBackground(new Color(102,153,255));
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

    }

    class Acciones implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == crear) {
                if (!campo_medida.getText().equals("")) {
                    try {
                        contenedor.añadir_bola(Integer.parseInt(campo_medida.getText()));
                        etiqueta_contador.setText(" " + contenedor.contar_bolas());
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null, "Introduzca un valor válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Indique la medida del diámetro", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (ae.getSource() == eliminar) {
                contenedor.eliminar_bola();
                etiqueta_contador.setText(" " + contenedor.contar_bolas());
            }

        }
    }

    public static void main(String[] args) {
        new Programa();
    }
}
