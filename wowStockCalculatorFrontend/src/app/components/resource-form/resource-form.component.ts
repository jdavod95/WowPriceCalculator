import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Resource } from 'src/app/domain/resource';
import { ResourceService } from 'src/app/services/resource.service';
import { ResourcesComponent } from '../resources/resources.component';

@Component({
  selector: 'app-resource-form',
  templateUrl: './resource-form.component.html',
  styleUrl: './resource-form.component.css'
})
export class ResourceFormComponent implements OnInit {
  
  public form: FormGroup;
  @ViewChild(ResourcesComponent) resourcesComponent!: ResourcesComponent;

  constructor(
    private resourceService: ResourceService,
    private formBuilder: FormBuilder
  ){
    this.form = this.formBuilder.group({
      name: ''
    });
  }

  ngOnInit(): void {
  }

  submit() {
    let resource: Resource = {
      name: this.form.controls['name'].value,
      onStock: 0
    };
    this.resourceService.addResource(resource)
      .subscribe((response: Resource) => {
        this.resourcesComponent.ngOnInit();
    });
  }
}
