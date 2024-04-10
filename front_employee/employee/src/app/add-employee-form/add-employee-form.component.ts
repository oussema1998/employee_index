import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-employee-form',
  template: `
    <form [formGroup]="registerForm" (ngSubmit)="save()">
  <label> Nom</label>
  <input class="form-control" type="text" placeholder="Nom" formControlName="nom">
  <div *ngIf="formControls.nom.invalid && (formControls.nom.dirty || formControls.nom.touched)" class="alert alert-danger">
    <div *ngIf="formControls.nom.errors.required">Le nom est requis</div>
    <div *ngIf="formControls.nom.errors.maxLength">Le nom ne peut pas dépasser 20 caractères</div>
  </div>
  <label> Email</label>
  <input class="form-control" type="email" placeholder="Email" formControlName="email">
  <div *ngIf="formControls.email.invalid && (formControls.email.dirty || formControls.email.touched)" class="alert alert-danger">
    <div *ngIf="formControls.email.errors.required">L'email est requis</div>
    <div *ngIf="formControls.email.errors.email">L'email doit être au format valide</div>
  </div>
  <input class="btn btn-success" type="submit" value="Save" [disabled]="registerForm.invalid">
</form>

  `,
  styleUrls: ['./add-employee-form.component.css']
})
export class AddEmployeeFormComponent {

}
