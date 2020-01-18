import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { LoginRequest } from './model/login-request';
import { LoginResponse } from './model/login-response';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private path: string = environment.baseUrl + "login";

  constructor(private http: HttpClient) { }

  public login(request: LoginRequest) {
    return this.http.post<LoginResponse>(this.path, request);
  }

}

