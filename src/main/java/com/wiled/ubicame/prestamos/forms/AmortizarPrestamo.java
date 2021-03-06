/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AmortizarPrestamo.java
 *
 * Created on May 21, 2011, 12:35:54 PM
 */
package com.wiled.ubicame.prestamos.forms;

import java.awt.Color;
import java.awt.Frame;
import com.wiled.ubicame.prestamos.utils.PrestamoConstants;
import com.wiled.ubicame.prestamos.utils.PrestamoException;
import com.wiled.ubicame.prestamos.datalayer.Controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import static com.wiled.ubicame.prestamos.utils.PrestamoUtils.containsOnlyNumbers;

/**
 *
 * @author edgar
 */
public class AmortizarPrestamo extends javax.swing.JDialog {
    private Frame frame;
    /** Creates new form AmortizarPrestamo */
    public AmortizarPrestamo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        controller = Controller.getInstance(PrestamoConstants.PROD_PU);
        this.frame = parent;
        
        tasaTxt.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onCalcular();
                }                
            }            
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        montoTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        calcularBtn = new javax.swing.JButton();
        tasaTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Amortizacion");
        setResizable(false);

        jLabel1.setText("Monto:");

        jLabel2.setText("Tasa:");

        calcularBtn.setBackground(java.awt.Color.green);
        calcularBtn.setText("Calcular");
        calcularBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tasaTxt, 0, 0, Short.MAX_VALUE)
                            .addComponent(montoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)))
                    .addComponent(calcularBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(montoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tasaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calcularBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onCalcular() {
            
        if (montoTxt.getText().isEmpty() || !containsOnlyNumbers(montoTxt.getText())) {
            JOptionPane.showMessageDialog(frame, "Por favor digite un monto", "ERROR DE VALIDACION", JOptionPane.ERROR_MESSAGE);
            montoTxt.grabFocus();
            montoTxt.setBackground(Color.red);
            montoTxt.setForeground(Color.WHITE);
            return;
        }

        if (tasaTxt.getText().isEmpty() || !containsOnlyNumbers(tasaTxt.getText())) {
            tasaTxt.setBackground(Color.WHITE);
            tasaTxt.setForeground(Color.BLACK);
            
            JOptionPane.showMessageDialog(frame, "Por favor digite una tasa valida", "ERROR DE VALIDACION", JOptionPane.ERROR_MESSAGE);
            tasaTxt.grabFocus();
            tasaTxt.setBackground(Color.red);
            tasaTxt.setForeground(Color.WHITE);
            return;
        }
        
        double monto = Double.valueOf(montoTxt.getText());
        float tasa = Float.valueOf(tasaTxt.getText());
        try {
            double resultado = controller.amortizarPrestamo(monto, tasa);
            JOptionPane.showMessageDialog(rootPane, "Cuotas de: RD$" + resultado + " pesos..", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
            
            montoTxt.setBackground(Color.WHITE);
            montoTxt.setForeground(Color.BLACK);
            montoTxt.setText("");
            
            tasaTxt.setBackground(Color.WHITE);
            tasaTxt.setForeground(Color.BLACK);
            tasaTxt.setText("");
            
            montoTxt.updateUI();
            tasaTxt.updateUI();
            
            montoTxt.grabFocus();
            
        } catch (PrestamoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calcularBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularBtnActionPerformed
        // TODO add your handling code here:
        onCalcular();
    }//GEN-LAST:event_calcularBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calcularBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField montoTxt;
    private javax.swing.JTextField tasaTxt;
    // End of variables declaration//GEN-END:variables
    private Controller controller;

}
