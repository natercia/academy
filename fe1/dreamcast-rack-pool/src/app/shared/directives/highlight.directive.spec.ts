import { Component } from '@angular/core';
import { HighlightDirective } from './highlight.directive';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';

@Component({
  template:'<div data-test="name" appHighlight></div>',
  imports:[HighlightDirective]
})
export class HostComponent{}

fdescribe('HighlightDirective', () => {
  let component: HostComponent;
  let fixture: ComponentFixture<HostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[HighlightDirective, HostComponent]
    }).compileComponents()

    fixture = TestBed.createComponent(HostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  

  it('should create an instance', () => {
    const div = fixture.debugElement.query(By.css('[data-test="name"]')).nativeElement
    const mouseEvent = new MouseEvent('mouseover')
    div.dispatchEvent(mouseEvent)
    fixture.detectChanges();
    expect(div.style.boxShadow).toBe("rgb(151, 183, 192) 0px 0px 30px 2px")
    //const directive = new HighlightDirective(div);
    //expect(directive).toBeTruthy();
  });

});
