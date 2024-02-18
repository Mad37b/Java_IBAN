
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;

public class VentanaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtNombre;
	public JTextField txtIBAN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCliente frame = new VentanaCliente();
					frame.setVisible(true);
				} catch (Exception event) {
					event.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	VentanaCliente() {
		setTitle("Alta de Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 321, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("NOMBRE CLIENTE:");
		lblNombre.setBounds(26, 24, 127, 14);
		contentPane.add(lblNombre);

		JLabel lblIBAN = new JLabel("IBAN:");
		lblIBAN.setBounds(26, 49, 46, 14);
		contentPane.add(lblIBAN);

		txtNombre = new JTextField();
		txtNombre.setBounds(184, 21, 111, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtIBAN = new JTextField();
		txtIBAN.setColumns(10);
		txtIBAN.setBounds(184, 46, 111, 20);
		contentPane.add(txtIBAN);

		JButton btnDarAlta = new JButton("Dar de alta");
		btnDarAlta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Fichero fichero = new Fichero();
				// obtener los datos del cliente
				String nombre = txtNombre.getText();
				String iban = txtIBAN.getText();
			
				
				// validar datos 
				
				ValidadorRedex validar=new ValidadorRedex();
				boolean datosValidos = validar.validarDatos(nombre, iban);
				if (datosValidos) {

					String nuevoCliente = nombre + "-" + iban + "-"  + "\n";

					verMensaje("Hola Pepe.Voy insertar:\n" + txtNombre.getText() + "\n" + txtIBAN.getText() + "\n");
					fichero.crearFichero(nuevoCliente);
					verMensaje("Cliente añadido");

					verMensaje("Datos de cliente guardados!!!");

					txtNombre.setText("");
					txtIBAN.setText("");
					

				}else  {
					verMensaje("No se ha introducido bien los datos del cliente");
				}

			}
		});

		btnDarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDarAlta.setBounds(184, 105, 111, 23);
		contentPane.add(btnDarAlta);
	}

	// Getters de los campos de texto para fichero

	String getNombreCliente() {
		return txtNombre.getText();
	}

	String getIBANCliente() {
		return txtIBAN.getText();
	}
	

	/**String getTarjeta() {
		return txtNie.getText();
	}**/

	void grabar() {
		String nuevoCliente = txtNombre.getText() + "-" + txtIBAN.getText() + "-"  + "\n";
		Fichero fichero = new Fichero();
		fichero.crearFichero(nuevoCliente);
	}
	private void verMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

}