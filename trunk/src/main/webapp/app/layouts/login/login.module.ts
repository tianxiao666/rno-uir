import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { JhiLoginComponent } from './login.component';
import { LoginService } from './login.service';
import { RouterModule } from '@angular/router';
import { loginRoutes } from './login.routing';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
      RouterModule.forRoot(loginRoutes, { useHash: true })
  ],
  declarations: [
      JhiLoginComponent,
  ],
  providers: [
      LoginService,
  ],
})
export class LoginModule {}
