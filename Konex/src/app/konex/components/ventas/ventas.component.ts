import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Venta } from '../../models/venta';
import { VentaService } from '../../services/venta.service';

declare var $: any;
@Component({
  selector: 'ventas',
  templateUrl: './ventas.component.html',
  styleUrls: ['./ventas.component.css']
})
export class VentasComponent implements OnInit {

  public fechaInicio: string = '';
  public fechaFin: string = ''

  public numberRows: number = 10;
  public numberPage: number = 1;
  public numRowsIni: number = 0;
  public numRowsEnd: number = this.numberRows;
  public pages:Array<number> = [];
  public ventas: Venta[] = [];

  constructor(private ventaService: VentaService) {

  }

  ngOnInit(): void {
  }

  consultarVentas(){
    this.ventaService.consultarVentas(this.fechaInicio, this.fechaFin).subscribe({
      next: (response: any)=>{
        console.log(response)
        this.ventas = response.bodyResponse;
        this.calculatePagesNumber();
      },
      complete: ()=>{
          
      },
      error: (error)=>{
        console.log(error)
      }
    });
  }

  calculatePagesNumber(){
    this.pages = [];
    var dataLength = this.ventas.length;
    var numberRows = this.numberRows;
    var pageNum = Math.ceil(dataLength/numberRows)

    for(var i=1; i<=pageNum; i++){
      this.pages.push(i);
    }
  }

  changeRowsSelect(){
    this.calculatePagesNumber();
    this.numRowsIni = 0;
    this.numRowsEnd = this.numberRows;
  }

  changePageSelect(){
    if(this.numberPage == 1){
      this.changeRowsSelect();
    } else{
      this.numRowsIni = parseInt(this.numberRows+"");
      this.numRowsEnd = this.numberRows * 2;
    }
  }

  getActualDate(){
    const now = new Date();
    const offsetMs = now.getTimezoneOffset() * 60 * 1000;
    const dateLocal = new Date(now.getTime() - offsetMs);
    return dateLocal.toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");
  }

}
