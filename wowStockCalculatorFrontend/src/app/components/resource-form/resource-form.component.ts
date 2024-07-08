import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Resource } from 'src/app/domain/resource';
import { ResourceService } from 'src/app/services/resource.service';

@Component({
  selector: 'app-resource-form',
  templateUrl: './resource-form.component.html',
  styleUrl: './resource-form.component.scss'
})
export class ResourceFormComponent implements OnInit {
  
  public form: FormGroup;

  @Output()
  public resourceCreated = new EventEmitter<void>()
  
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
        this.resourceCreated.emit();
    });
  }
}
