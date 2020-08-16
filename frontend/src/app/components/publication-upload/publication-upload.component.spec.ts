import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicationUploadComponent } from './publication-upload.component';

describe('PublicationUploadComponent', () => {
  let component: PublicationUploadComponent;
  let fixture: ComponentFixture<PublicationUploadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PublicationUploadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicationUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
