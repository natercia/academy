import { Component, inject } from '@angular/core';
import { Rack } from '../../../shared/models/rack.model';
import { RackComponent } from "../rack/rack.component";
import { DsModalModule } from '@bmw-ds/components';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { HighlightDirective } from '../../../shared/directives/highlight.directive';
import { RacksService } from '../../../shared/services/racks.service';
import { OrderPipe } from '../../../shared/pipes/order.pipe';

@Component({
  selector: 'app-rack-list',
  imports: [RackComponent, DsModalModule, SidebarComponent, OrderPipe],
  templateUrl: './rack-list.component.html',
  styleUrl: './rack-list.component.scss'
})
export class RackListComponent {
  private rackService = inject(RacksService);
  racks = this.rackService.racks;  

  onModalClose(id:Event){

  }
}
