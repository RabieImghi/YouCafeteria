import {CanActivateFn, Router} from '@angular/router';
import { inject } from "@angular/core";

export const isAuthenticatedGuardGuard: CanActivateFn = (route, state) => {

    const router = inject(Router);
    const token = localStorage.getItem('auth_access_token');
    if (token) {
        return true;
    }
    router.navigate(['/auth/login']);
    return false;
};