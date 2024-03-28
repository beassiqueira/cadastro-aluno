package cadastro;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class CadastroAluno extends JFrame implements ActionListener {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin, btnCadastrarNovoAluno;
    private JPanel panelLogin, panelAlunos;
    private JLabel lblLogo, lblCadastroAlunos;
    private JTable tblAlunos;
    private DefaultTableModel tblModel;
    private JScrollPane scrollPane;

    private List<Aluno> alunos;

    public CadastroAluno() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panelLogin = new JPanel();
        panelLogin.setLayout(new GridLayout(3, 2, 10, 10));
        panelLogin.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblUsuario = new JLabel("Usu�rio:");
        txtUsuario = new JTextField();
        panelLogin.add(lblUsuario);
        panelLogin.add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField();
        panelLogin.add(lblSenha);
        panelLogin.add(txtSenha);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);
        panelLogin.add(btnLogin);

        add(panelLogin);

        alunos = new ArrayList<>();

        tblModel = new DefaultTableModel(new String[]{"Nome", "Curso", "S�rie", "Data de Nascimento", "Respons�vel"}, 0);
        tblAlunos = new JTable(tblModel);
        scrollPane = new JScrollPane(tblAlunos);

        lblLogo = new JLabel(new ImageIcon("logo.png"));
        lblCadastroAlunos = new JLabel("Cadastro de Alunos", SwingConstants.CENTER);
        lblCadastroAlunos.setFont(new Font("Arial", Font.BOLD, 20));

        panelAlunos = new JPanel(new BorderLayout());
        panelAlunos.add(lblLogo, BorderLayout.NORTH);
        panelAlunos.add(lblCadastroAlunos, BorderLayout.CENTER);
        panelAlunos.add(scrollPane, BorderLayout.CENTER);

        btnCadastrarNovoAluno = new JButton("Cadastrar Novo Aluno");
        btnCadastrarNovoAluno.addActionListener(this);
        panelAlunos.add(btnCadastrarNovoAluno, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());

            // Simula autentica��o
            if (usuario.equals("admin") && senha.equals("admin")) {
                setTitle("Cadastro de Alunos");
                getContentPane().remove(panelLogin);
                getContentPane().add(panelAlunos);
                pack();
                carregarAlunos();
            } else {
                JOptionPane.showMessageDialog(this, "Usu�rio ou senha inv�lidos.");
            }
        } else if (e.getSource() == btnCadastrarNovoAluno) {
            abrirTelaCadastro();
        }
    }

    private void abrirTelaCadastro() {
        CadastroNovoAluno telaCadastro = new CadastroNovoAluno(this);
        telaCadastro.setVisible(true);
    }

    private void carregarAlunos() {
        // Simula dados
        alunos.add(new Aluno("Jo�o", "Matem�tica", "3� Ano", "10/05/2000", "Maria"));
        alunos.add(new Aluno("Maria", "Portugu�s", "2� Ano", "15/09/2001", ""));
        alunos.add(new Aluno("Pedro", "Hist�ria", "1� Ano", "20/12/1999", ""));
  

        for (Aluno aluno : alunos) {
            tblModel.addRow(new String[]{aluno.getNome(), aluno.getCurso(), aluno.getSerie(), aluno.getDataNascimento(), aluno.getResponsavel()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastroAluno app = new CadastroAluno();
            app.setVisible(true);
        });
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
        tblModel.addRow(new String[]{aluno.getNome(), aluno.getCurso(), aluno.getSerie(), aluno.getDataNascimento(), aluno.getResponsavel()});
    }
}

class Aluno {
    private String nome;
    private String curso;
    private String serie;
    private String dataNascimento;
    private String responsavel;

    public Aluno(String nome, String curso, String serie, String dataNascimento, String responsavel) {
        this.nome = nome;
        this.curso = curso;
        this.serie = serie;
        this.dataNascimento = dataNascimento;
        this.responsavel = responsavel;
    }

	public String getNome() {
		return nome;
	}

    public String getCurso() {
        return curso;
    }

    public String getSerie() {
        return serie;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getResponsavel() {
        return responsavel;
    }
}


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

        JLabel lblSerie = new JLabel("S�rie:");
        txtSerie = new JTextField();
        panelCadastro.add(lblSerie);
        panelCadastro.add(txtSerie);

        JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
        txtDataNascimento = new JTextField();
        panelCadastro.add(lblDataNascimento);
        panelCadastro.add(txtDataNascimento);

        JLabel lblResponsavel = new JLabel("Respons�vel:");
        txtResponsavel = new JTextField();
        panelCadastro.add(lblResponsavel);
        panelCadastro.add(txtResponsavel);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(this);
        panelCadastro.add(btnCadastrar);

        add(panelCadastro);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCadastrar) {
            String nome = txtNome.getText();
            String curso = txtCurso.getText();
            String serie = txtSerie.getText();
            String dataNascimento = txtDataNascimento.getText(); // DD/mm/aaaa
            String responsavel = txtResponsavel.getText();
            
            //TODO: Validar Formato de data
            
            //TODO: Validar Data de nacimento
            
            // Validar os dados antes de cadastrar
            if (!nome.isEmpty() && !curso.isEmpty() && !serie.isEmpty() && !dataNascimento.isEmpty()) {
                Aluno novoAluno = new Aluno(nome, curso, serie, dataNascimento, responsavel);
                telaPrincipal.adicionarAluno(novoAluno);
                JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
                dispose(); // Fecha a tela de cadastro ap�s cadastrar
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigat�rios.");
            } 
        }
    }
}
