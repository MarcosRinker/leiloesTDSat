/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    private conectaDAO conexao;

    public ProdutosDAO() {
        this.conexao = new conectaDAO();
        this.conn = this.conexao.connectDB();
    }

    public boolean conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11", "root", "81822440");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }

    public int cadastrarProduto(ProdutosDTO produto) {

        //conn = new conectaDAO().connectDB();
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO produtos VALUES(?,?,?,?)");
            st.setInt(1, produto.getId());
            st.setString(2, produto.getNome());
            st.setInt(3, produto.getValor());
            st.setString(4, produto.getStatus());

            status = st.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        String sql = "SELECT id, nome, valor, status FROM produtos";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            ArrayList<ProdutosDTO> listagem = new ArrayList<>();
            while (rs.next()) {
                ProdutosDTO produtos = new ProdutosDTO();

                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setValor(rs.getInt("valor"));
                produtos.setStatus(rs.getString("status"));

                listagem.add(produtos);

            }
            return listagem;
        } catch (Exception e) {
            return null;
        }

    }

    public void venderProduto(ProdutosDTO produto) {

        String sql = "UPDATE produtos SET status=?, WHERE id=?";
        try {

            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            stmt.setString(1, "Vendido");
            stmt.setInt(2, produto.getId());

            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao mudar status: " + e.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos(String status) {

        String sql = "SELECT * FROM produtos WHERE status LIKE ?";

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, "%" + status + "%");

            ResultSet rs = stmt.executeQuery();

            ArrayList<ProdutosDTO> lista = new ArrayList<>();

            while (rs.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                lista.add(produto);

            }
            return lista;
        } catch (Exception e) {
            return null;
        }

    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
        }

    }

}
