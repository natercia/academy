import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RackComponent } from './rack.component';
import { RacksService } from '../../../shared/services/racks.service';
import { ActivatedRoute } from '@angular/router';
import { By } from '@angular/platform-browser';

fdescribe('RackComponent', () => {
  let component: RackComponent;
  let fixture: ComponentFixture<RackComponent>;
  let rackService: RacksService;
  //let activatedRoute: ActivatedRoute;

  beforeEach(async () => {
    //rackService = jasmine.createSpyObj('RackService', ['unavailableRack', 'availableRack', 'deleteRack'])
    await TestBed.configureTestingModule({
      imports: [RackComponent],
      providers: [{provide: RacksService,
        useValue: jasmine.createSpyObj('RackService', ['unavailableRack', 'availableRack', 'deleteRack'])}, 
        {provide: ActivatedRoute, useValue: {snapshot: {params:{id: 1}}}}]
    },)
    .compileComponents();

    fixture = TestBed.createComponent(RackComponent);
    rackService = TestBed.inject(RacksService);
    //activatedRoute = TestBed.inject(ActivatedRoute)
    component = fixture.componentInstance;
    component.item = {id: 1}
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  

  it('should call delete with id when component clicked', () => {
    let expectedID = 1
    component.onDeleteClick()
    //expect(component.item).toBeFalsy()
    expect(rackService.deleteRack).toHaveBeenCalledWith(expectedID);
  });


  it('should call delete with idwhen html button clicked', () => {
    let expectedID = 1
    const delButton = fixture.debugElement.query(By.css('[data-test="rack.delete"]')).nativeElement
    delButton.click()
    expect(rackService.deleteRack).toHaveBeenCalledWith(expectedID);
  });
});
