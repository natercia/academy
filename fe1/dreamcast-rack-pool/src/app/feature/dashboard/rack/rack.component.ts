import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { Rack } from '../../../shared/models/rack.model';
import { DsButtonModule } from '@bmw-ds/components';
import { RouterLink } from '@angular/router';
import { DatePipe, LowerCasePipe } from '@angular/common';
import { HighlightDirective } from '../../../shared/directives/highlight.directive';
import { RacksService } from '../../../shared/services/racks.service';

@Component({
  selector: 'app-rack',
  imports: [DsButtonModule, RouterLink, DatePipe, HighlightDirective, LowerCasePipe],
  templateUrl: './rack.component.html',
  styleUrl: './rack.component.scss'
})
export class RackComponent {

  @Input() item?: Rack;
  
  private rackService = inject(RacksService);

  

  //@Output() onBooking = new EventEmitter<Number>;
  //@Output() onDelete = new EventEmitter<Number>;

  onReserveClick(){
    this.rackService.unavailableRack(this.item?.id!);
    //this.onBooking.emit(this.item?.id);
  }

  onUnReserveClick(){
    this.rackService.availableRack(this.item?.id!);
    //this.onBooking.emit(this.item?.id);
  }

  onDeleteClick(){
    if (confirm("Are you sure you want to delete this rack?")) { 
      this.rackService.deleteRack(this.item?.id!);
      //this.onDelete.emit(this.item?.id);
    }
  }
}
