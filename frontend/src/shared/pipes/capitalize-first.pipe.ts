import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'capitalizeFirst',
  standalone: true
})
export class CapitalizeFirstPipe implements PipeTransform {

  transform(value: string): string {
    if (!value) return value;

    // Lowercase the whole string and uppercase the first character
    return value.charAt(0).toUpperCase() + value.slice(1).toLowerCase();  
  }

}
