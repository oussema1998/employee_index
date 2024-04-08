import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Employee } from '../entities/employee';
@Injectable({
  providedIn: 'root'
})
export class EmployeeServiceService {

  private baseURL='http://localhost:8085/employee/getAll'
  constructor(private httpClient: HttpClient) { }
  getEmplyeeList(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(`${this.baseURL}`).pipe(
      catchError(error => {
        console.error('Error fetching employee list:', error);
        return throwError(error); // Renvoie l'erreur pour une gestion ultérieure
      })
    );
  }
}
