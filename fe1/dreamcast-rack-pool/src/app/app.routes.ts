import { Routes } from '@angular/router';
import { DashboardComponent } from './feature/dashboard/dashboard.component';
import { RackDetailComponent } from './feature/dashboard/rack-detail/rack-detail.component';

export const routes: Routes = [{
    path: "dashboard", component: DashboardComponent
},
{
    path: "racks/:serialNumber", component: RackDetailComponent
},
{
    path: "**", redirectTo: "dashboard"
}];
