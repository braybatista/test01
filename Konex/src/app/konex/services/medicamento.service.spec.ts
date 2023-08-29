import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { MedicamentoService } from './medicamento.service';

describe('MedicamentoService', () => {

  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule], 
    providers: [MedicamentoService]
  }));

   it('should be created', () => {
    const service: MedicamentoService = TestBed.get(MedicamentoService);
    expect(service).toBeTruthy();
   });

   it('should have consultarMedicamento function', () => {
    const service: MedicamentoService = TestBed.get(MedicamentoService);
    expect(service.consultarMedicamento).toBeTruthy();
   });
});