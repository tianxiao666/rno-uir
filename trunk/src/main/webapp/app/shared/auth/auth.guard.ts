import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { StateStorageService } from './state-storage.service';
import { LocalStorageService, SessionStorageService } from 'ng2-webstorage';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router,
    private stateStorageService: StateStorageService,
    private $localStorage: LocalStorageService,
    private $sessionStorage: SessionStorageService
    ) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    // authenticationToken
    if (this.$localStorage.retrieve('authenticationToken') || this.$sessionStorage.retrieve('authenticationToken')) {
      // logged in so return true
      return true;
    }
    // not logged in so redirect to login page with the return url
    this.stateStorageService.storeUrl(state.url);
    this.router.navigate(['/login']).catch((error) => console.error(error));
    return false;
  }
}
