import { Pipe, PipeTransform } from '@angular/core';
import { Rack } from '../models/rack.model';

@Pipe({
  name: 'order',
  pure: true
})
export class OrderPipe implements PipeTransform {

  transform(value: Rack[] , by: keyof Rack):Rack[] {

  return value.sort((a:Rack,b:Rack) => this.compare(a, b, by));
  }

  compare(a:Rack, b:Rack, by: keyof Rack): number {
    const a1 = a[by];
    const b1 = b[by];
    if (typeof a1 === 'string' && typeof b1 === "string"){
      return a1?.localeCompare(b1);
    } 
    else if(a1 && b1 && typeof a1 === "number" && typeof b1 === "number") {
      return a1 - b1
    }
    return 0;
  }
}
