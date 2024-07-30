import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipesTreeComponent } from './recipes-tree.component';

describe('RecipesTreeComponent', () => {
  let component: RecipesTreeComponent;
  let fixture: ComponentFixture<RecipesTreeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecipesTreeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecipesTreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
