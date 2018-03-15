import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UirSharedModule } from '../../shared';

import {
    DashboardComponent
} from './dashboard/dashboard.component';
import { mainState } from "./main.routing";

@NgModule({
    imports: [
        UirSharedModule,
        RouterModule.forRoot(mainState, { useHash: true })
    ],
    declarations: [
        DashboardComponent,
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MainModule {}
