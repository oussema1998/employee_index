import { Component } from '@angular/core';
import { Employee } from '../entities/employee';
import { EmployeeServiceService } from '../services/employee-service.service';
import { ActivatedRoute, Router } from '@angular/router';

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
            <td> <button class="btn btn-info" [routerLink]="['/details',employee.id]">details</button >
            <button class="btn btn-danger" (click)="supprimer(employee.id)">Delete</button></td>
          
            
        </tr>
    </tbody>
</table>
  `,
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent {
employees!: Employee[];
constructor (private empServ:EmployeeServiceService, private router: Router, private route: ActivatedRoute){}
ngOnInit(){
  
this.fillEmployees();
}

 supprimer(id: number){
    this.empServ.deleteEmployeeById(id).subscribe(()=> {
        // Suppression réussie, recharger le composant actuel
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate(['.'], { relativeTo: this.route });
      })
   
}

private fillEmployees(){this.empServ.getEmplyeeList().subscribe(data =>{this.employees=data;})}
}
