package cadastro;

public class Aluno {
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