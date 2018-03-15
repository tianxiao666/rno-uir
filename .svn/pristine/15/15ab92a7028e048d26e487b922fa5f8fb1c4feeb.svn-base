import { Component, OnInit } from '@angular/core';

import { JhiEventManager } from 'ng-jhipster';
import { Principal, LoginService } from '../../shared';
import { Router } from '@angular/router';
import { GlobalState } from '../../shared';

@Component({
  selector: 'jhi-ba-page-top',
  templateUrl: './ba-page-top.component.html',
  styleUrls: ['./ba-page-top.component.scss'],
})
export class BaPageTopComponent implements OnInit {

  public isScrolled: boolean;
  public isMenuCollapsed: boolean;
  public swaggerEnabled: boolean;
  public inProduction: boolean;
  account: Account;

  constructor(
    private loginService: LoginService,
    private principal: Principal,
    private eventManager: JhiEventManager,
    private router: Router,
    private _state: GlobalState
    ) {}

  ngOnInit(): void {
    this._state.subscribe('menu.isCollapsed', (isCollapsed) => {
      this.isMenuCollapsed = isCollapsed;
    });
    this.principal.identity().then((account) => {
      this.account = account;
    });
    this.registerAuthenticationSuccess();
  }

  registerAuthenticationSuccess() {
    this.eventManager.subscribe('authenticationSuccess', (message) => {
      this.principal.identity().then((account) => {
        this.account = account;
      });
    });
  }

  public toggleMenu() {
    this.isMenuCollapsed = !this.isMenuCollapsed;
    this._state.notifyDataChanged('menu.isCollapsed', this.isMenuCollapsed);
    return false;
  }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    getImageUrl() {
        return this.isAuthenticated() ? this.principal.getImageUrl() : null;
    }

  public scrolledChanged(isScrolled) {
    this.isScrolled = isScrolled;
  }

    logout() {
        this.loginService.logout();
        this.router.navigate(['']);
    }

    login() {

    }

    collapseNavbar() {

    }
}
