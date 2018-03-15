import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BackendDataImportModule } from './data-import/data-import.module';
import {BackendInterfereModule} from "./interfere/interfere.module";
import {BackendAreaModule} from "./area/area.module";
import {BackendTaskModule} from "./task/task.module";
import { BackendDataSearchModule } from './data-search/data-search.module';
import { BackendDataStateModule } from './data-state/data-state.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        BackendTaskModule,
        BackendAreaModule,
        BackendInterfereModule,
        BackendDataImportModule,
        BackendDataSearchModule,
        BackendDataStateModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BackendEntityModule {}
