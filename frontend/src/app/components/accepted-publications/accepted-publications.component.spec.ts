import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptedPublicationsComponent } from './accepted-publications.component';

describe('AcceptedPublicationsComponent', () => {
  let component: AcceptedPublicationsComponent;
  let fixture: ComponentFixture<AcceptedPublicationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AcceptedPublicationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptedPublicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
