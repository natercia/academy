import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'counter',
  pure: true
})
export class CounterPipe implements PipeTransform {

  transform(value: string , max: number) {
    return `${value ? value.length : 0}/${max}`;
  }

}
