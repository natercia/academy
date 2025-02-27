import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RackDetailComponent } from './rack-detail.component';

describe('RackDetailComponent', () => {
  let component: RackDetailComponent;
  let fixture: ComponentFixture<RackDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RackDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RackDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
