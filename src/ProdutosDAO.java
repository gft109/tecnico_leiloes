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
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        int stat;
        try {
            st = conn.prepareStatement("INSERT INTO produtos VALUES(?,?,?,?)");
            st.setString(1, null);
            st.setString(2, produto.getNome());
            st.setInt(3, produto.getValor());
            st.setString(4, produto.getStatus());
            stat = st.executeUpdate();
            return stat; //retorna 1 em caso de sucesso
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }   
    }

    public int venderProduto (int prodId) {
        conn = new conectaDAO().connectDB();
        int stat;
        try {
            st = conn.prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE id = ?;");
            st.setInt(1, prodId);
            stat = st.executeUpdate();
            return stat; //retorna 1 em caso de sucesso
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        } 
    }
    
    
    public List<ProdutosDTO> listarProdutos() {
        // colocar a função assim, quando for por filtro: public List<ProdutosDTO> listagem(String filtro)
        
        conn = new conectaDAO().connectDB();
        String sql = "select * from produtos";

        /*
        if (!filtro.isEmpty()) {
            sql = sql + " where nome like ?";
        }
        */

        try {
            st = conn.prepareStatement(sql);
            
            /*
            if (!filtro.isEmpty()) {
                st.setString(1, "%" + filtro + "%");
            }
            */
            
            rs = st.executeQuery();
            List<ProdutosDTO> lista = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produtos = new ProdutosDTO();
                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setValor(rs.getInt("valor"));
                produtos.setStatus(rs.getString("status"));
                lista.add(produtos);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }

    }
    
    
        
}

