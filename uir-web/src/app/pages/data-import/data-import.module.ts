import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgaModule } from '../../theme/nga.module';

import { DataImportComponent } from './data-import.component';
import { routing } from './data-import.routing';

import { AppTranslationModule } from '../../app.translation.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { ConditionComponent } from './condition/condition.component';

import { DataImportService } from './data-import.service';
import { GetJsonHttpService } from '../common/getJsonHttp.service';

import { ExcelService } from '../common/export.service';
import { TableComponent } from './data-table/table.component';
import { NG2DataTableModule } from 'angular2-datatable-pagination';

import { MyDatePickerModule } from 'mydatepicker';

import { AreaDataCnComponent } from './area-data-i/area-data-i.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    NgaModule,
    NgbModule,
    NG2DataTableModule,
    AppTranslationModule,
    MyDatePickerModule,
    routing,
  ],
  declarations: [
    DataImportComponent,
    ConditionComponent,
    TableComponent,
    AreaDataCnComponent,
  ],
  providers: [
    DataImportService,
    GetJsonHttpService,
    ExcelService,
  ],
})
export class DataImportModule {}
