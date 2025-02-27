import { Component, EventEmitter, inject, Output } from '@angular/core';
import { DsButtonModule } from '@bmw-ds/components';
import { ReactiveFormsModule, FormGroup, FormControl, FormsModule, Validators  } from '@angular/forms'
import { NgIf} from '@angular/common';
import { Rack } from '../../../shared/models/rack.model';
import { DatePipe } from '@angular/common';
import { CounterPipe } from '../../../shared/pipes/counter.pipe';
import { RacksService } from '../../../shared/services/racks.service';

@Component({
  selector: 'app-sidebar',
  imports: [DsButtonModule, NgIf, ReactiveFormsModule, FormsModule, CounterPipe],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {
  form: FormGroup = new FormGroup({
    serial: new FormControl('', [Validators.required]),
    assettag: new FormControl(''),
    location: new FormControl(''),
    teamID: new FormControl(''),
});

  //@Output() onCreate = new EventEmitter<Rack>;
  //@Output() closeModal = new EventEmitter<Number>;


  newRack?:Rack;
  private rackService = inject(RacksService);

  onSubmit(){
    console.log(this.form.value)
    let date = Date.now()
    this.newRack = {
      serialNumber: this.form.value.serial!, 
      teamId: this.form.value.teamID!,
      //rackAsset: this.form.value.assettag!,
      defaultLocation: this.form.value.location!, 
      status: "AVAILABLE", 
      }
    this.rackService.addRack(this.newRack)
    //this.onCreate.emit(this.newRack);
    //this.closeModal.emit(1);
  }
}
