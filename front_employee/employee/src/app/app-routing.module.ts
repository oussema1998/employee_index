import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { AddEmployeeFormComponent } from './add-employee-form/add-employee-form.component';
import { DetailsEmployeeComponent } from './details-employee/details-employee.component';

const routes: Routes = [{path:"list",component:EmployeeListComponent},{path:"",redirectTo:"list",pathMatch:"full"},
{path:"add",component:AddEmployeeFormComponent},
{path:"details/:id",component:DetailsEmployeeComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
