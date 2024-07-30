import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeControlComponent } from './recipe-control.component';

describe('RecipeControlComponent', () => {
  let component: RecipeControlComponent;
  let fixture: ComponentFixture<RecipeControlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecipeControlComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecipeControlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
