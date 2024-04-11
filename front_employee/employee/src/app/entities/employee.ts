export class Employee {
    id! :number;
    nom!: String;
    email!:String;
    constructor(nom: string, email: string) {
        this.nom = nom;
        this.email = email;
      }
}
