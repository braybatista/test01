import { Routes } from '@angular/router';
import { KonexAppRouting } from './konex/konex.routing';

export const AppRoutingModule: Routes = [
	...KonexAppRouting,
	{ path: '**', redirectTo: '/home', pathMatch: 'full' },
	{ path: '', redirectTo: '/home', pathMatch: 'full' }
];