import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'disponibilidadText'
})
export class DisponibilidadTextPipe implements PipeTransform {

  transform(disponibilidad : number): string {
    

    return  (disponibilidad<1)
     ? 'No Disponible'
     : 'Disponible'
   

}

}