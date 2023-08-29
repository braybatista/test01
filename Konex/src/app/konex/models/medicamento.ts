export class Medicamento {

  id?: number;
  nombre: string = '';
  laboratorio: string = '';
  fechaFabricacion: string | Date = '';
  fechaVencimiento: string | Date = '';
  stock: number | null = null;
  valorUnitario: number | null = null;

  constructor(){
  }
}