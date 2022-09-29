import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'disponibilidad'
})
export class DisponibilidadPipe implements PipeTransform {

  transform(disponibilidad : number): string {
    

 return  (disponibilidad<1)
  ? 'danger'
  : 'success'



}




  }


