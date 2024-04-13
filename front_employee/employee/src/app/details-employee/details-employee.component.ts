import { Component } from '@angular/core';
import { EmployeeServiceService } from '../services/employee-service.service';
import { Employee } from '../entities/employee';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-details-employee',
  template: `
    <h1>
      details employee {{id}}
</h1>
    <h3>id</h3>
    {{p.id}}<br>
    <h3>nom</h3>
    {{p.nom}}
    <h3>email</h3>
    {{p.email}}
  `,
  styleUrls: ['./details-employee.component.css']
})
export class DetailsEmployeeComponent {
p!:Employee
id!:number
  constructor(private es:EmployeeServiceService,private route:ActivatedRoute){}

  ngOnInit(){

  this.id=this.route.snapshot.params['id']

 this.es.getEmployeeById(this.id).subscribe((data)=>this.p=data)
  }

}
