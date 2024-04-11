package Entities;
import com.google.gson.annotations.SerializedName;

public class Employee {
    //@SerializedName("id")
    private int id;
    //@SerializedName("email")
    private String email;

    //@SerializedName("nom")
    private String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Employee(int id,  String nom,String email) {
        this.id = id;
        this.email = email;
        this.nom = nom;
    }

    public Employee(String nom,String email) {
        this.email = email;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
