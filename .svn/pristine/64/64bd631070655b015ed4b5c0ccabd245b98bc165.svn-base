import { Route } from '@angular/router';

import { UserRouteAccessService } from '../../../shared';
import { TestComponent } from './test.component';

export const dashboardRoute: Route = {
    path: 'test',
    component: TestComponent,
    data: {
        authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService]
};
