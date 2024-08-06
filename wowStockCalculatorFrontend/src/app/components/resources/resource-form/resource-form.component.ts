import { HttpResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { getQualityKey, mapQuality, Quality } from 'src/app/domain/quality';
import { Resource } from 'src/app/domain/resource';
import { ResourceService } from 'src/app/services/resource.service';

@Component({
  selector: 'app-resource-form',
  templateUrl: './resource-form.component.html',
  styleUrl: './resource-form.component.scss'
})
export class ResourceFormComponent implements OnInit {
  
  @Output()
  public resourceCreated = new EventEmitter<void>();
  
  public form: FormGroup;
  public submitted: boolean = false;
  public resourceExists: boolean = false;
  public qualities: Quality[];
  
  constructor(
    private resourceService: ResourceService,
    private formBuilder: FormBuilder
  ){
    this.form = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(1)]],
      quality: ''
    });
    this.qualities = Object.values(Quality);
  }

  ngOnInit(): void {
  }

  get nameField() {
    return this.form.controls['name'];
  }

  public submit() {
    this.submitted = true;

    if(this.form.invalid) {
      return;
    }

    let resource: Resource = {
      name: this.form.controls['name'].value,
      quality: (getQualityKey(this.form.controls['quality'].value))?.toUpperCase().replace(' ', '_')
    };

    this.resourceService.addResource(resource)
      .subscribe((response: HttpResponse<any>) => {
        if(response.status == 200) {
          this.resourceExists = true;
        }
        this.resourceCreated.emit();
    });

    this.form.reset();
    this.resourceExists = false;
    this.submitted = false;
  }
}
