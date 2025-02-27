import { Component, Output } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from "./feature/dashboard/header/header.component";

@Component({
  selector: 'app-root',
  imports: [HeaderComponent, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  standalone: true,
  
})
export class AppComponent {
  //appRack = Rack;
  //title = 'dreamcast-rack-pool';
}
