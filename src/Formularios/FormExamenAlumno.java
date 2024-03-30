package Formularios;

import java.sql.Connection;
import Proyecto.Conexion;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class FormExamenAlumno extends javax.swing.JFrame {

    public String idPregunta = "0";
    public String respuesta;
    public int minutos = 0;
    public int segundos = 0;
    public int puntos = 0;

    public void comprobarPregunta() {
        String respuestaEstudiante = "";
        if (botonOpcion1.isSelected()) {
            respuestaEstudiante = botonOpcion1.getText();
        } else if (botonOpcion2.isSelected()) {
            respuestaEstudiante = botonOpcion2.getText();

        } else if (botonOpcion3.isSelected()) {
            respuestaEstudiante = botonOpcion3.getText();

        } else {
            respuestaEstudiante = botonOpcion4.getText();
        }
        if (respuestaEstudiante.equals(respuesta)) {
            puntos++;
            String puntaje = String.valueOf(puntos);
            lblPuntaje.setText(puntaje);
        }

        //Cambiar numero de pregunta
        int numeroPregunta = Integer.parseInt(idPregunta);
        numeroPregunta++;
        idPregunta = String.valueOf(numeroPregunta);

        //Limpiar las opciones
        botonOpcion1.setSelected(false);
        botonOpcion2.setSelected(false);
        botonOpcion3.setSelected(false);
        botonOpcion4.setSelected(false);

        //Ocultar siguiente pregunta
        if (idPregunta.equals("10")) {
            btnSiguientePregunta.setVisible(false);
        }

    }

    public void Pregunta() {

        try {
            Connection con = new Conexion().conectar();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1 = st.executeQuery("select * from pregunta where idPregunta='" + idPregunta + "'");
            
            while (rs1.next()) {
                lblPreguntaActual.setText(rs1.getString(1));
                lblEnunciadoPregunta.setText(rs1.getString(2));
                botonOpcion1.setText(rs1.getString(3));
                botonOpcion2.setText(rs1.getString(4));
                botonOpcion3.setText(rs1.getString(5));
                botonOpcion4.setText(rs1.getString(6));
                respuesta = rs1.getString(7);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void SubirPregunta() {
        String numeAlum = lblNum.getText();
        comprobarPregunta();
        try {
            Connection con = new Conexion().conectar();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.executeUpdate("update alumno set notas='" + puntos + "' where idAlumno='" + numeAlum + "'");
            String puntaje = String.valueOf(puntos);
            setVisible(false);
            new FormPuntajeObtenido(puntaje).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    /**
     * Creates new form FormExamenEstudiante
     */
    public FormExamenAlumno() {
        initComponents();
    }

    Timer tiempo = null;

    public FormExamenAlumno(String numAlumno) {

        initComponents();
        lblNum.setText(numAlumno);

        /*MOSTRAMOS LA FECHA ACTUAL*/
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = new Date();
        lblFecha.setText(formatoFecha.format(fecha));

        /*MOSTRAMOS LA PRIMERA PREGUNTA CON LOS DATOS DEL ALUMNO*/
        try {
            Connection con = new Conexion().conectar();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM alumno WHERE idAlumno='" + numAlumno + "'");

            while (rs.next()) {
                lblNombre.setText((rs.getString(2)) + " " + (rs.getString(3)));
            }
            ResultSet rs2 = statement.executeQuery("SELECT * FROM pregunta WHERE idPregunta='" + idPregunta + "'");
            while (rs.next()) {
                lblPreguntaActual.setText(rs2.getString(1));
                lblEnunciadoPregunta.setText(rs2.getString(2));
                botonOpcion1.setText(rs2.getString(3));
                botonOpcion2.setText(rs2.getString(4));
                botonOpcion3.setText(rs2.getString(5));
                botonOpcion4.setText(rs2.getString(6));
                respuesta = rs2.getString(7);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        /*Tiempo de duracion del examen*/
        setLocationRelativeTo(this);
        tiempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblSegundos.setText(String.valueOf(segundos));
                lblMinutos.setText(String.valueOf(minutos));

                if (segundos == 60) {
                    segundos = 0;
                    minutos++;
                    if (minutos == 10) {
                        tiempo.stop();
//                        answerCheck();
//                        submit();
                    }
                }
                segundos++;
            }
        });
        tiempo.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblMinutos = new javax.swing.JLabel();
        lblSegundos = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lblNum = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblnumeroPreguntas = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblPreguntaActual = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblPuntaje = new javax.swing.JLabel();
        lblEnunciadoPregunta = new javax.swing.JLabel();
        botonOpcion1 = new javax.swing.JRadioButton();
        botonOpcion2 = new javax.swing.JRadioButton();
        botonOpcion3 = new javax.swing.JRadioButton();
        botonOpcion4 = new javax.swing.JRadioButton();
        btnSiguientePregunta = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        btnGuardarRespuesta = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        jLabel2.setText("Bienvenido Alumno");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Fecha :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, -1, -1));

        lblFecha.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblFecha.setText("jLabel4");
        jPanel1.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/graduado.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setText("Tiempo Restante :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 50, -1, -1));

        lblMinutos.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMinutos.setForeground(new java.awt.Color(255, 0, 0));
        lblMinutos.setText("00");
        jPanel1.add(lblMinutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 50, -1, -1));

        lblSegundos.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSegundos.setForeground(new java.awt.Color(255, 0, 0));
        lblSegundos.setText("00");
        jPanel1.add(lblSegundos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1520, 50, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("10 Min");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1477, 0, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setText("Tiempo :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1348, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 150));

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        jLabel10.setText("Alumno # :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        lblNum.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        lblNum.setText("5408");
        jPanel2.add(lblNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        jLabel12.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        jLabel12.setText("Nombre :");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        lblNombre.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        lblNombre.setText("Jorge Lopez");
        jPanel2.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 200, -1));

        jLabel14.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        jLabel14.setText("Total de Preguntas:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        lblnumeroPreguntas.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        lblnumeroPreguntas.setText("10");
        jPanel2.add(lblnumeroPreguntas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));

        jLabel16.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        jLabel16.setText("Pregunta N°:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        lblPreguntaActual.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        lblPreguntaActual.setText("00");
        jPanel2.add(lblPreguntaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, -1, -1));

        jLabel18.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        jLabel18.setText("Tus Puntaje:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        lblPuntaje.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        lblPuntaje.setText("00");
        jPanel2.add(lblPuntaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 480, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 430, 750));

        lblEnunciadoPregunta.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblEnunciadoPregunta.setText("Enunciado Pregunta");
        getContentPane().add(lblEnunciadoPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, -1, -1));

        botonOpcion1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        botonOpcion1.setText("jRadioButton1");
        botonOpcion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOpcion1ActionPerformed(evt);
            }
        });
        getContentPane().add(botonOpcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, -1, -1));

        botonOpcion2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        botonOpcion2.setText("jRadioButton2");
        botonOpcion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOpcion2ActionPerformed(evt);
            }
        });
        getContentPane().add(botonOpcion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 450, -1, -1));

        botonOpcion3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        botonOpcion3.setText("jRadioButton3");
        botonOpcion3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOpcion3ActionPerformed(evt);
            }
        });
        getContentPane().add(botonOpcion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 520, -1, -1));

        botonOpcion4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        botonOpcion4.setText("jRadioButton4");
        botonOpcion4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOpcion4ActionPerformed(evt);
            }
        });
        getContentPane().add(botonOpcion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 600, -1, -1));

        btnSiguientePregunta.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        btnSiguientePregunta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/siguiente.png"))); // NOI18N
        btnSiguientePregunta.setText("Siguiente");
        btnSiguientePregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguientePreguntaActionPerformed(evt);
            }
        });
        getContentPane().add(btnSiguientePregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 790, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("Opciones:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, -1, -1));

        btnGuardarRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnGuardarRespuesta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar-datos.png"))); // NOI18N
        btnGuardarRespuesta.setText("Guardar");
        btnGuardarRespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRespuestaActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarRespuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 780, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/FondoDatosEstudiante.png"))); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, -1, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonOpcion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOpcion1ActionPerformed
        // TODO add your handling code here:
        if (botonOpcion1.isSelected()) {
            botonOpcion2.setSelected(false);
            botonOpcion3.setSelected(false);
            botonOpcion4.setSelected(false);
        }
    }//GEN-LAST:event_botonOpcion1ActionPerformed

    private void botonOpcion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOpcion2ActionPerformed
        // TODO add your handling code here:
        if (botonOpcion2.isSelected()) {
            botonOpcion1.setSelected(false);
            botonOpcion3.setSelected(false);
            botonOpcion4.setSelected(false);
        }
    }//GEN-LAST:event_botonOpcion2ActionPerformed

    private void botonOpcion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOpcion3ActionPerformed

        // TODO add your handling code here:
        if (botonOpcion3.isSelected()) {
            botonOpcion2.setSelected(false);
            botonOpcion1.setSelected(false);
            botonOpcion4.setSelected(false);
        }
    }//GEN-LAST:event_botonOpcion3ActionPerformed

    private void botonOpcion4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOpcion4ActionPerformed
        // TODO add your handling code here:
        if (botonOpcion4.isSelected()) {
            botonOpcion2.setSelected(false);
            botonOpcion3.setSelected(false);
            botonOpcion1.setSelected(false);
        }
    }//GEN-LAST:event_botonOpcion4ActionPerformed

    private void btnSiguientePreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguientePreguntaActionPerformed
        comprobarPregunta();
        Pregunta();
    }//GEN-LAST:event_btnSiguientePreguntaActionPerformed

    private void btnGuardarRespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRespuestaActionPerformed
        int guardar = JOptionPane.showConfirmDialog(null, "¿Deseas enviar tu examen?","Seleccionar",JOptionPane.YES_NO_OPTION);
        if(guardar == 0){
            comprobarPregunta();
            SubirPregunta();
        }
    }//GEN-LAST:event_btnGuardarRespuestaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormExamenAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormExamenAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormExamenAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormExamenAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormExamenAlumno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton botonOpcion1;
    private javax.swing.JRadioButton botonOpcion2;
    private javax.swing.JRadioButton botonOpcion3;
    private javax.swing.JRadioButton botonOpcion4;
    private javax.swing.JButton btnGuardarRespuesta;
    private javax.swing.JButton btnSiguientePregunta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblEnunciadoPregunta;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblMinutos;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNum;
    private javax.swing.JLabel lblPreguntaActual;
    private javax.swing.JLabel lblPuntaje;
    private javax.swing.JLabel lblSegundos;
    private javax.swing.JLabel lblnumeroPreguntas;
    // End of variables declaration//GEN-END:variables
}
