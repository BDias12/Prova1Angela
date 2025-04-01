import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ConexaoBancoDeDados {

    private Connection conexao;
    private String URL_BancoDeDados;
    private String usuario;
    private String senha;

    

public ConexaoBancoDeDados(){
    URL_BancoDeDados = "jdbc:mysql://localhost:3306/sistema_cadastro";
    usuario = "root";
    senha = "root";

}

public void IniciarConexao(){
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexao = DriverManager.getConnection(URL_BancoDeDados, usuario, senha);
        System.out.println("Funcionou");
        }
    catch (Exception e) {

            e.printStackTrace();
            System.out.println("Deu errado");

    }
}

    private void EncerrarConexao() throws SQLException{

        if(conexao != null){
            conexao.close();
            
        }

        
    }
    public String InserirDadosPessoa(Pessoa objetoPessoa) throws SQLException {
        IniciarConexao();
    
        if (conexao != null) {
            PreparedStatement psInsert = conexao.prepareStatement("INSERT INTO pessoa (nome, endereco, telefone, cpf, tipo_sanguineo, fator_rh, curso, " +
                         "contato_emergencia, telefone_emergencia, altura, peso, imc) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            psInsert.setString(1, objetoPessoa.getNome());
            psInsert.setString(2, objetoPessoa.getEndereco());
            psInsert.setString(3, objetoPessoa.getTelefone());
            psInsert.setString(4, objetoPessoa.getCpf());
            psInsert.setString(5, objetoPessoa.getTipoSanguineo());
            psInsert.setString(6, objetoPessoa.getFatorRh());
            psInsert.setString(7, objetoPessoa.getCurso());
            psInsert.setString(8, objetoPessoa.getContatoEmergencia());
            psInsert.setString(9, objetoPessoa.getTelefoneContatoEmergencia());
            psInsert.setDouble(10, objetoPessoa.getAltura());
            psInsert.setDouble(11, objetoPessoa.getPeso());
            psInsert.setDouble(12, objetoPessoa.getImc());
    
            psInsert.executeUpdate();
            psInsert.close();
            EncerrarConexao();
            
            return "Cadastro Realizado com Sucesso!";
        } else {
            return "Erro! Inserção não realizada";
        }
    }
}