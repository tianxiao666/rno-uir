import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ng2-webstorage';

import { UirSharedModule, UserRouteAccessService } from './shared';
import { UirHomeModule } from './home/home.module';
import { UirAdminModule } from './admin/admin.module';
import { UirAccountModule } from './account/account.module';
import { UirEntityModule } from './entities/entity.module';

import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    JhiMainComponent,
    LayoutRoutingModule,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ActiveMenuDirective,
    ErrorComponent,
    BaMenuService,
    BaMenuItemComponent,
    BaSidebarComponent,
    BaScrollPositionDirective,
    BaMenuComponent,
    BaPageBottomComponent,
    BaPageTopComponent
} from './layouts';
import { UirAppComponent } from './app.component';
import { GlobalState } from './shared';
import { LoginModule } from './layouts/login/login.module';
import { MainModule } from './layouts/main/main.module';
import { DashboardComponent } from './layouts/main/dashboard/dashboard.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { JhiLoginComponent } from './layouts/login/login.component';
import { LoginService } from './layouts/login/login.service';
import { AuthGuard } from './shared/auth/auth.guard';
import { appRoutes } from './app.route';
import { RouterModule } from '@angular/router';
import { TestComponent } from './layouts/main/test/test.component';

@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        FormsModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        UirSharedModule,
        LoginModule,
        LayoutRoutingModule,
        UirAccountModule,
        UirHomeModule,
        UirAdminModule,
        UirEntityModule,
        /*MainModule*/
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent,
        UirAppComponent,
        BaMenuItemComponent,
        BaSidebarComponent,
        BaScrollPositionDirective,
        BaMenuComponent,
        BaPageBottomComponent,
        BaPageTopComponent,
        DashboardComponent,
        TestComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService,
        BaMenuService,
        GlobalState,
        AuthGuard
    ],
    bootstrap: [ UirAppComponent ],
})
export class UirAppModule {}
