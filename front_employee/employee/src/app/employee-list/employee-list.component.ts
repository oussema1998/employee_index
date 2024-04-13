import { Component } from '@angular/core';
import { Employee } from '../entities/employee';
import { EmployeeServiceService } from '../services/employee-service.service';

@Component({
  selector: 'app-employee-list',
  template: `
    <h2> Employee List</h2>
<table class = "table table-striped">
    <thead>
        <tr>
            <th> ID</th>
            <th> Email</th>
            <th> Name</th>
            <th>Actions</th>
           
        </tr>
    </thead>
    <tbody>
        <tr *ngFor = "let employee of employees" >
            <td> {{ employee.id }} </td>
            <td> {{ employee.email }} </td>
            <td> {{ employee.nom }} </td>
            <td> <button [routerLink]="['/details',employee.id]">details</button></td>
            
        </tr>
    </tbody>
</table>
  `,
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent {
employees!: Employee[];
constructor (private empServ:EmployeeServiceService){}
ngOnInit(){
  
this.fillEmployees();
}

private fillEmployees(){this.empServ.getEmplyeeList().subscribe(data =>{this.employees=data;})}
}
