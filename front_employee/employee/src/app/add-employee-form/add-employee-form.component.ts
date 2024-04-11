import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EmployeeServiceService } from '../services/employee-service.service';
import { Router } from '@angular/router';
import { Employee } from '../entities/employee';

@Component({
  selector: 'app-add-employee-form',
  template: `
    <form [formGroup]="this.addForm" (ngSubmit)="save()">
  <label> Nom</label>
  <input class="form-control" type="text" placeholder="Nom" formControlName="nomControl">
  <div *ngIf="this.addForm.get('nomControl')?.invalid && (this.addForm.get('nomControl')?.dirty || this.addForm.get('nomControl')?.touched)" class="alert alert-danger">
    <div *ngIf="this.addForm.get('nomControl')?.hasError('required')">nom required</div>
    <div *ngIf="this.addForm.get('nomControl')?.hasError('maxlength')">nom trop long</div>
  </div>
  <label> Email</label>
  <input class="form-control" type="email" placeholder="Email" formControlName="emailControl">
  <div *ngIf="this.addForm.get('emailControl')?.invalid && (this.addForm.get('emailControl')?.dirty || this.addForm.get('emailControl')?.touched)" class="alert alert-danger">
    <div *ngIf="this.addForm.get('emailControl')?.hasError('required')">L'email est requis</div>
    <div *ngIf="this.addForm.get('emailControl')?.hasError('pattern')">Invalid Email</div>
  </div>
  <input class="btn btn-success" type="submit" value="Save"(click)="save()" [disabled]="this.addForm.invalid">
</form>

  `,
  styleUrls: ['./add-employee-form.component.css']
})
export class AddEmployeeFormComponent {
  employee!: Employee
  addForm!: FormGroup
  constructor(private es:EmployeeServiceService,private route:Router){}
  ngOnInit(){
this.addForm= new FormGroup({
  nomControl: new FormControl('',[Validators.required,Validators.maxLength(20)]),
emailControl:new FormControl('',[Validators.required,Validators.pattern('[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}')])

})

  }

  save() {
     const nomValue = this.addForm.get('nomControl')?.value;
     const emailValue = this.addForm.get('emailControl')?.value;


     this.employee = new Employee( nomValue, emailValue );
     this.es.AddEmployee(this.employee).subscribe()
    //console.log('Nouvel employé :', this.employee);
     this.route.navigateByUrl('/list')


    
    }


}
