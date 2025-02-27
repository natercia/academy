import { Component, inject, Signal, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RacksService } from '../../../shared/services/racks.service';
import { DatePipe } from '@angular/common';
import { Rack } from '../../../shared/models/rack.model';
import {Location} from '@angular/common';
import { DsButtonModule } from '@bmw-ds/components';


@Component({
  selector: 'app-rack-detail',
  imports: [DatePipe, DsButtonModule],
  templateUrl: './rack-detail.component.html',
  styleUrl: './rack-detail.component.scss'
})
export class RackDetailComponent {
  route = inject(ActivatedRoute)
  private rackService = inject(RacksService);
  id: number= this.route.snapshot.params['serialNumber']
  item = signal<Rack>({});

  constructor(private _location: Location) 
  {}

  ngOnInit(){
    this.rackService.getOne(this.id).subscribe(rack => {this.item.set(rack)})
  }

  backClicked() {
    this._location.back();
  }
  

}
