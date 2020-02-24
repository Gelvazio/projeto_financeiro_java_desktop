package Principal;

/**
 *
 * @author Gelvazio Camargo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

    private static final String DRIVER  = "org.postgresql.Driver";
    private static final String HOST    = "salt.db.elephantsql.com";
    private static final String BANCO   = "ftgcyhzw";
    private static final String CONEXAO = "jdbc:postgresql://" + HOST +":5432/" + BANCO;
    private static final String USUARIO = "ftgcyhzw";
    private static final String SENHA   = "Qgs5XLVecOqlU6ZkzfAbmxpi7tQcYLbI";
    
    public static boolean testaConexao() {
        boolean verifica = false;
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(CONEXAO, USUARIO, SENHA);
            verifica = true;
        } catch (ClassNotFoundException erro) {
            JOptionPane.showMessageDialog(null, "Erro de driver! \n" + erro.getMessage());
            verifica = false;
            Conexao.closeAll(conn);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de Conexao! \n" + erro.getMessage());
            verifica = false;
            Conexao.closeAll(conn);
        }
        return verifica;
    }

    public static Connection getConexao() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(CONEXAO, USUARIO, SENHA);
        } catch (ClassNotFoundException erro) {
            JOptionPane.showMessageDialog(null, "Erro de driver! \n" + erro.getMessage());
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de Conexao! \n" + erro.getMessage());
        }
        return conn;
    }

    public static void closeAll(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexao! \n " + erro.getMessage());
        } finally {
        }
    }
}
