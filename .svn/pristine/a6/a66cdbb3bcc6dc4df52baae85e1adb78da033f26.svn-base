import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRouteSnapshot, NavigationEnd, RoutesRecognized, Routes, CanActivate} from '@angular/router';

import { JhiLanguageHelper, StateStorageService, Principal } from '../../shared';
import { BaMenuService } from '../ba-menu/baMenu.service';
import { PAGES_MENU } from './menu';
import { GlobalState } from '../../shared';

@Component({
    selector: 'jhi-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.scss']
})

export class JhiMainComponent implements OnInit {

    isMenuCollapsed: boolean;

    constructor(
        private jhiLanguageHelper: JhiLanguageHelper,
        private router: Router,
        private principal: Principal,
        private $storageService: StateStorageService,
        private _menuService: BaMenuService,
        private _state: GlobalState
    ) {
        this._state.subscribe('menu.isCollapsed', (isCollapsed) => {
            this.isMenuCollapsed = isCollapsed;
        });
    }

    private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {
        let title: string = (routeSnapshot.data && routeSnapshot.data['pageTitle']) ? routeSnapshot.data['pageTitle'] : 'uirApp';
        if (routeSnapshot.firstChild) {
            title = this.getPageTitle(routeSnapshot.firstChild) || title;
        }
        return title;
    }

    ngOnInit() {
        this._menuService.updateMenuByRoutes(<Routes>PAGES_MENU);
        this.router.events.subscribe((event) => {
            if (event instanceof NavigationEnd) {
                this.jhiLanguageHelper.updateTitle(this.getPageTitle(this.router.routerState.snapshot.root));
            }
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }
}
