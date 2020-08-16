import { TestBed } from '@angular/core/testing';

import { ScientificPublicationService } from './scientific-publication.service';

describe('ScientificPublicationService', () => {
  let service: ScientificPublicationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ScientificPublicationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
