import { Medicamento } from "./medicamento";

export class Venta {

  id?: number;
  fechaTransaccion: string | Date = '';
  medicamento: Medicamento = new Medicamento();
  cantidad: number = 0;
  valorUnitario: number | null = 0;
  valorTotal: number | null = 0;

  constructor(){
  }
}