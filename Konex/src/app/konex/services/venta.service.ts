import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Venta } from '../models/venta';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  private endpoint = environment.endpointVentaService;

  constructor(public http: HttpClient) {}

  public consultarVentas(fechaInicio: string, fechaFin: string){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("fechaInicio", fechaInicio);
    queryParams = queryParams.append("fechaFin", fechaFin);
    return this.http.get(this.endpoint, {params: queryParams});
  }

  realizarVenta(venta: Venta){
    return this.http.post(this.endpoint, venta);
  }
}
