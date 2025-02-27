import { inject, Injectable, signal } from '@angular/core';
import { Rack } from '../models/rack.model';
import { HttpService } from './http.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
}
)
export class RacksService {
  constructor(){
    this.loadRacks()
  }

httpService = inject(HttpService)
  racks = signal<Rack[]>([]);
  currentRack = signal<Rack>({});

  /*
  private racks: Rack[] = [
    {id: 1, serialNumber: "PTB00524", status: "Available", rackAsset:"ZBE RMT 01LL NIV", deleted: false } as Rack,
  ];
  */

  addRack(r:Rack){
    //this.racks.push(r)
    let newRack:Rack = {}
    this.httpService.post<Rack>('workstation/racks/', r).subscribe(rack => {newRack = rack;
      console.log("Rack:" + newRack.id + " created.")
      this.racks.update(racks => ([...racks, newRack]))
    })
    
  }

  unavailableRack(id:Number){
    this.httpService.getOne<Rack>('workstation/racks/' + id).subscribe(r => {
      r.status = "UNAVAILABLE";
      console.log(r)
      this.httpService.put<Rack>('workstation/racks/' + id, r).subscribe();
      console.log("Rack:" + id + " made unavailable.")
      this.racks.set(this.racks().filter( x => x.id!==id))
      this.racks.update(racks => ([...racks, r]))
    } )
    
  }

  availableRack(id:Number){
    this.httpService.getOne<Rack>('workstation/racks/' + id).subscribe(r => {
      r.status = "AVAILABLE";
      console.log(r)
      this.httpService.put<Rack>('workstation/racks/' + id, r).subscribe();
      console.log("Rack:" + id + " made available.")
      this.racks.set(this.racks().filter( x => x.id!==id))
      this.racks.update(racks => ([...racks, r]))
    } )
    
  }

  deleteRack(id:Number){
    this.httpService.delete<Rack>('workstation/racks/' + id).subscribe()
    this.racks.set(this.racks().filter( x => x.id!==id))
    console.log("Rack:" + id + " deleted.")
  }

  /*
  getRacks():Rack[]{
    console.log("arr" + this.racks())
    return this.racks();
  }
  */

  loadRacks(){
    this.httpService.get<Rack[]>('workstation/racks').subscribe(racks => {
      console.log("Got Racks" + racks)
      this.racks.set(racks);
    })
  }

  getOne(id:number):Observable<Rack> {
    return this.httpService.getOne<Rack>('workstation/racks/' + id)
    
  }
}
