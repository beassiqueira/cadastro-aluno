package cadastro;

import javax.swing.SwingUtilities;

public class Principal {
	
	  public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            CadastroAluno app = new CadastroAluno();
	            app.setVisible(true);
	        });
	    }

}
