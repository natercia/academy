import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RackListComponent } from './rack-list.component';
import { RacksService } from '../../../shared/services/racks.service';
import { ActivatedRoute } from '@angular/router';
import { Rack } from '../../../shared/models/rack.model';
import { signal } from '@angular/core';

fdescribe('RackListComponent', () => {
  let component: RackListComponent;
  let fixture: ComponentFixture<RackListComponent>;
  let racksMock:Rack[] = []
  let rackService: RacksService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RackListComponent],
            providers: [{provide: RacksService,
              useValue: {loadRacks: jasmine.createSpy("loadRacks"), racks: signal(racksMock)}}, 
              {provide: ActivatedRoute, useValue: {snapshot: {params:{id: 1}}}}]
          })
    .compileComponents();

    rackService = TestBed.inject(RacksService);
    (rackService.loadRacks as jasmine.Spy).and.returnValue([])
    fixture = TestBed.createComponent(RackListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
