import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Medicamento } from '../models/medicamento';

@Injectable({
  providedIn: 'root'
})
export class MedicamentoService {

  private endpoint = environment.endpointMedicamentoService;

  constructor(public http: HttpClient) {}

  public consultarMedicamentos(){
    return this.http.get(this.endpoint);
  }

  public consultarMedicamento(medicamento: Medicamento){
    return this.http.get(this.endpoint+"/"+medicamento.id);
  }

  public crearMedicamento(medicamento: Medicamento){
    return this.http.post(this.endpoint, medicamento);
  }

  public enrolarMedicamento(medicamento: Medicamento){
    return this.http.put(this.endpoint, medicamento);
  }

  public eliminarMedicamento(medicamento: Medicamento){
    return this.http.delete(this.endpoint+"/"+medicamento.id);
  }
}
