import { TestBed } from '@angular/core/testing';

import { BookdetailsService } from './bookdetails.service';

describe('BookdetailsService', () => {
  let service: BookdetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BookdetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
