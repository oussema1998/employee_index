import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeServiceService } from '../services/employee-service.service';
import { Employee } from '../entities/employee';

@Component({
  selector: 'app-edit-employee-form',
  template: `
    <form [formGroup]="editForm" (ngSubmit)="update()">
      <label> Nom</label>
      <input class="form-control" type="text" placeholder="Nom" formControlName="nomControl">
      <div *ngIf="editForm.get('nomControl')?.invalid && (editForm.get('nomControl')?.dirty || editForm.get('nomControl')?.touched)" class="alert alert-danger">
        <div *ngIf="editForm.get('nomControl')?.hasError('required')">nom required</div>
        <div *ngIf="editForm.get('nomControl')?.hasError('maxlength')">nom trop long</div>
      </div>
      <label> Email</label>
      <input class="form-control" type="email" placeholder="Email" formControlName="emailControl">
      <div *ngIf="editForm.get('emailControl')?.invalid && (editForm.get('emailControl')?.dirty || editForm.get('emailControl')?.touched)" class="alert alert-danger">
        <div *ngIf="editForm.get('emailControl')?.hasError('required')">L'email est requis</div>
        <div *ngIf="editForm.get('emailControl')?.hasError('pattern')">Invalid Email</div>
      </div>
      <input class="btn btn-success" type="submit" value="Update" [disabled]="editForm.invalid">
    </form>
  `,
  styleUrls: ['./edit-employee-form.component.css']
})
export class EditEmployeeFormComponent implements OnInit {
  employee!: Employee;
  editForm!: FormGroup;

  constructor(
    private es: EmployeeServiceService,
    private route: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.editForm = new FormGroup({
      nomControl: new FormControl('', [Validators.required, Validators.maxLength(20)]),
      emailControl: new FormControl('', [
        Validators.required,
        Validators.pattern('[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}')
      ])
    });

    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id) {
      this.es.getEmployeeById(Number(id)).subscribe(data => {
        this.employee = data;
        this.editForm.setValue({
          nomControl: this.employee.nom,
          emailControl: this.employee.email
        });
      });
    }
  }

  update() {
    const nomValue = this.editForm.get('nomControl')?.value;
    const emailValue = this.editForm.get('emailControl')?.value;

    this.employee.nom = nomValue;
    this.employee.email = emailValue;

    this.es.updateEmployee(this.employee).subscribe(() => {
      this.route.navigateByUrl('/list');
    });
  }
}
