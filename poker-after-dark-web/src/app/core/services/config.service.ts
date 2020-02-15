import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { GetConfigResponse } from 'src/app/shared/models/get-config-response';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private path: string = environment.baseUrl + "config";

  constructor(private http: HttpClient) { }

  public getConfig() {
    return this.http.get<GetConfigResponse>(this.path);
  }
}
