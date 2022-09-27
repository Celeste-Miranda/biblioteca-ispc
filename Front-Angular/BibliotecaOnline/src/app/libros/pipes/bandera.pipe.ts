import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'bandera'
})
export class BanderaPipe implements PipeTransform {

 

 
transform(valor: string ): string{

  return (valor === 'Español') 
  ? 'fi fi-es fis' 
  : 'fi fi-us fis';


}

}
