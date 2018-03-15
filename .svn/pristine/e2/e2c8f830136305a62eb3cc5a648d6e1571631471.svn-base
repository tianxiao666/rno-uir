import { Routes } from '@angular/router';

import {
   dashboardRoute
} from './dashboard/dashboard.routing';
import { AuthGuard } from '../../shared/auth/auth.guard';

const MAIN_ROUTES = [
    dashboardRoute
];

export const mainState: Routes = [{
    path: '',
    children: MAIN_ROUTES,
    canActivate: [AuthGuard]
}];
