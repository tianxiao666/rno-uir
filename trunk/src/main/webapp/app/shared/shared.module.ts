import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { DatePipe } from '@angular/common';

import {
    UirSharedLibsModule,
    UirSharedCommonModule,
    CSRFService,
    AuthServerProvider,
    AccountService,
    UserService,
    StateStorageService,
    LoginService,
    LoginModalService,
    Principal,
    HasAnyAuthorityDirective,
    JhiLoginModalComponent,
} from './';
import { JhiTranslateComponent, NgJhipsterModule } from 'ng-jhipster';

@NgModule({
    imports: [
        UirSharedLibsModule,
        UirSharedCommonModule,
    ],
    declarations: [
       // JhiLoginModalComponent,
        HasAnyAuthorityDirective
    ],
    providers: [
        LoginService,
        LoginModalService,
        AccountService,
        StateStorageService,
        Principal,
        CSRFService,
        AuthServerProvider,
        UserService,
        DatePipe,
    ],
    // entryComponents: [JhiLoginModalComponent],
    exports: [
        UirSharedCommonModule,
        // JhiLoginModalComponent,
        HasAnyAuthorityDirective,
        DatePipe,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class UirSharedModule {}
