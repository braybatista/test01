import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateFormat'
})
export class DateFormatPipe implements PipeTransform {

  transform(date: string | Date): string {
    return this.getActualDate(date);
  }

  getActualDate(date: string | Date){
    const now = new Date(date);
    const offsetMs = now.getTimezoneOffset() * 60 * 1000;
    const dateLocal = new Date(now.getTime() - offsetMs);
    return dateLocal.toISOString().split('T')[0].replace(/-/g, "/")
  }

}
