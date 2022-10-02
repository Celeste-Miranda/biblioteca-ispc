import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToolbarModule } from 'primeng/toolbar';
import {MenubarModule} from 'primeng/menubar';
import {ButtonModule} from 'primeng/button';
import {TableModule} from 'primeng/table';
import {BadgeModule} from 'primeng/badge';
import {FieldsetModule} from 'primeng/fieldset';
import {ChipsModule} from 'primeng/chips';
import { ChipModule } from 'primeng/chip';
import {CheckboxModule} from 'primeng/checkbox';
import {TriStateCheckboxModule} from 'primeng/tristatecheckbox';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ConfirmationService} from 'primeng/api';

@NgModule({
  declarations: [],
exports: [
    ToolbarModule,
    MenubarModule,
    ButtonModule,
    TableModule,
    BadgeModule,
    FieldsetModule,
    ChipsModule,
    ChipModule,
    CheckboxModule,
    TriStateCheckboxModule,
    ConfirmDialogModule
  ]
})
export class PrimeNgModule { }
