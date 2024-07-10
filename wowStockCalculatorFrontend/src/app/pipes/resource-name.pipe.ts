import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'resourceName',
  standalone: true
})
export class ResourceNamePipe implements PipeTransform {

  transform(resourceName: string | undefined): string {
    return resourceName == null ? '' : `[${resourceName}]`;
  }

}
