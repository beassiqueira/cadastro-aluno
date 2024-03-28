package cadastro;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class CadastroNovoAluno extends JFrame implements ActionListener {

    private JTextField txtNome, txtCurso, txtSerie, txtDataNascimento, txtResponsavel;
    private JButton btnCadastrar;
    private CadastroAluno telaPrincipal;

    public CadastroNovoAluno(CadastroAluno telaPrincipal) {
        this.telaPrincipal = telaPrincipal;

        setTitle("Cadastro de Novo Aluno");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(telaPrincipal);

        JPanel panelCadastro = new JPanel(new GridLayout(6, 2, 5, 5));
        panelCadastro.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField();
        panelCadastro.add(lblNome);
        panelCadastro.add(txtNome);

        JLabel lblCurso = new JLabel("Curso:");
        txtCurso = new JTextField();
        panelCadastro.add(lblCurso);
        panelCadastro.add(txtCurso);

        JLabel lblSerie = new JLabel("Série:");
        txtSerie = new JTextField();
        panelCadastro.add(lblSerie);
        panelCadastro.add(txtSerie);

        JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
        txtDataNascimento = new JTextField();
        panelCadastro.add(lblDataNascimento);
        panelCadastro.add(txtDataNascimento);

        JLabel lblResponsavel = new JLabel("Responsável:");
        txtResponsavel = new JTextField();
        panelCadastro.add(lblResponsavel);
        panelCadastro.add(txtResponsavel);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(this);
        panelCadastro.add(btnCadastrar);

        add(panelCadastro);
    }
    
    public static boolean validaAutorizacaoIdade (String data, String responsavel) {
    	
    	if (validarFormatoData(data)) {
        	if (isMenorQue18Anos(data) && responsavel.isEmpty()) {
        		return false;
        	}
    	} else {
    		return false;
    	}
    	return true;    	   	
    }
    
    public static boolean validarFormatoData(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Definir como estrito para evitar datas inválidas como 31 de fevereiro

        try {
            sdf.parse(data);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    public static boolean isMenorQue18Anos(String dataString) {
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  	    	 
        
        try {
            // Fazendo o parsing da string para um objeto LocalDate
            LocalDate dataNascimento = LocalDate.parse(dataString, formatter);
            
            // Obtendo a data atual
            LocalDate dataAtual = LocalDate.now();
            
            // Calculando a diferença de anos entre a data atual e a data de nascimento
            Period periodo = Period.between(dataNascimento, dataAtual);
                    
            // Verificando se a pessoa tem menos de 18 anos
            if (periodo.getYears() < 18) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao fazer parsing da data: " + e.getMessage());
        }
		return false;
    	
      
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCadastrar) {
            String nome = txtNome.getText();
            String curso = txtCurso.getText();
            String serie = txtSerie.getText();
            String dataNascimento = txtDataNascimento.getText();
            String responsavel = txtResponsavel.getText();
            
            //Validar Formato de data e idade
            if(validaAutorizacaoIdade(dataNascimento, responsavel)) {
            	
            	// Validar os dados antes de cadastrar
                if (!nome.isEmpty() && !curso.isEmpty() && !serie.isEmpty() && !dataNascimento.isEmpty()) {
                    Aluno novoAluno = new Aluno(nome, curso, serie, dataNascimento, responsavel);
                    telaPrincipal.adicionarAluno(novoAluno);
                    JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
                    dispose(); // Fecha a tela de cadastro após cadastrar
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.");
                } 
            	         
            } else {
            	JOptionPane.showMessageDialog(this, "Data em formato invalido, formato: dd/MM/yyyy ou -18, preencher responsável");
            }               
           
        }
    }
}

