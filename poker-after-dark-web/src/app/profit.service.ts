import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { GetProfitRequest } from './model/get-profit-request';
import { GetProfitResponse } from './model/get-profit-response';

@Injectable({
  providedIn: 'root'
})
export class ProfitService {

  private path: string = environment.baseUrl + "profit";

  constructor(private http: HttpClient) { }

  getProfit(request: GetProfitRequest) {
    return this.http.post<GetProfitResponse>(this.path, request);
  }

}
