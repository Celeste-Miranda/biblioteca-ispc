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
import { ConfirmationService, MessageService } from 'primeng/api';
import {Panel, PanelModule} from 'primeng/panel';
import {Toast, ToastModule} from 'primeng/toast';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';

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
    ConfirmDialogModule,
    PanelModule,
    ToastModule,
    MessagesModule,
    MessageModule
  ],
  providers: [
    MessageService

  ]
})
export class PrimeNgModule { }
