import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'replaceChar',
  standalone: true
})
export class ReplaceCharPipe implements PipeTransform {

  transform(value: string, findChar: string, replaceChar: string): string {
    if (!value || !findChar) return value;

    // Replace the 'findChar' with 'replaceChar'
    return value.replace(new RegExp(findChar, 'g'), replaceChar);
  }

}
