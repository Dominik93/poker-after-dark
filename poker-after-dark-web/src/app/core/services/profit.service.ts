import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { GetProfitResponse } from 'src/app/shared/models/get-profit-response';
import { GetProfitRequest } from 'src/app/shared/models/get-profit-request';

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
