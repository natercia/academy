import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable, InputOptions } from '@angular/core';
import { Observable, ObservedValueOf } from 'rxjs';
import { Rack } from '../models/rack.model';


@Injectable({
  providedIn: 'root'
})
export class HttpService {

  readonly urlRO = 'http://localhost:8045/'

  constructor(private httpClient: HttpClient) { }

  public get<T>(path:string): Observable<T>{
    console.log("Getting Racks")
    return this.httpClient.get<T>(this.urlRO + path);
  }

  public getOne<T>(path:string): Observable<T>{
    return this.httpClient.get<T>(this.urlRO + path)
  }

  public delete<T>(path:string): Observable<T> {
    console.log("Deleting Rack")
    return this.httpClient.delete<T>(this.urlRO + path);
  }

  public post<T>(path:string, rack:Rack): Observable<T>{
    console.log("Posting Rack")
    console.log(rack)
    return this.httpClient.post<T>(this.urlRO + path, rack);
  }

  public put<T>(path:string, rack:Rack): Observable<T>{
    console.log("Updating Rack")
    return this.httpClient.put<T>(this.urlRO + path, rack);
  }
}
