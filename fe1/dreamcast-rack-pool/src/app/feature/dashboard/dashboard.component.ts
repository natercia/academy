import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RackListComponent } from './rack-list/rack-list.component';

@Component({
  selector: 'app-dashboard',
  imports: [RackListComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})

export class DashboardComponent {

}
