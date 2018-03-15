import { Routes } from '@angular/router';

import { NavbarComponent } from './layouts';
import { JhiMainComponent } from './layouts/main/main.component';
import { DashboardComponent } from './layouts/main/dashboard/dashboard.component';
import { UserRouteAccessService } from './shared/auth/user-route-access-service';
import { JhiLoginComponent } from './layouts/login/login.component';
import { AuthGuard } from './shared/auth/auth.guard';
import {
    ActivateComponent,
    RegisterComponent,
    PasswordComponent,
    PasswordResetInitComponent,
    PasswordResetFinishComponent,
    SettingsComponent
} from './account';

import {
    AuditsComponent,
    UserMgmtComponent,
    UserDialogComponent,
    UserDeleteDialogComponent,
    UserMgmtDetailComponent,
    LogsComponent,
    JhiConfigurationComponent,
    JhiHealthCheckComponent,
    JhiDocsComponent,
    JhiMetricsMonitoringComponent,
    UserResolvePagingParams
    } from './admin';
import { TestComponent } from './layouts/main/test/test.component';

/*export const navbarRoute: Route = {
    path: '',
    component: NavbarComponent,
    outlet: 'navbar'
};*/

export const appRoutes: Routes = [

    /*{
        path: 'login',
        component: JhiLoginComponent,
    },*/
    {   path: 'main',
        component: JhiMainComponent,
        canActivate: [AuthGuard],
        children: [
            {path: 'dashboard', component: DashboardComponent},
            {path: 'test', component: TestComponent},
            {path: '', redirectTo: 'dashboard', pathMatch: 'full'},

            {path: 'settings', component: SettingsComponent},
            {path: 'activate', component: ActivateComponent},
            {path: 'password', component: PasswordComponent},
            {path: 'reset/finish', component: PasswordResetFinishComponent},
            {path: 'reset/request', component: PasswordResetInitComponent},
            {path: 'register', component: RegisterComponent},

            {path: 'audits', component: AuditsComponent},
            {path: 'jhi-configuration', component: JhiConfigurationComponent},
            {path: 'docs', component: JhiDocsComponent},
            {path: 'jhi-health', component: JhiHealthCheckComponent},
            {path: 'logs', component: LogsComponent},
            {path: 'user-management', resolve: {'pagingParams': UserResolvePagingParams},
                component: UserMgmtComponent},
            {path: 'user-management/:login', component: UserMgmtDetailComponent},
            {path: 'jhi-metrics', component: JhiMetricsMonitoringComponent},
            {path: 'user-management-new', component: UserDialogComponent, outlet: 'popup'},
            {path: 'user-management/:login/edit', component: UserDialogComponent, outlet: 'popup'},
            {path: 'user-management/:login/delete', component: UserDeleteDialogComponent, outlet: 'popup'}
        ]
    },
    /*{path: 'user-management-new', component: UserDialogComponent, outlet: 'popup'},
    {path: 'user-management/:login/edit', component: UserDialogComponent, outlet: 'popup'},
    {path: 'user-management/:login/delete', component: UserDeleteDialogComponent, outlet: 'popup'},*/
    { path: '',   redirectTo: 'main', pathMatch: 'full' },
    /*{ path: '**', component: JhiLoginComponent },*/
];
