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

        JLabel lblUsuario = new JLabel("Usuário:");
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

        tblModel = new DefaultTableModel(new String[]{"Nome", "Curso", "Série", "Data de Nascimento", "Responsável"}, 0);
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

            // Simula autenticação
            if (usuario.equals("Fabio") && senha.equals("123")) {
                setTitle("Cadastro de Alunos");
                getContentPane().remove(panelLogin);
                getContentPane().add(panelAlunos);
                pack();
                carregarAlunos();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.");
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
        alunos.add(new Aluno("Raianne", "TI", "2º Ano", "10/05/2007", "Maria"));
        alunos.add(new Aluno("Maria", "Eletro", "2º Ano", "15/09/2001", ""));
        alunos.add(new Aluno("Pedro", "TI", "1º Ano", "20/12/1999", ""));
  

        for (Aluno aluno : alunos) {
            tblModel.addRow(new String[]{aluno.getNome(), aluno.getCurso(), aluno.getSerie(), aluno.getDataNascimento(), aluno.getResponsavel()});
        }
    }

  
    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
        tblModel.addRow(new String[]{aluno.getNome(), aluno.getCurso(), aluno.getSerie(), aluno.getDataNascimento(), aluno.getResponsavel()});
    }
}




