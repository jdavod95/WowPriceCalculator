import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CraftingStat, getCraftingStatKey } from 'src/app/domain/crafting-stat';
import { Reagent } from 'src/app/domain/reagent';
import { Recipe } from 'src/app/domain/recipe';
import { Resource } from 'src/app/domain/resource';
import { RecipeService } from 'src/app/services/recipe.service';
import { ResourceService } from 'src/app/services/resource.service';

@Component({
  selector: 'app-recipe-form',
  templateUrl: './recipe-form.component.html',
  styleUrl: './recipe-form.component.scss'
})
export class RecipeFormComponent implements OnInit {

  @Output()
  public recipeCreated = new EventEmitter<void>();
  
  public requiredResources: Resource[] = [];
  public resultingResources: Resource[] = [];
  public craftingStats: CraftingStat[];
  public form: FormGroup;
  
  constructor(
    private resourceService: ResourceService,
    private recipeService: RecipeService,
    private formBuilder: FormBuilder
  ){
    this.form = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(1)]],
      difficulty: ['', [Validators.required, Validators.minLength(1)]],
      craftingStatList: this.formBuilder.array([]),
      requiredReagentList: this.formBuilder.array([]),
      resultReagentList: this.formBuilder.array([])
    });
    this.craftingStats = Object.values(CraftingStat);
    this.initCraftingStats();
  }
  
  get resultReagentList(): FormArray {
    return this.form.get('resultReagentList') as FormArray;
  }  
  
  get requiredReagentList(): FormArray {
    return this.form.get('requiredReagentList') as FormArray;
  }
  
  get craftingStatList(): FormArray {
    return this.form.get('craftingStatList') as FormArray;
  }
  
  public ngOnInit(): void {
    this.getResources();
    this.initReagentRows();
  }

  private initCraftingStats(){
    let statGroup = this.formBuilder.group({});

    for(let stat of this.craftingStats) {
      statGroup.addControl(stat, this.formBuilder.control(''))
    }
    
    this.craftingStatList.push(statGroup);
  }

  public submit() {
    let requiredReagents = [];
    let resultReagents = [];
    let craftingStats = [];
    
    let requiredReagentGroups = this.requiredReagentList.controls as FormGroup[];
    let resultReagentGroups = this.resultReagentList.controls as FormGroup[];
    let craftingStatsGroup = (this.craftingStatList.controls as FormGroup[])[0];
    
    for(let i = 0; i < requiredReagentGroups.length; i++){
      requiredReagents.push({
        resourceId: requiredReagentGroups[i].controls['requiredReagent-'+i].value,
        amount: requiredReagentGroups[i].controls['requiredReagentAmount-'+i].value
      });
    }

    for(let i = 0; i < resultReagentGroups.length; i++){
      resultReagents.push({
        resourceId: resultReagentGroups[i].controls['resultReagent-'+i].value,
        amount: resultReagentGroups[i].controls['resultReagentAmount-'+i].value
      });
    }
    
    for(let controlName in craftingStatsGroup.controls){
      if(craftingStatsGroup.controls[controlName].value) {
        craftingStats.push(getCraftingStatKey(controlName)!.toUpperCase().replace(' ', '_'));
      }
    }

    let recipe = {
      name: this.form.controls['name'].value as string,
      difficulty: this.form.controls['difficulty'].value as number,
      requiredReagents: requiredReagents,
      resultReagents: resultReagents,
      craftingStats: craftingStats 
    } as Recipe;

    this.recipeService.addRecipe(recipe).subscribe(response => {
      this.recipeCreated.emit()
    });

    this.form.reset();
    this.resetReagentRows();
    this.initReagentRows();
  }

  private resetReagentRows(){
    let length = this.requiredReagentList.length
    for(let i = 0; i < length; i++){
      this.removeRequiredReagentRow(0);
    }
    
    length = this.resultReagentList.length
    for(let i = 0; i < length; i++){
      this.removeResultReagentRow(0);
    }
  }

  private initReagentRows() {
    this.addRequiredReagentRow();
    this.addRequiredReagentRow();
    this.addResultReagentRow();
  }

  public addResultReagentRow(): void {
    let length = this.resultReagentList.length;
    let group = this.formBuilder.group({});

    group.addControl("resultReagent-" + length, this.formBuilder.control(''))
    group.addControl("resultReagentAmount-" + length, this.formBuilder.control(''))

    this.resultReagentList.push(group)
  }

  public removeResultReagentRow(index: number): void {
    this.resultReagentList.removeAt(index);
  }

  public addRequiredReagentRow() {
    let length = this.requiredReagentList.length;
    let group = this.formBuilder.group({});

    group.addControl("requiredReagent-" + length, this.formBuilder.control(''))
    group.addControl("requiredReagentAmount-" + length, this.formBuilder.control(''))
    
    this.requiredReagentList.push(group)
  }

  public removeRequiredReagentRow(index: number): void {
    this.requiredReagentList.removeAt(index);
  }

  private getResources() {
    this.resourceService.getResources().subscribe(response => {
      this.requiredResources = response;
      this.resultingResources = response;
    });
  }

  public applyRequiredReagentFilter($event: KeyboardEvent) {
  }

  public applyResultReagentFilter($event: KeyboardEvent) {
  }


}
