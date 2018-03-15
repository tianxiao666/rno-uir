import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { errorRoute } from './';
import { appRoutes } from '../app.route';

import { UirSharedModule } from '../shared';
import {userDialogRoute} from '../admin/user-management/user-management.route';

const LAYOUT_ROUTES = [
   // navbarRoute,
    ...userDialogRoute,
    ...errorRoute,
    ...appRoutes,
];

@NgModule({
    imports: [
        UirSharedModule,
        RouterModule.forRoot(LAYOUT_ROUTES, { useHash: true, enableTracing: true }),
    ],
    exports: [
        RouterModule
    ]
})
export class LayoutRoutingModule {}
