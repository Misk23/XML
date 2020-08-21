import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewPublicationsComponent } from './review-publications.component';

describe('ReviewPublicationsComponent', () => {
  let component: ReviewPublicationsComponent;
  let fixture: ComponentFixture<ReviewPublicationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewPublicationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewPublicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
