import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { AddEmployeeFormComponent } from './add-employee-form/add-employee-form.component';

const routes: Routes = [{path:"list",component:EmployeeListComponent},{path:"",redirectTo:"list",pathMatch:"full"},
{path:"add",component:AddEmployeeFormComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
