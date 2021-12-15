/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sekolah;

import java.sql.Statement;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Sntn_Prnwr
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TableView<data> tvsekolah;
    @FXML
    private TableColumn<data,Integer> colnik;
    @FXML
    private TableColumn<data,String> colnama;
    @FXML
    private TableColumn<data,String> coljenis_kelamin;
    @FXML
    private TableColumn<data,String> coljabatan;
    @FXML
    private TableColumn<data,String> colstatus;
    @FXML
    private TableColumn<data,Integer> colno_telp;
    @FXML
    private TableColumn<data,String> colalamat;
    
    
    @FXML
    private Button bttambah;
    @FXML
    private Button btupdate;
    @FXML
    private Button bthapus;
    @FXML
    private TextField tfnik;
    @FXML
    private TextField tfnama;
    @FXML
    private TextField tfjenis;
    @FXML
    private TextField tfjabatan;
    @FXML
    private TextField tfstatus;
    @FXML
    private TextField tftlp;
    @FXML
    private TextField tfalamat;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == bttambah) {
            InsertData();
        }else if (event.getSource() == btupdate){
        updateData(); 
    }
        else if (event.getSource()== bthapus){
            hapusData();
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata(); 
    }    
    public Connection getConnection(){
         Connection conn;
         try{
              conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/sekolah","root","");
              return conn;
         }catch (Exception ex){
             System.out.println("eror: " + ex.getMessage());
            return null;
         }
    }
    
    public ObservableList<data> getdataList(){
         ObservableList<data> datalist = FXCollections.observableArrayList();
         Connection conn = getConnection();
         String querry = "SELECT * FROM users";
         Statement st;
         ResultSet rs;
         try{
        st =  conn.createStatement();
        rs = st.executeQuery(querry);
        data users;
        while (rs.next()){
            users = new data(rs.getInt("nik"),rs.getString("nama"),rs.getString("jenis_kelamin"),rs.getString("jabatan"),rs.getString("status"),rs.getInt("no_telp"),rs.getString("alamat"));
            datalist.add(users);
        }
         }catch (Exception ex){ 
             ex.printStackTrace();
         }
         return datalist;
    }
    public void showdata(){
         ObservableList<data> list = getdataList();
         
         colnik.setCellValueFactory(new PropertyValueFactory<data,Integer>("nik"));
         colnama.setCellValueFactory(new PropertyValueFactory<data,String>("nama"));
         coljenis_kelamin.setCellValueFactory(new PropertyValueFactory<data,String>("jenis_kelamin"));
         coljabatan.setCellValueFactory(new PropertyValueFactory<data,String>("jabatan"));
         colstatus.setCellValueFactory(new PropertyValueFactory<data,String>("status"));
         colno_telp.setCellValueFactory(new PropertyValueFactory<data,Integer>("no_telp"));
         colalamat.setCellValueFactory(new PropertyValueFactory<data,String>("alamat"));
         
         tvsekolah.setItems(list);
    }
    private void InsertData(){
        String query  = "INSERT INTO users VALUES(" + tfnik.getText()+",'"+tfnama.getText()+"','"+tfjenis.getText()+"','"+tfjabatan.getText()+"','"+tfstatus.getText()+"',"+tftlp.getText()+",'"+tfalamat.getText()+"')";
        executeQuery(query);
        showdata();
    }
    
    private void updateData() {
        String query = "UPDATE users SET status = '"+tfstatus.getText()+ "',jabatan='"+tfjabatan.getText()+"',no_telp = "+tftlp.getText()+",nama='"+tfnama.getText()+"',jenis_kelamin='"+tfjenis.getText()+"',alamat='"+tfalamat.getText()+"'WHERE nik = "+tfnik.getText()+"";
        executeQuery(query);
        showdata();
    }
    
    private void hapusData(){
        String query ="DELETE FROM users WHERE nik="+tfnik.getText()+"";
        executeQuery(query);
        showdata();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
              ex.printStackTrace();
        }
     }

    @FXML
    private void handleMouseAction(MouseEvent event) {
    data klik = tvsekolah.getSelectionModel().getSelectedItem();
    tfnik.setText(""+klik.getNik());
    tfnama.setText(klik.getNama());
    tfjenis.setText(klik.getJenis_kelamin());
    tfjabatan.setText(klik.getJabatan());
    tfstatus.setText(klik.getStatus());
    tftlp.setText(""+klik.getNo_telp());
    tfalamat.setText(klik.getAlamat());
    }

}