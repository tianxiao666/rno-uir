import { Injectable } from '@angular/core';

@Injectable()
export class StateStorageService {
    constructor() {}

    storeUrl(url: string) {
        sessionStorage.setItem('previousUrl', url);
    }

    getUrl() {
        return sessionStorage.getItem('previousUrl');
    }
}
