package circulos_animados;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Contenedor_Bolas extends JComponent {
    
    Graphics g;
    int anchura = 515;
    int altura = 420;
    ArrayList<Bola> conjunto_bolas = new ArrayList<>();
    Random rnd = new Random();
    boolean activo;

    Contenedor_Bolas() {
        setPreferredSize(new Dimension(anchura, altura));
        setBorder(BorderFactory.createEtchedBorder());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(245, 245, 220));
        g.fillRect(0, 0, getWidth(), getHeight());
        try {
            for (int i = 0; i < conjunto_bolas.size(); i++) {
                conjunto_bolas.get(i).pintar_bola(g);
            }
            Thread.sleep(5);
            
        } catch (InterruptedException e) {
            System.out.println("Error al pintar la bola");
        }
        repaint();
    }

    void añadir_bola(int diametro) {
        conjunto_bolas.add(new Bola(diametro));
    }

    void eliminar_bola() {
        if (!conjunto_bolas.isEmpty()) {
            conjunto_bolas.remove(conjunto_bolas.size() - 1);
            
        }
    }
    
    void parar(){
        if (!conjunto_bolas.isEmpty()) {
            conjunto_bolas.parallelStream();
            
        }
    }

    int contar_bolas() {
        return conjunto_bolas.size();
    }


    class Bola {

        int columna = 0;
        int fila = 0;
        int direccion_columna = 1;
        int direccion_fila = 1;
        int diametro;

        Bola(int diametro) {
            this.diametro = diametro;
        }

        private Bola() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        void calcular_direccion_bola() {
            columna = columna - direccion_columna;
            fila = fila - direccion_fila;
            if (columna < 0) {
                columna = 0;
                direccion_columna = -1;
            } else if (columna + diametro > getWidth()) {
                direccion_columna = 1;
            }

            if (fila < 0) {
                fila = 0;
                direccion_fila = -1;
            } else if (fila + diametro > getHeight()) {
                direccion_fila = 1;
            }
        }

        void pintar_bola(Graphics g) {
            calcular_direccion_bola();
            g.setColor(new Color(rnd.nextInt(225), rnd.nextInt(225), rnd.nextInt(225)));
            g.fillOval(columna, fila, diametro, diametro);
        }
        
        
    
        
        }
   
}

