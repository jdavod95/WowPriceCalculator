import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PagingToolComponent } from './paging-tool.component';

describe('PagingToolComponent', () => {
  let component: PagingToolComponent;
  let fixture: ComponentFixture<PagingToolComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PagingToolComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PagingToolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
