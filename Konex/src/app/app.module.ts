import { HttpClientModule } from '@angular/common/http';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { KonexAppModule } from './konex/konex.module';

@NgModule({
	declarations: [
		AppComponent
	],
	imports: [
		RouterModule.forRoot(AppRoutingModule),
		HttpClientModule,
		KonexAppModule,
	],
	providers: [],
	bootstrap: [AppComponent],
	schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }

