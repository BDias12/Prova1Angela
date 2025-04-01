public class Pessoa {

    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;
    private String tipoSanguineo;
    private String fatorRh;
    private String curso;
    private String contatoEmergencia;
    private String telefoneContatoEmergencia;
    private double altura;
    private double peso;
    private double imc;

    public Pessoa(String nome, String endereco, String telefone, String cpf,
                String tipoSanguineo, String fatorRh, String curso,
                String contatoEmergencia, String telefoneContatoEmergencia,
                double altura, double peso) 
    {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.tipoSanguineo = tipoSanguineo;
        this.fatorRh = fatorRh;
        this.curso = curso;
        this.contatoEmergencia = contatoEmergencia;
        this.telefoneContatoEmergencia = telefoneContatoEmergencia;
        this.altura = altura;
        this.peso = peso;
        this.imc = calcularIMC();
    }

    private double calcularIMC() {
        return peso / (altura * altura);
        
    }

    public String getNome() {
        return nome;
    }

    public double getAltura() {
        return altura;
    }

    public String getContatoEmergencia() {
        return contatoEmergencia;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCurso() {
        return curso;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getFatorRh() {
        return fatorRh;
    }

    public double getImc() {
        return imc;
    }

    public double getPeso() {
        return peso;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getTelefoneContatoEmergencia() {
        return telefoneContatoEmergencia;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }
    

}