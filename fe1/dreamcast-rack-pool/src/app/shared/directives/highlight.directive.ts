import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  standalone: true,
  selector: '[appHighlight]'
})
export class HighlightDirective {

  private el:ElementRef

  constructor(el: ElementRef) { 
    this.el = el;
  }

  @HostListener('mouseover') onMouseOn() {
    this.el.nativeElement.style.boxShadow= "0px 0px 30px 2px rgb(151, 183, 192)";
  }
  @HostListener('mouseout') onMouseLeave() {
    this.el.nativeElement.style.boxShadow= "0px 0px 30px 2px #cfcfcf";
  
  }

}
