import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Medicamento } from '../../models/medicamento';
import { MedicamentoService } from '../../services/medicamento.service';
import { DateFormatPipe } from 'src/app/filter/date-format.pipe';
import { Venta } from '../../models/venta';
import { VentaService } from '../../services/venta.service';

declare var $: any;

@Component({
  selector: 'medicamentos',
  templateUrl: './medicamentos.component.html',
  styleUrls: ['./medicamentos.component.css']
})
export class MedicamentosComponent implements OnInit {

  public selectedMedicamento: Medicamento = new Medicamento();
  public venta: Venta = new Venta();
  public filterargs = {
    nombre: '',
    laboratorio: '',
    fechaFabricacion: '',
    fechaVencimiento: '',
    stock: null,
    valorUnitario: null
  }
  public numberRows: number = 10;
  public numberPage: number = 1;
  public numRowsIni: number = 0;
  public numRowsEnd: number = this.numberRows;
  public pages:Array<number> = [];
  public medicamentos: Medicamento[] = [];

  public modalCreateForm: FormGroup;
  public modalEnroleForm: FormGroup;

  constructor(
    private medicamentoService: MedicamentoService,
    private ventaService: VentaService,
    private formatDatePipe: DateFormatPipe
  ) {
    this.modalCreateForm = new FormGroup({
      nombre: new FormControl([ '', [Validators.required, Validators.minLength(5), Validators.maxLength(45) ] ]),
      laboratorio: new FormControl([ '', [Validators.required, Validators.minLength(5), Validators.maxLength(45) ] ]),
      fechaFabricacion: new FormControl(['', [Validators.required]]),
      fechaVencimiento: new FormControl(['', [Validators.required]]),
      stock: new FormControl([ '', [Validators.required, Validators.pattern(/^-?(0|[1-9]\d*)?$/), Validators.minLength(0), Validators.maxLength(10) ] ]),
      valorUnitario: new FormControl([ '',[Validators.required, Validators.pattern(/^-?[0-9]{1,10}(\.\d{0,2})?$/), Validators.minLength(3), Validators.maxLength(12) ] ]),

    });

    this.modalEnroleForm = new FormGroup({
      cantidad: new FormControl([ '', [Validators.required, Validators.pattern(/^-?(0|[1-9]\d*)?$/), Validators.minLength(0), Validators.maxLength(10) ] ]),
    });

    this.consultarMedicamentos();
  }

  ngOnInit(): void {
    $('.modal').modal({dismissible: false});
    $('.tooltipped').tooltip({delay: 20});
    $('.datepicker').datepicker({format: 'yyyy-mm-dd hh:mm:ss'});
  }

  consultarMedicamentos(){
    this.medicamentoService.consultarMedicamentos().subscribe({
      next: (response: any)=>{
        console.log(response)
        this.medicamentos = response.bodyResponse;
        this.calculatePagesNumber();
      },
      complete: ()=>{
          
      },
      error: (error)=>{
        console.log(error)
      }
    });
  }
  
  /* agregar o editar Medicamento */
  mergeMedicamento(medicamento: Medicamento){
    if(confirm('Está seguro que quiere ' + (medicamento.id ? 'editar' : 'agregar') + ' el medicamento ' + medicamento.nombre)){
      this.medicamentoService.crearMedicamento(this.selectedMedicamento).subscribe({
        next: (response: any) => {
          console.log(response)
          if(response.header.statusCode !== 200){
            alert("ha ocurrido un errror al tratar de crear el medicamento")
          } else{
            alert( (medicamento.id ? 'Edición' : 'Creación') + " Exitosa!!!")
          }
        },
        complete: () => {
          this.consultarMedicamentos();
          this.limpiarMedicamento();
        },
        error: (error) => {
          console.log(error);
          alert("Error: " + error.header.statusCode + " - " + error.header.statusMessage);
          this.limpiarMedicamento();
        }
      });
    }
  }
  
  /* eliminar Medicamento */
  eliminarMedicamento(medicamento: Medicamento){
    if(confirm('Está seguro que quiere Eliminar la medicamento?')){
      this.medicamentoService.eliminarMedicamento(medicamento).subscribe({
        next: (response: any) => {
          console.log(response)
          if(response.header.statusCode !== 200){
            alert("ha ocurrido un errror al tratar de eliminar el medicamento")
          } else{
            alert("Eliminacón Exitoso!!!")
          }
        },
        complete: () => {
          this.consultarMedicamentos();
          this.limpiarMedicamento();
        },
        error: (error) => {
          console.log(error)
          alert("Error: " + error.header.statusCode + " - " + error.header.statusMessage);
          this.limpiarMedicamento();
        }
      });
    }
  }

  /* realizar venta*/
  realizarVenta(medicamento: Medicamento, venta: Venta){
    venta.cantidad = parseInt(venta.cantidad+"");
    venta.medicamento = medicamento;
    venta.medicamento.fechaVencimiento = new Date(venta.medicamento.fechaVencimiento);
    venta.medicamento.fechaFabricacion = new Date(venta.medicamento.fechaFabricacion);
    venta.fechaTransaccion = new Date();
    venta.valorUnitario = venta.medicamento.valorUnitario;
    venta.valorTotal = venta.cantidad * (venta.valorUnitario ? venta.valorUnitario : 0);

    console.log(venta);

    this.ventaService.realizarVenta(venta).subscribe({
      next: (response: any) => {
        console.log(response)
        if(response.header.statusCode !== 200){
          alert("ha ocurrido un errror al tratar de realizar venta")
        } else{
          alert("Venta Exitosa!!!")
        }
      },
      complete: () => {
        this.consultarMedicamentos();
        this.limpiarMedicamento();
      },
      error: (error) => {
        console.log(error);
        alert("Error: " + error.header.statusCode + " - " + error.header.statusMessage);
        this.limpiarMedicamento();
      }
    });
  }

  limpiarMedicamento(){
    this.selectedMedicamento = new Medicamento();
    this.venta = new Venta();
  }
  
  cargarMedicamento(medicamento: Medicamento){
    this.selectedMedicamento = medicamento;
    this.selectedMedicamento.fechaFabricacion = this.formatDatePipe.transform(medicamento.fechaFabricacion);
    this.selectedMedicamento.fechaVencimiento = this.formatDatePipe.transform(medicamento.fechaVencimiento);
  }

  calculatePagesNumber(){
    this.pages = [];
    let dataLength = this.medicamentos.length;
    let numberRows = this.numberRows;
    let pageNum = Math.ceil(dataLength/numberRows)
    for(let i=1; i<=pageNum; i++){
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
}
