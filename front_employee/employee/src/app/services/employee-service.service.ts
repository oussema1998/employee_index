import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Employee } from '../entities/employee';
@Injectable({
  providedIn: 'root'
})
export class EmployeeServiceService {

  private baseURL='http://localhost:8085/employee'

  constructor(private httpClient: HttpClient) { }

  getEmplyeeList():Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.baseURL}/getAll`).pipe(
      catchError(error => {
        console.error('Error fetching employee list:', error);
        return throwError(error); // Renvoie l'erreur pour une gestion ultérieure
      })
    );
  }

  AddEmployee(e:Employee){
    return this.httpClient.post<Employee>(`${this.baseURL}/add`,e)
  }

  getEmployeeById(id:number){
    return this.httpClient.get<Employee>(`${this.baseURL}/get/`+id)
  }


  deleteEmployeeById(id:number){
    return this.httpClient.delete<Employee>(`${this.baseURL}/delete/`+id)
  }

  updateEmployee(employee: Employee) {
    return this.httpClient.put<Employee>(`${this.baseURL}/update`, employee);}

}
