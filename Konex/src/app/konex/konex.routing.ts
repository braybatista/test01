import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { IndexComponent } from './components/index/index.component';
import { MedicamentosComponent } from './components/medicamentos/medicamentos.component';
import { VentasComponent } from './components/ventas/ventas.component';


export const KonexAppRouting: Routes = [
  {
    path: 'home', component: IndexComponent,
    children: [
      { path: 'medicamentos', component: MedicamentosComponent },
      { path: 'ventas', component: VentasComponent },
      { path: 'home', component: HomeComponent },
    ]
  },
  { path: '**', redirectTo: '/home', pathMatch: 'full'},
  { path: '', redirectTo: '/home', pathMatch: 'full' },
];
